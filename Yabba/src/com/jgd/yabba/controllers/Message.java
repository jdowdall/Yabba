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

import com.jgd.yabba.models.MessageModel;
import com.jgd.yabba.store.MessageStore;
import com.jgd.yabba.store.UserStore;

/**
 * Servlet implementation class Message
 */
@WebServlet("/Message/*")
public class Message extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Message() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MessageModel messageModel = new MessageModel();
		List<MessageStore> messages = new LinkedList<MessageStore>();
		String msgArg = request.getPathInfo();
		
		UserStore userBean = new UserStore();
		HttpSession session = request.getSession();
		
		
		if (session.getAttribute("userLogin") == null) {
			RequestDispatcher rd = request.getRequestDispatcher("/LoginError.jsp");
			rd.forward(request, response);
		}else if(msgArg!=null){
			msgArg = msgArg.substring(1);
			System.out.println(msgArg);
			
			try{
				int msgID = Integer.parseInt(msgArg);
				System.out.println(msgID);
				
				messages = messageModel.getMessage(msgID);
				
				MessageStore messageBean = new MessageStore();
				messageBean = messages.get(0);
				
				request.setAttribute("messageBean", messageBean);
				RequestDispatcher rd = request.getRequestDispatcher("/SingleMessage.jsp");
				rd.forward(request, response);
				
			}catch (NumberFormatException e) {
				
				messages = messageModel.getUserMessages(msgArg);
			}
		}else
		{
			/*UserStore userBean = new UserStore();
			HttpSession session = request.getSession();
			userBean = (UserStore) session.getAttribute("userLogin");*/
			userBean = (UserStore) session.getAttribute("userLogin");
			int userid = userBean.getUserID();
			
		
			messages = messageModel.getMessages(userid);
		
			request.setAttribute("messages", messages);
			RequestDispatcher rd = request.getRequestDispatcher("/Message.jsp");
			rd.forward(request, response);
			
		}
		
		

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//String action = request.getParameter("action");
		
		if(request.getParameter("delete") != null){
			
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
		}else{
		
			String newMessage = request.getParameter("newMessage");
		
			System.out.println(newMessage);
		
			MessageModel messageModel = new MessageModel();
			UserStore userBean = new UserStore();
		
			HttpSession session = request.getSession();
			userBean = (UserStore) session.getAttribute("userLogin");
			int userid = userBean.getUserID();
			messageModel.addMessage(newMessage,userid);
		
			doGet(request,response);
		}
	}
	
	

}
