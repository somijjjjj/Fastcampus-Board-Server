package com.fastcampus.boardserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fastcampus.boardserver.dto.UserDTO;
import com.fastcampus.boardserver.dto.UserDTO.Status;
import com.fastcampus.boardserver.dto.request.UserDeleteId;
import com.fastcampus.boardserver.dto.request.UserLoginRequest;
import com.fastcampus.boardserver.dto.request.UserUpdatePasswordRequest;
import com.fastcampus.boardserver.dto.response.LoginResponse;
import com.fastcampus.boardserver.dto.response.UserInfoResponse;
import com.fastcampus.boardserver.service.impl.UserServiceImpl;
import com.fastcampus.boardserver.utils.SessionUtil;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/users")
@Log4j2
public class UserController {
	
	@Autowired 
	private final UserServiceImpl userService;
	
    private static final ResponseEntity<LoginResponse> FAIL_RESPONSE = new ResponseEntity<LoginResponse>(HttpStatus.BAD_REQUEST);
	private static LoginResponse loginResponse;
	
	public UserController(UserServiceImpl userService) {
		this.userService = userService;
	}
	
	@PostMapping("sign-up") 
	@ResponseStatus(HttpStatus.CREATED)
	public void singUp(@RequestBody UserDTO userDTO) {
		if (UserDTO.hasNullDataBeforRegister(userDTO)) {
			throw new RuntimeException("회원가입 정보를 확인해주세요.");
		}
		userService.register(userDTO);
	}
	
	@PostMapping("sign-in")
	public HttpStatus login(@RequestBody UserLoginRequest userLoginRequest,
							 HttpSession session) {
		ResponseEntity<LoginResponse> responseEntity = null;
		String id = userLoginRequest.getUserId();
		String pssword = userLoginRequest.getPassword();
		UserDTO userInfo = userService.login(id, pssword);
		
		if(userInfo == null) {
			return HttpStatus.NOT_FOUND;
		}else if(userInfo != null) {
			loginResponse = LoginResponse.success(userInfo);
			if(userInfo.getStatus() == Status.ADMIN) {
				SessionUtil.setLoginAdminId(session, id);
			}else {
				SessionUtil.setLoginMemberId(session, id);
			}
			responseEntity = new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
		}else {
			throw new RuntimeException("Login Error! 유저 정보가 없거나 지원되지 않는 유저입니다.");
		}
		return HttpStatus.OK;
	}
	
	@GetMapping("my-info")
	public UserInfoResponse memberInfo(HttpSession session) {
		String id = SessionUtil.getLoginMemberId(session);
		if(id == null) {
			id = SessionUtil.getLoginAdminId(session);
		}
		UserDTO memberInfo = userService.getUserInfo(id);
		return new UserInfoResponse(memberInfo);
	}
	
	@PutMapping("logout")
	public void logout(HttpSession session) {
		SessionUtil.clear(session);
	}
	
	@PatchMapping("password")
	public ResponseEntity<LoginResponse> updateUserPassword(@RequestBody UserUpdatePasswordRequest userUpdatePasswordRequest,
															HttpSession session){
		ResponseEntity<LoginResponse> responseEntity = null;
		String id = SessionUtil.getLoginMemberId(session);
		String beforePassword = userUpdatePasswordRequest.getBeforePassword();
		String afterPassword = userUpdatePasswordRequest.getAfterPassword();
		
		try {
			userService.updatePassword(id, beforePassword, afterPassword);
			ResponseEntity.ok(new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK));
		}catch(IllegalArgumentException e) {
			log.error("updatePassword 실패", e);
			responseEntity = FAIL_RESPONSE;
		}
		return responseEntity;
	}
	
	@DeleteMapping
	public ResponseEntity<LoginResponse> deleteId(@RequestBody UserDeleteId userDeleteId,
												 HttpSession session){
		ResponseEntity<LoginResponse> responseEntity = null;
		String id = SessionUtil.getLoginMemberId(session);
		
		try {
			userService.deleteId(id, userDeleteId.getPassword());
			responseEntity = new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
		}catch(RuntimeException e) {
			log.error("deleteId 실패", e);
			responseEntity = FAIL_RESPONSE;
		}
		return responseEntity;
	}
}
