package com.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.MemberDao;
import com.dto.MemberDto;

public class WithdrawAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String url = "member/loginForm.jsp";
		
		if(session.getAttribute("loginUser") == null) {
			url = "member/loginForm.jsp";
			request.setAttribute("message", "로그인이 안 되어 있어서 회원탈퇴 할 수 없어요. 다시 로그인 해 주세요");
		} else {
			// Get userid
			String userid = ((MemberDto) session.getAttribute("loginUser")).getUserid();
			
			// Delete user from db
			int result = MemberDao.getInstance().deleteMember(userid);
			
			// Get result
			if(result == 1) {
				request.setAttribute("message", "회원탈퇴에 성공했어요. Springfeed가 그리워 지면 언제든지 다시 찾아주세요...");
			} else {
				request.setAttribute("message", "회원탙퇴에 실패했어요. 다시 시도해 주세요. (이 기회에 탈퇴 안 하시는건 어떤긴가요? 농담이에요...)");
			}
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}