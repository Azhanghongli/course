package com.course.generator.server;

import com.course.generator.util.FreemarkerUtil;

import java.util.HashMap;
import java.util.Map;

public class ServerGenerator {
    static String toServicePath = "server\\src\\main\\java\\com\\course\\server\\service\\";
    public static void main(String[] args) throws Exception {
        String Domain = "Section";
        String domain = "section";
        Map<String, Object> map = new HashMap<>();
        map.put("Domain", Domain);
        map.put("domain", domain);

        // 生成service
        FreemarkerUtil.initConfig("service.ftl");
        FreemarkerUtil.generator(toServicePath + Domain + "Service.java", map);
    }

}
