package com.semi.distribution.deliverysale;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.distribution.receipt.ReceiptDAO;

@WebServlet("/DistributionDeliverySaleCompleteC")
public class DistributionDeliverySaleCompleteC extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DeliverySaleDAO.getDSdao().statusLevelUp4(request);
		response.sendRedirect("DistributionDeliverySaleClearC");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
