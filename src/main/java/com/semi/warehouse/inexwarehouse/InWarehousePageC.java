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
		request.setAttribute("sidebar","sj/sidebar.jsp");
        request.setAttribute("contentPage","sj/warehouse/in_ex_warehouse/in_ex_board.jsp");
        request.getRequestDispatcher("index2.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
