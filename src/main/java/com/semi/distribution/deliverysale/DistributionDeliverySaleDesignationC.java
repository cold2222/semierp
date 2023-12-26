package com.semi.distribution.deliverysale;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.distribution.receipt.ReceiptDAO;

@WebServlet("/DistributionDeliverySaleDesignationC")
public class DistributionDeliverySaleDesignationC extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		DeliverySaleDAO.getDSdao().statusLevelUp2(request);
		DeliverySaleDAO.getDSdao().insertShipping(request);
		DeliverySaleDAO.getDSdao().updateDeliveryDate(request);
		response.sendRedirect("DistributionDeliverySaleC");
	}

}
