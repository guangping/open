package com.varela.utils;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lance on 2016-07-07.
 */
public class FreemarkerUtils {

    private static Map<String, String> templete = new HashMap<>();

    /**
     * 添加模板
     */
    public static void addTemplete(String templeteName, String tpl) {
        if (StringUtils.isBlank(templeteName) || StringUtils.isBlank(tpl)) {
            throw new RuntimeException("模板名称或内容不能为空.");
        }
        templete.put(templeteName, tpl);
    }


    public static String get(String tplName, Map param) {
        String tpl = templete.getOrDefault(tplName, "");
        if (StringUtils.isBlank(tpl)) {
            return null;
        }
        String rval = null;
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setDefaultEncoding("utf-8");
        StringTemplateLoader stringLoader = new StringTemplateLoader();
        stringLoader.putTemplate("myTemplate", tpl);
        cfg.setTemplateLoader(stringLoader);
        try {
            Template template = cfg.getTemplate("myTemplate");
            StringWriter writer = new StringWriter();
            template.process(param, writer);
            rval = writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rval;
    }


}
