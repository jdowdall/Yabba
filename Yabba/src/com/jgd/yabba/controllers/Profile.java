package com.jgd.yabba.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jgd.yabba.models.UserModel;
import com.jgd.yabba.store.UserStore;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserModel userModel = new UserModel();
		UserStore userBean = new UserStore();
		
		HttpSession session = request.getSession();
		userBean = (UserStore) session.getAttribute("userLogin");
		
		if(userBean!=null){
		
			request.setAttribute("userBean", userBean);
			System.out.println(userBean.getEmail());
		
			RequestDispatcher rd = request.getRequestDispatcher("/Profile.jsp");
			rd.forward(request, response);
		}else{
			
			RequestDispatcher rd = request.getRequestDispatcher("/LoginError.jsp");
			rd.forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		UserModel userModel = new UserModel();
		
		UserStore userBean = new UserStore();
		
		HttpSession session = request.getSession();
		userBean = (UserStore) session.getAttribute("userLogin");
		
		userModel.updateUser(username, password, email,userBean);
		
		doGet(request,response);
	}

}
