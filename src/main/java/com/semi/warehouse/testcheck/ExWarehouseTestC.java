package com.semi.warehouse.testcheck;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ExWarehouseTestC")
public class ExWarehouseTestC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		ExWarehouseTestDAO.getEwdao().getExAllTest(request);

		request.getRequestDispatcher("sj/warehouse/ex_warehouse_interface.jsp").forward(request, response);
	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		ExWarehouseTestDAO.getEwdao().regExWareTest(request);
		ExWarehouseTestDAO.getEwdao().updateExWareTest(request);
		ExWarehouseTestDAO.getEwdao().upStockMTest(request);
		ExWarehouseTestDAO.getEwdao().getExWareTest(request);
		request.getRequestDispatcher("sj/warehouse/ex_warehouse.jsp").forward(request, response);
	}

}
