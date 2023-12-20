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
		request.setCharacterEncoding("UTF-8");
		
		if (request.getParameter("selectedIds") != null) {
			ExWarehouseTestDAO.getEwdao().regExWareTest(request);
			ExWarehouseTestDAO.getEwdao().updateExWareTest(request);
			ExWarehouseTestDAO.getEwdao().upStockMTest(request);
			ExWarehouseTestDAO.getEwdao().getExAllTest(request);
			// 확인하고 지우기
//			ExWarehouseTestDAO.getEwdao().getExWareTest(request);
		} else {
			ExWarehouseTestDAO.getEwdao().getExAllTest(request);
		}
		
		
		request.getRequestDispatcher("sj/warehouse/ex_warehouse_interface.jsp").forward(request, response);
//		request.getRequestDispatcher("sj/warehouse/ex_warehouse.jsp").forward(request, response);
	}

}
