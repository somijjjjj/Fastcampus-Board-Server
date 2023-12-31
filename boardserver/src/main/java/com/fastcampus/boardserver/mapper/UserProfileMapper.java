package com.fastcampus.boardserver.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fastcampus.boardserver.dto.UserDTO;

@Mapper
public interface UserProfileMapper {
	
	public UserDTO getUserProfile(@Param("id") String id);
	
	//유저 회원가입
	int insertUserProfile(@Param("id") String id, @Param("password") String password,
						  @Param("name") String name, @Param("phone") String phone, 
						  @Param("address") String address, 
						  @Param("createTime") String createTime, 
						  @Param("updateTime") String updateTime);
	
	//유저 탈퇴
	int deleteUserProfile(@Param("id") String id);
	
	//로그인 시 id, password 값 조회
	public UserDTO findByIdAndPassword(@Param("id") String id, 
									   @Param("password") String password);
	
	//아이디 중복 체크
	int idCheck(@Param("id") String id);
	
	//비밀번호 변경
    public int updatePassword(UserDTO userDTO);

    //주소 변경
    public int updateAddress(UserDTO userDTO);

	public int register(UserDTO userProfile);
}
