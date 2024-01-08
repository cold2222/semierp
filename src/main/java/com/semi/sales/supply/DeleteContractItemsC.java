package com.semi.sales.supply;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.login.EmployeeDAO;

@WebServlet("/DeleteContractItemsC")
public class DeleteContractItemsC extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (EmployeeDAO.getEmployeeManager().loginCheck(request)) {
			if (EmployeeDAO.getEmployeeManager().authorization(request, 101)
					|| EmployeeDAO.getEmployeeManager().authorization(request, 102)) {
		SupplyComDAO.getSdao().deleteContractItem(request);
		response.sendRedirect("UpdateContC?no="+request.getParameter("c_contract_no"));
			} else
				request.getRequestDispatcher("ContractC").forward(request, response);
		} else
			response.sendRedirect("LoginC");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
