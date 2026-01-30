package com.zeus.mapper;

import java.util.List;

import com.zeus.domain.Member;
import com.zeus.domain.MemberAuth;

public interface MemberMapper {
	//회원가입 및 회원권환설정
	public int create(Member member) throws Exception;

	public int createAuth(MemberAuth memberAuth) throws Exception;

	public List<Member> list() throws Exception;

	public Member read(Member member) throws Exception;

	public int update(Member member) throws Exception;

	public int delete(Member member) throws Exception;

	public int deleteAuth(Member member) throws Exception;
}
