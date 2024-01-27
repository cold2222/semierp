package com.semi.main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.adminpage.inform.CompanyInformDAO;
import com.semi.notice.CompanyNoticeDAO;

@WebServlet("/HC")
public class HC extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CompanyInformDAO.getCompanyInfromManager().getBroadCastCompanyInfroms(request);
		CompanyInformDAO.getCompanyInfromManager().getSessionDeptInforms(request);
		CompanyNoticeDAO.getCompanyNoticeManager().getMainPageNotice(request);
		request.setAttribute("selectedHeader", "main");
		request.setAttribute("contentPage", "sjh/main/main.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
