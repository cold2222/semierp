package com.semi.adminpage.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AdminUtils {

	public static String getCurrentYearMonth() {
		// 현재 날짜 정보 가져오기
		LocalDate currentDate = LocalDate.now();

		// 형식화된 년월 가져오기 (YY-MM 형식)
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
		String currentYearMonth = currentDate.format(formatter);
		
		return currentYearMonth;
	}

}
