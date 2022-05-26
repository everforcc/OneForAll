package cn.cc.dawn.local.craw.business.ys.service.impl;

import cn.cc.dawn.local.craw.business.ys.dao.YsCardDaoMapper;
import cn.cc.dawn.local.craw.business.ys.dto.YsCardVO;
import cn.cc.dawn.local.craw.business.ys.service.YsCardService;
import cn.cc.dawn.utils.http.dto.HttpParamDto;
import cn.cc.dawn.utils.commons.lang.RObjectsUtils;
import cn.cc.dawn.utils.commons.lang.RStringUtils;
import cn.cc.dawn.utils.enums.CharsetsEnum;
import cn.cc.dawn.utils.enums.HttpTypeEnum;
import cn.cc.dawn.config.init.properties.PropertiesHeader;
import cn.cc.dawn.utils.exception.AppCode;
import cn.cc.dawn.utils.http.IHttp;
import cn.cc.dawn.utils.commons.web.HttpParamUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Validated
@Service("ysCardServiceImpl")
@Transactional
public class YsCardServiceImpl implements YsCardService {

    /**
     * 待调整
     * 1. 加入池子信息
     * 2. 补充历史数据
     * 3. 分池子获取，分析，从前端录入,枚举
     */


    /* 1. 初始化数据 */
    /**
     * gacha_type
     * 100 新手祈愿
     * 301 角色活动祈愿与角色活动祈愿-2
     * 302 武器活动祈愿
     * 200 常驻祈愿
     */
    private static Map<String,String> wishMap = new HashMap<>();
    private static Map<String,List<String>> typeMap = new HashMap<>();
    //private static List<String> typeList = new ArrayList<>();
    static {

        /*map.put("常驻祈愿","200");
        map.put("新手祈愿","100");
        map.put("角色活动祈愿与角色活动祈愿-2","301");
        map.put("武器活动祈愿","302");*/

        /*typeList.add("100");
        typeList.add("200");
        typeList.add("301");
        typeList.add("302");*/

        wishMap.put("0","100"); // 新手
        wishMap.put("1","301"); // 400和301可能是双池子的区分
        wishMap.put("2","302"); // 武器活动祈愿
        wishMap.put("3","200"); // 常驻祈愿

        typeMap.put("up", Lists.newArrayList("301","400"));
        typeMap.put("武器", Lists.newArrayList("302"));
        typeMap.put("常驻", Lists.newArrayList("200"));
    }

    /**
     * 抽卡的html,json
     * path
     */
    private static final String htmlUrl = "https://webstatic.mihoyo.com/hk4e/event/e20190909gacha/index.html";
    private static final String jsonUrl = "https://hk4e-api.mihoyo.com/event/gacha_info/api/getGachaLog";

    /*@Resource
    private HttpMethod httpMethod = new HttpUrlConnectImpl();*/
    @Resource
    private IHttp IHttp;
    @Resource
    private YsCardDaoMapper ysCardDaoMapper;

    @Transactional
    @Override
    public int htmlUrlFlow(String json){
        return handleUrl(json,htmlUrl);
    }

    @Transactional
    @Override
    public int jsonUrlFlow(String json){

        return handleUrl(json,jsonUrl);
    }

    private int handleUrl(String json,String prefixUrl){
        /**
         * 1. 三种类型 便利，分别给出结果，根据选择，选择哪个查询哪个
         * 2.
         */

        //        typeList.forEach(e -> {
        //            try {
        //                flow(url,e);
        //            } catch (Exception ex) {
        //                ex.printStackTrace();
        //            }
        //        });

        JSONObject jsonObject = JSONObject.parseObject(json);

        AppCode.A01050.assertHasTrue(jsonObject.containsKey("webUrl"));
        AppCode.A01051.assertHasTrue(jsonObject.containsKey("selectType"));

        String webUrl = jsonObject.getString("webUrl");

        if(jsonUrl.equals(HttpParamUtils.getRootPathUrl(webUrl))){
            AppCode.A01054.assertHasTrue(webUrl.startsWith(jsonUrl));
        }else {
            AppCode.A01054.assertHasTrue(webUrl.startsWith(htmlUrl));
            webUrl = webUrl.replaceAll(htmlUrl,jsonUrl);
        }

        List<String> selectType = jsonObject.getObject("selectType", TypeReference.LIST_STRING);

        int count = 0;
        for(String type:selectType){
            count += flow(webUrl, wishMap.get(type));
        }
        return count;
    }

    /**
     * 根据type来区分
     * @param type
     * @return
     * @throws Exception
     */
    private int flow(String webUrl,String type){

        /**
         * 本次新增抽卡数
         */
        int count = 0;

        /**
         * 分页
         */
        int page = 0;
        /**
         * 结束标志，下次查询的参数
         */
        String end_id = "0";
        /**
         * 处理后的url
         */
        String concatUrl = "";
        /**
         * 分页依次查询，结束标志
         */
        boolean flag = true;
        /**
         *
         */
        String uid = "";

        while (flag) {
            // 3. 格式化url
            concatUrl = parseUrl(webUrl,type,page,end_id);
            log.info("请求地址: " + concatUrl);
            // 从文件读取json，本地文件测试使用
            // String json = JSONInit.getYSJson();
            // 4. 请求json
            String json = getJSONByUrl(concatUrl);
            // 5. 保存，为下一步准备数据
            List<YsCardVO> ysCardVOList = formData(json);

            if (ysCardVOList.size()==0){
                flag = false;
            }else {
                // 6. 保存到数据库
                for (YsCardVO ysCardVO : ysCardVOList) {

                    /**
                     * 第一次进入取出uid
                     */
                    if(RStringUtils.isBlank(uid)){
                        uid = ysCardVO.getUid();
                    }

                    // 下次查询的参数
                    end_id = ysCardVO.getCardid() + "";
                    YsCardVO returnVO = ysCardDaoMapper.selectByCardID(ysCardVO.getCardid());
                    if (RObjectsUtils.isNull(returnVO)) {
                        count++;
                        ysCardDaoMapper.insert(ysCardVO);
                    }else {
                        /**
                         * 如果有重复的说明，之前已经查过了，退出
                         */
                        log.debug("重复数据，已结束: " + returnVO);
                        flag = false;
                        break;
                    }
                }
                page++;
            }
        }

        /**
         * 1. 从地址取出json
         * 2. json转对象集合
         * 3. 集合查询，不存在入库
         */
        //analyse("101606716");
        //analyse(uid);
        return count;
    }

    /**
     * 处理参数
     * @param type
     * @param page
     * @param e_id
     * @return
     */
    private String parseUrl(String webUrl,String type,Integer page,String e_id){
        /**
         * 由于格式问题只能手动拼接 %s%s 否则需要转义，比较麻烦
         * String.format(urlPattern,page,id);
         * return webUrl + map.get(type) + "&page=" + page + "&end_id=" + id;
         */
        Map<String,String> map = new HashMap<>();
        map.put("page",page.toString());
        map.put("end_id",e_id);
        map.put("gacha_type",type);

        return HttpParamUtils.addOrUpdateUrlQueryMap(webUrl,map);
    }

    /**
     * 更新url查询参数
     * @param webUrl
     * @param type
     * @param page
     * @param e_id
     * @return
     */
    private String parseUrlMap(String webUrl,String type,Integer page,String e_id){
        // 由于格式问题只能手动拼接 %s%s 否则需要转义，比较麻烦
        // String.format(urlPattern,page,id);
        Map<String,String> queryMap = new HashMap<>();
        queryMap.put("gacha_type", wishMap.get(type));
        queryMap.put("page",page.toString());
        queryMap.put("end_id",e_id);

        HttpParamUtils.updateUrlQueryMap(webUrl,queryMap);

        return jsonUrl + wishMap.get(type) + "&page=" + page + "&end_id=" + e_id;
    }

    /**
     * 根据json请求数据
     * @param url
     * @return
     * @throws Exception
     */
    private String getJSONByUrl(String url){
        log.info("url:" + url);
        HttpParamDto httpParamDto = new HttpParamDto();
        httpParamDto.setUrl(url);
        httpParamDto.setCharset(CharsetsEnum.UTF_8);
        httpParamDto.setHttpTypeEnum(HttpTypeEnum.GET);
        httpParamDto.setTimeout(600);
        httpParamDto.setHeaders(PropertiesHeader.ysCardMap());

        String json = IHttp.getMsg(httpParamDto);
        log.info("返回json:" + json);
        return json;
    }

    /**
     * 格式化json为dto
     * @param json
     * @return
     */
    private List<YsCardVO> formData(String json){

        JSONObject jsonObject = JSON.parseObject(json);
        if("0".equals(jsonObject.getString("retcode"))){
            JSONObject jsonData = jsonObject.getJSONObject("data");
            int size = jsonData.getIntValue("size");
            List<YsCardVO> ysCardVOArrayList = new ArrayList<>(size);
            JSONArray jsonArray = jsonData.getJSONArray("list");
            int jsonarySize = jsonArray.size();

            // 等于0就退出
            if(0 == jsonarySize){// ?
                //flag = false;
                return new ArrayList<>();
            }

            for(int i=0;i<jsonarySize;i++){
                JSONObject vo = jsonArray.getJSONObject(i);
                vo.put("cardid",vo.getString("id"));
                vo.remove("id");
                YsCardVO ysCardVO = JSONObject.toJavaObject(vo,YsCardVO.class);

                ysCardVOArrayList.add(ysCardVO);
            }
            return ysCardVOArrayList;
        }else {
            /**
             * 或者返回有问题就退出
             * flag = false;
             * TODO 此处需要抛出异常
             * {"data":null,"message":"authkey timeout","retcode":-101}
             */
            throw new RuntimeException(jsonObject.getString("message"));
            //return new ArrayList<>();
        }
    }

    @Override
    public Map analyse(String json){

        JSONObject jsonObject = JSONObject.parseObject(json);
        AppCode.A01052.assertHasTrue(jsonObject.containsKey("uid"));
        AppCode.A01052.assertHasTrue(jsonObject.containsKey("typeList"));
        AppCode.A01053.assertHasTrue(jsonObject.containsKey("levelType"));

        String uid = jsonObject.getString("uid");
        List<String> typeList = jsonObject.getObject("typeList",TypeReference.LIST_STRING);
        String levelType = jsonObject.getString("levelType");

        //return JSON.toJSONString(analyseUid(uid,typeList));
        return analyseUid(uid,typeList,levelType);
    }

    /**
     * 分析抽卡出货情况
     * @param uid
     */
    private Map<String,Map<Integer,List<String>>> analyseUid(String uid,List<String> typeList,String levelType){
        log.info("analyse start");

        Map<String,Map<Integer,List<String>>> analyseMap = new HashMap<>();

        for(String type:typeList) {
            List<String> typeMapKey = typeMap.get(type);
            /**
             * 四星
             */
            List<String> analyseFour = new ArrayList<>();
            /**
             * 五星
             */
            List<String> analyseFive = new ArrayList<>();

            /**
             * 每种卡的数据
             */
            List<String> fourStartList = new ArrayList<>();
            List<String> fiveStartList = new ArrayList<>();

            /**
             * 四星和五星
             */
            Map<Integer,List<String>> allTypeMap = new HashMap<>();

            try {
                List<YsCardVO> ysCardVOListOrderByTime = ysCardDaoMapper.selectByUid(uid,typeMapKey);
                log.info("插入后，是否能查询到全部数据测试: " + ysCardVOListOrderByTime.size());
                int four = 0;
                int five = 0;

                for (YsCardVO ysCardVO : ysCardVOListOrderByTime) {
                    five++;
                    four++;
                    if ("4".equals(levelType)&&"4".equals(ysCardVO.getRankType())) {
                        String fourCard = four + "-" + ysCardVO.getName();
                        analyseFour.add(fourCard);
                        fourStartList.add(fourCard);
                        four = 0;
                    } else if ("5".equals(ysCardVO.getRankType())) {
                        String fiveCard = five + "-" + ysCardVO.getName();
                        analyseFive.add(fiveCard);
                        fiveStartList.add(fiveCard);
                        four = 0;
                        five = 0;
                    }
                }

                if("4".equals(levelType)){
                    allTypeMap.put(4,fourStartList);
                }
                allTypeMap.put(5,fiveStartList);

                log.info(" 结果 >>> ");
                log.info(type + " 四星出货 " + analyseFour.toString());
                log.info(type + " 五星出货 " + analyseFive.toString());

                analyseMap.put(type,allTypeMap);

                log.info("analyse end");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return analyseMap;
    }



}
