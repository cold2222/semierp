package com.semi.warehouse.inexwarehouse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.warehouse.inwarehouse.InWarehouseDAO;

@WebServlet("/InWarehousePageC")
public class InWarehousePageC extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InWarehouseDAO.getTwdao().getAll(request);
		InWarehouseDAO.getTwdao().paging(Integer.parseInt(request.getParameter("pageNum")), request);
		request.getRequestDispatcher("sj/warehouse/in_warehouse/in_warehouse_interface.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
