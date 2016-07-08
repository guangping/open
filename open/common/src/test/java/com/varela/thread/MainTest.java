package com.varela.thread;

import com.alibaba.fastjson.JSONObject;
import com.varela.utils.ChineseUtils;
import com.varela.utils.date.DatePattern;
import org.apache.commons.lang3.time.FastDateFormat;
import org.testng.annotations.Test;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by lance on 2016-07-04.
 */
public class MainTest {

    @Test
    public void run() {
        ExecutorService es = Executors.newFixedThreadPool(5);

        try {
            List<String> results = new ArrayList<>();
            //1.
            Map data = new HashMap();
            data.put("id", 1);
            Future<List<String>> future = es.submit(new MyTask2(data));
            List<String> list = future.get(2000, TimeUnit.MILLISECONDS);
            //System.out.println(list);
            results.addAll(list);

            //2
            data = new HashMap();
            data.put("id", 2);
            Future<List<String>> future2 = es.submit(new MyTask2(data));
            List<String> list2 = future2.get(2000, TimeUnit.MILLISECONDS);
            // System.out.println(list2);
            results.addAll(list2);

            System.out.println(results);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            es.shutdown();
        }
    }


    @Test
    public void runBatch() {
        ExecutorService es = Executors.newFixedThreadPool(5);

        List<MyTask2> list = new ArrayList<>();
        Map data = new HashMap();
        data.put("id", 1);
        list.add(new MyTask2(data));

        data = new HashMap();
        data.put("id", 2);
        list.add(new MyTask2(data));
        try {
            List<Future<List<String>>> futures = es.invokeAll(list, 2000, TimeUnit.MILLISECONDS);

            List<String> values;
            for (Future<List<String>> future : futures) {
                values = future.get();
                System.out.printf(JSONObject.toJSONString(values));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    public static class MyTask implements Runnable {
        @Override
        public void run() {

            FastDateFormat format = FastDateFormat.getInstance(DatePattern.YYYY_MM_DD_HH_MM_SS);
            try {
                Thread.sleep(10000);
                String date = format.format(new Date());
                System.out.println("当前时间:" + date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class MyTask2 implements Callable<List<String>> {

        private Map data;

        public MyTask2(Map data) {
            this.data = data;
        }

        @Override
        public List<String> call() throws Exception {
            System.out.println("data:" + JSONObject.toJSONString(data));
            MainTest mainTest = new MainTest();
            return mainTest.get();
        }
    }

    private List<String> get() {

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(ChineseUtils.getRandomChinese() + ChineseUtils.getRandomChinese());
        }
        System.out.println("当前线程:" + Thread.currentThread().getId() + ",结果:" + JSONObject.toJSONString(list));
        return list;
    }
}
