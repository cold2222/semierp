package com.semi.sales.supply;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateContentC")
public class UpdateContentC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		SupplyComDAO.getContent(request);
		request.setAttribute("page", "content/updateContent.jsp");
		request.getRequestDispatcher("jh/index.jsp").forward(request, response);
	
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		SupplyComDAO.updateContent(request);
		response.sendRedirect("UpdateContentC?num="+request.getParameter("num")+"&isSuccess="
				+ request.getAttribute("isSuccess"));	
	
	
	
	}

}
