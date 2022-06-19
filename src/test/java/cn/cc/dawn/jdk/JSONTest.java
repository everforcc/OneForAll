package cn.cc.dawn.jdk;

import cn.cc.dawn.local.craw.business.ys.dto.YsCardVO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class JSONTest {

    @Test
    public void jsonOrder(){
        String json = "{\"retcode\":0,\"message\":\"OK\",\"data\":{\"page\":\"0\",\"size\":\"6\",\"total\":\"0\",\"list\":[{\"uid\":\"101606716\",\"gacha_type\":\"301\",\"item_id\":\"\",\"count\":\"1\",\"time\":\"2022-04-06 10:16:53\",\"name\":\"魔导绪论\",\"lang\":\"zh-cn\",\"item_type\":\"武器\",\"rank_type\":\"3\",\"id\":\"1649210760000394416\"},{\"uid\":\"101606716\",\"gacha_type\":\"301\",\"item_id\":\"\",\"count\":\"1\",\"time\":\"2022-04-05 23:12:01\",\"name\":\"铁影阔剑\",\"lang\":\"zh-cn\",\"item_type\":\"武器\",\"rank_type\":\"3\",\"id\":\"1649171160000456416\"},{\"uid\":\"101606716\",\"gacha_type\":\"301\",\"item_id\":\"\",\"count\":\"1\",\"time\":\"2022-04-05 09:38:38\",\"name\":\"翡玉法球\",\"lang\":\"zh-cn\",\"item_type\":\"武器\",\"rank_type\":\"3\",\"id\":\"1649120760001185516\"},{\"uid\":\"101606716\",\"gacha_type\":\"301\",\"item_id\":\"\",\"count\":\"1\",\"time\":\"2022-04-05 09:38:31\",\"name\":\"以理服人\",\"lang\":\"zh-cn\",\"item_type\":\"武器\",\"rank_type\":\"3\",\"id\":\"1649120760001181116\"},{\"uid\":\"101606716\",\"gacha_type\":\"301\",\"item_id\":\"\",\"count\":\"1\",\"time\":\"2022-04-05 09:34:54\",\"name\":\"琴\",\"lang\":\"zh-cn\",\"item_type\":\"角色\",\"rank_type\":\"5\",\"id\":\"1649120760001062816\"},{\"uid\":\"101606716\",\"gacha_type\":\"301\",\"item_id\":\"\",\"count\":\"1\",\"time\":\"2022-04-04 09:48:43\",\"name\":\"飞天御剑\",\"lang\":\"zh-cn\",\"item_type\":\"武器\",\"rank_type\":\"3\",\"id\":\"1649034360001579716\"}],\"region\":\"cn_gf01\"}}";

        List<YsCardVO> ysCardVOList = formData(json);
        for (YsCardVO ysCardVO : ysCardVOList) {
            System.out.println(ysCardVO.toString());
        }
    }

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
             * 此处需要抛出异常
             * {"data":null,"message":"authkey timeout","retcode":-101}
             */
            throw new RuntimeException(jsonObject.getString("message"));
            //return new ArrayList<>();
        }
    }

}
