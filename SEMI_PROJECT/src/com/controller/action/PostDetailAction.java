package com.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.PostDao;
import com.dto.MemberDto;
import com.dto.PostDto;
import com.dto.ReplyDto;

public class PostDetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "postDetail.jsp";
		HttpSession session = request.getSession();
		//MeberDto mdto = (MemberDto) sessio.getAttribute("loginAdmin");
		//if(mdto==null) url = "spring.do?command=login";
		//else {
				int post_num = Integer.parseInt(request.getParameter("post_num"));
				String userid = "hong"; //((MemberDto) session.getAttribute("loginUser")).getUserid();
				PostDao pdao = PostDao.getInstance();
				PostDto pdto = pdao.getPost(post_num);
				ArrayList<ReplyDto> rdto = pdao.getReply(post_num);
				int result = pdao.postLikeCheck(post_num, userid);
				String fileName = "";
				if(result == 0) {
					fileName = "../images/beforeLike.png";
				} else {
					fileName = "../images/Like.png";
				}
				
				for(int i=0; i<rdto.size(); i++) {
					int replyLikeResult = pdao.replyLikeCheck(rdto.get(i).getReply_num(), userid);
					if(replyLikeResult==0) rdto.get(i).setReplyFileName("../images/beforeLike.png");
					else rdto.get(i).setReplyFileName("../images/Like.png");
				}
				
				////////////테스트용 코드 
				System.out.println("///");
				MemberDto mdto = new MemberDto();
				mdto.setUserid("hong");
				session.setAttribute("loginUser", mdto);
				String loginUser = ((MemberDto) session.getAttribute("loginUser")).getUserid();
				System.out.println(loginUser);
				System.out.println(pdto.getUserid());
				////////////테스트용 코드 여기까지 /////////////////////////
				
				request.setAttribute("likeResult", result);
				request.setAttribute("fileName", fileName);
				request.setAttribute("post_num", post_num);
				request.setAttribute("PostDto", pdto);
				request.setAttribute("ReplyDto", rdto);
		//}
		request.getRequestDispatcher(url).forward(request, response);
	}
}