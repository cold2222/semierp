package com.semi.sales.supply;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.login.EmployeeDAO;

@WebServlet("/UpdateContC")
public class UpdateContC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (EmployeeDAO.getEmployeeManager().loginCheck(request)) {
			if (EmployeeDAO.getEmployeeManager().authorization(request, 101)
					|| EmployeeDAO.getEmployeeManager().authorization(request, 102)) {
		SupplyComDAO.getSdao().getContractDetail(request);
		request.setAttribute("contentPage", "jh/contract/updateContract.jsp");
		request.setAttribute("sidebar", "jh/sidebar.jsp");
		request.setAttribute("selectedHeader", "contract");
		request.getRequestDispatcher("index2.jsp").forward(request, response);
			} else
				request.getRequestDispatcher("ContractC").forward(request, response);
		} else
			response.sendRedirect("LoginC");
	
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (EmployeeDAO.getEmployeeManager().loginCheck(request)) {
			if (EmployeeDAO.getEmployeeManager().authorization(request, 101)
					|| EmployeeDAO.getEmployeeManager().authorization(request, 102)) {
		SupplyComDAO.getSdao().updateCont(request);
		SupplyComDAO.getSdao().updateContractItems(request);
		response.sendRedirect("UpdateContC?no="+request.getParameter("c_contract_no")+"&isSuccess="
				+ request.getAttribute("isSuccess"));	
			} else
				request.getRequestDispatcher("ContractC").forward(request, response);
		} else
			response.sendRedirect("LoginC");
	}

}
