package com.semi.warehouse.warehouseboard;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/WarehouseBoardTestC")
public class WarehouseBoardTestC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
String operationType = request.getParameter("operationType");
        
        WarehouseBoardTestDAO.getWBTest(request, operationType);
	
		
		
		request.getRequestDispatcher("sj/warehouse_board/warehouse_board.jsp").forward(request, response);
				
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
