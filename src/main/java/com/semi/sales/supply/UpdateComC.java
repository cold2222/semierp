package com.semi.sales.supply;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/UpdateComC")
public class UpdateComC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	SupplyComDAO.getSdao().getCom(request);
	request.setAttribute("contentPage", "jh/company/updateCom.jsp");
	request.setAttribute("sidebar", "jh/sidebar.jsp");
	request.getRequestDispatcher("index2.jsp").forward(request, response);
		
		
		
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	SupplyComDAO.getSdao().updateCom(request);
	response.sendRedirect("UpdateComC?c_no="+request.getParameter("c_no")+"&isSuccess="
			+ request.getAttribute("isSuccess"));	
	
	}

}
