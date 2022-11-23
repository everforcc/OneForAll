/**
 * @Description
 * @Author everforcc
 * @Date 2022-07-02 18:27
 * Copyright
 */

package cn.cc.dawn.webapi.github;

import cn.cc.dawn.webapi.github.dto.UserReposReqDto;
import cn.cc.dawn.webapi.github.dto.UserReposResDto;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserReposResDtoTests {

    //@Test
    public String[] timeFormat(String timeStr) {
        //String timeStr = "2021-12-23T08:03:35Z";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        DateFormat dateParse = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(timeStr);
            String dateStr = dateParse.format(date);
            String[] dateAry = dateStr.split("-");
            return dateAry;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("时间格式化异常");
    }

    /**
     * github获取指定token的全部数据
     */
    @Test
    public void userRepos() {
        UserReposReqDto userReposReqDto = new UserReposReqDto();
        userReposReqDto.setToken("ghp_");
        String url = userReposReqDto.toStringWithBaseUrl();

        // 发起请求
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Authorization", "token " + userReposReqDto.getToken());

        HttpClient httpClient = HttpClients.createDefault();
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            String json = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            List<UserReposResDto> userReposResDtoList = JSONObject.parseArray(json, UserReposResDto.class);
            // 按时间顺序编号
            int i = 1;
            for (UserReposResDto userReposResDto : userReposResDtoList) {
                String[] dateAry = timeFormat(userReposResDto.getCreated_at());
                // | 序号 | 项目名 | 年 | 月 | 日 |
                System.out.printf("| %s | %s | %s | %s | %s |\n", i++, userReposResDto.getName(), dateAry[0], dateAry[1], dateAry[2]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
