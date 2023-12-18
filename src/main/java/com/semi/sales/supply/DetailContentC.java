package com.semi.sales.supply;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DetailContentC")
public class DetailContentC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SupplyComDAO.getSdao().searchContent(request);
		request.setAttribute("contentPage", "jh/content/content_company.jsp");
		request.setAttribute("sidebar", "jh/sidebar.jsp");
		request.getRequestDispatcher("index2.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
