package com.semi.distribution.deliverysale;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.distribution.receipt.ReceiptDAO;

@WebServlet("/DistributionDeliverySaleClearC")
public class DistributionDeliverySaleClearC extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DeliverySaleDAO.getDSdao().getClearList(request);
		DeliverySaleDAO.getDSdao().paging(1, request);
		request.setAttribute("sidebar","sb/distribution/distribution_sidebar.jsp");
		request.setAttribute("contentPage","sb/distribution/deliverysale/deliverysaleclear.jsp");
		request.getRequestDispatcher("index2.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
