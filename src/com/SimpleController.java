package com;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*
	web.xml 설정 
	<url-pattern>/simple</url-pattern>
	
	localhost:8090/WebServlet/simple  요청이 오면
	
    public class SimpleController 자바 파일 컴파일 실행 
    
    
   [ 서블릿(java 로 만든 웹서비스 파일)  ]
    서블릿 조건 : java 파일이  extends HttpServlet 상속 >> 웹 요청(request)객체 , 응답(response)객체 사용
    -servlet 은 url에서 바로 요청 안되요 >> 요청 >> mapping >> 요청 주소 생성
    -1. web.xml
    -2. @WebServlet
    
    -extends HttpServlet 반드시 상속 (웹)
    -SimpleController 서블릿 파일 웹 전용
    -servlet 이벤트 기반의 동작 (특정 사건(이벤트) 가 발생하면 [자동 호출]되는 함수 존재)
    -자동 호출 함수
    -protected void doGet()
    >> localhost:8090/WebServlet/simple 요청방식 GET방식 .... 자동 doGET 호출
    >> <form method="GET" or  <a href="/simple?num=1000> ...
    
    -protected void doPost()  요청방식 POST방식 .... 자동 doPOST 호출
    >> <form method="POST"   
    
    -doGet()  , doPOST() >>데이터 받기 (request , response) 사용 ...
    >>doPost(HttpServletRequest request, HttpServletResponse response) 
    >> 함수 안에서  request , response  객체 사용가능
    
*/
public class SimpleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public SimpleController() {
        super();
        System.out.println("SimpleController 생성자 함수 호출...");
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("클라이언트 요청 : GET");
		
		//JSP 페이지 작업 내용 그대로...
		
		//1. 한글처리
		request.setCharacterEncoding("UTF-8");
		//2. 데이터 받기(요청 의도 파악)
		String type = request.getParameter("type");
		
		//3.로직 (요청에 따른 업무 수행) >> service
		Object resultobj =null;
		if(type == null || type.equals("greeting")) {
			resultobj = "hello world";
		}else if(type.equals("date")) {
			resultobj =new Date();
		}else {
			resultobj = "invalid String type";
		}
		
		//4.요청 완료에 따른 결과를 저장 
		//MVC 패턴 방식 ...... (화면:JSP) ...서블릿에 만든 객체정보를 (JSP 전달)
		//결과를 저장 : session변수 , 특정 페이지(include , forward) request변수
		request.setAttribute("result", resultobj);
		
		
		//5.저장한 결과를 JSP  전달 (UI) 구성
		//결과를 forward 방식을 JSP 에게
		//요청한 주소는 변화가 없고  ...buffer forward 페이지 정보를 담아서 서비스
		
		RequestDispatcher dis = request.getRequestDispatcher("/simpleview.jsp");
		//getRequestDispatcher() view 페이지 정의 하기
		
		//정의한 페이지를 forward
		dis.forward(request, response);
		//serlvet 가지고 있는 request 객체의 주소 response객체의 주소 넘겨서 사용.... jsp
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("클라이언트 요청 : POST");
	}

}
