package com.kristin.java_base.time;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class DateTest {
    /**
     * LocalDate: 只包含日期，不包含时间
     */
    @Test
    public void localDateTest() {
        //获取当前时间
        LocalDate date = LocalDate.now();
        System.out.println(date);

        //获取年月日数值
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        System.out.println(year + " " + month + " " + day);

        //根据数值创建时间
        LocalDate newDate = LocalDate.of(2018, 11, 13);
        System.out.println(newDate);

        //判断两个时间是否相等
        LocalDate now1 = LocalDate.now();
        LocalDate now2 = LocalDate.of(2018, 12, 17);
        System.out.println(now1.equals(now2));

        //获取一周后的日期
        LocalDate today = LocalDate.now();
        LocalDate oneWeek = today.plus(1, ChronoUnit.WEEKS);
        System.out.println(today + " " + oneWeek);

        //找出一年前的一天
        LocalDate nowDay = LocalDate.now();
        LocalDate previousYesr = nowDay.minus(1, ChronoUnit.YEARS);
        LocalDate nextYear = nowDay.plus(1, ChronoUnit.YEARS);
        System.out.println(previousYesr + " " + nextYear);

        //判断某个日期在另一个日期的前面还是后面
        LocalDate nowday = LocalDate.now();
        LocalDate tomorrow = nowday.plus(1, ChronoUnit.DAYS);
        System.out.println(tomorrow.isAfter(today));
        System.out.println(tomorrow.isBefore(today));

        //检查是否是闰年
        // output: false
        System.out.println(nowday.isLeapYear());

    }

    @Test
    public void localTimeTest() {
        //获取当前时间,精确到秒，毫秒
        LocalTime now = LocalTime.now();
        System.out.println(now);

        //增加时间里的小时数
        LocalTime localTime = LocalTime.now();
        LocalTime twohoursAfter = localTime.plusHours(2);
        System.out.println(twohoursAfter);


    }

    @Test
    public void clockTest() {
        //Clock获取当前时间，根据系统时钟和UTC，返回当前时间
        Clock clock = Clock.systemUTC();
        System.out.println(clock);

        clock = Clock.systemDefaultZone();
        System.out.println(clock);
    }

    @Test
    public void yearMonthTest() {
        // 获取当年的月份数
        YearMonth currentYearMonth = YearMonth.now();
        System.out.println(currentYearMonth + " " + currentYearMonth.lengthOfMonth());
        YearMonth creditYearMonth = YearMonth.of(2018, Month.FEBRUARY);
        System.out.println(creditYearMonth);
        /**
         * output:2018-12 31
         *        2018-02
         */
    }

    @Test
    public void monthDayTest() {
        //判断某月某日是否是纪念日,即对月份和日期进行比较
        LocalDate dateOfBirth = LocalDate.of(2008, 12, 17);
        LocalDate now = LocalDate.now();
        MonthDay birthday = MonthDay.of(dateOfBirth.getMonthValue(), dateOfBirth.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(now);
        if (currentMonthDay.equals(birthday)) {
            System.out.println("today is your birthday");
        } else {
            System.out.println("what a pity");
        }
    }

    @Test
    public void periodTest() {
        //判断两个日期相差几个月,几天
        LocalDate today = LocalDate.now();
        LocalDate dates = LocalDate.of(2018, Month.MARCH, 14);
        Period period = Period.between(today, dates);
        System.out.println(today + ", " + dates + ", " + period.getMonths() + ", " + period.getDays());
    }


    @Test
    public void zonedDateTimeTest() {
        //将本地时间转换成特定时区的时间
        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId zone = ZoneId.of(ZoneId.SHORT_IDS.get("ACT"));
        ZonedDateTime dateAndTimeInNewYork = ZonedDateTime.of(localDateTime, zone);
        System.out.println(dateAndTimeInNewYork);
    }

    @Test
    public void zoneOffsetTest() {
        //可以看到现在时间日期和时区关联上了，注意OffsetDateTime主要是用来给机器理解的，平时使用就用前面结束的ZoneDateTime类就可以了
        LocalDateTime localDate = LocalDateTime.of(2018, Month.DECEMBER, 18, 10, 41, 30);
        ZoneOffset offset = ZoneOffset.of("+05:30");
        OffsetDateTime date = OffsetDateTime.of(localDate, offset);
        System.out.println(date);
    }

    @Test
    public void instantTest() {
        // Instant获取当前时间戳,可以和Date进行转换
        Instant timeStamp = Instant.now();
        System.out.println(timeStamp);
        System.out.println(Date.from(timeStamp));

        Date date = new Date();
        System.out.println(date);
        System.out.println(date.toInstant());
    }

    @Test
    public void timeformatTest() {
        //预定义格式器对日期进行解析
        String daystr = "20181217";
        LocalDate format = LocalDate.parse(daystr, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(daystr + ", " + format);
        String tomorrowstr = "2018-12-18";
        format = LocalDate.parse(tomorrowstr, DateTimeFormatter.ISO_DATE);
        System.out.println(daystr + ", " + format);

        //自定义格式器
        String goodDay = "12 10 2018";
        DateTimeFormatter myformatter = DateTimeFormatter.ofPattern("MM dd yyyy");
        LocalDate formatter = LocalDate.parse(goodDay, myformatter);
        System.out.println(formatter);

        //对日期进行格式化，转换成字符串
        System.out.println("对日期进行格式化，转换成字符串");
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM dd yyyy HH:mm a");
        String landing = localDateTime.format(dateTimeFormatter);
        System.out.println(localDateTime + " " + landing);
    }

    public static Date localDateToDate(LocalDateTime d) {
        return Date.from(d.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static void getDateStartAndEnd() {
        Date date = new Date();
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        Date startTime = Date.from(dateTime.with(LocalTime.of(0, 0, 0))
                .atZone(ZoneId.systemDefault()).toInstant());
        Date endTime = Date.from(dateTime.plusDays(1).with(LocalTime.of(0, 0, 0)).minusSeconds(1)
                .atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(startTime);
        System.out.println(endTime);
    }

    public static void getStringStartAndEnd() {
        
    }

    public static void main(String[] args) {
        DateTest.getDateStartAndEnd();
    }
}

