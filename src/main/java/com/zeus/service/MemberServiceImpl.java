package com.zeus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zeus.domain.Member;
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
		return 0;
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
		return null;
	}

	@Override
	public Member read(int userNo) throws Exception {
		return null;
	}

}
