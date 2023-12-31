package com.fastcampus.boardserver.service.impl;

import org.springframework.stereotype.Service;

import com.fastcampus.boardserver.dto.UserDTO;
import com.fastcampus.boardserver.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	//유저 회원가입
	@Override
	public void register(UserDTO userProfile) {
		boolean duplIdResult = isDuplicatedId(userProfile.getUserId());
	}
	
	//로그인
	@Override
	public UserDTO login(String id, String password) {
		return null;
	}
	
	//중복체크
	@Override
	public boolean isDuplicatedId(String id) {
		return false;
	}
	
	//유저 정보 조회
	@Override
	public UserDTO getUserInfo(String userId) {
		return null;
	}
	
	//비밀번호 변경
	@Override
	public void updatePassword(String id, String beforePassword, String afterPassword) {
		
	}
	
	//유저 삭제
	@Override
	public void deleteId(String id, String password) {
		
	}

}
