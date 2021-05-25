package com.callor.todo.controllor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.callor.todo.config.DBInfo;
import com.callor.todo.model.TodoVO;
import com.callor.todo.service.TodoService;
import com.callor.todo.service.impl.TodoServiceImpl;

@WebServlet("/todo/*")
public class TodoController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5864697281065747959L;
	protected TodoService tdService;

	public TodoController() {
		tdService = new TodoServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String subPath = req.getPathInfo();
		String td_seq = req.getParameter(DBInfo.TODO.td_seq);
		if (subPath.equals("/insert")) {

			System.out.println("내용없음");
		} else if (subPath.equals("/delete")) {

			System.out.println(td_seq);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String subPath = req.getPathInfo();
		System.out.println(subPath);

		String td_date = req.getParameter(DBInfo.TODO.td_date);
		String td_time = req.getParameter(DBInfo.TODO.td_time);
		String td_todo = req.getParameter(DBInfo.TODO.td_todo);
		String td_area = req.getParameter(DBInfo.TODO.td_area);
		TodoVO tdVO = new TodoVO();
		tdVO.setTd_date(td_date);
		tdVO.setTd_time(td_time);
		tdVO.setTd_todo(td_todo);
		tdVO.setTd_area(td_area);

		System.out.println(tdVO.toString());

		if (subPath.equals("/insert")) {
			int result = tdService.insert(tdVO);
			if (result > 0) {
				System.out.println("저장이 완료되었음");
				resp.sendRedirect("/todo/");
			} else {
				System.out.println("저장실패");
			}

		} else if (subPath.equals("/delete")) {

			String[] strSeq = req.getParameterValues(DBInfo.TODO.td_seq);
			if (strSeq == null) {
				System.out.println("지울 항목 선택안하고 삭제버튼클릭함");
				resp.sendRedirect("/todo/");
			} else {
				for (int i = 0; i < strSeq.length; i++) {
					System.out.println("삭제한 SEQ: " + strSeq[i]);
					Long td_seq = Long.valueOf(strSeq[i]);
					tdService.delete(td_seq);
				}

				System.out.println("삭제");
				resp.sendRedirect("/todo/");
			}
		} else if (subPath == null || subPath.equals("")) {
			System.out.println("요청Path가 없음");
			System.out.println(subPath);

		}
	}
}
