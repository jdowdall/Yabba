package com.jgd.yabba.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jgd.yabba.models.MessageModel;
import com.jgd.yabba.store.UserStore;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String messageidstr = request.getParameter("messageid");
		String useridstr = request.getParameter("userid");
		
		int messageid = Integer.parseInt(messageidstr);
		int userid = Integer.parseInt(useridstr);
		
		UserStore userBean = new UserStore();
		HttpSession session = request.getSession();
		userBean = (UserStore) session.getAttribute("userLogin");
		
		if(userid==userBean.getUserID()){
			
			MessageModel messageModel = new MessageModel();
			
			messageModel.deleteMessage(messageid);
			
		}
		else{
			RequestDispatcher rd = request.getRequestDispatcher("/PermissionError.jsp");
			rd.forward(request, response);
		}
		
		
		
		
	}


}
