package com.callor.todo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TodoDTO {
	private Long td_seq = 0L;
	private String td_date;
	private String td_time;
	private String td_todo;
	private String td_area;

}
