package com.semi.sales.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.login.EmployeeDAO;

@WebServlet("/ProductPageC")
public class ProductPageC extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (EmployeeDAO.getEmployeeManager().loginCheck(request)) {
		ProductDAO.getPdao().getAllProduct(request);
		ProductDAO.getPdao().getAllUnit(request);
		ProductDAO.getPdao().getAllType(request);
		ProductDAO.getPdao().paging(Integer.parseInt(request.getParameter("pageNum")), request);
		request.setAttribute("contentPage", "jh/product/supply_product.jsp");
		request.setAttribute("sidebar", "jh/sidebar.jsp");
		request.setAttribute("selectedHeader", "contract");
		request.getRequestDispatcher("index2.jsp").forward(request, response);
		} else
			response.sendRedirect("LoginC");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
