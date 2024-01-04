package com.semi.adminpage.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.semi.adminpage.staff.StaffDTO;
import com.semi.login.EmployeeDTO;

public class AdminUtils {
	// 현재 날짜의 yyyy-MM
	public static String getCurrentYearMonth() {
		// 현재 날짜 정보 가져오기
		LocalDate currentDate = LocalDate.now();

		// 형식화된 년월 가져오기 (YY-MM 형식)
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
		String currentYearMonth = currentDate.format(formatter);

		return currentYearMonth;
	}

	// 현재 날자의 yyyy-MM-DD
	public static String getCurrentDate() {
		// 현재 날짜 가져오기
		LocalDate currentDate = LocalDate.now();

		// 날짜 포맷 지정
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		// 포맷 적용하여 출력
		String formattedDate = currentDate.format(formatter);
		return formattedDate;
	}

	// 파라미터의 yyyy-MM-DD 값 리턴, 값이 없을 경우 현재의 날자값 기준으로 반환
	public static String getParamDate(HttpServletRequest request) {
		String ParamDate = request.getParameter("setDate");
		if (ParamDate == null || ParamDate.isEmpty()) {
			ParamDate = AdminUtils.getCurrentDate();
		}
		return ParamDate;
	}

	// 파라미터의 yyyy-MM 값 리턴, 값이 없을 경우 현재의 날자값 기준으로 반환
	public static String getParamYearMonth(HttpServletRequest request) {
		String ParamDate = request.getParameter("setDate");
		if (ParamDate == null || ParamDate.isEmpty()) {
			return getCurrentYearMonth();
		}
		String[] dateSplit = ParamDate.split("-");
		return dateSplit[0] + "-" + dateSplit[1];

	}

	// 페이징 처리(request, 대상 DTO배열, 한페이지당 표시할 열 갯수)
	public static <T> List<T> setPaging(HttpServletRequest request, ArrayList<T> arrayList, int itemsPerPage) {
		// 페이징
		int totalItems = arrayList.size();
		int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);
		int currentPage = 1;
		int[] indexList = new int[5];
		String pageNoParam = request.getParameter("pageNo");
		if (pageNoParam != null && !pageNoParam.isEmpty())
			currentPage = Integer.parseInt(pageNoParam);

		int startIndex = (currentPage - 1) * itemsPerPage;
		int endIndex = Math.min(startIndex + itemsPerPage, totalItems);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lastPage", totalPages);

		int startPageIndex = 1;
		int count = 0;
		if (currentPage > 3 && totalPages > 5) {
			startPageIndex = currentPage - 2;
			for (int i = startPageIndex; i <= currentPage + 2; i++)
				indexList[count++] = i;
		} else {
			for (int i = startPageIndex; i <= totalPages; i++)
				indexList[count++] = i;
		}
		request.setAttribute("indexList", indexList);

		return arrayList.subList(startIndex, endIndex);

	}

	public static <T> List<T> setPagingWithIndex(HttpServletRequest request, ArrayList<T> arrayList, int itemsPerPage,
			int index) {
		// 페이징
		int totalItems = arrayList.size();
		int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);
		int currentPage = 1;
		int[] indexList = new int[5];
		String pageNoParam = request.getParameter("pageNo" + index);
		if (pageNoParam != null && !pageNoParam.isEmpty())
			currentPage = Integer.parseInt(pageNoParam);

		int startIndex = (currentPage - 1) * itemsPerPage;
		int endIndex = Math.min(startIndex + itemsPerPage, totalItems);
		request.setAttribute("currentPage" + index, currentPage);
		request.setAttribute("lastPage" + index, totalPages);

		int startPageIndex = 1;
		int count = 0;
		if (currentPage > 3 && totalPages > 5) {
			startPageIndex = currentPage - 2;
			for (int i = startPageIndex; i <= currentPage + 2; i++)
				indexList[count++] = i;
		} else {
			for (int i = startPageIndex; i <= totalPages; i++)
				indexList[count++] = i;
		}
		request.setAttribute("indexList" + index, indexList);

		return arrayList.subList(startIndex, endIndex);

	}
	
	public static int sumCi_deptsValues(String[] ci_depts) {
		int sum = 0;
		for (String dept : ci_depts) {
			sum += Integer.parseInt(dept);
		}
		
		return sum;
	}
	
	public static int getSessionDeptIndex(HttpServletRequest request) {
		int[] deptLib = {1, 101, 102, 201, 202};
		EmployeeDTO empInfo = (EmployeeDTO) request.getSession().getAttribute("empInfo");
		
		int idx = -1;
		System.out.println(empInfo.getE_deptno());
		int deptno = empInfo.getE_deptno();
		for(int i = 0; i < deptLib.length; i++) {
			if(deptLib[i] == deptno)
				idx = i;
		}
		
		return idx;
	}
}
