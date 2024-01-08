package com.semi.statistics.controller.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.login.EmployeeDAO;
import com.semi.statistics.contract.ContractDAO;


@WebServlet("/ProductStatisticByMonthC")
public class ProductStatisticByMonthC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(EmployeeDAO.getEmployeeManager().loginCheck(request)) {
			// 기능 위치
			ContractDAO.getContractsInfo(request);
			request.setAttribute("sidebar","sjh/statistics/statistics_sidebar.jsp");
			request.setAttribute("contentPage","sjh/statistics/statistics_main.jsp");
			
			request.setAttribute("selectedHeader", "statistics");
			
			request.getRequestDispatcher("index2.jsp").forward(request, response);
		}
		else 
			response.sendRedirect("LoginC");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
