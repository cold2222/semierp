package com.semi.warehouse.inexwarehouse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/InWarehouseC")
public class InWarehouseC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		InWarehouseDAO.getTwdao().getAll(request);
		
		
		
		
//		request.setAttribute("contentPage", "sj/warehouse/warehouse.jsp");
		request.getRequestDispatcher("sj/warehouse/in_warehouse_interface.jsp").forward(request, response);
	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		if (request.getParameter("selectedIds") != null) {
			InWarehouseDAO.getTwdao().regInWare(request);
			InWarehouseDAO.getTwdao().updateInWareStatus(request);
			// 계산테이블에 등록
			InWarehouseDAO.getTwdao().regStock(request);
			// 계산테이블에 + 
			InWarehouseDAO.getTwdao().upStock(request);
			
			//계산 테이블 보여주기
			InWarehouseDAO.getTwdao().getAll(request);
			
		} else {
			InWarehouseDAO.getTwdao().getAll(request);
			
		}
		
		request.getRequestDispatcher("sj/warehouse/in_warehouse_interface.jsp").forward(request, response);
	}

}
