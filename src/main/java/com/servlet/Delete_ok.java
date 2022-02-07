package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Delete_ok")
public class Delete_ok extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Delete_ok() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * 아이디는 세션에서 얻고
		 * executeUpdate()문장으로 삭제
		 * 삭제성공시 세션을 전부 삭제하고 login.jsp로 리다이렉트
		 * 실패시 login_welcome.jsp로 리다이렉트
		 */
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("user_id");
		
		MemberDAO dao = MemberDAO.getInstance();
		int result = dao.delete(id);
		
		if(result == 1) {
			session.invalidate();
			response.sendRedirect("login.jsp");
		} else {
			response.sendRedirect("login_welcome.jsp");
		}
		
		
		
		
//		String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
//		String uid = "jsp";
//		String upw = "jsp";
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		String sql = "delete from member where id = ?";
//		
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			
//			conn = DriverManager.getConnection(url, uid, upw);
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, id);
//			
//			int result = pstmt.executeUpdate();
//			
//			if(result == 1) {
//				
//				session.invalidate();
//				response.sendRedirect("login.jsp");
//				
//			} else {
//				
//				response.sendRedirect("login_welcom.jsp");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(conn != null) conn.close();
//				if(pstmt != null) pstmt.close();
//			} catch (Exception e2) {
//				// TODO: handle exception
//			}
//		}
		
		
		
		
		
		
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
