package com.zdj.TMBookStore.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;

/**
 * @author 华韵流风
 * @ClassName GetRequest
 * @Description TODO
 * @Date 2021/5/13 20:01
 * @packageName cn.softeem.taobao.utils
 */
public class GetRequest extends HttpServletRequestWrapper {

    private final HttpServletRequest request;

    public GetRequest(HttpServletRequest request,String encoding) {
        super(request);
        this.request = request;
    }

    @Override
    public String getParameter(String name) {
        String value = getRequest().getParameter(name);
        return new String(value.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> map = request.getParameterMap();
        Set<String> keys = map.keySet();
        for (String key : keys) {
            String[] values = map.get(key);
            for (int i = 0; i < values.length; i++) {
                values[i] = new String(values[i].getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8);
            }
        }
        return map;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = request.getParameterValues(name);
        for (int i = 0; i < values.length; i++) {
            values[i] = new String(values[i].getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8);
        }

        return values;
    }
}
