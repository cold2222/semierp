package com.semi.statistics.controller.distribution;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.login.EmployeeDAO;
import com.semi.statistics.distribution.StatisticsDistributionDAO;
import com.semi.statistics.product.StatisticsProductDAO;

@WebServlet("/StatisticDistributionC")
public class StatisticDistributionC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(EmployeeDAO.getEmployeeManager().loginCheck(request)) {
			// 기능 위치
			StatisticsDistributionDAO.getDistributionStatistic(request, response);
			request.setAttribute("sidebar","sjh/statistics/statistics_sidebar.jsp");
			request.setAttribute("contentPage","sjh/statistics/distribution/distribution_statistic.jsp");
			
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
