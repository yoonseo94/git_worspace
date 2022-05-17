<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<section id=enroll-container>
	<h2>회원 탈퇴</h2>
	<form method="POST">
		<div>
			<label for="password">비밀번호</label>
			<input type="password" name="password" />
		</div>
		<div>
			<input type="submit" value="확인" />
			<input type="button" value="뒤로" onclick="history.go(-1);" />
		</div>
	</form>
</section>
	<script>
<% 
	if("탈퇴가 완료되었습니다.".equals(msg)) {
		session.removeAttribute("loginMember");
%>
	location.reload();
<%	} %>
	</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>