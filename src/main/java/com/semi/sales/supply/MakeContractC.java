package com.semi.sales.supply;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/MakeContractC")
public class MakeContractC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	request.setAttribute("contentPage", "jh/contract/contract_reg.jsp");
	request.setAttribute("sidebar", "jh/sidebar.jsp");
	request.getRequestDispatcher("index2.jsp").forward(request, response);
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	SupplyComDAO.getSdao().regCont(request);
	SupplyComDAO.getSdao().regContents(request);
	SupplyComDAO.getSdao().getCont(request);
	SupplyComDAO.getSdao().getContent(request);
	request.setAttribute("contentPage", "jh/contract/contract_output.jsp");
	request.setAttribute("sidebar", "jh/sidebar.jsp");
	request.getRequestDispatcher("index2.jsp").forward(request, response);
	
	
	
	
	}

}
