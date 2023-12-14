package com.semi.warehouse.testcheck;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/TestwarehouseC")
public class TestwarehouseC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		TestwarehouseDAO.getAllTest(request);
		
		
		
		
//		request.setAttribute("contentPage", "sj/warehouse/warehouse.jsp");
		request.getRequestDispatcher("sj/warehouse/in_warehouse_interface.jsp").forward(request, response);
	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		TestwarehouseDAO.regInWareTest(request);
		TestwarehouseDAO.updateInWareTest(request);
		// 계산테이블에 등록
		TestwarehouseDAO.regStockTest(request);
		// 계산테이블에 + 
		TestwarehouseDAO.upStockTest(request);
		TestwarehouseDAO.getInWareTest(request);
		request.getRequestDispatcher("sj/warehouse/warehouse.jsp").forward(request, response);
	}

}
