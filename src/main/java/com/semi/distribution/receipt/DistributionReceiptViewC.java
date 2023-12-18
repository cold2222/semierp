package com.semi.distribution.receipt;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.distribution.delivery.DeliveryDAO;

@WebServlet("/DistributionReceiptViewC")
public class DistributionReceiptViewC extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReceiptDAO.getRdao().getReceiptData(request);
		ReceiptDAO.getRdao().getReceiptItemList(request);
		ReceiptDAO.getRdao().Shift(ReceiptDAO.getRdao().getEmpList(),ReceiptDAO.getRdao().getRestMemberList(request),request);
		request.setAttribute("sidebar","sb/distribution/distribution_sidebar.jsp");
		request.setAttribute("contentPage","sb/distribution/receipt/receiptview.jsp");
		request.getRequestDispatcher("index2.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
