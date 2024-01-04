package com.fastcampus.boardserver.service.impl;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastcampus.boardserver.dto.CategoryDTO;
import com.fastcampus.boardserver.mapper.CategoryMapper;
import com.fastcampus.boardserver.service.CategoryServcie;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CategoryServiceImpl implements CategoryServcie{
	
	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public void register(String accountId, CategoryDTO categoryDTO) {
		if(accountId != null) {
			categoryMapper.register(categoryDTO);
		}else {
			log.error("register ERROR! {}", categoryDTO);
			throw new RuntimeException("register EROOR! 게시글 카테고리 등록 메서드를 확인해주세요. "+ categoryDTO);
		}
	}

	@Override
	public void update(CategoryDTO categoryDTO) {
		if(categoryDTO != null) {
			categoryMapper.updateCategory(categoryDTO);
		}else {
			log.error("update ERROR! {}", categoryDTO);
			throw new RuntimeException("update EROOR! 게시글 카테고리 수정 메서드를 확인해주세요. "+ categoryDTO);
		}
	}

	@Override
	public void delete(int categoryId) {
		if(categoryId != 0) {
			categoryMapper.deleteCategory(categoryId);
		}else {
			log.error("delete ERROR! {}", categoryId);
			throw new RuntimeException("delete EROOR! 게시글 카테고리 삭제 메서드를 확인해주세요. "+ categoryId);
		}
	}

}
