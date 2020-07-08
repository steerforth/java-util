package com.steer.tool.util.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class DateUtil {

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

}
