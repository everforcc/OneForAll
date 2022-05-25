package cn.cc.dawn.local.craw.business.bilibili.flow;

import cn.cc.dawn.local.craw.business.bilibili.constant.BilConstant;
import cn.cc.dawn.local.craw.business.bilibili.entity.BilibiliAlbumDto;
import cn.cc.dawn.local.craw.business.bilibili.entity.BilibiliAlbumPicDto;
import cn.cc.dawn.local.craw.business.bilibili.utils.*;
import cn.cc.dawn.local.craw.business.data.dto.HttpParamDto;
import cn.cc.dawn.utils.constant.DateFormatConstant;
import cn.cc.dawn.utils.commons.lang.RSimpleDateFormat;
import cn.cc.dawn.utils.enums.HttpTypeEnum;
import cn.cc.dawn.utils.exception.AppCode;
import cn.cc.dawn.utils.file.IFile;
import cn.cc.dawn.utils.file.impl.FileApacheUtils;
import cn.cc.dawn.utils.file.FilePath;
import cn.cc.dawn.utils.http.IHttp;
import cn.cc.dawn.utils.commons.web.HttpParamUtils;
import cn.cc.dawn.utils.http.impl.IHttpApacheImpl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import static com.alibaba.fastjson.serializer.SerializerFeature.PrettyFormat;

/**
 * Yukino
 * 2020/3/3
 */
@Slf4j
@Component
public class Bilibili_Album {

    IHttp IHttp = new IHttpApacheImpl();
    IFile iFile = new FileApacheUtils();

    /**
     * TODO
     */
    private static ExecutorService multiThread;

    @Autowired
    private void setExecutorService(ExecutorService multiThread){
        Bilibili_Album.multiThread = multiThread;
    }

    // 指定up的id
    //private String poster_uid = "12";

    public Bilibili_Album() {

    }

    //参数 UP 的唯一id
//    public Bilibili_Album(String poster_uid) {
//        this.poster_uid = poster_uid;
//    }

    List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
    public Map<String,List<BilibiliAlbumDto>> map_album = new HashMap<String,List<BilibiliAlbumDto>>();
    public Map<String,List<BilibiliAlbumPicDto>> map_album_pic = new HashMap<String,List<BilibiliAlbumPicDto>>();

    public String requestFlow(String poster_uid){

        try {
            checkUser(poster_uid);
        } catch (Exception e) {
            e.printStackTrace();
            throw AppCode.A01200.toUserException(e);
        }
        //1. 先根据链接获取 json
        String countJson = null;
        try {
            countJson = allAlbumJson(poster_uid);
        } catch (Exception e) {
            e.printStackTrace();
            throw AppCode.A01200.toUserException(e);
        }
        //2. 从json 得到相册的个数
        String count = albumCount(countJson);
        //3. 获取所有详情的json 的url
        String allDetailUrl = albumDetailUrl(poster_uid, count);
        //4. 获取所有的json
        String allAlbumDetailJson = null;
        try {
            allAlbumDetailJson = albumDetailJson(allDetailUrl);
        } catch (Exception e) {
            e.printStackTrace();
            throw AppCode.A01200.toUserException(e);
        }
        //JSON.toJSONString(allAlbumDetailJson, PrettyFormat);
        //5. 格式化json并保存
        // 传入总数，每次处理后显示剩余数量
        saveJson(poster_uid, allAlbumDetailJson,"json");
        //6. 保存文件
        try {
            allImgUrl(poster_uid,allAlbumDetailJson, true);
        } catch (Exception e) {
            e.printStackTrace();
            throw AppCode.A01200.toUserException(e);
        }

        return "success";
    }

    public boolean checkUser(String poster_uid) throws Exception {
        String user_msg =  String.format(BilConstant.user_msg,poster_uid);
        String json = Request_Method.js_commom(user_msg, HttpTypeEnum.GET.type);

        JSONObject jsonObject = JSONObject.parseObject(json);
        AppCode.A01200.assertHasTrue(jsonObject.containsKey("code"));
        AppCode.A01200.assertHasTrue(jsonObject.containsKey("message"));
        String code = jsonObject.getString("code");
        if ("0".equals(code)){
         return true;
        }
        throw AppCode.A01200.toUserException(jsonObject.getString("message"));
    }

    /**
     * 返回相册总数 json
     * @return
     * @throws Exception
     */
    public String allAlbumJson(String poster_uid) throws Exception {
        String forAlbumCount =  String.format(BilConstant.album_allcount,poster_uid);
        return Request_Method.js_commom(forAlbumCount, HttpTypeEnum.POST.type);
    }


    /**
     * 获取相簿总个数
     * @param json
     * @return
     */
    public String albumCount(String json){
        log.info("albumCount: {}",json);
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONObject jsonObjectDate = jsonObject.getJSONObject("data");
        String count = jsonObjectDate.getString("all_count");
        log.info("all_count: {}",count);
        return count;
    }

    /**
     *  获取所有相册的数据的地址
     * @param poster_uid
     * @param page_size
     * @return
     */
    public String albumDetailUrl(String poster_uid, String page_size) {
        String album_alldetail = String.format(BilConstant.album_alldetail, poster_uid, page_size);
        log.info(album_alldetail);
        return album_alldetail;
    }

    public String albumDetailJson(String url) throws Exception {
        HttpParamDto httpParamDto = new HttpParamDto();
        httpParamDto.setUrl(url);
        httpParamDto.setHttpTypeEnum(HttpTypeEnum.GET);
        //String allAlbumDetailJson = httpMethod.getMsg(httpParamDto);
        String allAlbumDetailJson = Request_Method.js_commom(url,HttpTypeEnum.POST.type);
        log.info(allAlbumDetailJson);
        return allAlbumDetailJson;
    }

    public void saveMsg(String poster_uid, String msg, String path, String fileName){
        //String fileName = poster_uid + "-" + DateUtils.nowTime(DateFormatConstant.yyyy_MM_dd_HH_mm_ss) + ".txt";
        String file = FilePath.build(BilConstant.BILIBILI_FILE_PATH_BUSI)
                .ofPath(poster_uid,path)
                .ofFileName(fileName)
                .path();

        try {
            iFile.write(file,msg);
        } catch (Exception e) {
            e.printStackTrace();
            throw AppCode.A00150.toUserException(e);
        }
    }

    public void saveJson(String poster_uid, String allAlbumDetailJson, String path){
        String fileName = poster_uid + "-" + RSimpleDateFormat.nowTime(DateFormatConstant.yyyy_MM_dd_HH_mm_ss) + ".txt";
        JSONObject jsonObject = JSON.parseObject(allAlbumDetailJson);
        allAlbumDetailJson = JSON.toJSONString(jsonObject, PrettyFormat);
        saveMsg(poster_uid,allAlbumDetailJson,path,fileName);
    }

    /**
     * 获取所有照片
     * @param json
     * @return
     * @throws Exception
     */
    public  Map<String,Object> allImgUrl(String poster_uid,String json,Boolean down)throws Exception{
        Map<String,Object> map = new HashMap<String,Object>();

        //  留着入库用
        BilibiliAlbumDto bilibiliAlbumDto;
        BilibiliAlbumPicDto bilibiliAlbumPicDto;

        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONObject jsonObjectDate = jsonObject.getJSONObject("data");
        JSONArray jsonArray = jsonObjectDate.getJSONArray("items");

        List<BilibiliAlbumDto> bilibili_albumDtos = jsonArray.toJavaList(BilibiliAlbumDto.class);

        int albumSize = bilibili_albumDtos.size();
        for(int i = 0 ;i<albumSize;i++){
            bilibiliAlbumDto = bilibili_albumDtos.get(i);

            List<BilibiliAlbumPicDto> bilibiliAlbumPicDtoList = bilibiliAlbumDto.getPictures();
            int picSize = bilibiliAlbumPicDtoList.size();
            for(int j = 0 ; j < picSize ; j++ ){
                bilibiliAlbumPicDto = bilibiliAlbumPicDtoList.get(j);
                String img_src = bilibiliAlbumPicDto.getImg_src();
                String doc_id = bilibiliAlbumPicDto.getDoc_id();
                log.info("图片下载地址: {}",img_src);

                if(down) {
                    try {
//                        multiThread.execute(new Runnable() {
//                            @Override
//                            public void run() {
//                                try {
//                                    Method_down.down(img_src, "相册",poster_uid,doc_id);
//                                } catch (Exception e) {
//                                    String fileName = FilePath.build(BilConstant.bilibiliFilePath)
//                                            .ofPath("error")
//                                            .ofFileName("errorUrl.txt")
//                                            .path();
//                                    try {
//                                        iFileHandle.write(fileName,doc_id + "\r\n");
//                                    } catch (IOException ex) {
//                                        ex.printStackTrace();
//                                    }
//                                    e.printStackTrace();
//                                }
//                            }
//                        });
                        //调用下载
                        String fileName = HttpParamUtils.fileNameFromUrl(img_src);

                        String path = FilePath.build(BilConstant.BILIBILI_FILE_PATH_BUSI)
                                .ofPath(poster_uid,"相册",doc_id)
                                .path();
                        Method_down.down(img_src,fileName,path);

                    }catch (Exception e){
                        saveMsg(poster_uid,doc_id + "\r\n","error","errorUrl.txt");
                        e.printStackTrace();
                        e.printStackTrace();
                    }
                }
            }
        }
//        map.put("map_album",list_album);
//        map.put("map_album_pic",list_album_pic);
        map.put("key","共多少数据待处理,已放入队列");
        return map;
    }


    public static void main(String[] args) {
        try {
            /**
             * 每个地方的异常逻辑都要单独处理
             */
            String poster_uid = "28380168";
            Bilibili_Album bilibili_album = new Bilibili_Album();
//            String json = bilibili_album.allAlbumJson("28380168");
//            bilibili_album.albumCount(json);
            //log.info(bilibili_album.albumDetailUrl("abc", "def"));
            bilibili_album.requestFlow("28380168");
            //String allDetailUrl = bilibili_album.albumDetailUrl(poster_uid,"2");
            //4. 获取所有的json
            //String allAlbumDetailJson = bilibili_album.albumDetailJson(allDetailUrl);
            //bilibili_album.allImgUrl(allAlbumDetailJson,true);

//            Map<String,Object> map = new HashMap<>();
//            map.put("a","b");
//            map.put("c","d");
//            com.alibaba.fastjson.JSONObject jsonObject =  new com.alibaba.fastjson.JSONObject(map);
//            System.out.println(JSON.toJSONString(jsonObject, PrettyFormat));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
