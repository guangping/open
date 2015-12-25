package com.api.test;

import com.alibaba.fastjson.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lance on 12/25/2015.
 */
public class CityTest {

    private List<City> cityList = null;

    @BeforeMethod
    public void before() {
        cityList = new ArrayList<>();
        City city = new City();
        city.setName("深圳");
        city.setDescription("深圳，别称鹏城，广东省辖市，地处广东省南部，珠江三角洲东岸，与香港一水之隔，东临大亚湾和大鹏湾，西濒珠江口和伶仃洋，南隔深圳河与香港相连，北部与东莞、惠州接壤。");
        city.setPhoto(getImageUrl("shenzheng.jpg"));
        cityList.add(city);

        city = new City();
        city.setName("广州");
        city.setDescription("广州是广东省省会。从秦朝开始，广州一直都是华南地区的政治、军事、经济、文化和科教中心。");
        city.setPhoto(getImageUrl("guangzhou.jpg"));
        cityList.add(city);

        city = new City();
        city.setName("上海");
        city.setDescription("上海（Shanghai），简称“沪”或“申”，中华人民共和国直辖市，中国国家中心城市，中国的经济、金融、贸易、航运中心。");
        city.setPhoto(getImageUrl("shanghai.jpg"));
        cityList.add(city);

        city = new City();
        city.setName("北京");
        city.setDescription("北京（Beijing），简称京，中华人民共和国首都、直辖市、国家中心城市、超大城市，全国政治中心、文化中心、国际交往中心、科技创新中心，是中国共产党中央委员会、中华人民共和国中央人民政府和全国人民代表大会的办公所在地。");
        city.setPhoto(getImageUrl("beijing.jpg"));
        cityList.add(city);

        city = new City();
        city.setName("南京");
        city.setDescription("南京，简称宁，是江苏省会，地处中国东部地区，长江下游，濒江近海。全市下辖11个区，总面积6597平方公里，2013年建成区面积752.83平方公里，常住人口818.78万");
        city.setPhoto(getImageUrl("nanjing.jpg"));
        cityList.add(city);

        city = new City();
        city.setName("厦门");
        city.setDescription("厦门市（英文：Amoy），别称鹭岛，简称鹭，位于福建省东南端，西接龙海，北邻南安，东南与大小金门和大担岛隔海相望。是闽南地区的主要城市之一，与漳州、泉州并称“厦漳泉”，闽南金三角经济区。");
        city.setPhoto(getImageUrl("xiamen.jpg"));
        cityList.add(city);

        city = new City();
        city.setName("杭州");
        city.setDescription("杭州市，简称杭，浙江省省会，位于中国东南沿海、浙江省北部、钱塘江下游、京杭大运河南端，是浙江省的政治、经济、文化和金融中心，中国七大古都之一，中国重要的电子商务中心之一。");
        city.setPhoto(getImageUrl("hanzhou.jpg"));
        cityList.add(city);

        city = new City();
        city.setName("芒市");
        city.setDescription("芒市，傣语为“黎明之城”，位于云南省西南部，是中华人民共和国云南省德宏傣族景颇族自治州州府所在地，全州的政治、经济、文化中心，是一个典型的以傣族、景颇族、德昂族、阿昌族、傈僳族为主的少数民族边境县市。芒市与缅甸毗邻，国境线长68.23公里，是中国通往东南亚、南亚和西亚的重要门户之一。总面积2987平方公里，截至2012年，总人口38万。市政府驻勐焕街道。");
        city.setPhoto(getImageUrl("mangshi.jpg"));
        cityList.add(city);
    }

    /**
     * json数组
     */
    @Test
    public void run() {
        String json = JSONObject.toJSONString(cityList);
        System.out.println(json);
    }

    /**
     * 分页的json
     */


    private String getImageUrl(String arg) {

        return arg;
    }

    public static class City {
        private String name;

        private String description;

        private String photo;

        public City() {
        }

        public City(String name, String description, String photo) {
            this.name = name;
            this.description = description;
            this.photo = photo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        @Override
        public String toString() {
            return "City{" +
                    "name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    ", photo='" + photo + '\'' +
                    '}';
        }
    }
}
