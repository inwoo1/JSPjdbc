package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Login_ok")
public class Login_ok extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Login_ok() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberDAO dao = MemberDAO.getInstance();
		
		MemberVO vo = dao.login(id, pw);
		
		if(vo == null) {
			response.sendRedirect("login_fail.jsp");
		}else {
			//로그인성공, 세션에 값저장
			HttpSession session = request.getSession();
			session.setAttribute("user_id", vo.getId());
			session.setAttribute("user_name", vo.getName());
			response.sendRedirect("login_welcome.jsp");
		}
		
		//1. DAO에 MemberVO login(id,pw)메서드를 생성하고 호출
		//MemberVO가 null값이라면 로그인 실패 -> fail페이지로
		//MemberVO가 값을 가지고 있다면 -> login_welcom.jsp로 이동
		
//		
//		String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
//		String uid = "jsp";
//		String upw = "jsp";
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;  //select문을 사용하기 위함
//		
//		String sql = "select * from member where id = ? and pw = ?";
//		
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			
//			conn = DriverManager.getConnection(url, uid, upw);
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, id);
//			pstmt.setString(2, pw);
//			
//			rs= pstmt.executeQuery();
//			
//			if(rs.next()) {   //아이디 비밀번호가 맞은경우
//				
//				String name = rs.getString("name");//name컬럼
//				
//				//request에서 세션을 얻는다.
//				HttpSession session = request.getSession();
//				session.setAttribute("user_id", id);
//				session.setAttribute("user_name", name);
//				
//				//성공페이지로
//				response.sendRedirect("login_welcome.jsp");
//				
//			}else { //아이디 비밀번호가 틀린경우
//				response.sendRedirect("login_fail.jsp");
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(conn != null) conn.close();
//				if(pstmt != null) pstmt.close();
//				if(rs != null) rs.close();
//			} catch (Exception e2) {
//				// TODO: handle exception
//			}
//		}
		
	}

}
