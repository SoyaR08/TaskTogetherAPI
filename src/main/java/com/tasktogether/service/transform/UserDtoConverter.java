package com.tasktogether.service.transform;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasktogether.dto.MinUserInfo;
import com.tasktogether.model.User;
import com.tasktogether.repository.UserRepository;

@Service
public class UserDtoConverter {

	@Autowired
	UserRepository userData;
	
	public MinUserInfo mapToMinUserInfo(User u) {
		return new MinUserInfo(u);
	}
	
	public List<MinUserInfo> mapToMinUserInfo(List<User> l) {
		return l.stream().map(user -> mapToMinUserInfo(user)).collect(Collectors.toList());
	}
	
}
