package com.semi.adminpage.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

public class AdminUtils {

	public static String getCurrentYearMonth() {
		// 현재 날짜 정보 가져오기
		LocalDate currentDate = LocalDate.now();

		// 형식화된 년월 가져오기 (YY-MM 형식)
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
		String currentYearMonth = currentDate.format(formatter);
		
		return currentYearMonth;
	}
	
	public static String getCurrentDate() {
		// 현재 날짜 가져오기
        LocalDate currentDate = LocalDate.now();

        // 날짜 포맷 지정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 포맷 적용하여 출력
        String formattedDate = currentDate.format(formatter);
        return formattedDate;
	}
	
	public static String getParamDate(HttpServletRequest request) {
		String ParamDate = request.getParameter("setDate");
		if (ParamDate == null || ParamDate.isEmpty()) {
			ParamDate = AdminUtils.getCurrentDate();
		}
		return ParamDate;
	}
	
	public static String getParamYearMonth(HttpServletRequest request) {
		String ParamDate = request.getParameter("setDate");
		if (ParamDate == null || ParamDate.isEmpty()) {
			return getCurrentYearMonth();
		}
		String[] dateSplit = ParamDate.split("-");
		return dateSplit[0] + "-" + dateSplit[1];
		
	}
}
