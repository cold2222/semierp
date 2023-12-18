package com.semi.sales.supply;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CompanyRegC")
public class CompanyRegC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SupplyComDAO.getSdao().getCom(request);
		request.setAttribute("contentPage", "jh/company/company_reg.jsp");
		request.setAttribute("sidebar", "jh/sidebar.jsp");
		request.getRequestDispatcher("index2.jsp").forward(request, response);
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		SupplyComDAO.getSdao().regCom(request);
		SupplyComDAO.getSdao().getAllCom(request);
		request.setAttribute("contentPage", "jh/company/output_supply.jsp");
		request.setAttribute("sidebar", "jh/sidebar.jsp");
		request.getRequestDispatcher("index2.jsp").forward(request, response);
	
	
	}

}
