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
	
	SupplyComDAO.getSdao().getCom(request);
	request.setAttribute("page", "contract/contract_reg.jsp");
	request.getRequestDispatcher("jh/index.jsp").forward(request, response);
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	SupplyComDAO.regCont(request);
	SupplyComDAO.getAllCont(request);
	request.setAttribute("page", "contract_output.jsp");
	request.getRequestDispatcher("jh/index.jsp").forward(request, response);
	
	
	
	
	}

}
