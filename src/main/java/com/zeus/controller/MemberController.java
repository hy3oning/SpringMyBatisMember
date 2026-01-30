package com.zeus.controller;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zeus.domain.Member;
import com.zeus.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@MapperScan(basePackages = "com.zeus.mapper")
@RequestMapping("/member")
public class MemberController {
	@Autowired
	private MemberService memberService;

	@GetMapping("/insertForm")
	public String memberInsertForm(Model model) {
		return "member/insertForm";
	}

	@PostMapping("/insert")
	public String memberInsert(Member member, Model model) {
		log.info("insert member =" + member.toString());
		try {
			int count = memberService.register(member);
			if (count > 0) {
				model.addAttribute("message", "%s님 가입축하드립니다.".formatted(member.getName()));
				return "member/success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message", "%s님 등록 실패.".formatted(member.getName()));
		return "member/failed";
	}

	@GetMapping("/memberList")
	public String memberList(Model model) {
		log.info("memberList");
		try {
			List<Member> memberList = memberService.list();
			model.addAttribute("memberList", memberList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "member/memberList";
	}

	@GetMapping("/detail")
	public String memberDetail(Member m, Model model) {
		log.info("Detail =" + m.toString());
		try {
			Member member = memberService.read(m);
			if (member == null) {
				model.addAttribute("message", "%s 님의 정보가 없습니다.".formatted(m.getNo()));
				return "member/failed";
			}
			model.addAttribute("member", member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "member/detail";
	}

	@GetMapping("/delete")
	public String memberDelete(Member member, Model model) {
		log.info("Delete member =" + member.toString());
		try {
			int count = memberService.delete(member);
			if (count > 0) {
				model.addAttribute("message", "%s 님의 정보가 삭제되었습니다.".formatted(member.getName()));
				return "member/success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message", "%s 님의 정보가 삭제실패되었습니다.".formatted(member.getName()));
		return "member/failed";

	}

	@GetMapping("/updateForm")
	public String memberUpdateForm(Member m, Model model) {
		log.info("memberUpdate board =" + m.toString());
		try {
			Member member = memberService.read(m);
			if (member == null) {
				model.addAttribute("message", "%s 님의 정보가 없습니다.".formatted(m.getName()));
				return "member/failed";
			}
			model.addAttribute("member", member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "member/updateForm";
	}

	@PostMapping("/update")
	public String memberUpdate(Member m, Model model) {
		log.info("boardUpdate board =" + m.toString());
		try {
			int count = memberService.update(m);
			if (count > 0) {
				model.addAttribute("message", "%s 님의 정보가 수정되었습니다.".formatted(m.getName()));
				return "member/success";

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message", "%s 님의 정보가 수정이 되지 않았습니다.".formatted(m.getName()));
		return "member/failed";
	}

	@GetMapping("/search")
	public String memberSearch(Member member, Model model) {
		log.info("boardSearch board=" + member.toString());
		try {
			List<Member> memberList = memberService.search(member);
			model.addAttribute("memberList", memberList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "member/memberList";
	}
}
