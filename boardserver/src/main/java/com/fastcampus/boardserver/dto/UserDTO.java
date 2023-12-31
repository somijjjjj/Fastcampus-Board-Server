package com.fastcampus.boardserver.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString 
public class UserDTO {
	
	public static boolean hasNullDataBeforRegister(UserDTO userDTO) {
		return userDTO.getUserId() == null || userDTO.getPassword() == null || userDTO.getNickName() == null;
	}
	
	public enum Status{
		DEFAULT, ADMIN, DELETED
	}
	
	//user 테이블 속성
	private int id;
	private String userId;
	private String password;
	private String nickName;
	private boolean isAdmin;
	private Date createTime;
	private boolean isWithDraw;
	private Status status;
	private Date updateTime;
}
