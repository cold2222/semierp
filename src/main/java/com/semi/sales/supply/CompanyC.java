package com.semi.sales.supply;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.login.EmployeeDAO;
import com.semi.sales.product.ProductDAO;

@WebServlet("/CompanyC")
public class CompanyC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (EmployeeDAO.getEmployeeManager().loginCheck(request)) {
				SupplyComDAO.getSdao().getAllCom(request);
				SupplyComDAO.getSdao().paging(1, request);
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
