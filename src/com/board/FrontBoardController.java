package com.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//web.xml 설정
public class FrontBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FrontBoardController() {
        super();
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response , String method) throws ServletException, IOException{
    	//GET , POST  두가지 요청에 대해서 동작하는 함수
    	//method >>  요청 > GET,POST 확인
    	System.out.println("클라이언트 요청 : " + method);
    	
    	//1. 한글처리
    	//2. 요청받기(request)
    	//3. 요청판단 (판단의 기분 : parameter 기준 >> command 방식)
    	//3.1 192.168.0.128:8090/WebServlet_1/board?cmd=login&id=kglim&pwd=1004 요청 서버로
    	//서버는 cmd변수가 가지는 값을 가지고 판단 : login >> 로그인 처리 ....
    	//3.2 192.168.0.128:8090/WebServlet_1/board?cmd=list   게시판 보여 달라고 ...
    	
    	//String command = request.getParameter("cmd");
    	//if(command.equals("login")){ 로그인 서비스 처리 ....}
    	//else if(command.equals("list")){게시판 목록보기 서비스}
    	
    	//command 방식 반대 >> URL주소 방식
    	//192.168.0.128:8090/WebServlet_1/board/boardlist
    	//192.168.0.128:8090/WebServlet_1/board/boardwrite?title=방가&content=방가방가
    	//마지막 주소값을 추출 
    	//   /boardlist   >> 게시판 목록보기
    	//   /boardwrite?title=방가&content=방가방가  >> 게시판 글쓰기 판단 하겠다
    	
    	
    	//4. 결과 저장 (request,   session ,  application)
    	
    	
    	//5. view 지정 
    	//view page : *.jsp
    	//WebContent > board > boardlist.jsp
    	//WebContent > error > error404.jsp
    	
    	
    	//위 방식은 보안상 문제 >> 실개발 > WebContent > WEB-INF > views폴더 생성 > board or member or admin  폴더 관리
    	
    	//ex) 보안폴더 : WEB-INF > views > board > boardlist.jsp
    	
    	//6. view 에게 request객체 forward 해서 사용가능 ....
    	
    	//한글화
    	request.setCharacterEncoding("UTF-8");
    	
    	String cmd= request.getParameter("cmd");
    	
    	//요청판단
    	String viewpage=null;
    	
    	//cmd > null > error.jsp
    	//cmd > boardlist > list.jsp
    	//cmd > boardwrite> write.jsp 서비스 할까
    	
    	if(cmd==null) {
    		viewpage = "/error/error.jsp"; //error.jsp 클라언트에게 전달
    	}else if(cmd.equals("boardlist")) {
    		//실제 업무 처리(서비스)
    		/*
    		 DB 연결 .. select .. 객체 담고 ... 저장
    		 boardDao dao = new boardDao();
    		 List<board> boardlist = dao.selectBoardList();
    		 request.setAttribute("list",boardlist);
    		 forward > view > 전달 (request.getAttribute()) 가지고 와서 화면 출력
    		 출력시에는 EL & JSTL 사용
    		 */
    		 viewpage = "/board/boardlist.jsp";
    	}else if(cmd.equals("boardwrite")) {
    		 viewpage = "/board/boardwrite.jsp";
    	}else if(cmd.equals("login")) {  //보안폴더
    		viewpage = "/WEB-INF/views/login/login.jsp";
    	}else {
    		viewpage="/error/error.jsp";
    	}
    	
    	//결과저장
    	//List<Emp> list = new ArrayList<>();
    	//list.add(new Emp(200,김유신);
    	//request.setAttribute("emplist",list);
    	
    	//view 지정
    	RequestDispatcher dis = request.getRequestDispatcher(viewpage);    	
    	
    	//데이터 전달(forward)
    	dis.forward(request, response);
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doProcess(request, response, "GET");
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   doProcess(request, response, "POST");

	}

}
