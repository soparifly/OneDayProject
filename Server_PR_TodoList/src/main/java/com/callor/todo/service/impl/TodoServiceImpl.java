package com.callor.todo.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.callor.todo.config.DBInfo;
import com.callor.todo.config.MySQLDBContract;
import com.callor.todo.model.TodoDTO;
import com.callor.todo.model.TodoVO;
import com.callor.todo.service.TodoService;

public class TodoServiceImpl implements TodoService {

	protected Connection dbConn;

	public TodoServiceImpl() {
		dbConn = MySQLDBContract.getConnection();
	}

	protected List<TodoDTO> select(PreparedStatement pStr) throws SQLException {

		List<TodoDTO> tdList = new ArrayList<TodoDTO>();
		ResultSet rStr = pStr.executeQuery();

		while (rStr.next()) {
			TodoDTO todoDTO = new TodoDTO();
			todoDTO.setTd_seq(rStr.getLong(DBInfo.TODO.td_seq));
			todoDTO.setTd_date(rStr.getString(DBInfo.TODO.td_date));
			todoDTO.setTd_time(rStr.getString(DBInfo.TODO.td_time));
			todoDTO.setTd_todo(rStr.getString(DBInfo.TODO.td_todo));
			todoDTO.setTd_area(rStr.getString(DBInfo.TODO.td_area));
			tdList.add(todoDTO);

		}
		System.out.println(tdList.toString());
		return tdList;

	}

	@Override
	public List<TodoDTO> selectAll() {
		// TODO 전체 목록을 불러와 화면에 출력함
		String sql = " SELECT * FROM tbl_todoList ";
		sql += " ORDER BY td_date DESC, td_time DESC ";

		PreparedStatement pStr = null;

		try {
			pStr = dbConn.prepareStatement(sql);
			List<TodoDTO> tdList = this.select(pStr);
			pStr.close();
			System.out.println(tdList.toString());

			return tdList;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<TodoDTO> findByDate(String td_date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(TodoVO todoVO) {
		// TODO 내용 수정하기
		String sql = " INSERT INTO tbl_todoList ";
		sql += " ( ";
		sql += " td_date, ";
		sql += " td_time, ";
		sql += " td_todo, ";
		sql += " td_area) ";

		sql += " VALUES (?,?,?,?) ";

		PreparedStatement pStr = null;

		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, todoVO.getTd_date());
			pStr.setString(2, todoVO.getTd_time());
			pStr.setString(3, todoVO.getTd_todo());
			pStr.setString(4, todoVO.getTd_area());

			return pStr.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updqte(TodoVO todoVO) {
		// TODO 수정하기
		String sql = " UPDATE tbl_todoList SET ";
		sql += " ( ";
		sql += " td_date=?, ";
		sql += " td_time=?, ";
		sql += " td_todo=?, ";
		sql += " td_area=? , ";

		sql += " WHERE td_seq = ? ";

		PreparedStatement pStr = null;

		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, todoVO.getTd_date());
			pStr.setString(2, todoVO.getTd_time());
			pStr.setString(3, todoVO.getTd_todo());
			pStr.setString(4, todoVO.getTd_area());

			return pStr.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(Long seq) {
		// TODO 삭제하기

		String sql = " DELETE FROM tbl_todoList ";
		sql += " WHERE td_seq = ? ";

		PreparedStatement pStr = null;

		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setLong(1, seq);
			pStr.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public TodoDTO findByID(String td_seq) {
		// TODO Auto-generated method stub
		String sql = " SELECT * FROM view_TodoList ";
		sql += " WHERE 일련번호 = ? ";

		PreparedStatement pStr = null;

		try {
			pStr = dbConn.prepareStatement(sql);

			pStr.setString(1, td_seq);

			List<TodoDTO> tdList = this.select(pStr);

			if (tdList != null && tdList.size() > 0) {
				return tdList.get(0);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
