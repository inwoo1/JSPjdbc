<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String name = request.getParameter("name");
	String region = request.getParameter("region");
	String gender = request.getParameter("gender");

	//데이터베이스 연결주소
	String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
	String uid = "jsp";
	String upw = "jsp";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	String sql = "insert into member values(?, ?, ?, ?, ?)";
	try {
	 //1. 드라이버 호출
	 Class.forName("oracle.jdbc.driver.OracleDriver");
	 
	 //2. 커넥션 생성
	 conn = DriverManager.getConnection(url,uid,upw);
	 
	 //3. state객체생성
	 pstmt = conn.prepareStatement(sql);
	 pstmt.setString(1, id);
	 pstmt.setString(2, pw);
	 pstmt.setString(3, name);
	 pstmt.setString(4, region);
	 pstmt.setString(5, gender);
	 //insert,update,delete - executeUpdate()실행
	 int result = pstmt.executeUpdate();
	 
	if(result == 1){ //성공
	  	response.sendRedirect("success.jsp");	
	}else {  //실패
		response.sendRedirect("fail.jsp");
	}
	
	} catch(Exception e){
		e.printStackTrace();
		response.sendRedirect("fail.jsp");
	} finally {
		try{
			if(conn != null) conn.close();
			if(pstmt != null) pstmt.close();
		} catch(Exception e2){
			
		}
	}
	
	
	
%>    
