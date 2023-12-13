package com.semi.distribution.test;

import java.util.Calendar;

public class test {

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		
		int ty = cal.get(Calendar.YEAR);
		int tm = cal.get(Calendar.MONTH)+1;
		int td = cal.get(Calendar.DATE);
	}

}
