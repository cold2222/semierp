package com.semi.adminpage.controller.dept;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.adminpage.dept.DeptDAO;
import com.semi.adminpage.util.AdminUtils;
import com.semi.login.EmployeeDAO;

@WebServlet("/DeptC")
public class DeptC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(EmployeeDAO.getEmployeeManager().loginCheck(request)) {
			if(EmployeeDAO.getEmployeeManager().authorization(request, 1)) {
				DeptDAO.getDeptManager().getContractDeptInfo(request, 1);
				DeptDAO.getDeptManager().getContractDeptInfo(request, 2);
				DeptDAO.getDeptManager().getDistributionDeptInfo(request);
				DeptDAO.getDeptManager().getWarehouseDeptInfo(request);
				request.setAttribute("currentDate", AdminUtils.getCurrentDate());
				request.setAttribute("selectedPage", "dept");
				request.setAttribute("contentPage", "dept/dept_info.jsp");
				request.getRequestDispatcher("sjh/admin/admin_index.jsp").forward(request, response);
			} else
				request.getRequestDispatcher("HC").forward(request, response);
		}
		else 
			response.sendRedirect("LoginC");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
