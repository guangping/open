package com.varela.utils;

import org.apache.regexp.RE;
import org.apache.regexp.RECompiler;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: guangping
 * Date: 2014-05-20 22:16
 * To change this template use File | Settings | File Templates.
 * <p/>
 * 正则表达式
 */
public class RegExpUtils {
     /* 非负数："^([1-9]\d*|\d+\.\d+)$"
    只能输入数字："^[0-9]*$"。
    只能输入n位的数字："^\d{n}$"。
    只能输入至少n位的数字："^\d{n,}$"。
    只能输入m~n位的数字：。"^\d{m,n}$"
    只能输入零和非零开头的数字："^(0|[1-9][0-9]*)$"。
    只能输入有两位小数的正实数："^[0-9]+(.[0-9]{2})?$"。
    只能输入有1~3位小数的正实数："^[0-9]+(.[0-9]{1,3})?$"。
    只能输入非零的正整数："^\+?[1-9][0-9]*$"。
    只能输入非零的负整数："^\-[1-9][]0-9"*$。
    只能输入长度为3的字符："^.{3}$"。
    只能输入由26个英文字母组成的字符串："^[A-Za-z]+$"。
    只能输入由26个大写英文字母组成的字符串："^[A-Z]+$"。
    只能输入由26个小写英文字母组成的字符串："^[a-z]+$"。
    只能输入由数字和26个英文字母组成的字符串："^[A-Za-z0-9]+$"。
    只能输入由数字、26个英文字母或者下划线组成的字符串："^\w+$"。
    验证用户密码："^[a-zA-Z]\w{5,17}$"正确格式为：以字母开头，长度在6~18之间，只能包含字符、数字和下划线。
    验证是否含有^%&',;=?$\"等字符："[^%&',;=?$\x22]+"。
    只能输入汉字："^[\u4e00-\u9fa5]{0,}$"
    验证Email地址："^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$"。
    验证InternetURL："^http://%28[/\w-]+\.)+[\w-]+(/[\w-./?%&=]*)?$"。
    验证电话号码："^(\(\d{3,4}-)|\d{3.4}-)?\d{7,8}$"正确格式为："XXX-XXXXXXX"、"XXXX-XXXXXXXX"、"XXX-XXXXXXX"、"XXX-XXXXXXXX"、"XXXXXXX"和"XXXXXXXX"。
    验证身份证号（15位或18位数字）："^\d{15}|\d{18}$"。
    验证一年的12个月："^(0?[1-9]|1[0-2])$"正确格式为："01"～"09"和"1"～"12"。
    验证一个月的31天："^((0?[1-9])|((1|2)[0-9])|30|31)$"正确格式为；"01"～"09"和"1"～"31"。*/

    /*
    * 非负数
    * */
    public static final String NO_NG_NUM = "^([1-9]\\d*|\\d+\\.\\d+)$";

    /*
    * 非负数 含两位小数
    * */
    public static final String NO_NG_NUM_2_DECIMAL = "(([1-9][\\d]*)(\\.[\\d]{1,2})?)|(0\\.[\\d]{1,2})";

    /*
    * 数字
    * */
    public static final String NUM = "^[0-9]*$";

    /*
    * 0或大于零的正整数
    * */
    public static final String ZERO_NUM = "^(0|[1-9]\\d*)$";

    /*
    * 正整数
    * */
    public static final String UP_ZERO_NUM = "^\\+?[1-9][0-9]*$";

    /*
    * 负整数
    * */
    public static final String DOWN_ZERO_NUM = "^\\-[1-9][]0-9*$";

    /*
    * email
    * */
    public static final String EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    /*
    * internet url
    * */
    public static final String INTERNET_URL = "^http://%28[/\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$";

    /*
    * 中文
    * */
    public static final String CHINESE_CHARACTER = "^[\u4e00-\u9fa5]{0,}$";

    /*
    * 匹配由26个英文字母组成的字符串
    * */
    public static final String ENGLISH_CHARACTER = "^[A-Za-z]+$";

    /*
    * 由数字和26个英文字母组成的字符串
    * */
    public static final String ENGLISH_NUM_CHARACTER = "^[A-Za-z]+$";

    /*
   * 日期正则
   * */
    public static final String DATE_PATTERN = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";

    /*
    *邮箱正则
    * */
    public static final String EMAIL_PATTERN = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";

    /*
    * 手机正则
    * */
    public static final String PHONE_PATTERN = "^(13[0-9]|14[0-9]|15[0-9]|18[0-9]|17[0-9])\\d{8}$";

    /*
    * 密码格式规则
    * */
    public static final String PASSWD = "^[\\u4E00-\\u9FA5\\uf900-\\ufa2d\\w\\.\\?\\_\\s]{6,18}$";


    /*
    *正则表达式验证
    * */
    public static boolean compileJDK(String exp, String str) {
        Pattern pat = Pattern.compile(exp);
        Matcher mat = pat.matcher(str);
        return mat.find();
    }

    public static List<String> compileJDKValues(String exp, String str) {
        Pattern pat = Pattern.compile(exp);
        Matcher matcher = pat.matcher(str);
        List<String> list = new ArrayList<String>();
        while (matcher.find()) {//注意 ，需要有这个判断条件，之前没有用这个条件就直接group（）老是获取不到
            list.add(matcher.group());
        }
        return list;
    }

    /*
    *正则表达式验证 jakarta-regexp
    * */
    public static boolean compileJAk(String exp, String str) {
        RE re = new RE(); // 新建正则表达式对象;
        RECompiler compiler = new RECompiler(); // 新建编译对象;
        re.setProgram(compiler.compile(exp)); // 编译
        return re.match(str);
    }

    /*
    *符合正则表达式验证的值
    * */
    public static List<String> compileJAkValues(String exp, String str) {
        RE re = new RE(); // 新建正则表达式对象;
        RECompiler compiler = new RECompiler(); // 新建编译对象;
        re.setProgram(compiler.compile(exp)); // 编译

        List<String> list = new ArrayList<String>();
        if (re.match(str)) {
            for (int i = 0; i < re.getParenCount(); i++) {
                list.add(re.getParen(i));
            }
        }
        return list;
    }
}
