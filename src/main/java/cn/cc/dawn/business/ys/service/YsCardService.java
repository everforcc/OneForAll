package cn.cc.dawn.business.ys.service;

import cn.cc.dawn.business.ys.dao.YsCardDaoMapper;
import cn.cc.dawn.business.ys.dto.YsCardVO;
import cn.cc.dawn.common.dto.HttpParam;
import cn.cc.dawn.utils.enums.CharsetsEnum;
import cn.cc.dawn.utils.enums.HttpTypeEnum;
import cn.cc.dawn.config.init.properties.PropertiesHeader;
import cn.cc.dawn.utils.http.HttpMethod;
import cn.cc.dawn.utils.http.impl.HttpUrlConnectImpl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Service("ysCardService")
@Transactional
public class YsCardService {

    /**
     * 待调整
     * 1. 加入池子信息
     * 2. 补充历史数据
     * 3. 分池子获取，分析，从前端录入,枚举
     */

    // gacha_type
    private static Map<String,String> map = new HashMap<>();
    static {
        /*map.put("常驻祈愿","200");
        map.put("新手祈愿","100");
        map.put("角色活动祈愿与角色活动祈愿-2","301");
        map.put("武器活动祈愿","302");*/
        map.put("1","301"); // 400和301可能是双池子的区分
        map.put("2","302"); // 武器活动祈愿
        map.put("3","200"); // 常驻祈愿
        //map.put("4","100"); // 新手
    }

//    private String cardUrl = "https://hk4e-api.mihoyo.com/event/gacha_info/api/getGachaLog?authkey_ver=1&sign_type=2&auth_appid=webview_gacha&init_type=301&gacha_id=ae6e3aa81978b13a9bb14cd653b80168f87e1c&timestamp=1641338980&lang=zh-cn&device_type=mobile&ext=%7b%22loc%22%3a%7b%22x%22%3a-3766.102783203125%2c%22y%22%3a250.91119384765626%2c%22z%22%3a-2364.514404296875%7d%2c%22platform%22%3a%22Android%22%7d&game_version=CNRELAndroid2.4.0_R5383366_S5437228_D5420700&plat_type=android&region=cn_gf01&authkey=rgoooaaX4F%2fkJQUC89LEP8SuvXY%2bNnwdVIcfuzCas%2b29vOo5YYAF8ZqRQ0WR7AsF2t3ZcJXVI5xFmSLvYUhkHKnDXKJldNunc9botakvWtLJpRF6bK%2b58jv3nzZsXZfFPE3ciJXtx9ynfr%2fWrGKXGBpK2UXRXq1MGz7P0MYciN0vN3qa%2fkjhIWNqeuv7X4BxvrFjPxzeaoGPdp2nFonHEjZe3RedkBjIQOD%2fHaPuYGYpRIbW%2fYKZUEVj7hf8gZIQGwH%2b%2fy7bfaZDjC904Fq%2fggacCXdIdQLIEueC7ygH8lwBHO9iQe7NlI4rkwLRtJErrm4bvYLHMJvY4Nrz7Y4f3Y1SErVh9DJyA0frmSGMU1iejGKo%2bsJn0cbnxvyPGmngpaKa4Vmdg4TBzcjWTLEDswfR5gJQ1N5UMJTg74Ku3EUZIFKfeKde2BWbaoDhhA7tAXSImXnkfqge4KKb%2bG7Baihl3ZklvvqCFJMKUTfczWNJFnNEBApasA3HdkRbnAL0WutdR5lJviF7R1WlhWd1iL8W7TSENysXlVwcu0OW4%2bv4NAToa9ocuyl5ZQswH7%2fuOSTw9fEodwiFKOPF%2f54T%2ftCAZYJze3zvoosqDfuFC870Q67q2XvR00CqSJBh13qt9PAxbss4Q8RyMnm2%2bcpwWYeucY0o9nyK9YJUNbew0Hfmi6KJnQUYrG2b20y%2f2mf%2b2Dt5c5OUpOX3aeO7jt7pMeUNj06YZ4S8KHmXhFxprGycH2ejHjidcRxb1pgbXSA6r9S4q1xCCAPRAaCJYo4%2bt%2bTaIp7nOy1cqwOsYJf97A1xy%2bzAG1yPqARsIZ2zqORQBg%2fO0GnNrwaMfDyt7q7amxhn1GMrx3KkujHSE2nnoBe%2fyK64UOJqCn4iLwZlc9W%2bBYc1jIlU5MjMsBLVV74OBjyDgg0l%2fX5DBOcqThJkUVZbE22hTLKVvqfwvbooyGN1hriHPvP7fMgNhGgxYeQXhgHB5dOg0lsf%2fJ7pml09qcefT5zdlt%2bM3MKUEFVXec69TmUHC03ELCUpjQFjng15GdfaxKGGK%2bvkuG82%2fxuM37LnVsdP5Z7qQ0ZpCGs7UJwBv%2b7Knch50USTcML91oRLD8ZKVCgvfB3x40kGmLwmnnR%2bEpiN0yW69GAmtzyHy3cRnZL4kzvBjmGZAHS3cWMif1RQu82er%2bQa%2fHUOm%2bJHn85DIfjEcK90sWNn9y2wnzFANCGydPD4BVuaohOaR7QsBdvy0jeaztNvWyLjabsSdvWN781b81uV5WZ4%2bLLBr%2fvkHYWJBLM6A9tnoW4KRinqdvi28Z%2fQKPzCYBvod0WMnpMHQaqB69z6b1GDSovxPe0dVcWQv4fp9d5SkaXe%2bRZvgw%3d%3d&game_biz=hk4e_cn&size=6" +
//            "&gacha_type=301" +
//            "&page=0" +
//            "&end_id=0";
    private static String cardUrl = "https://hk4e-api.mihoyo.com/event/gacha_info/api/getGachaLog?authkey_ver=1&sign_type=2&auth_appid=webview_gacha&init_type=200&gacha_id=98662eb647ab7727369235bd73bd8bebfc8198&timestamp=1641338793&lang=zh-cn&device_type=mobile&ext=%7b%22loc%22%3a%7b%22x%22%3a-3766.072021484375%2c%22y%22%3a250.9093475341797%2c%22z%22%3a-2364.532470703125%7d%2c%22platform%22%3a%22Android%22%7d&game_version=CNRELAndroid2.4.0_R5691054_S5715829_D5736476&plat_type=android&region=cn_gf01&authkey=rgoooaaX4F%2fkJQUC89LEP8SuvXY%2bNnwdVIcfuzCas%2b29vOo5YYAF8ZqRQ0WR7AsF2t3ZcJXVI5xFmSLvYUhkHKnDXKJldNunc9botakvWtLJpRF6bK%2b58jv3nzZsXZfFPE3ciJXtx9ynfr%2fWrGKXGBpK2UXRXq1MGz7P0MYciN0vN3qa%2fkjhIWNqeuv7X4BxvrFjPxzeaoGPdp2nFonHEjZe3RedkBjIQOD%2fHaPuYGYpRIbW%2fYKZUEVj7hf8gZIQGwH%2b%2fy7bfaZDjC904Fq%2fggacCXdIdQLIEueC7ygH8lwBHO9iQe7NlI4rkwLRtJErrm4bvYLHMJvY4Nrz7Y4f3Y1SErVh9DJyA0frmSGMU1iejGKo%2bsJn0cbnxvyPGmngpaKa4Vmdg4TBzcjWTLEDswfR5gJQ1N5UMJTg74Ku3EUZIFKfeKde2BWbaoDhhA7tAXSImXnkfqge4KKb%2bG7Baihl3ZklvvqCFJMKUTfczWNJFnNEBApasA3HdkRbnAL0WutdR5lJviF7R1WlhWd1iL8W7TSENysXlVwcu0OW4%2bv4NAToa9ocuyl5ZQswH7%2fuOSTw9fEodwiFKOPF%2f54T%2ftCAZYJze3zvoosqDfuFC870Q67q2XvR00CqSJBh13qt9PAxbss4Q8RyMnm2%2bcpwWYeucY0o9nyK9YJUNbew0HerY6ntO%2fw51Aewx4HPExmY6LfwyVbX%2fRmEgOwAiRxSel8E6DNEYxOl7xuRmDqMIoh5aCx%2ffPYKytNsCEAI1cF6HPf%2bgM2RNZnuN9DoSW69%2bb3KhFxSYzVuxhnvAU3%2fhnHZaZ3U%2fKFC803rtSIwUtCX7%2fGJ1ObDaJyaPTX5whvuGOLppDjr2msxOFg97qJkZHWzhNam14pXzhuyYU4pQ5B%2fReNex2dEZbe6scOx8gA%2bMnAD%2b6NdQdZCX%2f61jhbHpxmix5Ga%2b6DzUURnMcgnbw5t07rMlBGdMNTU5W6L5P13zlR5OXAwx2NSfcmoldZzTZbjRdNRbpxykEJgNOh4RL1bUuQ3%2by%2baWj6HlZSwO5Gmd7MUt8AoDJ%2b4lnTw9SB3TS2PAXA%2faPSXgsBrW%2bk2SLh1rXIjMUD7fMXtq4sUVerVfA3exaqt8jGHZ0TVAbJD%2bD%2fVoLGcPqAb8qC9PBPdVzHtJH%2fAVKlgbn%2fw1kynQBQP%2fIPrn9CU2fzwgUR588gw%2fN%2bF59hyr3xu6ENPyM40dYKQhBUhXzlzSzgnH%2fpPX%2bXgKh2%2b9%2f7aIIR5pRqv7igIz2QSRpxM9BXNmj9d85LI%2bB9bkv8Ckc%2bpdj7ZHmbuVublKkX%2b0ECgKOCG6bEf0kgAyPqeUkHhwgGK26P9Ew2IGDOCJCitQzJW3qBTC%2f15MJrEHA%3d%3d&game_biz=hk4e_cn&size=6" +
            "&gacha_type="
            // + map.get("3") +
            // "&page="
        ;
    private int gacha_type;
    private static int page = 0;
    private static String end_id = "0";
    // 这个方法还要调整，目前只能单个使用
    private static boolean flag = true;

    // 四星
    private List<Integer> analyseFour = new ArrayList<>();
    // 五星
    private List<Integer> analyseFive = new ArrayList<>();

    private HttpMethod httpMethod = new HttpUrlConnectImpl();
    @Resource
    private YsCardDaoMapper ysCardDaoMapper;


    @Transactional
    public int flow(String type) throws Exception {
        int count = 0;
        //1. 拼接url
        String concatUrl = "";
        //2. 从地址取出json

        while (flag) {
            // 3. 格式化url
            concatUrl = parseUrl(type,page,end_id);
            // 从文件读取json
            // String json = JSONInit.getYSJson();
            // 4. 请求json
            String json = getJSONByUrl(concatUrl);
            // 5. 保存，为下一步准备数据
            List<YsCardVO> ysCardVOList = formData(json);

            // 6. 保存到数据库
            for(YsCardVO ysCardVO:ysCardVOList){
                // 下次查询的参数
                end_id = ysCardVO.getCardid() + "";
                YsCardVO returnVO = ysCardDaoMapper.selectByCardID(ysCardVO.getCardid());
                if(Objects.isNull(returnVO)){
                    count++;
                    ysCardDaoMapper.insert(ysCardVO);
                }
            }

            page++;
        }
        flag = true;

        /**
         * 1. 从地址取出json
         * 2. json转对象集合
         * 3. 集合查询，不存在入库
         */
        analyse("101606716");
        return count;
    }

    private String parseUrl(String type,int page,String id){
        // 由于格式问题只能手动拼接 %s%s 否则需要转义，比较麻烦
        // String.format(urlPattern,page,id);
        return cardUrl + map.get(type) + "&page=" + page + "&end_id=" + id;
    }

    private String getJSONByUrl(String url) throws Exception {
        log.info("url:" + url);
        HttpParam httpParam = new HttpParam();
        httpParam.setUrl(url);
        httpParam.setCharset(CharsetsEnum.UTF_8);
        httpParam.setHttpTypeEnum(HttpTypeEnum.GET);
        httpParam.setTimeout(600);
        httpParam.setHeaders(PropertiesHeader.ysCardMap());

        String json = httpMethod.getMsg(httpParam);
        log.info("json:" + json);
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
                flag = false;
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
            // 或者返回有问题就退出
            flag = false;
            return new ArrayList<>();
        }
    }

    public void analyse(String uid){
        log.info("analyse start");
        analyseFour = new ArrayList<>();
        analyseFive = new ArrayList<>();
        try {

            //for(Map.Entry entry:map.entrySet()){

            // TODO 需要分不同的池子
                List<YsCardVO> ysCardVOListOrderByTime = ysCardDaoMapper.selectByUid(uid);
                int four = 0;
                int five = 0;
                for(YsCardVO ysCardVO:ysCardVOListOrderByTime){
                    four++;
                    five++;
                    if("4".equals(ysCardVO.getRankType())){
                        analyseFour.add(four);
                        four = 0;
                    }else if("5".equals(ysCardVO.getRankType())){
                        analyseFive.add(five);
                        five = 0;
                    }
                }
                log.info(" 结果 >>> ");
                log.info(" 四星出货 " + analyseFour.toString());
                log.info(" 五星出货 " + analyseFive.toString());
            //}
            log.info("analyse end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
