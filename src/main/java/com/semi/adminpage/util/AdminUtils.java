package com.semi.adminpage.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.semi.adminpage.staff.StaffDTO;

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
	
	public static <T> void setPaging(HttpServletRequest request, ArrayList<T> arrayList, int itemsPerPage) {
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
        request.setAttribute("staffsInfo", arrayList.subList(startIndex, endIndex));
        
        int startPageIndex = 1;
        int count = 0;
        if(currentPage > 3 && totalPages > 5) {
        	startPageIndex = currentPage-2;
        	for(int i = startPageIndex; i <= currentPage + 2; i++)
        		indexList[count++] = i;
        } else {
        	for(int i = startPageIndex; i <= totalPages; i++)
        		indexList[count++] = i;
        }
        request.setAttribute("indexList", indexList);
				
	}
}
