package com.callor.todo.controllor;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.callor.todo.model.TodoDTO;
import com.callor.todo.model.TodoVO;
import com.callor.todo.service.TodoService;
import com.callor.todo.service.impl.TodoServiceImpl;

@WebServlet("/")
public class HomeController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4707446746094213170L;
	protected TodoService tdService;

	public HomeController() {
		tdService = new TodoServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		TodoVO tdVO = new TodoVO();
		
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat st = new SimpleDateFormat("HH:mm:ss");
		
		Date date = new Date(System.currentTimeMillis());
		tdVO.setTd_seq(0L);
		tdVO.setTd_date(sd.format(date));
		tdVO.setTd_time(st.format(date));
		
		req.setAttribute("TD", tdVO);
		

		List<TodoDTO> tdList = tdService.selectAll();
		req.setAttribute("TDLIST", tdList);
		req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
	
	
	}

	
}
