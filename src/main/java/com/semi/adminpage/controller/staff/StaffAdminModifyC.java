package com.semi.adminpage.controller.staff;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.adminpage.dept.DeptDAO;
import com.semi.adminpage.staff.StaffDAO;
import com.semi.login.EmployeeDAO;

@WebServlet("/StaffAdminModifyC")
public class StaffAdminModifyC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(EmployeeDAO.getEmployeeManager().loginCheck(request)) {
			if(EmployeeDAO.getEmployeeManager().authorization(request, 1)) {
				DeptDAO.getDeptManager().getDepts(request);
				StaffDAO.getStaffManager().getStaffInfo(request);
				request.setAttribute("selectedPage", "staff");
				request.setAttribute("contentPage", "staff/staff_modify.jsp");
				request.getRequestDispatcher("sjh/admin/admin_index.jsp").forward(request, response);
			} else
				request.getRequestDispatcher("HC").forward(request, response);
		}
		else 
			response.sendRedirect("LoginC");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(EmployeeDAO.getEmployeeManager().loginCheck(request)) {
			if(EmployeeDAO.getEmployeeManager().authorization(request, 1)) {
				StaffDAO.getStaffManager().modifyStaff(request);
				response.sendRedirect("StaffAdminModifyC?e_no="+request.getParameter("e_no"));
			} else
				request.getRequestDispatcher("HC").forward(request, response);
		}
		else 
			response.sendRedirect("LoginC");
	}

}
