package com.mp3.service;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;

import com.mp3.domain.MemberVO;

public interface MemberFindService {
	//아이디 찾기(이메일)
		public boolean MemverIDFindMail(MemberVO member);	
	
		//비밀번호 찾기(이메일)
		public boolean MemverPassFindMail(MemberVO member);
		// 이메일 인증
		public void send_mail(MemberVO member, String div);
		//이름 체크
		public boolean namecheck(MemberVO member);
	}
