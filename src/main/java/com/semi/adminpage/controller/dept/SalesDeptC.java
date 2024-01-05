package com.semi.adminpage.controller.dept;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.adminpage.dept.DeptDAO;
import com.semi.adminpage.staff.StaffDAO;
import com.semi.login.EmployeeDAO;

@WebServlet("/SalesDeptC")
public class SalesDeptC extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(EmployeeDAO.getEmployeeManager().loginCheck(request)) {
			if(EmployeeDAO.getEmployeeManager().authorization(request, 1)) {
				DeptDAO.getContractDeptInfo(request, 2);
				StaffDAO.getContractStaffsInfo(request, 2);
				request.setAttribute("selectedPage", "salesDept");
				request.setAttribute("contentPage", "dept/sales_dept_info.jsp");
				request.getRequestDispatcher("sjh/admin/admin_index.jsp").forward(request, response);
			} else
				request.getRequestDispatcher("HC").forward(request, response);
		}
		else 
			response.sendRedirect("LoginC"); 
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
