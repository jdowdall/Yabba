package com.jgd.yabba.controllers;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;


import com.jgd.yabba.models.MessageModel;
import com.jgd.yabba.models.UserModel;
import com.jgd.yabba.store.UserStore;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/Register.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		UserModel userModel = new UserModel();
		
		
		userModel.addUser(username, password, email);
		
		UserStore userStore = new UserStore();
		
		userStore=null;
		
		userStore = userModel.login(email, password);
		
		if(userStore!=null){
			System.out.println("Login Successful");
			System.out.println(userStore.getUsername());
			
			HttpSession session = request.getSession();
			session.setAttribute("userLogin", userStore);
			
			//RequestDispatcher rd = request.getRequestDispatcher("/Message.jsp");
			//rd.forward(request, response);
			
			response.sendRedirect("/Yabba/Message");

		}
	}
	
	}


