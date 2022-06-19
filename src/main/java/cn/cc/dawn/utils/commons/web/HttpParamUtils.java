package cn.cc.dawn.utils.commons.web;

import cn.cc.dawn.utils.commons.lang.RStringUtils;
import cn.cc.dawn.utils.constant.CharsetsConstant;
import cn.cc.dawn.utils.constant.CommonCharConstant;
import cn.cc.dawn.utils.constant.NumberConstant;
import cn.cc.dawn.utils.exception.AppCode;
import cn.cc.dawn.utils.annotation.ReflectFileFiled;
import com.google.common.base.Joiner;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class HttpParamUtils {


    /**
     * google的工具包
     * @param source
     * @return
     */
    public static String asUrlParamsGuava(Map<String, String> source){
        return Joiner.on(CommonCharConstant.AND)
                // 用指定符号代替空值,key 或者value 为null都会被替换
                .useForNull(CommonCharConstant.EMP)
                .withKeyValueSeparator(CommonCharConstant.EQ)
                .join(source);
    }

    /**
     * 只要确保你的编码输入是正确的,就可以忽略掉 UnsupportedEncodingException
     */
    public static String asUrlParams(Map<String, String> source){
        Iterator<String> it = source.keySet().iterator();
        StringBuilder paramStr = new StringBuilder();
        while (it.hasNext()){
            String key = it.next();
            String value = source.get(key);
            if (RStringUtils.isBlank(value)){
                continue;
            }
            try {
                // URL 编码
                value = URLEncoder.encode(value, CharsetsConstant.UTF_8.toString());
            } catch (UnsupportedEncodingException e) {
                // do nothing
            }
            paramStr.append(CommonCharConstant.AND).append(key).append(CommonCharConstant.EQ).append(value);
        }
        // 去掉第一个&
        return paramStr.substring(NumberConstant.N_1);
    }

    /**
     * 通过反射拼接参数
     * 对象中的所有字段必须是参数的一个
     * @param obj
     * @return
     */
    public static String asUrlParams(Object obj){
        Class<?> clazz = obj.getClass();
        StringBuffer stringBuffer = new StringBuffer();
        // 多个字段
        Field[] fields = clazz.getDeclaredFields();

        if(Objects.isNull(fields)){
            return stringBuffer.toString();
        }

        for(Field field:fields){
            String alias = null;
            try {
                ReflectFileFiled reflectFileFiled = field.getAnnotation(ReflectFileFiled.class);

                if(Objects.nonNull(reflectFileFiled)){
                    if(!reflectFileFiled.use()) {
                        continue;
                    }else {
                        alias = reflectFileFiled.alias();
                    }
                }

                field.setAccessible(true);
                Object value = field.get(obj);
                if(Objects.nonNull(value)){
                    String name = null;
                    if(RStringUtils.isBlank(alias)){
                        name = field.getName();
                    }else {
                        name = alias;
                    }
                    stringBuffer.append(name + CommonCharConstant.EQ + URLEncoder.encode(value.toString(), CharsetsConstant.UTF_8.toString()) + CommonCharConstant.AND);
                    //stringBuffer.append(name + CommonCharConstant.EQ + value + CommonCharConstant.AND);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 如果没有一个字段需要处理
         */
        if(stringBuffer.length() == 0){
            return stringBuffer.toString();
        }

        // 去掉最后一个 & 字符
        return stringBuffer.substring(0,stringBuffer.length()-1);
    }

    public static String getRootUrl(String weburl){
        URL url = null;
        try {
            url = new URL(weburl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url.getProtocol() + CommonCharConstant.URL_SPLIT + url.getHost();
    }

    public static String getRootPathUrl(String weburl){
        URL url = null;
        try {
            url = new URL(weburl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url.getProtocol() + CommonCharConstant.URL_SPLIT + url.getHost() + url.getPath();
    }

    /**
     * https://www.runoob.com/data-structures/data-structures-tutorial.html
     * @param weburl
     * @return
     */
    public static String urlToPathWithOutLast(String weburl){
        // 处理掉lastpath
        weburl = withOutLastPath(weburl);
        URL url = null;
        try {
            url = new URL(weburl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String path = File.separator + url.getHost() + url.getPath();
        return path.replace("/",File.separator);
    }

    public static Map<String,String> getUrlQueryMap(String weburl){
        Map<String,String> map = new HashMap<>();
        URL url = null;
        try {
            url = new URL(weburl);
            String params = url.getQuery();
            if(RStringUtils.isBlank(params)){
                return map;
            }

            /**
             * 用 & 拆分
             */
            String[] paramsAry =  params.split(CommonCharConstant.AND);

            for(String str: paramsAry){
                /**
                 * 每个参数按 = 拆分
                 */
                String[] paramAry = str.split(CommonCharConstant.EQ);
                if (1 == paramAry.length){
                    map.put(paramAry[0],CommonCharConstant.EMP);
                }else if(2 == paramAry.length){
                    map.put(paramAry[0],paramAry[1]);
                }else {
                    throw new RuntimeException("参数处理异常");
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static String updateUrlQueryMap(String webUrl,Map<String,String> updateMap){
        Map<String,String> queryMap = getUrlQueryMap(webUrl);
        for(Map.Entry entry:updateMap.entrySet()){
            if (!queryMap.containsKey(entry.getKey())){
                throw new RuntimeException("修改参数异常");
            }
            queryMap.put((String)entry.getKey(),(String)entry.getValue());
        }
        return urlWithOutParams(webUrl) + CommonCharConstant.QUERY_SPLIT + asUrlParamsGuava(queryMap);
    }

    public static String addOrUpdateUrlQueryMap(String webUrl,Map<String,String> updateMap){
        Map<String,String> queryMap = getUrlQueryMap(webUrl);
        for(Map.Entry entry:updateMap.entrySet()){
            queryMap.put((String)entry.getKey(),(String)entry.getValue());
        }
        return urlWithOutParams(webUrl) + CommonCharConstant.QUERY_SPLIT + asUrlParamsGuava(queryMap);
    }

    public static String getUrlQueryValue(String webUrl,String key){
        if(RStringUtils.isEmpty(key)){
            throw new RuntimeException("查询参数不能为空");
        }
        Map<String,String> queryMap = getUrlQueryMap(webUrl);
        return queryMap.get(key);
    }

    public static String urlWithOutParams(String webUrl){
        URL url = null;
        try {
            url = new URL(webUrl);
            return url.getProtocol() + CommonCharConstant.URL_SPLIT + url.getHost() + url.getPath();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static boolean checkUrlEffect(String weburl){
        URL url = null;
        try {
            url = new URL(weburl);
        } catch (MalformedURLException e) {
            AppCode.A00100.toUserException(e.toString());
            return false;
        }
        return true;
    }

    public static String fileNameFromUrl(String urlPath){
        String imageName = urlPath.substring(urlPath.lastIndexOf("/") + 1, urlPath.length());
        if (imageName.contains("?")) {
            imageName = imageName.substring(0, imageName.lastIndexOf("?"));
            imageName = imageName.substring(imageName.lastIndexOf("/") + 1, imageName.length());
        }
        return imageName;
    }

    public static String withOutLastPath(String urlPath){
        String imageName = urlPath.substring(0,urlPath.lastIndexOf("/"));

        return imageName;
    }

    public static String fileSuffixFromUrl(String urlPath){
        return fileSuffixFromUrl(urlPath,false);
    }

    public static String fileSuffixFromUrl(String urlPath,boolean flag){
        String imageName = fileNameFromUrl(urlPath);
        // "." + url.substring(url.lastIndexOf(".")+1,url.length())
        String suffix = imageName.replaceFirst("^.+\\.", (flag ? "." : ""));
        return suffix;
    }

    public static void main(String[] args) {

            try {
//                String url = "http://www.baidu.com/1.txt?ab=b";
//                System.out.println(HttpParamUtils.fileNameFromUrl(url));
//
//                String rname = "1.1.1.1.txt";
//                rname = rname.replaceFirst("^.+\\.", (false ? "." : ""));
//                System.out.println(rname);
//                System.out.println(HttpParamUtils.fileSuffixFromUrl(url));
//                String weburl = "https://webstatic.mihoyo.com/hk4e/event/e20190909gacha/index.html?authkey_ver=1&sign_type=2&auth_appid=webview_gacha&init_type=301&gacha_id=50eef7be3b7945930041f9f20310e0bbcb8a7a&timestamp=1648597804&lang=zh-cn&device_type=mobile&ext=%7b%22loc%22%3a%7b%22x%22%3a-3540.392333984375%2c%22y%22%3a237.4633026123047%2c%22z%22%3a-3238.4052734375%7d%2c%22platform%22%3a%22Android%22%7d&game_version=CNRELAndroid2.6.0_R6461336_S6575074_D6551263&plat_type=android&region=cn_gf01&authkey=rgoooaaX4F%2fkJQUC89LEP8SuvXY%2bNnwdVIcfuzCas%2b29vOo5YYAF8ZqRQ0WR7AsF2t3ZcJXVI5xFmSLvYUhkHKnDXKJldNunc9botakvWtLJpRF6bK%2b58jv3nzZsXZfFPE3ciJXtx9ynfr%2fWrGKXGBpK2UXRXq1MGz7P0MYciN0vN3qa%2fkjhIWNqeuv7X4BxvrFjPxzeaoGPdp2nFonHEjZe3RedkBjIQOD%2fHaPuYGYpRIbW%2fYKZUEVj7hf8gZIQGwH%2b%2fy7bfaZDjC904Fq%2fggacCXdIdQLIEueC7ygH8lwBHO9iQe7NlI4rkwLRtJErrm4bvYLHMJvY4Nrz7Y4f3Y1SErVh9DJyA0frmSGMU1iejGKo%2bsJn0cbnxvyPGmngpaKa4Vmdg4TBzcjWTLEDswfR5gJQ1N5UMJTg74Ku3EUZIFKfeKde2BWbaoDhhA7tAXSImXnkfqge4KKb%2bG7Baihl3ZklvvqCFJMKUTfczWNJFnNEBApasA3HdkRbnAL0WutdR5lJviF7R1WlhWd1iL8W7TSENysXlVwcu0OW4%2bv4NAToa9ocuyl5ZQswH7%2fuOSTw9fEodwiFKOPF%2f54T%2ftCAZYJze3zvoosqDfuFC870Q67q2XvR00CqSJBh13qt9PAxbss4Q8RyMnm2%2bcpwWYeucY0o9nyK9YJUNbew0Hfui7j7lYlpwYamzXd0cMQL2xSM4R0Vu%2fZThBr0dUVYG8wAuDZ1rfvCgtxF0Kv%2fxykSO98Hf%2bR5dbE42r5LuuaJfxq1zkCyDe98FAjd4MVRUE2n4AIEVU1Lyw%2bviezgqHCwqmbis4kDj2lJ93jQhj20kZJnG7kvFPw2gm52Qz%2biUYhY3ABc1OPK5zV%2fG9RJtcgjvhYzzGa1IViQlfzmsDls8MjIWY%2fzVofvM6iDswLHueu1yI7YrbqmEwamOTio%2fZ%2fb6hfiScFC3cqEFXwkhl2eK8aQ0U8JYjuaQbSTkMwauhWVRD4xdHX7ljCd3mDrdASnZkpg%2ftLM%2fbjGjNgnym%2f9SzOduI%2fffU12Gcxf33Fh3HBhj7wBBIs9EJtCY8P5HeJJXMKlEOoY%2bRkDt7qgIVgq3gXmEvyXNzJdMfiLhSfXRTqvmfBINVVz%2fNPJt6rs69yBdKQJon4kJyNOXRBMNtz6MXhPjz68lYDtHMyeupvtDWmyy029Q47sJSrB5ZqVa4M%2bdwz%2b8ZX5dTwW4Bt%2bQQvpwrGI9WnHMgqE135s5kbz4quSoUFlRYPcM%2bbM2uIk5EgbWtSVBFJfB%2fSXMfoyjtmJrq1UyU%2bygMeCRskSLCmaSFN1HACSDJ8zfWNzuUunldArVWx%2bF%2bioWfaPchjQVKibaa%2bganNh0IcTCBRnoQdIvA%3d%3d&game_biz=hk4e_cn#/log";
//                URL url = new URL(weburl);
//                System.out.println(url.getPath());
//                System.out.println(url.getRef());
//                System.out.println(url.getQuery());
//                System.out.println(url.getProtocol());
//                System.out.println(url.getPort());
//                System.out.println(CommonCharConstant.URL_SPLIT);
//                System.out.println(url.getHost());
//
//                Map<String,String> map = getUrlQueryMap(weburl);
//
//                map.put("authkey_ver","authkey_ver");
//
//                String updateUrl = updateUrlQueryMap(weburl,map);
//
//                System.out.println(weburl);
//                System.out.println(updateUrl);
                //  map.forEach((k,v) -> System.out.println("k: " + k + " \r\nv: " + v));
                //  map.forEach((k,v) -> System.out.println(k + " :" + v));

                String weburl = "https://www.runoob.com/data-structures/data-structures-tutorial.html";
                URL url = new URL(weburl);
                System.out.println(HttpParamUtils.fileNameFromUrl(weburl));
                System.out.println(HttpParamUtils.withOutLastPath(weburl));
                System.out.println(url.getHost());
                System.out.println(url.getPath());

                System.out.println(urlToPathWithOutLast(weburl));
            } catch (Exception e) {
                e.printStackTrace();
            }


    }

}
