package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.HelloMvcUtils;
import member.model.dto.Member;
import member.model.service.MemberService;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/member/memberDelete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// jsp 위임
		request
			.getRequestDispatcher("/WEB-INF/views/member/memberDelete.jsp")
			.forward(request, response);
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 인코딩처리 
			// 2. 사용자입력값처리
			HttpSession session = request.getSession();
			Member member = (Member)session.getAttribute("loginMember"); 
			
			String memberId = member.getMemberId();
			String password = HelloMvcUtils.encrypt(request.getParameter("password"),memberId);
			// 3. 업무로직
			String msg = null;
			if(member.getPassword().equals(password)) {
				int result = memberService.deleteMember(memberId);
				msg = "탈퇴가 완료되었습니다.";
			} else {
				msg = "입력된 현재 비밀번호가 일치하지 않습니다.";
			}
			session.setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/member/memberDelete");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
