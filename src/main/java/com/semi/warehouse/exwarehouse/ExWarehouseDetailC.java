package com.semi.warehouse.exwarehouse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.login.EmployeeDAO;

@WebServlet("/ExWarehouseDetailC")
public class ExWarehouseDetailC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (EmployeeDAO.getEmployeeManager().loginCheck(request)) {
			
			if (EmployeeDAO.getEmployeeManager().authorization(request, 202)) {

				ExWarehouseDAO.getEwdao().getExDetail(request);
				ExWarehouseDAO.getEwdao().getSelectedContract(request);
				request.setAttribute("selectedHeader", "stock");
				request.setAttribute("sidebar", "sj/sidebar.jsp");
				request.setAttribute("contentPage", "sj/warehouse/ex_warehouse/ex_warehouse_detail.jsp");
				request.getRequestDispatcher("index2.jsp").forward(request, response);
			} else {
				// 권한이 없는 경우에 대한 처리(들어온 페이지로)
				request.getRequestDispatcher("ExWarehouseC").forward(request, response);
			}
		} else {
			// 로그인되지 않은 경우 로그인 페이지로 리다이렉트
			response.sendRedirect("LoginC");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ExWarehouseDAO.getEwdao().regExWare(request);
		ExWarehouseDAO.getEwdao().updateContractStatus3(request);
		ExWarehouseDAO.getEwdao().upStock(request);
		response.sendRedirect("ExWarehouseC");
	}

}
