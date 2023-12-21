package com.semi.distribution.deliverysale;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.distribution.delivery.DeliveryDAO;

@WebServlet("/DistributionDeliverySaleViewC")
public class DistributionDeliverySaleViewC extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DeliverySaleDAO.getDSdao().getdeliverySaleData(request);
		DeliverySaleDAO.getDSdao().getDeliverySaleItemList(request);
		request.setAttribute("sidebar","sb/distribution/distribution_sidebar.jsp");
		request.setAttribute("contentPage","sb/distribution/deliverysale/deliverysaleview.jsp");
		request.getRequestDispatcher("index2.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
