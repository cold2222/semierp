package com.semi.adminpage.controller.inform;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.adminpage.inform.CompanyInformDAO;
import com.semi.login.EmployeeDAO;


@WebServlet("/CompanyInformModifyC")
public class CompanyInformModifyC extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(EmployeeDAO.getEmployeeManager().loginCheck(request)) {
			if(EmployeeDAO.getEmployeeManager().authorization(request, 1)) {
				CompanyInformDAO.getCompanyInfromManager().getCompanyInform(request);
				request.setAttribute("selectedPage", "companyInform");
				request.setAttribute("contentPage", "inform/company_inform_modify.jsp");
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
