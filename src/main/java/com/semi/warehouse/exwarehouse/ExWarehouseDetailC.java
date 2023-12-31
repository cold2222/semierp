package com.semi.warehouse.exwarehouse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ExWarehouseDetailC")
public class ExWarehouseDetailC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ExWarehouseDAO.getEwdao().getExDetail(request);
		ExWarehouseDAO.getEwdao().getSelectedContract(request);
		request.setAttribute("selectedHeader", "stock");
		request.setAttribute("sidebar","sj/sidebar.jsp");
        request.setAttribute("contentPage","sj/warehouse/ex_warehouse/ex_warehouse_detail.jsp");
        request.getRequestDispatcher("index2.jsp").forward(request, response);
	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			ExWarehouseDAO.getEwdao().regExWare(request);
			ExWarehouseDAO.getEwdao().updateContractStatus3(request);
			ExWarehouseDAO.getEwdao().upStock(request);
			response.sendRedirect("ExWarehouseC");
	}

}
