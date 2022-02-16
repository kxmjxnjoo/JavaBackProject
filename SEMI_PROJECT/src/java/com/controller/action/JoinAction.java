

public class JoinAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "join/join.jsp";

		// Get parameter
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");

		// Create MemberDto
		MemberDto mdto = new MemberDto();
		mdto.setUserid(userid);
		mdto.setPassword(pwd);
		mdto.setName(name);
		mdto.setEmail(email);
		mdto.setPhone(phone);

		// Insert MemberDto to DB
		int result = MemberDao.getInstance().insertMember(mdto);

		// Send message
		if(result == 1) {
			// Follow myself
			FollowDao.getInstance().insertFollow(userid, userid);
			request.setAttribute("message", "회원가입에 성공했어요. 로그인해 주세요");
			url = "login/login.jsp";
		} else {
			request.setAttribute("message", "회원가입에 실패했어요. 다시 시도해 주세요");
		}

		// Redirect
		request.getRequestDispatcher(url).forward(request, response);
	}
}