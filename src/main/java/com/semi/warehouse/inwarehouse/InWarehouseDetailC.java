package com.semi.warehouse.inwarehouse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/InWarehouseDetailC")
public class InWarehouseDetailC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		InWarehouseDAO.getTwdao().getInDetail(request);
		
		
		
		
		request.getRequestDispatcher("sj/warehouse/in_warehouse/in_warehouse_detail.jsp").forward(request, response);
	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
			InWarehouseDAO.getTwdao().regInWare(request);
			InWarehouseDAO.getTwdao().updateInWareStatus(request);
			InWarehouseDAO.getTwdao().regStock(request);
			InWarehouseDAO.getTwdao().upStock(request);
			
			response.sendRedirect("sj/warehouse/in_warehouse/in_warehouse_interface.jsp");
		//request.getRequestDispatcher("sj/warehouse/in_warehouse/in_warehouse_interface.jsp").forward(request, response);
	}

}
