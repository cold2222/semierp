package com.semi.distribution.delivery;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DistributionWorkListAPIC")
public class DistributionWorkListAPIC extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DistributionWorkListAPI.getWapi().Shift
		(DistributionWorkListDAO.getWdao().getEmpList(), DistributionWorkListDAO.getWdao().getRestMemberList(request), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
