package com.semi.warehouse.inwarehouse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.login.EmployeeDAO;

@WebServlet("/InWarehouseDetailC")
public class InWarehouseDetailC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (EmployeeDAO.getEmployeeManager().loginCheck(request)) {
			
			if (EmployeeDAO.getEmployeeManager().authorization(request, 202)) {

				InWarehouseDAO.getTwdao().getSelectedContract(request);
				InWarehouseDAO.getTwdao().getInDetail(request);
				request.setAttribute("sidebar", "sj/sidebar.jsp");
				request.setAttribute("contentPage", "sj/warehouse/in_warehouse/in_warehouse_detail.jsp");
				request.setAttribute("selectedHeader", "stock");
				request.getRequestDispatcher("index2.jsp").forward(request, response);
			} else {
				// 권한이 없는 경우에 대한 처리(들어온 페이지로)
				request.getRequestDispatcher("InWarehouseC").forward(request, response);
			}
		} else {
			// 로그인되지 않은 경우 로그인 페이지로 리다이렉트
			response.sendRedirect("LoginC");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		InWarehouseDAO.getTwdao().regInWare(request);
		InWarehouseDAO.getTwdao().updateContractStatus4(request);
		InWarehouseDAO.getTwdao().regStock(request);
		InWarehouseDAO.getTwdao().upStock(request);
		response.sendRedirect("InWarehouseC");
	}

}
