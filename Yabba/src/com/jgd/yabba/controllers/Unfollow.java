package com.jgd.yabba.controllers;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jgd.yabba.models.FollowModel;
import com.jgd.yabba.store.UserStore;

/**
 * Servlet implementation class Unfollow
 */
@WebServlet("/Unfollow")
public class Unfollow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Unfollow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("userLogin") == null) {
			RequestDispatcher rd = request.getRequestDispatcher("/LoginError.jsp");
			rd.forward(request, response);
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("/Unfriend.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
FollowModel followModel = new FollowModel();
		
		if(request.getParameter("unfollow") != null){
			String useridstr = request.getParameter("userid");
			int userid = Integer.parseInt(useridstr);
			System.out.println("follow user"+ userid);
			
			UserStore userBean = new UserStore();
			HttpSession session = request.getSession();
			userBean = (UserStore) session.getAttribute("userLogin");
			
			followModel.unfollow(userBean,userid);
			response.sendRedirect("/Yabba/Message");
		}else{
		
			String username = request.getParameter("username");
		
		
		
		
			List<UserStore> users = new LinkedList<UserStore>();
		
			users = followModel.findUsers(username);
		
			request.setAttribute("users", users);
		
			RequestDispatcher rd = request.getRequestDispatcher("/Users.jsp");
			rd.forward(request, response);
		}
	}

}
