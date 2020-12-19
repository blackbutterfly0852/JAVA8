package dateTime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Practice {

    public static void main(String[] args) throws InterruptedException {
        // 기존 : 네이밍이 명확하지 않다. Date 이지만 근본적으로 TimeStamp 이다.
        Date date1 = new Date();
        long time = date1.getTime();
        System.out.println("date = " + date1);
        System.out.println("time = " + time);

        //Thread.sleep(1000*3);
        Date after3Seconds = new Date();
        System.out.println("after3Seconds = " + after3Seconds) ;
        after3Seconds.setTime(time); // mutable 하다 -> 멀티쓰레드에서 안전하지 않다.
        System.out.println("after3Seconds = " + after3Seconds) ;
        System.out.println("=================================");

        Calendar dongwooBirthDay = new GregorianCalendar(1991,Calendar.AUGUST,25);
        System.out.println("dongwooBirthDay = " + dongwooBirthDay.getTime());
        dongwooBirthDay.add(Calendar.DAY_OF_YEAR,1); // mutable 하다. -> 멀티쓰레드에서 안전하지 않다.
        System.out.println("dongwooBirthDay = " + dongwooBirthDay.getTime());
        System.out.println("=================================");

        // 자바 8에서 제공하는 Date-Time API
        // 1) 기계적 사용 == Date

        Instant instant1 = Instant.now();
        System.out.println("instant1 = " + instant1); // 기준시, UTC, GMT
        System.out.println("instant1 = " + instant1.atZone(ZoneId.of("UTC"))); // 기준시, UTC, GMT

        ZoneId zone = ZoneId.systemDefault();
        System.out.println("zone = " + zone);
        ZonedDateTime zonedDateTime = instant1.atZone(zone);
        System.out.println("zonedDateTime = " + zonedDateTime);
        System.out.println("=================================");

        // 2) 사람용 사용
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime = " + localDateTime); // 서버가 미국이면 미국시간 표출
        LocalDateTime birthDay =  LocalDateTime.of(1991, Month.AUGUST,25,0,0,0);
        System.out.println("birthDay = " + birthDay);

        ZonedDateTime nowInFrance = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
        System.out.println("nowInFrance = " + nowInFrance);

        Instant nowInstant = Instant.now();
        nowInstant.atZone(ZoneId.of("Europe/Paris"));
        System.out.println("nowInstant = " + nowInstant);
        System.out.println("=================================");

        // 3) 기간
        LocalDate today = LocalDate.now();
        System.out.println("today = " + today);
        LocalDate nextYearBirthDay = LocalDate.of(2021,Month.AUGUST,25);

        System.out.println("nextYearBirthDay = " + nextYearBirthDay);
        // Period 사람용
        Period period = Period.between(today,nextYearBirthDay);
        System.out.println("period.getYears() = " + period.getYears());
        System.out.println("period.getMonths() = " + period.getMonths());
        System.out.println("period.getDays() = " + period.getDays());

        Period until = today.until(nextYearBirthDay);
        System.out.println("until.get(ChronoUnit.DAYS) = "  + until.get(ChronoUnit.DAYS)) ;
        System.out.println("=================================");

        // Duration 기계용
        Instant now1 = Instant.now();
        Instant plus = now1.plus(10, ChronoUnit.SECONDS);
        Duration duration = Duration.between(now1, plus);
        System.out.println("duration.getSeconds() = " + duration.getSeconds());
        System.out.println("=================================");

        // LocalDateTime
        LocalDateTime now2 = LocalDateTime.now();
        now2.plus(10, ChronoUnit.DAYS); // ChronoUnit 사용 편함, 이 상태 에서는 변경 안됨 -> 아래처럼 새로운 인스턴스를 생성(immutable)
        LocalDateTime plus1 = now2.plus(10, ChronoUnit.DAYS);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println("now2.format(dateTimeFormatter) = " + now2.format(dateTimeFormatter));
        System.out.println("=================================");

        // Parsing
        LocalDate parse = LocalDate.parse("08/25/1991", dateTimeFormatter);
        System.out.println("parse = " +  parse);
        System.out.println("=================================");

        // 호환
        Date date2 = new Date();
        Instant instant2 = date2.toInstant();
        Date date3 = Date.from(instant2);

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        // toInstant() 최신 API 변경 메소드
        LocalDateTime dateTime1 = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        ZonedDateTime dateTime2 = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault());
        GregorianCalendar from = GregorianCalendar.from(dateTime2);

        // toZoneId() 최신 API 변경 메소드
        ZoneId zoneId = TimeZone.getTimeZone("PST").toZoneId();
        TimeZone timeZone = TimeZone.getTimeZone(zoneId);
    }
}
