/**
 * 鐗堟潈 (c) 2010 娣卞湷甯備箙涔?
 * 淇濈暀鎵?湁鏉冨埄銆?
 */
package com.hdos.customerimagecycleview.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 描述：
 *
 * @创建时间：Jan 21, 2010
 */
public class StringUtils {
    /**
     * 返回length位的字符串
     *
     * @param src    后缀字符串
     * @param length 总字符串长度
     * @param preStr 前缀字符串
     * @return
     */
    public static String preStrToLength(String src, int length, String preStr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length - src.length(); i++) {
            sb.append(preStr);
        }
        sb.append(src);
        return sb.toString();
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 替换手机号
     *
     * @param mobile
     * @return
     */
    public static String replaceMobile(String mobile) {

        return mobile.substring(0, 3) + "****" + mobile.substring(7, 11);
    }


    /**
     * 替换银行卡姓名
     *
     * @param bankName
     * @return
     */
    public static String replaceBankName(String bankName) {
        String buff = bankName;
        if (!StringUtils.isEmpty(bankName) && bankName.length() > 0) {
            if (bankName.length() == 3) {
                buff = "**" + bankName.substring(bankName.length() - 1, bankName.length());
            } else if (bankName.length() == 2) {
                buff = "*" + bankName.substring(bankName.length() - 1, bankName.length());
            } else if (bankName.length() == 4) {
                buff = "***" + bankName.substring(bankName.length() - 1, bankName.length());
            }

        }
        return buff;
    }

    public static String replaceCard(String card) {
        String buff = card;
        if (!StringUtils.isEmpty(card) && card.length() > 4) {
            buff = card.substring(0, 4) + " ****" + " **** " + card.substring(card.length() - 4, card.length());
        }
        return buff;
    }

    public static String replacePhone(String phone) {
        String buff = phone;
        if (!StringUtils.isEmpty(phone) && phone.length() > 4) {
            buff = phone.substring(0, 4) + "****" + phone.substring(phone.length() - 4, phone.length());
        }
        return buff;
    }

    /**
     * 替换银行卡号
     *
     * @param bankNum
     * @return
     */
    public static String replaceBankNum(String bankNum) {
        String buff = bankNum;
        if (!StringUtils.isEmpty(bankNum) && bankNum.length() > 4) {
            buff = bankNum.substring(0, 4) + " ****" + " **** " + bankNum.substring(bankNum.length() - 4, bankNum.length());
        }
        return buff;
    }

    public static String getAppendStr(String id, int length, char appendChar) {
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < length - id.length(); i++) {
            str.append(appendChar);
        }
        str.append(id);
        return str.toString();
    }

    public static String toUTF8FromISO8859(String srcStr) {
        if (StringUtils.isEmpty(srcStr)) {
            return srcStr;
        }

        try {
            return new String(srcStr.getBytes("ISO-8859-1"), "UTF-8");
        } catch (Exception e) {
            Log.i("转码失败[" + srcStr + "]", e.getMessage());
            return null;
        }

    }

    /**
     * 检查字符串是否为空
     */
    public static boolean isEmpty(String s) {
        return (s == null || "".equals(s.trim()) || "null".equals(s.trim()));
    }

    /**
     * 检查集合是否为空
     */
    public static boolean isEmpty(Collection c) {
        if (c == null)
            return true;
        else {
            return c.isEmpty();
        }
    }

    /**
     * 检查字符串是否不为空
     */
    public static boolean isNotEmpty(String s) {
        return ((s != null) && (!"".equals(s.trim())) && (!"null".equals(s
                .trim())));
    }

    /**
     * 检查集合是否不为空
     */
    public static boolean isNotEmpty(Collection c) {
        return c != null && !c.isEmpty();
    }

    public static int occurTimes(String string, String c) {
        int pos = -2;
        int n = 0;

        while (pos != -1) {
            if (pos == -2) {
                pos = -1;
            }
            pos = string.indexOf(c, pos + 1);
            if (pos != -1) {
                n++;
            }
        }
        return n;
    }

    /**
     * 返回    ("XXX","yyy")
     **/
    public static String getDbInParamStr(String[] list) {
        StringBuffer returnSb = new StringBuffer();
        for (int i = 0; i < list.length; i++) {
            String v = list[i];
            if (StringUtils.isNotEmpty(v)) {
                if (i > 0) {
                    if (StringUtils.isNotEmpty(v))
                        returnSb.append(",");
                }
                returnSb.append("'" + v + "'");
            }
        }
        return "(" + returnSb.toString() + ")";
    }


	/*public static String getDbTime(){
        return cn.toruk.pub.Tools.getSysTimeFormat("yyyy-MM-dd HH:mm:ss");
	}*/


    /**
     * 功能：判断字符串是否是数值. 默认允许有正负号,默认允许有小数点
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        boolean sign = true;
        int point_bef = Integer.MAX_VALUE;// 小数点前有几位
        int point_aft = Integer.MAX_VALUE;// 小数点后有几位
        return isNumeric(str, sign, point_bef, point_aft);
    }

    /**
     * 功能：判断字符串是否是数值
     *
     * @param str
     * @param sign  是否允许有正负号
     * @param point 是否允许有小数点
     * @return
     */
    public static boolean isNumeric(String str, boolean sign, boolean point) {
        int point_bef = Integer.MAX_VALUE;// 小数点前有几位
        int point_aft = Integer.MAX_VALUE;// 小数点后有几位
        if (!point)
            point_aft = 0;

        return isNumeric(str, sign, point_bef, point_aft);
    }

    /**
     * 功能：判断字符串是否是数值
     *
     * @param str
     * @param sign      是否允许有正负号
     * @param point_bef 精度,小数点前有几位
     * @param point_aft 精度,小数点后有几位,如果为0,则为整数
     * @return
     */
    public static boolean isNumeric(String str, boolean sign, int point_bef,
                                    int point_aft) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        boolean point = true;// 是否允许小数点
        if (point_aft == 0) {
            point = false;// 不允许有小数点
        } else {
            point = true;
        }
        StringBuffer pat = new StringBuffer();
        if (sign) {
            pat.append("[+|-]?");
        }
        if (point_bef == 0) {
            pat.append("[0]");
        } else {
            pat.append("[0-9]{1,");
            pat.append(point_bef);
            pat.append("}");
        }
        if (point && str.indexOf(".") != -1) {// 允许小数点,并且有小数点
            pat.append("[.]");
            pat.append("[0-9]{1,");// 小数点后必须有一位
            pat.append(point_aft);
            pat.append("}");
        }
        Pattern pattern = Pattern.compile(pat.toString());
        if (!pattern.matcher(str).matches()) {
            return false;
        } else {// 排除如00.1,返回false
            if (str.indexOf(".") != -1
                    && str.substring(0, str.indexOf(".")).length() > 1
                    && Integer.valueOf(str.substring(0, str.indexOf("."))) == 0) {
                return false;
            } else {
                return true;
            }
        }
    }

    /**
     * 功能：查看字符串是否有这个子字符串
     *
     * @param str    主字符串
     * @param substr 字字符串
     * @return
     */
    public static boolean hasSubstring(String str, String substr) {
        if (str == null || substr == null)
            return false;
        int strLen = str.length();
        int substrLen = substr.length();
        for (int i = 0; (i + substrLen) <= strLen; i++) {
            if (str.substring(i, i + substrLen).equalsIgnoreCase(substr)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 功能：验证是否是正确的手机号
     *
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile) {
        if (StringUtils.isEmpty(mobile))
            return false;
        return Pattern.matches("^(1)\\d{10}$", mobile);
    }


    public static boolean isRealName(String name) {
        if (StringUtils.isEmpty(name))
            return false;
        return Pattern.matches("^([a-zA-Z0-9\\u4e00-\\u9fa5\\·]{2,8})$", name);
    }

    /**
     * 判断推荐码
     *
     * @param code
     * @return
     */
    public static boolean isReferralCode(String code) {
        if (StringUtils.isEmpty(code))
            return false;
        return Pattern.matches("^([0-9]{4,6})$", code);

    }

    /**
     * 功能：字符串不以"/"结尾，则在串尾加"/"
     *
     * @param s
     * @return
     */
    public static String addSlashInEnd(String s) {
        if (s != null) {
            s = s.trim();
            if (!s.endsWith("/")) {
                s = s + "/";
            }
        } else {
            s = "";
        }
        return s;
    }

    /**
     * 功能：字符串如果以/开头,则去掉第一个/
     *
     * @param s
     * @return
     */
    public static String delSlashInEnd(String s) {
        if (s != null) {
            s = s.trim();
            if (s.startsWith("/")) {
                s = s.substring(1, s.length());
            }
        } else {
            s = "";
        }
        return s;
    }

    /**
     * 功能：字符串不以"/"结尾，则在串尾加"/";字符串如果以/开头,则去掉第一个/
     *
     * @return
     */
    public static String dealSlash(String s) {
        if (s != null) {
            s = s.trim();
            if (!s.endsWith("/")) {
                s = s + "/";
            }
            if (s.startsWith("/")) {
                s = s.substring(1, s.length());
            }
        } else {
            s = "";
        }
        return s;

    }

    /**
     * 功能：传入一个数字类型的参数，返回一个小数点后两位的小数
     *
     * @param parm
     */
    public static String converDouble(String parm) {
        if (isNumeric(parm, false, true)) {
            if (parm.indexOf(".") >= 0) {
                String value = parm.substring(parm.indexOf(".") + 1);
                if (value.length() == 1) {
                    return parm + "0";
                } else if (value.length() > 2) {
                    return parm.substring(0, parm.indexOf(".") + 1)
                            + value.substring(0, 2);
                } else {
                    return parm;
                }
            } else {
                return parm + ".00";
            }
        }
        return null;
    }

    /**
     *
     * 功能：将List<DataArea>的字符转换回为List对象<br>
     * 注意:DataArea中的Key不能有"=",如果出现"="将会当做是Value的值内容
     *
     * @param str
     *            List<DataArea>的字符串<br>
     *            格式:[{a=a, b=b, c=c},{d=d, e=e}];<br>
     *            以"[{"开头"}]"结尾; 对象间隔为"},{",或者"}, {"; 元素间隔为", "
     * @return
     */

	/*public static List<DataMap> stringToList(String str) {
        List<DataMap> list = new ArrayList<DataMap>();
		if (isBlank(str)) {
			return list;
		}
		str = str.trim();
		try {
			if (str.substring(0, 2).equals("[{")
					&& str.substring(str.length() - 2, str.length()).equals(
							"}]")) {
				// 是以[{开头 }]结尾
				str = str.substring(2, str.length() - 2);// 掐头去尾
			} else {
				return list;
			}
			String[] element = str.split("}");
			for (String ele : element) {
				DataMap data = new DataMap();
				if (",{".equals(ele.substring(0, 2))) {
					ele = ele.substring(2);
				} else if (", {".equals(ele.substring(0, 3))) {
					ele = ele.substring(3);
				}
				data = getData(ele);
				list.add(data);
			}
			return list;
		} catch (Exception e) {
			return list;
		}
	}

	public static DataMap getData(String str) {
		DataMap data = new DataMap();
		while (str.lastIndexOf("=") != -1) {
			String val = str.substring(str.lastIndexOf("=") + 1);
			str = str.substring(0, str.lastIndexOf("="));
			String obj = null;
			if (str.lastIndexOf(", ") != -1) {
				obj = str.substring(str.lastIndexOf(", ") + 2);
				str = str.substring(0, str.lastIndexOf(", "));
			} else {
				obj = str;
			}
			if ("null".equals(val)) {
				val = null;
			}
			data.add(obj, val);
		}
		return data;
	}*/

    /**
     * @param urlstr
     * @return
     * @Description：将请求url路径返回字符串
     */
    public static String urlToString(String urlstr) {
        try {
            URL url = new URL(urlstr);
            URLConnection connection = url.openConnection();
            byte[] btArr = null;
            if (connection != null && connection.getInputStream() != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int b = 0;
                b = connection.getInputStream().read();
                while (b != -1) {
                    baos.write(b);
                    b = connection.getInputStream().read();
                }
                btArr = baos.toByteArray();
            }
            if (btArr != null) {
                String ret = new String(btArr, "UTF-8");
                return ret;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param
     * @return
     * @Description：字符数组转 字符串 “，”隔开 {"SDF","345"} == "SDF,345"
     */
    public static String arryTOString(String[] data) {
        String returnData = "";
        if (null != data && data.length > 0) {
            for (String temp : data) {
                returnData = temp + "," + returnData;
            }
            returnData = returnData.substring(0, returnData.length() - 1);
        }
        return returnData;
    }

    public static String arryTOString(List<String> data) {
        if (data == null) {
            return "";
        }
        return arryTOString(data.toArray(new String[data.size()]));
    }

    /**
     * @param
     * @return
     * @Description：取文件名的后缀
     */
    public static String getFileSuffix(String file_name) {
        String returnData = "";
        if (!StringUtils.isEmpty(file_name)) {
            int len = file_name.lastIndexOf(".");
            if (len > 0) {
                returnData = file_name.substring(len + 1, file_name.length());
            }
        }
        return returnData;
    }

    /**
     * @param userId
     * @return
     * @Description：验证用户名是否合法,数字或字母或下划线
     */
    public static boolean validateUserId(String userId) {
        if (userId == null) {
            return false;
        }
        String regex = "^[a-zA-Z0-9_]{1,32}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(userId);
        return m.matches();
    }

    /**
     * 功能：验证用户预付费卡
     * /^\d{16}$/g
     *
     * @param userPreCard
     * @return
     */
    public static boolean isUserPreCard(String userPreCard) {
        if (StringUtils.isEmpty(userPreCard))
            return false;
        return Pattern.matches("^\\d{16}$", userPreCard);
    }

    // 返还网 ('1','2')
    public static String getInParamStr(List<String> list) {
        StringBuffer returnSb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            String v = list.get(i);
            if (StringUtils.isEmpty(v)) {
                if (i > 0) {
                    returnSb.append(",");
                }
                returnSb.append("'" + v + "'");
            }
        }
        return "(" + returnSb.toString() + ")";
    }

    public static String getInParamStr(String[] liststr) {
        List<String> list = Arrays.asList(liststr);
        return getInParamStr(list);
    }

    public static String subStr(String str, int length) {
        if (str == null) {
            return "";
        }
        if (str.length() <= length) {
            return str;
        } else {
            return str.substring(0, length);
        }
    }


    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否为乱码
     **/
    public static boolean isMessyCode(String strName) {
        if (strName == null) {
            return false;
        }
        Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
        Matcher m = p.matcher(strName);
        String after = m.replaceAll("");
        String temp = after.replaceAll("\\p{P}", "");
        char[] ch = temp.trim().toCharArray();
        float chLength = ch.length;
        float count = 0;
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (!Character.isLetterOrDigit(c)) {
                if (!isChinese(c)) {
                    count = count + 1;
                }
            }
        }
        float result = count / chLength;
        if (result > 0.4) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * inputStream转换成String字符串
     *
     * @param is
     * @return
     */
    public static String inputStream2String(InputStream is) {
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        try {
            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    /**
     * 判断是否是密码
     *
     * @param pwd
     * @return
     */
    public static Boolean isPassword(String pwd) {
        if (StringUtils.isEmpty(pwd))
            return false;
//        if (pwd.length() >= 6 && pwd.length() <= 20) {
//            return true;
//        } else {
//            return false;
//        }
        return Pattern.matches("^[a-zA-Z0-9]{6,16}$",pwd);
        //return Pattern.matches("^[a-zA-Z\\\\d\\\\.@]{6,20}$", pwd);
    }


    public static Boolean isTwoDecimal(String number) {
        Boolean flag = false;
        if (number.contains(".")) {
            String[] tempS = number.split("\\.");
            String tempC = tempS[1];
            int resultNum = tempC.length();
            if (resultNum > 2) {
                flag = true;
                return flag;
            }
        }
        return flag;
    }


    /**
     * 构造数据包协议 length 2 ;data ;mac 8
     *
     * @param out
     * @param packageMsg
     * @throws IOException
     */
    public static void genProtocol(DataOutputStream out, String packageMsg) throws IOException {
        byte[] bytes = packageMsg.getBytes();  //消息内容
        short totalLen = (short) (8 + bytes.length);   //数据包长度

        out.write(short2Byte(totalLen));              //写入消息类型
        out.write(bytes);                      //写入消息内容
//        out.write();

        out.flush();
    }

    // 短整型转化为字节类型，低位在前
    public static byte[] short2Byte(short number) {
        int temp = number;
        byte[] b = new byte[2];
        b[1] = Integer.valueOf(temp & 0xff).byteValue();// 将最低位保存在高位
        System.out.println(b[1]);
        temp = temp >> 8; // 向右移8位
        b[0] = Integer.valueOf(temp & 0xff).byteValue();
        System.out.println(b[0]);
        return b;
    }

    public static void main(String arg[]) {
        short a = 12;
        short2Byte(a);

    }

}
