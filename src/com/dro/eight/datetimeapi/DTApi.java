package com.dro.eight.datetimeapi;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Set;

public class DTApi {


	private static void testLocalDate() {
		
		LocalDate localDate = LocalDate.now();
		System.out.println("now --- " + localDate);
		
		LocalDate tomorrow = LocalDate.now().plusDays(1);
		System.out.println("tomorrow --- " + tomorrow);

		LocalDate previousMonthSameDay = LocalDate.now().minus(1, ChronoUnit.MONTHS);
		System.out.println("previousMonthSameDay --- " + previousMonthSameDay);

		LocalDate localDate02 = LocalDate.of(2020, Month.JANUARY, 30);
		System.out.println("localDate02 --- " + localDate02);
		
		LocalDate localDate03 = LocalDate.parse("2025-12-23");
		System.out.println("localDate03 --- " + localDate03);
		
		DayOfWeek sunday = LocalDate.parse("2016-06-12").getDayOfWeek();
		System.out.println("dayOfWeek for  2016-06-12 --- " + sunday);
 
		int twelve = LocalDate.parse("2016-06-12").getDayOfMonth();
		System.out.println("dayOfMonth for  2016-06-12 --- " + twelve);

	}
	
	private static void testLocaTime() {
		
		LocalTime localTime = LocalTime.now();
		System.out.println("now --- " + localTime);
		
		LocalTime localTime02 = LocalTime.of(7, 24);
		System.out.println("localTime02 --- " + localTime02);

		LocalTime sixThirty = LocalTime.parse("06:30");
		System.out.println("sixThirty --- " + sixThirty);
		
		LocalTime sevenThirty = LocalTime.parse("06:30").plus(1, ChronoUnit.HOURS);
		System.out.println("sevenThirty --- " + sevenThirty);
		
		int six = LocalTime.parse("06:30").getHour();
		System.out.println("hour --- " + six);


	}
	
	private static void testLocalDateTime() {
		
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println("now --- " + localDateTime);
		
		System.out.println("tomorrow --- " + localDateTime.plusDays(1)); // changes are not stored --- IMMUTABLE!

		System.out.println("now minus 2 hours --- " + localDateTime.minusHours(2));
		
		LocalDateTime localDateTime02 = LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30);
		System.out.println("localDateTime02 --- " + localDateTime02);

		LocalDateTime localDateTime03 = LocalDateTime.parse("2015-02-20T06:30:00");
		System.out.println("localDateTime03 --- " + localDateTime03);
	
		LocalDateTime localDateTime04 = LocalDateTime.ofEpochSecond(1465817690, 0, ZoneOffset.UTC);
		System.out.println("ofEpochSecond --- " + localDateTime04);
	}
	
	private static void testZonedDateTime() {
		
		ZonedDateTime zonedDateTime = ZonedDateTime.now();
		System.out.println("now --- " + zonedDateTime);
		
		Set<String> allZoneIds = ZoneId.getAvailableZoneIds();
		System.out.println("allZoneIds --- " + allZoneIds);

		ZoneId zoneId = ZoneId.of("Europe/Paris");
		System.out.println("zoneId --- " + zoneId);
		
		ZonedDateTime zonedDateTime02 = ZonedDateTime.of(LocalDateTime.now(), zoneId);
		System.out.println("zonedDateTime02 --- " + zonedDateTime02);

	}
	
	// Period works only with LocalDate
	private static void testPeriod() {
		
		LocalDate initialDate = LocalDate.parse("2007-05-10");

		LocalDate finalDate = initialDate.plus(Period.ofDays(5));

		int five01 = Period.between(initialDate , finalDate).getDays();
		System.out.println("five01 --- " + five01);

		int five02 = (int) ChronoUnit.DAYS.between(initialDate , finalDate.plus(2, ChronoUnit.MONTHS));
		System.out.println("five02 --- " + five02);
		
		// It does not work with LocalDateTime
		// System.out.println(Period.between(LocalDateTime.now(), LocalDateTime.now().plus(5, ChronoUnit.MONTHS)));

	}
	
	// Duration works only with
	private static void testDuration() {
		
		LocalTime initialTime = LocalTime.of(6, 30, 0);
		 
		LocalTime finalTime = initialTime.plus(Duration.ofSeconds(30));

		int thirty01 = (int) Duration.between(finalTime, initialTime).getSeconds();
		System.out.println("thirty01 --- " + thirty01);

		int thirty02 = (int) ChronoUnit.SECONDS.between(finalTime, initialTime);
		System.out.println("thirty02 --- " + thirty02);

	}
	
	private static void testFormatting() {
		
		LocalDateTime localDateTime = LocalDateTime.of(2015, Month.JANUARY, 25, 6, 30);

		String localDateString01 = localDateTime.format(DateTimeFormatter.ISO_DATE);
		System.out.println("localDateString ISO --- " + localDateString01);

		String localDateString02 = localDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		System.out.println("localDateString yyyy/MM/dd --- " + localDateString02);

		
		String localDateString03 = localDateTime
		  .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
		  .withLocale(Locale.UK));
		System.out.println("localDateString MEDIUM --- " + localDateString03);

	}
	
	public static void main(String[] args) {
		
		System.out.println("testLocalDate -------------------");
		testLocalDate();
		System.out.println();
		
		System.out.println("testLocaTime -------------------");
		testLocaTime();
		System.out.println();
		
		System.out.println("testLocalDateTime -------------------");
		testLocalDateTime();
		System.out.println();
		
		System.out.println("testZonedDateTime -------------------");
		testZonedDateTime();
		System.out.println();		
		
		System.out.println("testPeriod -------------------");
		testPeriod();
		System.out.println();
		
		System.out.println("testDuration -------------------");
		testDuration();
		System.out.println();
		
		System.out.println("testFormatting -------------------");
		testFormatting();
		System.out.println();
		
	}


}
