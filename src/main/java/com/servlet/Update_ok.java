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


@WebServlet("/Update_ok")
public class Update_ok extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Update_ok() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * 아이디는 세션에서 얻습니다
		 * 폼태그에서 넘어오는 파라미터값을 받습니다
		 * executeUpdate() 문장으로 업데이트 작업을 수행하고
		 * 1을 반환하면 세션에 변경된 이름을 저장한 후에 update_result.jsp
		 * 0을 반환하면 login.jsp로 리다이렉트
		 * 
		 */
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("user_id");
		
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String region = request.getParameter("region");
		String gender = request.getParameter("gender");
		
		/*DAO에 int update (필요 매개변수);
		executeUpdate() 문장으로 업데이트 작업을 수행하고
		 * 1을 반환하면 세션에 변경된 이름을 저장한 후에 update_result.jsp
		 * 0을 반환하면 login.jsp로 리다이렉트
		 * */
		MemberDAO dao = MemberDAO.getInstance();
		int result = dao.Update(id, pw, name, region, gender);
		
		if(result == 1) {
			session.setAttribute("user_name", name);
			response.sendRedirect("update_result.jsp");
		}else {
			response.sendRedirect("login.jsp");
		}
		
		
		/*
		 * String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1"; String uid = "jsp";
		 * String upw = "jsp";
		 * 
		 * Connection conn = null; PreparedStatement pstmt = null;
		 * 
		 * String sql =
		 * "update member set pw = ?, name = ?, region = ?, gender = ? where id = ?";
		 * 
		 * try { Class.forName("oracle.jdbc.driver.OracleDriver");
		 * 
		 * conn = DriverManager.getConnection(url, uid, upw);
		 * 
		 * pstmt = conn.prepareStatement(sql); 
		 * pstmt.setString(1, pw);
		 * pstmt.setString(2, name); 
		 * pstmt.setString(3, region); 
		 * pstmt.setString(4,gender);
		 * pstmt.setString(5, id);
		 * int result = pstmt.executeUpdate();
		 * 
		 * if(result == 1) { //세션의 이름변경 . 같은이름에 덮어씌우기 
		 * 	session.setAttribute("user_name",name); 
		 * 	response.sendRedirect("update_result.jsp");
		 * 
		 * } else { response.sendRedirect("login.jsp");
		 * 
		 * }
		 * 
		 * } catch (Exception e) { e.printStackTrace(); } finally { try { if (conn !=
		 * null) conn.close(); if (pstmt != null) pstmt.close(); } catch (Exception e2)
		 * { // TODO: handle exception } }
		 */
		
	}

}
