package com.semi.distribution.notice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DistributionNoticeViewC")
public class DistributionNoticeViewC extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeDAO.getNdao().getOneBBS(request);
		request.setAttribute("sidebar","sb/distribution/distribution_sidebar.jsp");
		request.setAttribute("contentPage","sb/distribution/notice/noticeview.jsp");
		request.setAttribute("selectedHeader", "distribution");
		request.getRequestDispatcher("index2.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
