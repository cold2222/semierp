package com.semi.distribution.shift;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.semi.distribution.employee.EmployeeDTO;

public class PaidVacationLogic {

	public static void CalcPaidVacation(ArrayList<EmployeeDTO> emps, ArrayList<ShiftDTO> paidVacation,
			HttpServletRequest request) {

		HashMap<String, ArrayList<String>> empPaidVacation = new HashMap<String, ArrayList<String>>();
		
		for (EmployeeDTO emp : emps) {
			ArrayList<String> paidVacationDate = new ArrayList<String>();
			for (int i = 0; i < paidVacation.size(); i++) {
				
				if (paidVacation.get(i).getE_id().equals(emp.getE_id())) {
					paidVacationDate.add(paidVacation.get(i).getW_date());
				}
			
			}
			empPaidVacation.put(emp.getE_id(), paidVacationDate);
		}
		
		HashMap<String, String[]> empsPaidVacationNum = new HashMap<String, String[]>();
		ArrayList<String> date = null;
		
		for (EmployeeDTO emp : emps) {
			String[] nowAndLast = new String[3];
			int nowYearUse = 0;
			int lastYearUse = 0;
			
			LocalDate hireDate = emp.getE_hire_date().toLocalDate();
			int nowYear = LocalDate.now().getYear();
			int month = hireDate.getMonthValue() + 6;
			if(month > 12) {
				month -= 12;
			}
			String paidMonth = month+"";
			int nowMonth = LocalDate.now().getMonthValue();
			int year = nowYear;
			if(month < nowMonth) {
				year -= 1;
			}
			LocalDate lastYearStart = LocalDate.of(year, month, 1);
			month += 11;
			if(month > 12) {
				month -= 11;
				year++;
			}
			LocalDate tempDate = LocalDate.of(year, month-1, 1);
			int day = tempDate.lengthOfMonth();
			LocalDate lastYearEnd = LocalDate.of(year, month-1, day);
			LocalDate nowYearStart = LocalDate.of(year, month, 1);
			
			/*
			 * System.out.println(emp.getE_id()+"작년시작"+lastYearStart);
			 * System.out.println(emp.getE_id()+"작년끝"+lastYearEnd);
			 * System.out.println(emp.getE_id()+"올해시작"+nowYearStart);
			 * System.out.println("날짜나옴?"+hireDate);
			 */
			
			ArrayList<String> temp = empPaidVacation.get(emp.getE_id());
			for (String pV : temp) {
				LocalDate pVDate = LocalDate.parse(pV);
				if(!pVDate.isBefore(lastYearStart) && !pVDate.isAfter(lastYearEnd)) {
					lastYearUse++;
				}
				if(!pVDate.isBefore(nowYearStart)) {
					nowYearUse++;
				}
				
			}
			nowAndLast[0] = nowYearUse+"";
			nowAndLast[1] = lastYearUse+"";
			nowAndLast[2] = paidMonth;
			
			empsPaidVacationNum.put(emp.getE_id(), nowAndLast);
			
		}
		
		request.setAttribute("paidVacation", empsPaidVacationNum);
		request.setAttribute("emps", emps);
		
		


		
	}
}
