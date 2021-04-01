package com.bit;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//192.168.0.128:8090/WebServlet_1/action.do 요청 FrontServletController 실행
@WebServlet(description = "여기는 설명을 하는 곳입니다", 
            urlPatterns = { "/action.do"}
           )
public class FrontServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public FrontServletController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		System.out.println("GET");
		
		//192.168.0.128:8090/WebServlet_1/action.do?cmd=greeting
		
		request.setCharacterEncoding("UTF-8");
		
		String cmd = request.getParameter("cmd");
		
		String msg="";
		
		if(cmd.equals("greeting")){
			Message m = new Message();
			msg = m.getMessage(cmd);
		}
		
		request.setAttribute("msg", msg);
		
		RequestDispatcher dis = request.getRequestDispatcher("/greeting.jsp");
		
		dis.forward(request, response);
				
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
