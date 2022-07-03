/**
 * @Description
 * @Author everforcc
 * @Date 2022-07-02 18:27
 * Copyright
 */

package cn.cc.dawn.webapi.github;

import cn.cc.dawn.webapi.github.dto.UserReposDto;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class UserReposDtoTests {

    @Test
    public void userRepos(){

        String url = "https://api.github.com/user/repos?visibility=private&sort=created";
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Authorization", "token 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");
        HttpClient httpClient = HttpClients.createDefault();
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            String json = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
            List<UserReposDto> userReposDtoList = JSONObject.parseArray(json, UserReposDto.class );
            for(UserReposDto userReposDto: userReposDtoList) {
                System.out.println(userReposDto.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
