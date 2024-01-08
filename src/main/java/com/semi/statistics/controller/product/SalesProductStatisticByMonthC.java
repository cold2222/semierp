package com.semi.statistics.controller.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.login.EmployeeDAO;
import com.semi.statistics.product.StatisticsProductDAO;


@WebServlet("/SalesProductStatisticByMonthC")
public class SalesProductStatisticByMonthC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(EmployeeDAO.getEmployeeManager().loginCheck(request)) {
			// 기능 위치
			StatisticsProductDAO.getProductsOrderByCountAll(request, 2);
			request.setAttribute("sidebar","sjh/statistics/statistics_sidebar.jsp");
			request.setAttribute("contentPage","sjh/statistics/product/import_product_static.jsp");
			
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
