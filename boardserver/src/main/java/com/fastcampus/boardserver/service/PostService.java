package com.fastcampus.boardserver.service;

import java.util.List;

import com.fastcampus.boardserver.dto.PostDTO;

public interface PostService {
	
	void register(String id, PostDTO postDTO);
	
	List<PostDTO> getMyPosts(int accoutId);
	
	void updatePosts(PostDTO postDTO);
	
	void deletePosts(int userId, int postId);

}
