package com.semi.sales.supply;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MakeContentsC")
public class MakeContentsC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SupplyComDAO.getContent(request);
		request.setAttribute("page", "contents_reg.jsp");
		request.getRequestDispatcher("jh/index.jsp").forward(request, response);
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SupplyComDAO.regContents(request);
		SupplyComDAO.getAllContents(request);
		request.setAttribute("page", "final_contents.jsp");
		request.getRequestDispatcher("jh/index.jsp").forward(request, response);
	
	
	
	
	}

}
