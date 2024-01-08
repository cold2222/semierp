package com.semi.sales.supply;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.login.EmployeeDAO;

@WebServlet("/CompanyRegC")
public class CompanyRegC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (EmployeeDAO.getEmployeeManager().loginCheck(request)) {
			if (EmployeeDAO.getEmployeeManager().authorization(request, 101)
					|| EmployeeDAO.getEmployeeManager().authorization(request, 102)) {
				request.setAttribute("contentPage", "jh/company/company_reg.jsp");
				request.setAttribute("sidebar", "jh/sidebar.jsp");
				request.setAttribute("selectedHeader", "contract");
				request.getRequestDispatcher("index2.jsp").forward(request, response);
			} else
				request.getRequestDispatcher("CompanyC").forward(request, response);
		} else
			response.sendRedirect("LoginC");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (EmployeeDAO.getEmployeeManager().loginCheck(request)) {
			if (EmployeeDAO.getEmployeeManager().authorization(request, 101)
					|| EmployeeDAO.getEmployeeManager().authorization(request, 102)) {

				SupplyComDAO.getSdao().regCom(request);
				response.sendRedirect("CompanyC");
			} else
				request.getRequestDispatcher("CompanyC").forward(request, response);
		} else
			response.sendRedirect("LoginC");
	}

}
