<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% 
    	Member loginMember = (Member)session.getAttribute("loginMember");   	
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="/WEB-INF/views/joinFrm.html">회원가입페이지</a><br>
<a href="/WEB-INF/views/selectAllMember.jsp">전체회원조회</a><br>
<%	if(loginMember == null){ %>
	<fieldset>
		<legend>로그인</legend>
		<form action="/login" method="post">
			아이디 : <input type="text" name="memberId"><br>
			비밀번호 : <input type="password" name="memberPw"><br>
			<input type="submit" value="로그인">
			<input type="reset" value="초기화">
			<br>
			<a href="/views/joinFrm.html">회원가입</a>
		</form>
	</fieldset>
	<%} else{ %>
	<h1>[<%=loginMember.getMemberName() %>]</h1>
	<h3><a href="/mypage">마이페이지로 이동</a></h3>
	<h3><a href="/selectAllMember">전체 회원 조회</h3>
	<%} %>
</body>
</html>