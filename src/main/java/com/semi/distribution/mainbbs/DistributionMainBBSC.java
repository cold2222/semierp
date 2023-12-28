package com.semi.distribution.mainbbs;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.distribution.delivery.DistributionWorkListAPI;
import com.semi.distribution.delivery.DistributionWorkListDAO;

@WebServlet("/DistributionMainBBSC")
public class DistributionMainBBSC extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MainBBSDAO.getBbsdao().getNotice(request);
		MainBBSDAO.getBbsdao().getSpecialNote(request);
		MainBBSDAO.getBbsdao().getDeliverySaleListOfFive(request);
		MainBBSDAO.getBbsdao().getReceiptListOfFive(request);
		MainBBSDAO.getBbsdao().getClearListOfFive(request);
		DistributionWorkListAPI.getWapi().Shift
		(DistributionWorkListDAO.getWdao().getEmpList(), DistributionWorkListDAO.getWdao().getRestMemberList(request), request, response);
		
		request.setAttribute("sidebar","sb/distribution/distribution_sidebar.jsp");
		request.setAttribute("contentPage","sb/distribution/main_bbs/main_bbs.jsp");
		
		request.setAttribute("selectedHeader", "distribution");
		
		request.getRequestDispatcher("index2.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
