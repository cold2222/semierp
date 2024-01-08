package com.semi.sales.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.login.EmployeeDAO;
import com.semi.sales.supply.SupplyComDAO;

@WebServlet("/UpdateProductC")
public class UpdateProductC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (EmployeeDAO.getEmployeeManager().loginCheck(request)) {
			if (EmployeeDAO.getEmployeeManager().authorization(request, 101)
					|| EmployeeDAO.getEmployeeManager().authorization(request, 102)) {
		ProductDAO.getPdao().getP(request);
		ProductDAO.getPdao().getAllUnit(request);
		ProductDAO.getPdao().getAllType(request);
		request.setAttribute("contentPage", "jh/product/updateProduct.jsp");
		request.setAttribute("sidebar", "jh/sidebar.jsp");
		request.setAttribute("selectedHeader", "contract");
		request.getRequestDispatcher("index2.jsp").forward(request, response);
			} else
				request.getRequestDispatcher("ProductC").forward(request, response);
		} else
			response.sendRedirect("LoginC");
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (EmployeeDAO.getEmployeeManager().loginCheck(request)) {
			if (EmployeeDAO.getEmployeeManager().authorization(request, 101)
					|| EmployeeDAO.getEmployeeManager().authorization(request, 102)) {
		ProductDAO.getPdao().updateProduct(request);
		System.out.println(request.getParameter("id"));
		response.sendRedirect("UpdateProductC?id="+request.getParameter("id"));	
			} else
				request.getRequestDispatcher("ProductC").forward(request, response);
		} else
			response.sendRedirect("LoginC");
	}

}
