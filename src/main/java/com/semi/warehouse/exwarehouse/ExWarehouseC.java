package com.semi.warehouse.exwarehouse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ExWarehouseC")
public class ExWarehouseC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		ExWarehouseDAO.getEwdao().getExAll(request);
		
		request.getRequestDispatcher("sj/warehouse/ex_warehouse/ex_warehouse_interface.jsp").forward(request, response);
	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
//			ExWarehouseDAO.getEwdao().regExWare(request);
//			ExWarehouseDAO.getEwdao().updateExWareStatus(request);
//			ExWarehouseDAO.getEwdao().upStock(request);
//			ExWarehouseDAO.getEwdao().getExDetail(request);
//		
//		response.sendRedirect("sj/warehouse/ex_warehouse/ex_warehouse_interface.jsp");
//		request.getRequestDispatcher("sj/warehouse/ex_warehouse/ex_warehouse_interface.jsp").forward(request, response);
	}

}
