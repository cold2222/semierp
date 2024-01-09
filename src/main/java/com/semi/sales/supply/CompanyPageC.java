package com.semi.sales.supply;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.login.EmployeeDAO;

@WebServlet("/CompanyPageC")
public class CompanyPageC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (EmployeeDAO.getEmployeeManager().loginCheck(request)) {
			SupplyComDAO.getSdao().getAllCom(request);
			int p = Integer.parseInt(request.getParameter("p"));
			SupplyComDAO.getSdao().paging(p, request);
			request.setAttribute("contentPage", "jh/company/supply_company.jsp");
			request.setAttribute("sidebar", "jh/sidebar.jsp");
			request.setAttribute("selectedHeader", "contract");
			request.getRequestDispatcher("index2.jsp").forward(request, response);
		} else
			response.sendRedirect("LoginC");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
