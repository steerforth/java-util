package com.steer.tool.util.date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateUtilTest {
    private Logger log = LoggerFactory.getLogger(DateUtilTest.class);
    @Test
    public void testPrint(){
        LocalDateTime now = LocalDateTime.now();
        log.info("{}",now);
        //一天的开始
        LocalDateTime t1 = LocalDate.now().atStartOfDay();
        log.info("{}",t1);
        //一天的结束
        LocalDateTime t2 = LocalDate.now().atTime(LocalTime.MAX);
        log.info("{}",t2);
        LocalDateTime t3=LocalDateTime.of(2020,1,1,19,30,0);
        log.info("{}",t3);
        LocalDateTime t4=LocalDateTime.parse("2020-12-03T10:15:30");
        log.info("{}",t4);
    }

    @Test
    public void testCaculate(){
        LocalDateTime t1 = LocalDateTime.now();
        LocalDateTime t4 = t1.plusDays(3).plusHours(5);
        LocalDateTime t5 = t1.plusDays(-3).plusHours(-5);
        log.info("{}",t4);
        log.info("{}",t5);

        Duration duration = Duration.between(t5,t4);
        log.info("时间差:{}秒",duration.getSeconds());

        long day = t5.until(t4, ChronoUnit.DAYS);
        log.info("时间差:{}天",day);

        LocalDate ld1 = LocalDate.now();
        LocalDate ld2 = ld1.plusDays(5);

        Period period = Period.between(ld1,ld2);
        log.info("时间差:{}天",period.getDays());
    }


    @Test
    public void testUtil(){
        String date = DateUtil.localDateTimeFormat(LocalDateTime.now(),"yyyy年MM月dd日 HH时mm分ss秒");
        log.info("现在时间:{}",date);
    }

    @Test
    public void testInstant(){
        // Date Instant
        Instant instant = new Date().toInstant();
        Date date = Date.from(Instant.now());
        log.info("instant:{}  date:{}",instant,date);

        // Instant LocalDateTime，当前时区
        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        Instant instant2 = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant();

        // Date LocalDateTime
        LocalDateTime localDateTime3 = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
        Date date3 = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());

        // LocalDateTime LocalDate
        LocalDate localDate4 = LocalDateTime.now().toLocalDate();
        LocalDateTime localDateTime4 = LocalDate.now().atStartOfDay();

        // LocalDateTime LocalTime
        LocalTime localTime5 = LocalDateTime.now().toLocalTime();
        LocalDateTime localDateTime5 = LocalDateTime.of(LocalDate.now(), LocalTime.now());
    }

    @Test
    public void testZoneTimeFormat(){
        // 指定日期时间
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        // ISO_LOCAL_DATE 2020-03-25
        DateTimeFormatter.ISO_LOCAL_DATE.format(zonedDateTime);
        // ISO_OFFSET_DATE 2020-03-25+08:00
        DateTimeFormatter.ISO_OFFSET_DATE.format(zonedDateTime);
        // ISO_DATE 2020-03-25+08:00
        DateTimeFormatter.ISO_DATE.format(zonedDateTime);
        // ISO_LOCAL_TIME 22:22:55.6747072
        DateTimeFormatter.ISO_LOCAL_TIME.format(zonedDateTime);
        // ISO_OFFSET_TIME 22:22:55.6747072+08:00
        DateTimeFormatter.ISO_OFFSET_TIME.format(zonedDateTime);
        // ISO_TIME 22:22:55.6747072+08:00
        DateTimeFormatter.ISO_TIME.format(zonedDateTime);
        // ISO_LOCAL_DATE_TIME 2020-03-25T22:22:55.6747072
        DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(zonedDateTime);
        // ISO_OFFSET_DATE_TIME 2020-03-25T22:22:55.6747072+08:00
        DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(zonedDateTime);
        // ISO_ZONED_DATE_TIME 2020-03-25T22:22:55.6747072+08:00[Asia/Shanghai]
        DateTimeFormatter.ISO_ZONED_DATE_TIME.format(zonedDateTime);
        // ISO_DATE_TIME 2020-03-25T22:22:55.6747072+08:00[Asia/Shanghai]
        DateTimeFormatter.ISO_DATE_TIME.format(zonedDateTime);
        // ISO_ORDINAL_DATE 2020-085+08:00
        DateTimeFormatter.ISO_ORDINAL_DATE.format(zonedDateTime);
        // ISO_WEEK_DATE 2020-W13-3+08:00
        DateTimeFormatter.ISO_WEEK_DATE.format(zonedDateTime);
        // ISO_INSTANT 2020-03-25T14:22:55.674707200Z
        DateTimeFormatter.ISO_INSTANT.format(zonedDateTime);
        // BASIC_ISO_DATE 20200325+0800
        DateTimeFormatter.BASIC_ISO_DATE.format(zonedDateTime);
        // RFC_1123_DATE_TIME Wed, 25 Mar 2020 22:22:55 +0800
        DateTimeFormatter.RFC_1123_DATE_TIME.format(zonedDateTime);
    }

    /**
     * 多线程共享Calendar对象
     */
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Test
    public void testThreadUnSafe(){
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                String dateStr = dateFormat.format(new Date());
                try {
                    Date parse = dateFormat.parse(dateStr);
                    String dateStr2 = dateFormat.format(parse);
                    Assertions.assertEquals(dateStr,dateStr2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Test
    public void testThreadSafe(){
        for (int i = 0; i < 1000; i++) {
            new Thread(()->{
                String dateStr = LocalDate.now().format(formatter);
                LocalDate parse = LocalDate.parse(dateStr,formatter);
                String dateStr2 = formatter.format(parse);
                Assertions.assertEquals(dateStr,dateStr2);
            }).start();
        }
    }

}
