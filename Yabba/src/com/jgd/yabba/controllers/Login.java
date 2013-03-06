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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		UserModel userModel = new UserModel();
		UserStore userStore = new UserStore();
		
		userStore=null;
		
		userStore = userModel.login(email, password);
		
		if(userStore!=null){
			System.out.println("Login Successful");
			System.out.println(userStore.getUsername());
			
			HttpSession session = request.getSession();
			session.setAttribute("userLogin", userStore);
			
			/*RequestDispatcher rd = request.getRequestDispatcher("/Message.jsp");
			rd.forward(request, response);*/
			
			response.sendRedirect("/Yabba/Message");
		}else{
			
			doGet(request,response);
			
		}
		
		
	}

}
