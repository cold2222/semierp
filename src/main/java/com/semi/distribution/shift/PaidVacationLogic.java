package com.semi.distribution.shift;

import java.time.LocalDate;
import java.util.ArrayList;
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

				if (paidVacation.get(i).getE_id().equals(emp.getE_no())) {
					paidVacationDate.add(paidVacation.get(i).getW_date());
				}
			}
			empPaidVacation.put(emp.getE_no(), paidVacationDate);
		}

		HashMap<String, String[]> empsPaidVacationNum = new HashMap<String, String[]>();
		ArrayList<String> date = null;
		for (EmployeeDTO emp : emps) {
			String[] nowAndLast = new String[3];
			int nowYearUse = 0;
			int lastYearUse = 0;

			LocalDate hireDate = emp.getE_joined_company().toLocalDate();
			LocalDate now = LocalDate.now();
			LocalDate paidVacationMonth = hireDate.plusMonths(6);
			
			LocalDate currentYear = now;
			if (paidVacationMonth.isBefore(now)) {
				currentYear = currentYear.minusYears(1);
			}
			
			LocalDate lastYearStart = LocalDate.of(now.minusYears(2).getYear(), paidVacationMonth.getMonth(), 1);
			paidVacationMonth.plusMonths(11);
			LocalDate lastYearEnd = lastYearStart.plusYears(1).minusDays(1);
			LocalDate nowYearStart = lastYearEnd.plusDays(1);
				
			System.out.println("去年スタート :    " + lastYearStart);
			System.out.println("去年終わり :    " + lastYearEnd);
			System.out.println("今年スタート :    " + nowYearStart);
			ArrayList<String> temp = empPaidVacation.get(emp.getE_no());
			
			for (String pV : temp) {
				LocalDate pVDate = LocalDate.parse(pV);
				System.out.println(pVDate);
				if (!pVDate.isBefore(lastYearStart) && !pVDate.isAfter(lastYearEnd)) {
					lastYearUse++;
				}
				if (!pVDate.isBefore(nowYearStart)) {
					nowYearUse++;
				}
			}
			String paidMonth = paidVacationMonth.getMonthValue() + "";
			nowAndLast[0] = nowYearUse + "";
			nowAndLast[1] = lastYearUse + "";
			nowAndLast[2] = paidMonth;

			empsPaidVacationNum.put(emp.getE_no(), nowAndLast);
		}

		request.setAttribute("paidVacation", empsPaidVacationNum);
		request.setAttribute("emps", emps);

	}
}
