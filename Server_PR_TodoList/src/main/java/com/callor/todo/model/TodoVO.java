package com.callor.todo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class TodoVO {
	private Long td_seq = 0L;
	private String td_date;
	private String td_time;
	private String td_todo;
	private String td_area;

}
