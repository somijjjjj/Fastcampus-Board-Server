package com.fastcampus.boardserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

	public enum SortStatus{
		CATEGORIES, NEWST, OLDEST
	}
	
	private int id;
	private String name;
	private SortStatus sortStatus;
	private int searchCount;
	private int pagingStartOffset;
}
