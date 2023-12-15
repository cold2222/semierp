package com.semi.distribution.shift;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.semi.distribution.db.DBManger;
import com.semi.distribution.employee.EmployeeDTO;

public class ShiftDAO {
	public static ArrayList<EmployeeDTO> getEmployee() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<EmployeeDTO> emps = new ArrayList<EmployeeDTO>();
		
		String sql = "select * from employee where e_deptno = 201 order by e_no";
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("사원조회성공");
			
			EmployeeDTO emp = null;
			while(rs.next()) {
				emp = new EmployeeDTO();
				emp.setE_no(rs.getString(1));
				emp.setE_pw(rs.getString(2));
				emp.setE_deptno(rs.getString(3));
				emp.setE_name(rs.getString(4));
				emp.setE_rank(rs.getString(5));
				emp.setE_tel(rs.getString(6));
				emp.setE_email(rs.getString(7));
				emp.setE_joined_company(rs.getDate(8));
				emps.add(emp);
			}
			return emps;
			
		}catch (Exception e) {
			System.err.println("사원조회실패");
			e.printStackTrace();
		}finally {
			DBManger.close(con, pstmt, rs);
		}
		
		
		return null;
	}

	public static ArrayList<ShiftDTO> getWorkStatus(HttpServletRequest request) {
		int year = 0;
		int month = 0;

		Calendar cal = Calendar.getInstance();
		
		String choiceYear = request.getParameter("year");
		String choiceMonth = request.getParameter("month");

		if (choiceYear != null) {
			year = Integer.parseInt(choiceYear);
		} else {
			year = cal.get(Calendar.YEAR);
		}

		if (choiceMonth != null) {
			month = Integer.parseInt(choiceMonth);
		} else {
			month = cal.get(Calendar.MONTH) + 1;
		}
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String monthString = "0"+month;
		if(month >= 10) {
			monthString = month + "";
		}
		
		
		String sql = "select e_no, work_num, TO_CHAR(work_date, 'YYYY-MM-DD') as work_date"
				+ " from distribution_shift where to_char(work_date,'YYYY-MM') = ? order by e_no";
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, year+"-"+monthString);
			rs = pstmt.executeQuery();
			
			ArrayList<ShiftDTO> workList = new ArrayList<ShiftDTO>();
			ShiftDTO work = null;
			while(rs.next()) {
				work = new ShiftDTO(rs.getString(1),rs.getString(2),rs.getString(3));
				workList.add(work);
			}
			
			return workList;
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManger.close(con, pstmt, rs);
		}
		return null;
		
	}

	public static void getCalendarStatus(ArrayList<ShiftDTO> workStatus, ArrayList<EmployeeDTO> emps, HttpServletRequest request) {
		int year = 0;
		int month = 0;

		Calendar cal = Calendar.getInstance();

		String choiceYear = request.getParameter("year");
		String choiceMonth = request.getParameter("month");

		if (choiceYear != null) {
			year = Integer.parseInt(choiceYear);
		} else {
			year = cal.get(Calendar.YEAR);
		}

		if (choiceMonth != null) {
			month = Integer.parseInt(choiceMonth);
		} else {
			month = cal.get(Calendar.MONTH) + 1;
		}

		cal.set(year, month - 1, 1);
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;
		int lastDay = cal.getActualMaximum(Calendar.DATE);
		int week = cal.get(Calendar.DAY_OF_WEEK) - 1;

		String[] dayOfWeek = {"日", "月", "火", "水", "木", "金", "土"};
		String[] dayOfWeekArr = new String[lastDay];
		
		int count = 0;
		int i = week;
		while(true) {
			dayOfWeekArr[count] = dayOfWeek[i];
			
			count++;
			i++;
			
			if(count == dayOfWeekArr.length) {
				break;
			}

			if(i == 7) {
				i = 0;
			}
		}

		HashMap<String,String[]> calendarList = new HashMap<String, String[]>();
		for(EmployeeDTO emp : emps) {
			String[] arr = new String[lastDay];
			for(int j=0; j<arr.length; j++) {
				arr[j] = "1";
			}
			calendarList.put(emp.getE_no(), arr);
		}
		
		
		for (int j = 0; j < calendarList.size(); j++) {
			
			for(EmployeeDTO emp : emps) {
				String[] empMapArr = calendarList.get(emp.getE_no());
					
				for (ShiftDTO test : workStatus) {
						
					if(emp.getE_no().equals(test.getE_id())) {
						String[] temp = test.getW_date().split("-");
							int arrIndex = Integer.parseInt(temp[2])-1;
							empMapArr[arrIndex] = test.getW_n();
							
						}
					}
			}
		}
		request.setAttribute("emps", emps);
		request.setAttribute("calendarMap", calendarList);
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("lastDay", lastDay);
		request.setAttribute("dayOfWeek", dayOfWeekArr);
	}
}
