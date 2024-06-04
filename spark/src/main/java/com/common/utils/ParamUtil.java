package com.common.utils;

import com.common.constants.IntConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.*;
import java.util.Map.Entry;

/**
 * 请求参数工具类
 *
 * @author uke
 *
 */
@SuppressWarnings({ "rawtypes", "PMD.LowerCamelCaseVariableNamingRule", "PMD.CollectionInitShouldAssignCapacityRule" })
public class ParamUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParamUtil.class);

    /**
     * <li>判断字符串是否为空</li>
     * <li>NULL,空格字符返回真</li>
     *
     * @param value
     * @return
     */
    public static boolean isEmpty(final String value) {

        return null == value || "".equals(value.trim());
    }

    /**
     * <li>用于处理JS参数解码,配合WEB页面二次转码</li>
     * <li>JS：params="&name="+encodeURI(encodeURI("姓名"));</li>
     * <li>JAVA：ParamUtil.decode(request,"name")=="姓名"</li>
     *
     * @param request
     * @param param
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String decode(final HttpServletRequest request, final String param)
            throws UnsupportedEncodingException {

        final String value = request.getParameter(param);
        return java.net.URLDecoder.decode(value, getUTF8String());
    }

    public static String getUTF8String() {

        return "UTF-8";
    }

    /**
     * <li>获取Request中的参数，并且去除NULL</li>
     *
     * @param request
     * @param param
     * @return
     */
    public static String getChn(final HttpServletRequest request, final String param) {

        return getChn(request, param, "");
    }

    /**
     * ISO中文
     *
     * @param request
     * @param param
     * @return
     */
    public static String getIsoChn(final HttpServletRequest request, final String param) {

        final String value = request.getParameter(param);
        try {
            return new String(value.getBytes("iso-8859-1"), getUTF8String());
        } catch (final UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
            return value;
        }
    }

    /**
     * <li>获取Request中的参数，并且去除NULL,以指定内容取代</li>
     *
     * @param request
     * @param param
     * @param defValue
     * @return
     */
    public static String getChn(final HttpServletRequest request, final String param, final String defValue) {

        String value = null;
        try {
            value = decode(request, param);
        } catch (final Exception e) {
            LOGGER.error(e.getMessage(), e);
            value = request.getParameter(param);
        }
        return null == value ? defValue : value;
    }

    /**
     * <li>获取Request中的参数，并且去除NULL</li>
     *
     * @param request
     * @param param
     * @return
     */
    public static String get(final HttpServletRequest request, final String param) {

        return get(request, param, "");
    }

    /**
     * <li>获取Request中的参数，并且去除NULL,以指定内容取代</li>
     *
     * @param request
     * @param param
     * @param defValue
     * @return
     */
    public static String get(final HttpServletRequest request, final String param, final String defValue) {

        final String value = request.getParameter(param);
        return null == value ? defValue : value.trim();
    }

    /**
     * 整型值
     *
     * @param request
     * @param param
     * @param defValue
     * @return
     */
    public static int getInt(final HttpServletRequest request, final String param, final int defValue) {

        final String value = request.getParameter(param);
        if (!isEmpty(value)) {
            try {
                return Integer.parseInt(value);
            } catch (final Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return defValue;
    }

    /**
     * 布尔值
     *
     * @param request
     * @param param
     * @return
     */
    public static boolean getBoolean(final HttpServletRequest request, final String param) {

        return "true".equals(get(request, param));
    }

    /**
     * 金额类型
     *
     * @param request
     * @param param
     * @return
     */
    public static Double getDouble(final HttpServletRequest request, final String param) {

        try {
            final String value = get(request, param, get0String()).replaceAll(getdString(), "");
            return new Double(value);
        } catch (final Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    public static String get0String() {

        return "0";
    }

    public static String getdString() {

        return ",";
    }

    /**
     * 日期类型
     *
     * @param request
     * @param param
     * @return
     */
    public static Date getDate(final HttpServletRequest request, final String param) {

        return DateUtil.parseDate(get(request, param));
    }

    /**
     * 日期类型字符串yyyyMMdd
     *
     * @param request
     * @param param
     * @return
     */
    public static String getDateStr(final HttpServletRequest request, final String param) {

        return get(request, param).replaceAll("-|\\/", "");
    }

    /**
     * 向从Request中获取字符串值到Map中,包含空值
     *
     * @param request
     * @param param
     * @param paramMap
     */
    public static void putStr2Map(final HttpServletRequest request, final String param, final Map paramMap) {

        final String val = get(request, param);
        if (StringUtils.isNotEmpty(val)) {
            paramMap.put(param, val);
        }
    }

    /**
     * 向从Request中获取整型值到Map中，不包含空值
     *
     * @param request
     * @param param
     * @param paramMap
     */
    public static void putInt2Map(final HttpServletRequest request, final String param, final Map paramMap) {

        paramMap.put(param, Integer.valueOf(getInt(request, param, 0)));
    }

    /**
     * 向从Request中获取浮点值到Map中，不包含空值
     *
     * @param request
     * @param param
     * @param paramMap
     */
    public static void putDouble2Map(final HttpServletRequest request, final String param, final Map paramMap) {

        paramMap.put(param, getDouble(request, param));
    }

    /**
     * 向从Request中将日期值yyyy-MM-dd转成yyyyMMdd到Map中，不包含空值
     *
     * @param request
     * @param param
     * @param paramMap
     */
    public static void putDateStr2Map(final HttpServletRequest request, final String param, final Map paramMap) {

        final String value = getDateStr(request, param);
        if (!isEmpty(value)) {
            paramMap.put(param, value);
        }
    }

    private static String[] forbids = { "action" };

    private static boolean checkParams(final String name) {

        for (int i = 0; i < forbids.length; i++) {
            if (forbids[i].equals(name)) {
                return false;
            }
        }
        return true;
    }

    public static String generyHiddenInput(final String name, final String value) {

        String html = "<input type='hidden' name='0' value=\"1\">";
        html = html.replaceFirst(get0String(), name);
        html = html.replaceFirst("1", value);
        return html;
    }

    /**
     * 转换参数
     *
     * @param request
     * @param prefix
     * @param forbid
     * @return
     */
    public static String fixParamToHtml(final HttpServletRequest request, final String prefix, final boolean forbid) {

        final StringBuffer bf = new StringBuffer();
        final Enumeration names = request.getParameterNames();
        while (names.hasMoreElements()) {
            final String name = (String) names.nextElement();
            final String value = get(request, name);
            if (!isEmpty(value)) {
                if (forbid) {
                    if (!checkParams(name)) {
                        continue;
                    }
                }
                bf.append(generyHiddenInput(prefix + name, value));
            }
        }
        return bf.toString();
    }

    /**
     * 转换参数
     *
     * @param request
     * @param prefix
     * @return
     */
    public static String fixParamToHtml(final HttpServletRequest request, final String prefix) {

        return fixParamToHtml(request, prefix, true);
    }


    /**
     * 将指定前缀的参数的前缀去除，并生成隐含HTML对象
     *
     * @param request
     * @param prefix
     * @return
     */
    public static String decodeParamToHtml(final HttpServletRequest request, final String prefix) {

        final StringBuffer bf = new StringBuffer();
        final Enumeration names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            if (name.startsWith(prefix) && !"_backUrl".equals(name)) {
                final String value = get(request, name);
                name = name.substring(prefix.length());
                if (!isEmpty(value)) {
                    bf.append(generyHiddenInput(name, value));
                }
            }
        }
        return bf.toString();
    }

    /**
     * 将装饰过的HttpServletRequest参数分离出来，存储到MAP中
     * <li>request: _name ==> map:_name</li>
     *
     * @param request
     * @param prefix
     * @return
     */
    public static Map enfixParam(final HttpServletRequest request, final String prefix) {

        final Enumeration names = request.getParameterNames();
        final Map params = new HashMap();
        while (names.hasMoreElements()) {
            final String name = (String) names.nextElement();
            if (name.startsWith(prefix)) {
                final String value = get(request, name);
                if (!isEmpty(value)) {
                    params.put(name, value);
                }
            }
        }
        return params;
    }

    /**
     * <li>还原被装饰过的HttpServletRequest参数</li>
     * <li>去除装饰，并将还原后的参数存储到MAP中</li>
     * <li>request: _name ==> map:name</li>
     *
     * @param request
     * @param prefix
     * @return
     */
    public static Map defixParam(final HttpServletRequest request, final String prefix) {

        final Enumeration names = request.getParameterNames();
        final Map params = new HashMap();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            if (name.startsWith(prefix)) {
                final String value = get(request, name);
                name = name.substring(prefix.length());
                if (!isEmpty(value)) {
                    params.put(name, value);
                }
            }
        }
        return params;
    }

    /**
     *
     * @param request
     */
    public static String getRequestUrl(final HttpServletRequest request) {

        final String url = request.getRequestURL().toString();
        final String ctx = request.getContextPath();
        if (ctx != null && !"".equals(ctx)) {
            final int index = url.indexOf(ctx);
            return url.substring(index + ctx.length() + 1);
        }
        return url;
    }

    /**
     *
     * @param request
     */
    public static String getRequestUrlWithMethod(final HttpServletRequest request) {

        final String requestUrl = getRequestUrl(request);
        return requestUrl + ":" + request.getMethod();
    }

    /**
     * 解析请求，并将参数值对以key:value字符串保存
     *
     * @param request
     */
    public static String favorit(final HttpServletRequest request) {

        final StringBuffer bf = new StringBuffer();
        bf.append("URL:'").append(getRequestUrlWithMethod(request)).append("'");
        final Set<Entry<String, String[]>> paramEntrySet = request.getParameterMap().entrySet();
        for (final Entry<String, String[]> entry : paramEntrySet) {
            final String name = entry.getKey();
            final String[] value = entry.getValue();
            if (value != null && value.length > 0) {
                bf.append(getdString()).append(name).append(":\"").append(value[0]).append("\"");
            }
        }
        return bf.toString();
    }

    /**
     * 解析请求，并将参数值对以key:value字符串保存
     *
     * @param request
     */
    public static String favorit(final HttpServletRequest request, final String url) {

        final StringBuffer bf = new StringBuffer();
        bf.append("IP:'").append(getIpAddr(request)).append(" ");
        bf.append("URL:'").append(url).append("'");
        final Set<Entry<String, String[]>> paramEntrySet = request.getParameterMap().entrySet();
        for (final Entry<String, String[]> entry : paramEntrySet) {
            final String name = entry.getKey();
            final String[] value = entry.getValue();
            if (value != null && value.length > 0) {
                bf.append(getdString()).append(name).append(":\"").append(value[0]).append("\"");
            }
        }
        return bf.toString();
    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址, 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值
     *
     * @return ip
     */
    public static String getIpAddr(final HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !getUnknownString().equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.indexOf(getdString()) != -1) {
                ip = ip.split(getdString())[0];
            }
        }
        if (ip == null || ip.length() == 0 || getUnknownString().equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        ip = getIpAddr1(ip, request);
        return ip;
    }

    public static String getIpAddr1(String ip, final HttpServletRequest request) {

        if (ip == null || ip.length() == 0 || getUnknownString().equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || getUnknownString().equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || getUnknownString().equalsIgnoreCase(ip)) {
            ip = getIpAddr2(ip, request);
        }
        return ip;
    }

    public static String getIpAddr2(String ip, final HttpServletRequest request) {

        if (ip == null || ip.length() == 0 || getUnknownString().equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || getUnknownString().equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || getUnknownString().equalsIgnoreCase(ip)) {
            ip = getIpAddr3(ip, request);
        }
        return ip;
    }

    public static String getIpAddr3(String ip, final HttpServletRequest request) {

        if (ip == null || ip.length() == 0 || getUnknownString().equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getUnknownString() {

        return "unknown";
    }


    public static String getLocalMac() {

        final StringBuffer sb = new StringBuffer();
        try {
            final InetAddress ia = InetAddress.getLocalHost();
            //获取网卡，获取地址
            final byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
            for (int i = 0; i < mac.length; i++) {
                if (i != 0) {
                    sb.append("-");
                }
                //字节转换为整数
                final int temp = mac[i] & 0xff;
                final String str = Integer.toHexString(temp);
                if (str.length() == IntConstants.N1) {
                    sb.append("0");
                    sb.append(str);
                } else {
                    sb.append(str);
                }
            }
        } catch (final Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return sb.toString().toUpperCase();
    }

}
