package com.tasktogether.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tasktogether.model.Member;
import com.tasktogether.model.MemberID;
import com.tasktogether.model.User;

public interface MemberRepository extends JpaRepository<Member, MemberID> {
	
	List<Member> findByUserEquals(User user);
	
	Page<Member> findByUserEquals(User user, Pageable pageable);
	
}
