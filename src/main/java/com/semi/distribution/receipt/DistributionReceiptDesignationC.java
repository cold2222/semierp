package com.semi.distribution.receipt;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DistributionReceiptDesignationC")
public class DistributionReceiptDesignationC extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReceiptDAO.getRdao().statusLevelUp2(request);
		ReceiptDAO.getRdao().insertShipping(request);
		response.sendRedirect("DistributionReceiptC");
	}

}
