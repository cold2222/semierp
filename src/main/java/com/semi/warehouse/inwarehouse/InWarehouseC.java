package com.semi.warehouse.inwarehouse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.login.EmployeeDAO;

@WebServlet("/InWarehouseC")
public class InWarehouseC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (EmployeeDAO.getEmployeeManager().loginCheck(request)) {
			// 권한 확인 일단은 202만 들어가게 설정해놓음
			if (EmployeeDAO.getEmployeeManager().authorization(request, 201)|| EmployeeDAO.getEmployeeManager().authorization(request, 202)) {

				InWarehouseDAO.getTwdao().getAll(request);
				InWarehouseDAO.getTwdao().paging(1, request);

				request.setAttribute("sidebar", "sj/sidebar.jsp");
				request.setAttribute("contentPage", "sj/warehouse/in_warehouse/in_warehouse_interface.jsp");
				request.setAttribute("selectedHeader", "stock");
				request.getRequestDispatcher("index2.jsp").forward(request, response);
			} else {
				// 권한이 없는 경우에 대한 처리(들어온 페이지로)
				request.getRequestDispatcher("WarehouseBoardC").forward(request, response);
			}
		} else {
			// 로그인되지 않은 경우 로그인 페이지로 리다이렉트
			response.sendRedirect("LoginC");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
