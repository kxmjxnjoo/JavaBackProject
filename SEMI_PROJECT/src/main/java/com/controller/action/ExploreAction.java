package com.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.action.Action;
import com.dao.PostDao;
import com.dto.PostDto;

public class ExploreAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/post/explore.jsp";
		HttpSession session = request.getSession();
		//MeberDto mdto = (MemberDto) sessio.getAttribute("loginAdmin");
		//if(mdto==null) url = "spring.do?command=login";
		//else {
			ArrayList<PostDto> postList = PostDao.getInstance().getBestPost();
			request.setAttribute("posts", postList);
			
		
		//}
		request.getRequestDispatcher(url).forward(request, response);	
	}
}