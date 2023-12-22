package com.semi.sales.supply;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ContractPageC")
public class ContractPageC extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SupplyComDAO.getSdao().getAllCont(request);
		SupplyComDAO.getSdao().pagingContract(Integer.parseInt(request.getParameter("pageNum")), request);
		request.setAttribute("contentPage", "jh/contract/contract_company.jsp");
		request.setAttribute("sidebar", "jh/sidebar.jsp");
		request.getRequestDispatcher("index2.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}