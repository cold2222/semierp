package com.semi.distribution.deliverydata;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.distribution.deliverysale.DeliverySaleDAO;

@WebServlet("/DistributionDeliveryDataUpdateC")
public class DistributionDeliveryDataUpdateC extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		DeliveryDataDAO.getDdao().updateShipping(request);
		DeliveryDataDAO.getDdao().updateDeliveryDate(request);
		response.sendRedirect("DistributionDeliveryDataViewC?c_contract_no="+request.getParameter("c_contract_no"));
	}

}
