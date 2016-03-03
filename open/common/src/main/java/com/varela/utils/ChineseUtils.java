package com.varela.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * Created by 51offer on 2016/2/24.
 * <p/>
 * 中文辅助类
 */
public class ChineseUtils {

    /**
     * 获取长度
     */
    public static int getLength(String str) {
        if (StringUtils.isNotBlank(str)) {
            int length = 0;
            for (int i = 0; i < str.length(); i++) {
                int ascii = Character.codePointAt(str, i);
                if (ascii >= 0 && ascii <= 255)
                    length++;
                else
                    length += 2;

            }
            return length;
        }
        return 0;
    }

    /**
     * 获取长度
     */
    public static int getLengthRegex(String s) {
        s = s.replaceAll("[^\\x00-\\xff]", "**");
        int length = s.length();
        return length;
    }

    /**
     * 截取字符串
     */
    public static String getSubString(String str, int length) {
        int count = 0;
        int offset = 0;
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] > 256) {
                offset = 2;
                count += 2;
            } else {
                offset = 1;
                count++;
            }
            if (count == length) {
                return str.substring(0, i + 1);
            }
            if ((count == length + 1 && offset == 2)) {
                return str.substring(0, i);
            }
        }
        return "";
    }

    /*
   * 获取随机汉字
   * */
    public static String getRandomChinese() {
        String str = null;
        int hightPos, lowPos; // 定义高低位
        Random random = new Random();
        hightPos = (176 + Math.abs(random.nextInt(39)));//获取高位值
        lowPos = (161 + Math.abs(random.nextInt(93)));//获取低位值
        byte[] b = new byte[2];
        b[0] = (new Integer(hightPos).byteValue());
        b[1] = (new Integer(lowPos).byteValue());
        try {
            str = new String(b, "GBk");//转成中文
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }


    /*
    * 将所有汉字转换成全拼音输出
    * */
    public static String converToEnameUpper(String name) throws BadHanyuPinyinOutputFormatCombination {
        StringBuilder succeedPinyin = new StringBuilder();
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);//大小写输出
        //如果不想要音调 删除就行
     /*   format.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);//音调设置
        format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);//音调*/
        char[] ar = name.toCharArray();
        for (int i = 0; i < ar.length; i++) {
            String[] a = PinyinHelper.toHanyuPinyinStringArray(ar[i], format);
            if (null != a) {
                succeedPinyin.append(a[0]);
            } else {
                succeedPinyin.append(ar[i]);
            }
        }
        return succeedPinyin.toString();
    }
}
