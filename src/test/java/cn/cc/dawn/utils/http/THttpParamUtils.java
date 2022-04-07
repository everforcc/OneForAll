package cn.cc.dawn.utils.http;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.MalformedURLException;
import java.net.URL;

@SpringBootTest
public class THttpParamUtils {

    @Test
    void paramsToMap(){
        String weburl = "https://webstatic.mihoyo.com/hk4e/event/e20190909gacha/index.html?authkey_ver=1&sign_type=2&auth_appid=webview_gacha&init_type=301&gacha_id=50eef7be3b7945930041f9f20310e0bbcb8a7a&timestamp=1648597804&lang=zh-cn&device_type=mobile&ext=%7b%22loc%22%3a%7b%22x%22%3a-3540.392333984375%2c%22y%22%3a237.4633026123047%2c%22z%22%3a-3238.4052734375%7d%2c%22platform%22%3a%22Android%22%7d&game_version=CNRELAndroid2.6.0_R6461336_S6575074_D6551263&plat_type=android&region=cn_gf01&authkey=rgoooaaX4F%2fkJQUC89LEP8SuvXY%2bNnwdVIcfuzCas%2b29vOo5YYAF8ZqRQ0WR7AsF2t3ZcJXVI5xFmSLvYUhkHKnDXKJldNunc9botakvWtLJpRF6bK%2b58jv3nzZsXZfFPE3ciJXtx9ynfr%2fWrGKXGBpK2UXRXq1MGz7P0MYciN0vN3qa%2fkjhIWNqeuv7X4BxvrFjPxzeaoGPdp2nFonHEjZe3RedkBjIQOD%2fHaPuYGYpRIbW%2fYKZUEVj7hf8gZIQGwH%2b%2fy7bfaZDjC904Fq%2fggacCXdIdQLIEueC7ygH8lwBHO9iQe7NlI4rkwLRtJErrm4bvYLHMJvY4Nrz7Y4f3Y1SErVh9DJyA0frmSGMU1iejGKo%2bsJn0cbnxvyPGmngpaKa4Vmdg4TBzcjWTLEDswfR5gJQ1N5UMJTg74Ku3EUZIFKfeKde2BWbaoDhhA7tAXSImXnkfqge4KKb%2bG7Baihl3ZklvvqCFJMKUTfczWNJFnNEBApasA3HdkRbnAL0WutdR5lJviF7R1WlhWd1iL8W7TSENysXlVwcu0OW4%2bv4NAToa9ocuyl5ZQswH7%2fuOSTw9fEodwiFKOPF%2f54T%2ftCAZYJze3zvoosqDfuFC870Q67q2XvR00CqSJBh13qt9PAxbss4Q8RyMnm2%2bcpwWYeucY0o9nyK9YJUNbew0Hfui7j7lYlpwYamzXd0cMQL2xSM4R0Vu%2fZThBr0dUVYG8wAuDZ1rfvCgtxF0Kv%2fxykSO98Hf%2bR5dbE42r5LuuaJfxq1zkCyDe98FAjd4MVRUE2n4AIEVU1Lyw%2bviezgqHCwqmbis4kDj2lJ93jQhj20kZJnG7kvFPw2gm52Qz%2biUYhY3ABc1OPK5zV%2fG9RJtcgjvhYzzGa1IViQlfzmsDls8MjIWY%2fzVofvM6iDswLHueu1yI7YrbqmEwamOTio%2fZ%2fb6hfiScFC3cqEFXwkhl2eK8aQ0U8JYjuaQbSTkMwauhWVRD4xdHX7ljCd3mDrdASnZkpg%2ftLM%2fbjGjNgnym%2f9SzOduI%2fffU12Gcxf33Fh3HBhj7wBBIs9EJtCY8P5HeJJXMKlEOoY%2bRkDt7qgIVgq3gXmEvyXNzJdMfiLhSfXRTqvmfBINVVz%2fNPJt6rs69yBdKQJon4kJyNOXRBMNtz6MXhPjz68lYDtHMyeupvtDWmyy029Q47sJSrB5ZqVa4M%2bdwz%2b8ZX5dTwW4Bt%2bQQvpwrGI9WnHMgqE135s5kbz4quSoUFlRYPcM%2bbM2uIk5EgbWtSVBFJfB%2fSXMfoyjtmJrq1UyU%2bygMeCRskSLCmaSFN1HACSDJ8zfWNzuUunldArVWx%2bF%2bioWfaPchjQVKibaa%2bganNh0IcTCBRnoQdIvA%3d%3d&game_biz=hk4e_cn#/log";

        try {
            URL url = new URL(weburl);
            System.out.println(url.getPath());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

}
