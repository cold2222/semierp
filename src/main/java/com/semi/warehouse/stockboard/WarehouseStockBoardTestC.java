package com.semi.warehouse.stockboard;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/WarehouseStockBoardTestC")
public class WarehouseStockBoardTestC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        WarehouseStockBoardTestDAO.getWSBTest(request);
        
		request.getRequestDispatcher("sj/warehouse/stock_board/stock_board.jsp").forward(request, response);
				
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
