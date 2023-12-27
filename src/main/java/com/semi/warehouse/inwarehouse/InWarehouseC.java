package com.semi.warehouse.inwarehouse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/InWarehouseC")
public class InWarehouseC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InWarehouseDAO.getTwdao().getAll(request);
		InWarehouseDAO.getTwdao().paging(1, request);
	
		request.setAttribute("sidebar","sj/sidebar.jsp");
		request.setAttribute("contentPage","sj/warehouse/in_warehouse/in_warehouse_interface.jsp");
		request.getRequestDispatcher("index2.jsp").forward(request, response);
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
