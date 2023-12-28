package com.semi.distribution.shift;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.distribution.test.TestDAO;

@WebServlet("/DistributionShiftC")
public class DistributionShiftC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShiftDAO.getCalendarStatus(ShiftDAO.getWorkStatus(request),ShiftDAO.getEmployee(),request);
		request.setAttribute("sidebar","sb/distribution/distribution_sidebar.jsp");
		request.setAttribute("contentPage","sb/distribution/shift/shift.jsp");
		request.setAttribute("selectedHeader", "distribution");
		request.getRequestDispatcher("index2.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
