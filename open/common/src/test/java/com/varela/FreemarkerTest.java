package com.varela;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Before;
import org.testng.annotations.Test;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by lance on 2016/4/20.
 */
public class FreemarkerTest {

    private Configuration cfg = null;

    @Before
    public void setUp() {
        cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setLocale(Locale.CHINA);
        cfg.setDefaultEncoding("utf-8");
        cfg.setCacheStorage(new freemarker.cache.MruCacheStorage(20, 250));

        StringTemplateLoader stringLoader = new StringTemplateLoader();
        stringLoader.putTemplate("name", "this is cache ${name}");
        stringLoader.putTemplate("tem", "this is name ${name}");
        cfg.setTemplateLoader(stringLoader);
    }

    @org.junit.Test
    public void run() throws Exception {
        Map param = new HashMap<>();
        param.put("name", "北京");

        Template template = cfg.getTemplate("name", Locale.CHINA);

        StringWriter writer = new StringWriter();
        template.process(param, writer);
        System.out.println(writer.toString());

        template = cfg.getTemplate("tem", Locale.CHINA);

        writer = new StringWriter();
        template.process(param, writer);
        System.out.println(writer.toString());
    }
}
