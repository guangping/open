package com.varela;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lance on 2016/4/20.
 */
public class FreemarkerTest {

    @Test
    public void templete() {
        Configuration cfg = new Configuration();
        //Template template=new Template();

        StringTemplateLoader stringLoader = new StringTemplateLoader();
        String templateContent = "欢迎：${name}";
        stringLoader.putTemplate("myTemplate", templateContent);

        cfg.setTemplateLoader(stringLoader);
        try {
            Template template = cfg.getTemplate("myTemplate", "utf-8");
            Map root = new HashMap();
            root.put("name", "javaboy2012");

            StringWriter writer = new StringWriter();
            template.process(root, writer);
            System.out.println(writer.toString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
