package cn.cc.dawn.local.craw.bilibili.flow;

import cn.cc.dawn.local.craw.bilibili.entity.Bilibili_album;
import cn.cc.dawn.local.craw.bilibili.entity.Bilibili_album_pic;
import cn.cc.dawn.local.craw.bilibili.utils.*;
import cn.cc.dawn.local.craw.web.data.dto.HttpParamDto;
import cn.cc.dawn.utils.enums.HttpTypeEnum;
import cn.cc.dawn.utils.file.IFileHandle;
import cn.cc.dawn.utils.file.impl.FileApacheUtils;
import cn.cc.dawn.utils.file.path.FilePath;
import cn.cc.dawn.utils.http.HttpMethod;
import cn.cc.dawn.utils.http.impl.HttpApacheImpl;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
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

    HttpMethod httpMethod = new HttpApacheImpl();
    IFileHandle iFileHandle = new FileApacheUtils();

    /**
     * TODO
     */
    private static ExecutorService multiThread;

    @Autowired
    private void setExecutorService(ExecutorService multiThread){
        Bilibili_Album.multiThread = multiThread;
    }

    // 指定up的id
    private String poster_uid = "12";

    public Bilibili_Album() {

    }

    //参数 UP 的唯一id
    public Bilibili_Album(String poster_uid) {
        this.poster_uid = poster_uid;
    }

    //获取相册总数的url
    private String forCountUrl = "https://api.vc.bilibili.com/link_draw/v1/doc/upload_count?uid=";


    List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
    public Map<String,List<Bilibili_album>> map_album = new HashMap<String,List<Bilibili_album>>();
    public Map<String,List<Bilibili_album_pic>> map_album_pic = new HashMap<String,List<Bilibili_album_pic>>();

    public void requestFlow(String poster_uid)throws Exception{

        //1. 先根据链接获取 json
        String countJson = allAlbumJson(poster_uid);
        //2. 从json 得到相册的个数
        String count = albumCount(countJson);
        //3. 获取所有详情的json 的url
        String allDetailUrl = albumDetailUrl(poster_uid,count);
        //4. 获取所有的json
        String allAlbumDetailJson = albumDetailJson(allDetailUrl);
        //JSON.toJSONString(allAlbumDetailJson, PrettyFormat);
        String fileName = FilePath.build(BilConstant.bilibiliFilePath)
                .ofPath("json")
                .ofFileName("json.txt")
                .path();
        try {

            //com.alibaba.fastjson.JSONObject jsonObject =  new com.alibaba.fastjson.JSONObject(allAlbumDetailJson);
            com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(allAlbumDetailJson);


            iFileHandle.write(fileName,JSON.toJSONString(jsonObject, PrettyFormat));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        allImgUrl(allAlbumDetailJson,true);
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
        JSONObject jsonObject = JSONObject.fromObject(json);
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
        //return "https://api.vc.bilibili.com/link_draw/v1/doc/doc_list?uid="+poster_uid+"&page_num=0&biz=all&page_size=" +page_size ;
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

    /**
     * 获取所有照片
     * @param json
     * @return
     * @throws Exception
     */
    public  Map<String,Object> allImgUrl(String json,Boolean down)throws Exception{
        Map<String,Object> map = new HashMap<String,Object>();

        /**
         * 留着入库用
         */
        Bilibili_album bilibili_album;
        Bilibili_album_pic bilibili_album_pic;


        JSONObject jsonObject = JSONObject.fromObject(json);
        JSONObject jsonObjectDate = jsonObject.getJSONObject("data");
        JSONArray jsonArray = jsonObjectDate.getJSONArray("items");

        List<Bilibili_album> list_album = new ArrayList<Bilibili_album>();
        List<Bilibili_album_pic> list_album_pic = new ArrayList<Bilibili_album_pic>();

        for(int i = 0 ;i<jsonArray.size();i++){
            bilibili_album = new Bilibili_album();

            JSONObject jsonObj = (JSONObject)jsonArray.get(i);
            bilibili_album.setAlbum(jsonObj.getString("doc_id"));
            bilibili_album.setPoster_uid(jsonObj.getString("poster_uid"));
            bilibili_album.setTitle(jsonObj.getString("title"));
            bilibili_album.setDescription(jsonObj.getString("description"));
            bilibili_album.setCount(jsonObj.getString("count"));
            bilibili_album.setCtime(jsonObj.getString("ctime"));
            bilibili_album.setView(jsonObj.getString("view"));
            bilibili_album.setLike(jsonObj.getString("like"));

            list_album.add(bilibili_album);
            JSONArray pic_array = jsonObj.getJSONArray("pictures");
            for(int j = 0 ; j < pic_array.size() ; j++ ){
                bilibili_album_pic = new Bilibili_album_pic();
                JSONObject json_pic = (JSONObject)pic_array.get(j);
                bilibili_album_pic.setDoc_id(jsonObj.getString("doc_id"));
                bilibili_album_pic.setPoster_uid(jsonObj.getString("poster_uid"));

                String img_src = json_pic.getString("img_src");
                String doc_id = jsonObj.getString("doc_id");
                bilibili_album_pic.setImg_src(img_src);

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
                        Method_down.down(img_src, "相册\\" + poster_uid + "\\" + jsonObj.getString("doc_id"));
                        //System.out.println("图片下载地址:"+json_pic.getString("img_src"));
                    }catch (Exception e){
                        String fileName = FilePath.build(BilConstant.bilibiliFilePath)
                                .ofPath("error")
                                .ofFileName("errorUrl.txt")
                                .path();
                        try {
                            iFileHandle.write(fileName,doc_id + "\r\n");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        e.printStackTrace();
                        e.printStackTrace();
                    }
                }


                bilibili_album_pic.setImg_width(json_pic.getString("img_width"));
                bilibili_album_pic.setImg_height(json_pic.getString("img_height"));
                bilibili_album_pic.setImg_size(json_pic.getString("img_size"));
                list_album_pic.add(bilibili_album_pic);
            }
        }
        map.put("map_album",list_album);
        map.put("map_album_pic",list_album_pic);

        return map;
    }


    public static void main(String[] args) {
        try {
            /**
             * 每个地方的异常逻辑都要单独处理
             */
            String poster_uid = "28380168";
            Bilibili_Album bilibili_album = new Bilibili_Album(poster_uid);
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
