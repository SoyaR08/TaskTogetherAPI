package com.tasktogether.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasktogether.model.Member;
import com.tasktogether.model.User;
import com.tasktogether.repository.MemberRepository;

@Service
public class MemberService {

	@Autowired
	MemberRepository memberData;
	
	public List<Member> findMembershipsByUser(User u) {
		return memberData.findByUserEquals(u);
	}
	
	public Member addMember(Member m) {
		return memberData.save(m);
	}
	
	public List<Member> addMembers(List<Member> l) {
		return memberData.saveAll(l);
	}
}
