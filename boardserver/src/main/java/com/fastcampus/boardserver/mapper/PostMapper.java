package com.fastcampus.boardserver.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fastcampus.boardserver.dto.PostDTO;

@Mapper
public interface PostMapper {
	
	public void register(PostDTO postDTO);
	
	public List<PostDTO> selectMyPosts(int accoutId);
	
	public void updatePosts(PostDTO postDTO);
	
	public void deletePosts(int postId);

}
