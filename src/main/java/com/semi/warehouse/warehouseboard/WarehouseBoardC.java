package com.semi.warehouse.warehouseboard;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/WarehouseBoardC")
public class WarehouseBoardC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operationType = request.getParameter("operationType");
        WarehouseBoardDAO.getWbtdao().getWBTest(request, operationType);
        WarehouseBoardDAO.getWbtdao().calcStock(request, operationType);
        WarehouseBoardDAO.getWbtdao().paging(1, request);
		
		request.setAttribute("sidebar","sj/sidebar.jsp");
		request.setAttribute("contentPage","sj/warehouse/warehouse_stock_board/warehouse_board.jsp");
		request.getRequestDispatcher("index2.jsp").forward(request, response);

		
		
				
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
