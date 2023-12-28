package com.semi.distribution.specialnote;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/DistributionSpecialNoteUpdateC")
public class DistributionSpecialNoteUpdateC extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SpecialNoteDAO.getSdao().getOneBBS(request);
		SpecialNoteDAO.getSdao().contentEnter(request);
		request.setAttribute("sidebar","sb/distribution/distribution_sidebar.jsp");
		request.setAttribute("contentPage","sb/distribution/specialnote/specialnoteupdate.jsp");
		request.setAttribute("selectedHeader", "distribution");
		request.getRequestDispatcher("index2.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SpecialNoteDAO.getSdao().updateBBS(request);
		String s_num = (String)request.getAttribute("s_num");
		response.sendRedirect("DistributionSpecialNoteViewC?s_num="+s_num);
	}

}
