package com.semi.warehouse.inexwarehouse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/InExBoardC")
public class InExBoardC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageNum = 1; // 기본 페이지 번호
		
	    try {
	        // pageNum 파라미터가 있을 경우 파라미터 값을 사용
	        pageNum = Integer.parseInt(request.getParameter("pageNum"));
	    } catch (NumberFormatException e) {
	        // 예외 발생 시 기본값 사용
	    }

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
	        
        
	        
	        InExWarehouseDAO.getIedao().paging(pageNum, request);
	        request.getRequestDispatcher("sj/warehouse/in_ex_warehouse/in_ex_board.jsp").forward(request, response);
	    }
	
		
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
