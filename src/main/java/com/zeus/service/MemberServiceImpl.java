package com.zeus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zeus.domain.Member;
import com.zeus.domain.MemberAuth;
import com.zeus.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper mapper;

	@Override
	@Transactional
	public int register(Member member) throws Exception {
		int count = mapper.create(member);
		if (count > 0) {
			MemberAuth memberAuth = new MemberAuth();
			memberAuth.setNo(member.getNo());
			memberAuth.setAuth("ROLE_USER");
			mapper.createAuth(memberAuth);
		}
		return count;
	}

	@Override
	@Transactional
	public int update(Member member) throws Exception {
		return 0;
	}

	@Override
	@Transactional
	public int delete(int userNo) throws Exception {
		return 0;
	}

	@Override
	public List<Member> list() throws Exception {
		return mapper.list();
	}

	@Override
	public Member read(int userNo) throws Exception {
		return null;
	}

}
