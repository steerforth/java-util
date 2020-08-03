package com.steer.tool.util.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class DateUtil {

    public static final DateTimeFormatter MID_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter FULL_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter ZONE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");

    /**
     * @param date yyyy-MM-dd
     * @return
     */
    public static LocalDate localDateParse(String date){
        return LocalDate.parse(date,DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * yyyy-MM-dd HH:mm
     * @param date
     * @return
     */
    public static LocalDateTime localDateTimeParse(String date){
        return LocalDateTime.parse(date,MID_FORMATTER);
    }

    /**
     * @param date yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static LocalDateTime localDateTimeParseFull(String date){
        return LocalDateTime.parse(date,FULL_FORMATTER);
    }

    public static LocalDate localDateParse(String date,String pattern){
        return LocalDate.parse(date,DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime localDateTimeParse(String date,String pattern){
        return LocalDateTime.parse(date,DateTimeFormatter.ofPattern(pattern));
    }

    public static String localDateFormat(LocalDate localDate,String pattern){
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String localDateTimeFormat(LocalDateTime localDateTime, String pattern){
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 转为本地时区时间
     * @param date
     * @param pattern
     * @return
     */
    public static LocalDateTime localDateTimeZoneParse(String date,String pattern){
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(date, DateTimeFormatter.ofPattern(pattern));
        return zonedDateTime.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * 转为本地时区时间
     * @param date eg.2020-07-28T14:33+04:00
     * @return
     */
    public static LocalDateTime localDateTimeZoneParse(String date){
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(date, ZONE_FORMATTER);
        return zonedDateTime.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDate localDateZoneParse(String date,String pattern){
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(date, DateTimeFormatter.ofPattern(pattern));
        return zonedDateTime.withZoneSameInstant(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDate localDateZoneParse(String date){
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(date, ZONE_FORMATTER);
        return zonedDateTime.withZoneSameInstant(ZoneId.systemDefault()).toLocalDate();
    }

    public static Date localDateToDate(LocalDate localDate){
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime){
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    public static LocalDate dateToLocalDate(Date date){
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDate();
    }

    public static LocalDateTime dateToLocalDateTime(Date date){
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    public static LocalDate firstDayOfMonth(LocalDate localDate){
        return localDate.with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 获取东8区时间戳（毫秒）
     * @param dateTime
     * @return
     */
    public static long getTimeStampMill(LocalDateTime dateTime){
        return dateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 获取东8区时间戳（秒）
     * @param dateTime
     * @return
     */
    public static long getTimeStamp(LocalDateTime dateTime){
        return dateTime.toEpochSecond(ZoneOffset.of("+8"));
    }

}
