<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	Member member = (Member)request.getAttribute("member");
    	
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>마이페이지</h1>
	<hr>
	<form action="/updateMember" method="post">
		번호  : <input type="text" name="memberNo" value="<%=member.getMemberNo() %>" readonly><br>
		아이디  : <input type="text" name="memberId" value="<%=member.getMemberId() %>" readonly><br>
		비밀번호  : <input type="text" name="memberPw" value="<%=member.getMemberPw() %>" readonly><br>
		이름  : <input type="text" name="memberName" value="<%=member.getMemberName() %>" readonly><br>
		전화번호  : <input type="text" name="phone" value="<%=member.getPhone() %>"><br>
		이메일  : <input type="text" name="email" value="<%=member.getEmail() %>"><br>	
		성별  : <input type="text" name="gender" value="<%=member.getGender() %>" readonly><br>
		가입일  : <input type="text" name="enrollDate" value="<%=member.getEnrollDate() %>" readonly><br>	
		<input type="submit" value="정보번경">
	</form>
	<a href="/">메인페이지로 이동</a>





</body>
</html>