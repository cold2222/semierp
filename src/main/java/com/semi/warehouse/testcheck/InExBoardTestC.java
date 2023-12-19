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
		 

		 String operationType = request.getParameter("operationType");

	        if ("exWarehouse".equals(operationType)) {
	            InExWarehouseDAO.getIedao().getExWare(request);
	            InExWarehouseDAO.getIedao().paging(1,request);
	        } else if ("inWarehouse".equals(operationType)) {
	            InExWarehouseDAO.getIedao().getInWare(request);
	            InExWarehouseDAO.getIedao().paging(1,request);
	        } else if ("all".equals(operationType)) {
	            //InExWarehouseDAO.getallWare(request);
	        	InExWarehouseDAO.getIedao().getSearchedWare(request);
	        	InExWarehouseDAO.getIedao().paging(1,request);
	        } else {
	            InExWarehouseDAO.getIedao().getallWare(request);
	            InExWarehouseDAO.getIedao().paging(1,request);
	        }
	        
	        
	        
	        
	        request.getRequestDispatcher("sj/warehouse/in_ex_board.jsp").forward(request, response);
	    }
		
		
		
		
// 일단 안될때를 대비해서 놔둠 		
		
//		// 날짜별로 나열가능하도록 바꾼 것 
//		 if ("exWarehouse".equals(request.getParameter("operationType"))) {
//			 // 출고만 선택한 경우 출고 데이터만 가져옴
//			 InExWarehouseDAO.getExWare(request);
//		 } else if ("inWarehouse".equals(request.getParameter("operationType"))) {
//			 // 입고만 선택한 경우 입고 데이터만 가져옴
//			 InExWarehouseDAO.getInWare(request);
//			 // 전체 선택한 경우 
//		 } else if ("all".equals(request.getParameter("operationType"))) {
//			 InExWarehouseDAO.getallWare(request);
//		 } else {
//			 InExWarehouseDAO.getallWare(request);
//		 }
//		
//
//		 
//		 
//
//		request.getRequestDispatcher("sj/warehouse/in_ex_board.jsp").forward(request, response);
				
//	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
