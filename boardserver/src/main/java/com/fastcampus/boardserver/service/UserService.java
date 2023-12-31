package com.fastcampus.boardserver.service;

import com.fastcampus.boardserver.dto.UserDTO;

public interface UserService {
	
	//유저 회원가입 
	void register(UserDTO userProfile);
	
	//로그인
	UserDTO login(String id, String password);
	
	//중복체크
	boolean isDuplicatedId(String id);
	
	//유저 정보 조회
	UserDTO getUserInfo(String userId);
	
	//비밀번호 변경
	void updatePassword(String id, String beforePassword, String afterPassword);
	
	//유저 삭제
	void deleteId(String id, String password);
}
