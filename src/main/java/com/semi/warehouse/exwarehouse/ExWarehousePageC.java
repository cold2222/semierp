package com.semi.warehouse.exwarehouse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ExWarehousePageC")
public class ExWarehousePageC extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ExWarehouseDAO.getEwdao().getExAll(request);
		ExWarehouseDAO.getEwdao().paging(Integer.parseInt(request.getParameter("pageNum")), request);
		request.setAttribute("sidebar","sj/sidebar.jsp");
        request.setAttribute("contentPage","sj/warehouse/ex_warehouse/ex_warehouse_interface.jsp");
        request.getRequestDispatcher("index2.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
