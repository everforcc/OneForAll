/**
 * @Description
 * @Author everforcc
 * @Date 2022-10-17 15:06
 * Copyright
 */

package cn.cc.dawn.config.wrapper;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 封装器
 * 信息处理类
 */
@Slf4j
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private final byte[] data;

    private final Map<String, String[]> parameterMap;

    /**
     * 构造一个请求对象的封装器
     *
     * @param request 请求
     */
    public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        // 重复读
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        IOUtils.copy(request.getInputStream(), byteArrayOutputStream);
        this.data = byteArrayOutputStream.toByteArray();
        this.parameterMap = new HashMap<>();
        this.parameterMap.putAll(request.getParameterMap());
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    public ServletInputStream getInputStream() {

        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.data);

        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

        };

    }

    /**
     * 获取参数的值
     *
     * @return 值
     */
    @Override
    public Map<String, String[]> getParameterMap() {
        return parameterMap;
    }

    @Override
    public String getHeader(String name) {
        String header = super.getHeader(name);
        if (StringUtils.isNotEmpty(header)) {
            return escapeHtml(header);
        }
        return null;
    }

    @Override
    public String getParameter(String name) {
        String parameter = super.getParameter(name);
        if (StringUtils.isNotEmpty(parameter)) {
            return escapeHtml(parameter);
        }
        return null;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] parameters = super.getParameterValues(name);
        if (parameters != null) {
            int length = parameters.length;
            String[] escapedParameters = new String[length];
            for (int i = 0; i < length; i++) {
                escapedParameters[i] = escapeHtml(parameters[i]);
            }
            return escapedParameters;
        }
        return null;
    }

    private String escapeHtml(String value) {
        log.info("防跨站攻击信息处理");
        String result = StringEscapeUtils.escapeHtml4(value);
        log.info("处理后: {}", result);
        return result;
    }

}
