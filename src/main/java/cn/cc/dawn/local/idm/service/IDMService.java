package cn.cc.dawn.local.idm.service;

import cn.cc.dawn.utils.commons.lang.RObjectsUtils;
import cn.cc.dawn.utils.exception.AppCode;
import cn.cc.dawn.utils.commons.web.HttpParamUtils;
import cn.cc.dawn.utils.commons.regex.RegexConstant;
import cn.cc.dawn.utils.commons.regex.RegexUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@Validated
@Service
public class IDMService {

    //HttpMethod httpMethod;

    //private final static int threadSize = 10;
    private final static String rangeFormat = "bytes=%s-%s";
    private final static String path = "E:\\filesystem\\project\\OneForAll\\test\\";

    @Autowired
    private ExecutorService multiThread;

    /**
     * 实现这玩儿
     * 主要是逻辑，先处理免登录的
     */
    public void flow(@NotNull(message = "url不能为空") String url,int threadSize){
        /**
         * 1. 获取总大小
         * 2. 根据要求分段
         * 3. 请求各个分段数据
         * 4. 合并
         */
        long size = getSize(url);

        List<String> rangeList = getRangeList(size,threadSize);

        int index= rangeList.size();
        for(int i = 0;i<index;i++) {
            multiThread.execute(new ReqRange(rangeList.get(i),url,i,size/threadSize));
        }

        //multiThread.shutdown();
        while(true) {
            if (multiThread.isTerminated()) {
                log.info("执行完毕-a");
                break;
            } else {
                //System.out.println("正在执行 >>> ");
                int threadCount = ((ThreadPoolExecutor) multiThread).getActiveCount();
                //log.info("剩余线程数: {}",threadCount);
                if(0 == threadCount) {
                    log.info("执行完毕-b");
                    break;
                }
            }
        }

        //  合并数据
        concatFile(url,threadSize);
    }

    private static long getSize(String urlPath){
        URL url = null;
        try {
            url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept","application/json");
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);

            Map<String, List<String>> stringListMap = conn.getHeaderFields();
            for (Map.Entry entry:stringListMap.entrySet()){
                if("Content-Length".equalsIgnoreCase((String) entry.getKey())){
                    String string = entry.getValue().toString();
                    log.info("string: " + string);
                    String range = RegexUtils.matcheStr(RegexConstant.mathNumber,string,1);
                    AppCode.A01200.assertHasTrue(RObjectsUtils.nonNull(range));
                    long size = Long.valueOf(range);
                    return size;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        throw AppCode.A01200.toUserException();
    }

    /**
     * 组装请求头
     * @param size
     * @return
     */
    public static List<String> getRangeList(long size,int threadSize){
        List<String> rangeList = new ArrayList<>();
        long range = size/threadSize;

        for(int i=0;i<threadSize-1;i++){
            rangeList.add(String.format(rangeFormat,(i * range),(i + 1) * range - 1));
        }
        rangeList.add(String.format(rangeFormat,((threadSize-1) * range),size));
//        rangeList.add(String.format(rangeFormat,0 * range,range));
//        rangeList.add(String.format(rangeFormat,(1 * range + 1),2 * range));
//        rangeList.add(String.format(rangeFormat,(2 * range + 1),size));

        return rangeList;
    }

    /**
     * 合并文件
     * @param urlPath
     */
    public void concatFile(String urlPath,int threadSize){
        String realFileName = HttpParamUtils.fileNameFromUrl(urlPath);
        //String suffix = HttpParamUtils.fileSuffixFromUrl(urlPath,true);
        File target = new File(path + "targetfile-" + realFileName);
        for(int i = 0;i<threadSize;i++) {
            String fileName = i + "-" + realFileName;
            File temp = new File(path + fileName);

            log.info("复制{}文件到target中",temp);
            try {
                //FileUtils.copyFile(temp,target,true);
                byte[] bytes = FileUtils.readFileToByteArray(temp);
                FileUtils.writeByteArrayToFile(target,bytes,true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public class ReqRange implements Runnable{

        private String range;
        private String urlPath;
        private int index;
        private long size;

        public ReqRange(String range, String urlPath, int index,long size) {
            this.range = range;
            this.urlPath = urlPath;
            this.index = index;
            this.size = size;
        }

        @Override
        public void run() {
            URL url = null;
            try {
                url = new URL(urlPath);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Accept","application/json");
                conn.setRequestProperty("Range",range);
                conn.setRequestMethod("GET");
                conn.setDoOutput(true);

                InputStream inputStream = conn.getInputStream();

                String fileName = index + "-" + HttpParamUtils.fileNameFromUrl(urlPath);

                //FileUtils.copyInputStreamToFile(inputStream,new File(path + fileName));
                streamToFile(inputStream,path,fileName,new BigDecimal(size));

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        String url = "https://i0.hdslb.com/bfs/album/32fffb9d5fcedc536bdc8e16e213444bbc0847dc.jpg";
        log.info("文件大小: {}" , getSize(url));
        long size = 101;
        for (String s : getRangeList(size,10)) {
            log.info(s);
        }

        //String string = "[3025311]";
//        String string = "3025311";
//        String range = RegexUtils.matcheStr("\\D*(\\d*)\\D*",string,1);
//        System.out.println("range: " + range);
    }

    public static void streamToFile(InputStream inputStream, String filePath, String fileName, BigDecimal fileLength)throws Exception{
        // 总时间
        Date begindate = new Date();

        File file = new File(filePath + File.separator+ fileName);
        //  加参数是否允许重复写入
        if(file.exists()){
            // 如果文件存在那么不在写入?
            log.info(filePath+fileName+"文件存在");
            return;
        }

        FileOutputStream fo = new FileOutputStream(file);
        // rate两位小数
        DecimalFormat df = new DecimalFormat("00");
        // 间隔
        BigDecimal rate = new BigDecimal(0.01);
        // 比例
        BigDecimal tempRate = new BigDecimal(0);

        /**
         * 以流的方式进行下载
         */
        byte[] buf = new byte[1024];
        int length = 0;
        // 文件长度 累计计算当前比例
        BigDecimal tempLength = new BigDecimal(length);

        //据说包装了有缓存，不切换上下文，比较快
        //BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        while ((length = inputStream.read(buf, 0, buf.length)) != -1) {
            if(fileLength!=null&&(fileLength.compareTo(new BigDecimal(0))==1)) {
                tempLength = tempLength.add(new BigDecimal(length));
                // 每 1% 跳出一行数据     num * 100 / 总长 向下取整
                tempRate = new BigDecimal(df.format(tempLength.multiply(new BigDecimal(100)).divide(fileLength, 1, BigDecimal.ROUND_DOWN)));
                // 如果当前比例大于之前存储的比例，那么就输出一行,表示下载比例的数据
                if (tempRate.compareTo(rate) == 1) {
                    System.out.println(fileName + ":下载进度 >>>>>> " + tempRate + "%");
                    rate = tempRate;
                }
            }
            // 写入文件
            fo.write(buf, 0, length);
        }

        //bufferedInputStream.close();
        inputStream.close();
        fo.close();

        /**
         * 计算下载所用时间
         */
        Date enddate = new Date();
        double time = enddate.getTime() - begindate.getTime();

        log.info(fileName + "下载耗时:" + time/1000 + " s");
    }

}
