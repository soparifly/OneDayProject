package com.callor.todo.service;

import java.util.List;

import com.callor.todo.model.TodoDTO;
import com.callor.todo.model.TodoVO;

public interface TodoService {
	//모든목록
	public List<TodoDTO> selectAll();
	public TodoDTO findByID(String td_seq);
	//날짜별로찾기
	public List<TodoDTO> findByDate(String td_date);
	
	
	//입력하기
	public int insert(TodoVO todoVO);
	//수정하기
	public int updqte(TodoVO todoVO);
	//삭제하기
	public int delete(Long seq);
	
	
}
