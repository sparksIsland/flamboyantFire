package com.common.utils;

import com.common.constants.IntConstants;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期格式化
 */

/**
 * @Author: freemarker
 * @Description: DateUtil
 * @Date: 2019-10-18
 * @Version: 1.0
 */
@SuppressWarnings({"PMD.CyclomaticComplexity", "PMD.LowerCamelCaseVariableNamingRule",
        "PMD.AvoidCommentBehindStatementRule"})
@Slf4j
public class DateUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

    public static final String DATE_FORMATTER = getYYYYMMDDString();

    public final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public final SimpleDateFormat YMD = new SimpleDateFormat(getYYYYMMDDString1());

    public static String getYYYYMMDDString() {

        return "yyyyMMdd";
    }

    public static String getYYYYMMDDString1() {

        return "yyyy-MM-dd";
    }

    public static String getYYYYMMDDString2() {

        return "yyyyMMddHHmmss";
    }

    public static String getYYYYMMDDString3() {

        return "yyyy-MM-dd HH:mm:ss";
    }

    public static String get0String() {

        return "0";
    }

    public static String getYYString() {

        return "YYYY";
    }

    public static String getMMString() {

        return "MM";
    }

    public static String getDDString() {

        return "DD";
    }

    public static Date parseDate(final String dateStr, final String format) {

        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        Date date = null;
        try {
            final java.text.DateFormat df = new SimpleDateFormat(format);
            String dt = dateStr;// .replaceAll(getBarString(), "/");
            if ((!"".equals(dt)) && (dt.length() < format.length())) {
                dt += format.substring(dt.length()).replaceAll("[YyMmDdHhSs]", get0String());
            }
            date = df.parse(dt);
        } catch (final Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return date;
    }

    public static Date parseDate(final String dateStr) {

        return parseDate(dateStr, DATE_FORMATTER);
    }

    public static Date parseDate(final String dateStr, final SimpleDateFormat format) {

        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        Date date = null;
        try {
            date = format.parse(dateStr);
        } catch (final Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return date;
    }

    public static String todayStr(final String format) {

        return formatDateToStr(new Date(), format);
    }

    public static Date today(final String format) {

        return parseDate(todayStr(format), format);
    }

    public static String yearStr() {

        return formatDateToStr(new Date(), getYYString());
    }

    /**
     * 获取年度后2位
     *
     * @return
     */
    public static String yearLast2Str() {

        return yearStr().substring(2, 4);
    }

    public static String todayStr() {

        return formatDateToStr(new Date(), DATE_FORMATTER);
    }

    public static String todayfulldata() {

        final SimpleDateFormat dateformat1 = new SimpleDateFormat(getYYYYMMDDString2());
        final String a1 = dateformat1.format(new Date());
        return a1;
    }

    public static Date today() {

        return parseDate(todayStr(), DATE_FORMATTER);
    }

    /**
     * @param date
     *            需要格式化的日期對像
     * @param formatter
     *            格式化的字符串形式
     * @return 按照formatter指定的格式的日期字符串
     */
    public static String formatDateToStr(final Date date, final String formatter) {

        if (date == null) {
            return "";
        }
        try {
            return new SimpleDateFormat(formatter).format(date);
        } catch (final Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return "";
    }

    /**
     * 生成默认格式的日期
     *
     * @param date
     */
    public static String formatDateToStr(final Date date) {

        return formatDateToStr(date, DATE_FORMATTER);
    }

    /**
     * 將日期按照指定的模式格式化
     *
     * @param date
     *            {@link Date}
     * @param format
     *            如：yyyy/MM/dd
     * @return 返回空表示格式化產生異常
     */
    public static String format(final Date date, final String format) {

        String result = "";
        try {
            if (date != null) {
                final java.text.DateFormat df = new SimpleDateFormat(format);
                result = df.format(date);
            }
        } catch (final Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 将一种字符日期转化成另外一种日期格式
     *
     * @param date
     * @param fmtSrc
     * @param fmtTag
     */
    public static String format(final String date, String fmtSrc, String fmtTag) {

        if (null == fmtSrc) {
            return null;
        }
        if (fmtSrc.equals(fmtTag)) {
            return date;
        }
        String year, month, daty;
        int Y, M, D;
        fmtSrc = fmtSrc.toUpperCase();
        Y = fmtSrc.indexOf(getYYString());
        M = fmtSrc.indexOf(getMMString());
        D = fmtSrc.indexOf(getDDString());
        if (Y < 0 || M < 0 || D < 0) {
            return date;
        }
        year = date.substring(Y, Y + 4);
        month = date.substring(M, M + 2);
        daty = date.substring(D, D + 2);
        fmtTag = fmtTag.toUpperCase();
        fmtTag = fmtTag.replaceAll(getYYString(), year);
        fmtTag = fmtTag.replaceAll(getMMString(), month);
        fmtTag = fmtTag.replaceAll(getDDString(), daty);
        return fmtTag;
    }

    /**
     * 計算指定年月的日期數
     *
     * @param year
     * @param month
     */
    public static int maxDayOfMonth(final int year, final int month) {

        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                final boolean isRunYear = (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
                return isRunYear ? 29 : 28;
            default:
                return 31;
        }
    }

    /**
     * 获取指定年月的日期數
     *
     * @param year
     * @param month
     */
    public static int maxDayOfMonth(final String year, final String month) {

        return maxDayOfMonth(Integer.parseInt(year), Integer.parseInt(month));
    }

    /**
     * 获取指定年月上月月末日期
     *
     * @param year
     * @param month
     */
    public static String getLastMonthDate(final String year, final String month) {

        return getLastMonthDate(Integer.parseInt(year), Integer.parseInt(month));
    }

    /**
     * 获取指定年月上月月末日期
     *
     * @param year
     * @param month
     */
    public static String getLastMonthDate(int year, int month) {

        if (month <= IntConstants.N1) {
            year -= 1;
            month = 12;
        } else {
            month -= 1;
        }
        final StringBuffer bfDate = new StringBuffer();
        bfDate.append(year);
        if (month < IntConstants.N10) {
            bfDate.append(get0String());
        }
        bfDate.append(month);
        bfDate.append(maxDayOfMonth(year, month));
        return bfDate.toString();
    }

    /**
     * 提前N天的日期
     *
     * @param date
     * @param days
     */
    public static Date beforeDate(final Date date, final int days) {

        final Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR, -days);
        return c.getTime();

    }

    /**
     * @param date
     * @param hour
     */
    public static Date addHour(final Date date, final int hour) {

        final Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY, hour);
        return c.getTime();
    }

    /**
     * 一周前的日期
     *
     */
    public static Date getLastWeek() {

        return getNextDay(-7);
    }

    /**
     * 取相对天数，正数为向后，负数为向前
     *
     * @param day
     */
    public static Date getNextDay(final int day) {

        final Calendar c = new GregorianCalendar();
        c.add(Calendar.DATE, day);
        return c.getTime();
    }

    public static int curHour(final Calendar cal) {

        return cal.get(Calendar.HOUR_OF_DAY);
    }

    public static int curMinute(final Calendar cal) {

        return cal.get(Calendar.MINUTE);
    }

    public static int curSecond(final Calendar cal) {

        return cal.get(Calendar.SECOND);
    }

    public static String getmaoString() {

        return ":";
    }

    public static String curTimeStr() {

        final Calendar cal = new GregorianCalendar();
        // 分别取得当前日期的年、月、日
        final StringBuffer bf = new StringBuffer(10);
        final int hour = cal.get(Calendar.HOUR_OF_DAY);
        if (hour < IntConstants.N10) {
            bf.append(get0String());
        }
        bf.append(hour);
        bf.append(getmaoString());
        final int minite = cal.get(Calendar.MINUTE);
        if (minite < IntConstants.N10) {
            bf.append(get0String());
        }
        bf.append(minite);
        bf.append(getmaoString());
        final int second = cal.get(Calendar.SECOND);
        if (second < IntConstants.N10) {
            bf.append(get0String());
        }
        bf.append(second);
        return bf.toString();
    }

    /***************************************************************************
     * @功能 计算当前日期某年的第几周
     * @return interger
     * @throws ParseException
     **************************************************************************/
    public static int getWeekNumOfYear() {

        final Calendar calendar = new GregorianCalendar();
        final int iWeekNum = calendar.get(Calendar.WEEK_OF_YEAR);
        return iWeekNum;
    }

    /***************************************************************************
     * @功能 计算指定日期某年的第几周
     * @return interger
     * @throws ParseException
     **************************************************************************/
    public static int getWeekNumOfYearDay(final String strDate) {

        final Calendar calendar = new GregorianCalendar();
        calendar.setTime(parseDate(strDate));
        final int iWeekNum = calendar.get(Calendar.WEEK_OF_YEAR);
        return iWeekNum;
    }

    /***************************************************************************
     * @功能 计算某年某周的开始日期
     * @return interger
     * @throws ParseException
     **************************************************************************/
    public static String getWeekFirstDay(final int yearNum, final int weekNum) {

        final Calendar cal = new GregorianCalendar();
        cal.set(Calendar.YEAR, yearNum);
        cal.set(Calendar.WEEK_OF_YEAR, weekNum);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        // 分别取得当前日期的年、月、日
        final String tempYear = Integer.toString(yearNum);
        final String tempMonth = Integer.toString(cal.get(Calendar.MONTH) + 1);
        final String tempDay = Integer.toString(cal.get(Calendar.DATE));
        return tempYear + getBarString() + tempMonth + getBarString() + tempDay;
    }

    public static String getBarString() {

        return "-";
    }

    public static String getWeekFirstDay(final int weekNum) {

        final Calendar cal = new GregorianCalendar();
        cal.set(Calendar.WEEK_OF_YEAR, weekNum);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        // 分别取得当前日期的年、月、日
        final String tempMonth = Integer.toString(cal.get(Calendar.MONTH) + 1);
        final String tempDay = Integer.toString(cal.get(Calendar.DATE));
        return cal.get(Calendar.YEAR) + getBarString() + tempMonth + getBarString() + tempDay;
    }

    /***************************************************************************
     * @功能 计算某年某周的结束日期
     * @return interger
     * @throws ParseException
     **************************************************************************/
    public static String getWeekEndDay(final int yearNum, final int weekNum) {

        final Calendar cal = new GregorianCalendar();
        cal.set(Calendar.YEAR, yearNum);
        cal.set(Calendar.WEEK_OF_YEAR, weekNum + 1);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        // 分别取得当前日期的年、月、日
        final String tempMonth = Integer.toString(cal.get(Calendar.MONTH) + 1);
        final String tempDay = Integer.toString(cal.get(Calendar.DATE));
        return cal.get(Calendar.YEAR) + getBarString() + tempMonth + getBarString() + tempDay;
    }

    public static String getWeekEndDay(final int weekNum) {

        final Calendar cal = new GregorianCalendar();
        cal.set(Calendar.WEEK_OF_YEAR, weekNum + 1);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        // 分别取得当前日期的年、月、日
        final String tempMonth = Integer.toString(cal.get(Calendar.MONTH) + 1);
        final String tempDay = Integer.toString(cal.get(Calendar.DATE));
        return cal.get(Calendar.YEAR) + getBarString() + tempMonth + getBarString() + tempDay;
    }

    public static String getDatePreHours(final int preHours) {

        // 当前日期
        final Date date = new Date();
        // 格式化对象
        final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMATTER + " HH:mm:ss");
        // 日历对象
        final Calendar calendar = new GregorianCalendar();
        // 设置当前日期
        calendar.setTime(date);
        // 小时增减
        calendar.add(Calendar.HOUR_OF_DAY, preHours);

        return sdf.format(calendar.getTime());
    }

    public static String getDate() {

        final SimpleDateFormat sdf = new SimpleDateFormat(getYYYYMMDDString1());
        return sdf.format(new Date());
    }

    public static String getTime() {

        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }

    /**
     * 获取指定日期的下一周日期
     *
     * @param date
     * @return
     */
    public static String getNextWeekDay(final String dateStr, final int weekday) {

        final Calendar cal = new GregorianCalendar();
        cal.setTime(DateUtil.parseDate(dateStr, getYYYYMMDDString1()));
        final int weekNum = cal.get(Calendar.WEEK_OF_YEAR);
        cal.set(Calendar.WEEK_OF_YEAR, weekNum + 1);
        cal.set(Calendar.DAY_OF_WEEK, weekday + 1);
        return format(cal.getTime(), getYYYYMMDDString1());
    }

    /**
     * 获取指定日期的当前一周日期
     *
     * @param date
     * @return
     */
    public static String getCurrWeekDay(final String dateStr, final int weekday) {

        final Calendar cal = new GregorianCalendar();
        cal.setTime(DateUtil.parseDate(dateStr, getYYYYMMDDString1()));
        cal.set(Calendar.DAY_OF_WEEK, weekday + 1);
        return format(cal.getTime(), getYYYYMMDDString1());
    }

    /**
     * 获取指定日期的下个月日期
     */
    public static String getCurrMonthDay(final String dateStr, final int day) {

        final Calendar cal = new GregorianCalendar();
        cal.setTime(DateUtil.parseDate(dateStr, getYYYYMMDDString1()));
        final int month = cal.get(Calendar.MONTH) + 1;
        final int year = cal.get(Calendar.YEAR);
        final int endDay = maxDayOfMonth(year, month);
        int valDay = day;
        if (day > endDay) {
            valDay = endDay;
        }
        cal.set(Calendar.DATE, valDay);
        return format(cal.getTime(), getYYYYMMDDString1());
    }

    /**
     * 获取指定日期的下个月日期
     */
    public static String getNextMonthDay(final String dateStr, final int day) {

        final Calendar cal = new GregorianCalendar();
        cal.setTime(DateUtil.parseDate(dateStr, getYYYYMMDDString1()));
        final int month = cal.get(Calendar.MONTH) + 2;
        cal.set(Calendar.MONTH, month - 1);
        final int year = cal.get(Calendar.YEAR);
        final int endDay = maxDayOfMonth(year, month);
        int valDay = day;
        if (day > endDay) {
            valDay = endDay;
        }
        cal.set(Calendar.DATE, valDay);
        return format(cal.getTime(), getYYYYMMDDString1());
    }

    /**
     * 前几个月日期
     *
     * @param dateStr
     * @param number
     * @return
     */
    public static String beforeMonthDate(final String dateStr, final int number) {

        final Calendar cal = new GregorianCalendar();
        cal.setTime(DateUtil.parseDate(dateStr, DATE_FORMATTER));
        final int year = cal.get(Calendar.YEAR);
        final int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DATE);
        final int endDay = maxDayOfMonth(year, month);
        day = Math.min(endDay, day);
        cal.set(Calendar.MONTH, month - number);
        cal.set(Calendar.DATE, day);

        return format(cal.getTime(), DATE_FORMATTER);
    }

    /**
     * 得到二个日期间的间隔天数
     */
    public static long getTwoDay(final String sj1, final String sj2) {

        final SimpleDateFormat myFormatter = new SimpleDateFormat(getYYYYMMDDString1());
        long day = -1;
        try {
            final Date date = myFormatter.parse(sj1);
            final Date mydate = myFormatter.parse(sj2);
            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (final Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return day;
    }

    /**
     * 获取指定日期的预约日期 weekday
     *
     * @param date
     * @return
     */
    public static String getPlanWeekDay(final String startDate, final String endDate, final int day) {

        String dt = getCurrWeekDay(startDate, day);
        if (dt.compareTo(startDate) < 0) {
            dt = getNextWeekDay(startDate, day);
        }
        if (dt.compareTo(endDate) > 0) {
            dt = "";
        }
        return dt;
    }

    /**
     * 获取指定日期的预约日期day
     *
     * @param date
     * @return
     */
    public static String getPlanMonthDay(final String startDate, final String endDate, final int day) {

        String dt = getCurrMonthDay(startDate, day);
        if (dt.compareTo(startDate) < 0) {
            dt = getNextMonthDay(startDate, day);
        }
        if (dt.compareTo(endDate) > 0) {
            dt = "";
        }
        return dt;
    }

    /**
     * 将字符串yyyyMMdd 格式成 字符串yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String fmtmat(final String date) {

        final SimpleDateFormat df = new SimpleDateFormat(getYYYYMMDDString());
        try {
            final Date newDate = df.parse(date);
            final SimpleDateFormat sdf = new SimpleDateFormat(getYYYYMMDDString1());
            return sdf.format(newDate);
        } catch (final Exception ex) {
            return date;
        }
    }

    /**
     * date 格式成 字符串yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String fmtmatfulldate(final Date date) {

        if (date != null) {
            final SimpleDateFormat df = new SimpleDateFormat(getYYYYMMDDString3());
            return df.format(date);
        }
        return null;
    }

    /**
     * date 格式成 字符串yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static Date getFormatStrToDate(final String date) {

        if (date != null) {
            final SimpleDateFormat df = new SimpleDateFormat(getYYYYMMDDString3());
            try {
                return df.parse(date);
            } catch (ParseException e) {
                log.error(e.getMessage());
            }
        }
        return null;
    }

    /**
     * 将字符串yyyyMMddHHmmss 格式成 字符串yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String fmtmatfulldate(final String date) {

        final SimpleDateFormat df = new SimpleDateFormat(getYYYYMMDDString2());
        try {
            final Date newDate = df.parse(date);
            final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(newDate);
        } catch (final Exception ex) {
            return date;
        }
    }

    /**
     * 返回当天yyyyMMdd形式的日期
     *
     * @return
     */
    public static String fullDate() {

        final SimpleDateFormat df = new SimpleDateFormat(getYYYYMMDDString());
        final Date today = new Date();
        return df.format(today);
    }

    /**
     * 返回当天24小时制的时间hhMMss
     *
     * @return
     */
    public static String fullTime() {

        final SimpleDateFormat df = new SimpleDateFormat("HHmmss");
        final Date today = new Date();
        return df.format(today);
    }

    /**
     * 获取时间戳
     *
     * @return
     */
    public static String getTimeStamp() {

        final SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssS");
        final Date today = new Date();
        return df.format(today);
    }

    public static Date firstDate(final Calendar calendar) {

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date lastDate(final Calendar calendar) {

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static Date firstDate(final Date date) {

        if (date != null) {
            final Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            return calendar.getTime();
        }
        return null;
    }

    public static Date lastDate(final Date date) {

        if (date != null) {
            final Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 0);
            return calendar.getTime();
        }
        return null;
    }

}
