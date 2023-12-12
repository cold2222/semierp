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
	request.setAttribute("page", "company/updateCom.jsp");
	request.getRequestDispatcher("jh/index.jsp").forward(request, response);
		
		
		
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	SupplyComDAO.getSdao().updateCom(request);
	response.sendRedirect("UpdateComC?num="+request.getParameter("num")+"&isSuccess="
			+ request.getAttribute("isSuccess"));	
	
	}

}
