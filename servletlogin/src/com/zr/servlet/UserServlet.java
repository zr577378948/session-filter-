package com.zr.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zr.entity.User;
import com.zr.service.UserService;
import com.zr.service.UserServiceImpl;

/**
 * Servlet implementation class userServlet
 */
@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		UserService service = new UserServiceImpl();
		User user = service.findByName(name);
		
		HttpSession session = request.getSession();
		if(user == null){
			//用户名不存在
			response.sendRedirect("login.jsp");
		}else{
			if(!password.equals(user.getPassword())){
				response.sendRedirect("login.jsp");
			}else{
				session.setAttribute("name", user.getName());
				session.setMaxInactiveInterval(2);
				response.sendRedirect("logok.jsp");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
