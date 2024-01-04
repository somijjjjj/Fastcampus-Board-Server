package com.fastcampus.boardserver.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fastcampus.boardserver.dto.CategoryDTO;

@Mapper
public interface CategoryMapper {
	
	public int register(CategoryDTO categoryDTO);
	
	public void updateCategory(CategoryDTO categoryDTO);
	
	public void deleteCategory(int categoryId);

}
