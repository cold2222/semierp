package com.semi.sales.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.login.EmployeeDAO;

@WebServlet("/DeleteProductC")
public class DeleteProductC extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (EmployeeDAO.getEmployeeManager().loginCheck(request)) {
			if (EmployeeDAO.getEmployeeManager().authorization(request, 101)
					|| EmployeeDAO.getEmployeeManager().authorization(request, 102)) {
		ProductDAO.getPdao().deleteProduct(request);
		response.sendRedirect("ProductC");
			} else
				request.getRequestDispatcher("ProductC").forward(request, response);
		} else
			response.sendRedirect("LoginC");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
