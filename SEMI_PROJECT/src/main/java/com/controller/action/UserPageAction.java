package com.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.FollowDao;
import com.dao.PostViewDao;
import com.dto.MemberDto;
import com.dto.MemberViewDto;
import com.dto.PostViewDto;
import com.dao.MemberViewDao;

public class UserPageAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "post/userPage.jsp";
		
		// Get parameter
		String userid = request.getParameter("userid");
		
		// Get user's info
		MemberViewDto user = MemberViewDao.getInstance().getMember(userid);
		request.setAttribute("user", user);
		
		// Get user's post
		ArrayList<PostViewDto> postList = PostViewDao.getInstance().getAllPostById(userid);
		request.setAttribute("posts", postList);
		
		// Get whether loginUser is following user
		HttpSession session = request.getSession();
		String loginUserid = ((MemberDto) session.getAttribute("loginUser")).getUserid();
		int isFollowing = FollowDao.getInstance().isFollowing(loginUserid, userid);
		request.setAttribute("isFollowing", isFollowing);
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}