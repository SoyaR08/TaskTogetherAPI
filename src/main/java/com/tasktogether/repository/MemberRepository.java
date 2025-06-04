package com.tasktogether.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasktogether.model.Member;
import com.tasktogether.model.MemberID;
import com.tasktogether.model.User;

public interface MemberRepository extends JpaRepository<Member, MemberID> {
	
	List<Member> findByUserEquals(User user);
	
}
