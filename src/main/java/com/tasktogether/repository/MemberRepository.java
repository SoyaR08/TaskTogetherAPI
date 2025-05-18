package com.tasktogether.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasktogether.model.Member;
import com.tasktogether.model.MemberID;

public interface MemberRepository extends JpaRepository<Member, MemberID>{

}
