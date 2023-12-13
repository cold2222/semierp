package com.semi.warehouse.testcheck;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/InExBoardTestC")
public class InExBoardTestC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 if ("all".equals(request.getParameter("operationType"))) {
	            // 전체를 선택한 경우 모든 데이터를 가져옴
	            ExWarehouseTestDAO.getExWareTest(request);
	            TestwarehouseDAO.getInWareTest(request);
	        } else if ("exWarehouse".equals(request.getParameter("operationType"))) {
	            // 출고만 선택한 경우 출고 데이터만 가져옴
	            ExWarehouseTestDAO.getExWareTest(request);
	        } else if ("inWarehouse".equals(request.getParameter("operationType"))) {
	            // 입고만 선택한 경우 입고 데이터만 가져옴
	            TestwarehouseDAO.getInWareTest(request);
	        }
		
		

		request.getRequestDispatcher("sj/warehouse/in_ex_board.jsp").forward(request, response);
				
	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
