package com.semi.distribution.test;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

public class TestDAO {
	
	
	
	
	
	
	
	public static void getCalendar(HttpServletRequest request) {
		int year = 0;
		int month = 0;
		
		Calendar cal = Calendar.getInstance();
		
		String choiceYear = request.getParameter("year");
		String choiceMonth = request.getParameter("month");
		
		if(choiceYear != null) {
			year = Integer.parseInt(choiceYear);
		}else {
			year = cal.get(Calendar.YEAR);
		}
		
		if(choiceYear != null) {
			month = Integer.parseInt(choiceMonth);
		}else {
			month = cal.get(Calendar.MONTH)+1;
		}
		
		cal.set(year, month-1, 1);
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH)+1;
		int week = cal.get(Calendar.DAY_OF_WEEK);
		System.out.println(week);
		System.out.println(Calendar.DAY_OF_WEEK);
		
		
		
		
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		
		
		
	}

}
