package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import entity.DinnerTable;
import factory.BeanFactory;
import service.IOperatorDinnerTableService;

/**
 * Servlet implementation class DinnerTableServlet
 */
public class DinnerTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IOperatorDinnerTableService odts = BeanFactory.getInstance("IOperatorDinnerTableService", IOperatorDinnerTableService.class);
	private String url ;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String method = request.getParameter("method");
		if("list".equals(method)){
			//显示全部
			listTable(request,response);
			
		}else if ("addTable".equals(method)){
			//添加
			addTable(request,response);
		}else if("deleteTable".equals(method)){
			deleteTable(request,response);
		}else if("freeTable".equals(method)){
			//退桌
			freeTable(request,response);
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	private void freeTable(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String s = request.getParameter("stutus");
		if(Integer.parseInt(s)==1){
			odts.freeTable(Integer.parseInt(id));
		}else if (Integer.parseInt(s)==0){
			odts.reserveTable(Integer.parseInt(id));
		}
		
		listTable(request,response);
	}

	private void deleteTable(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		odts.deleteDinnerTable(Integer.parseInt(id));
		listTable(request,response);
	}

	private void addTable(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("tableName");
		DinnerTable d = new DinnerTable();
		d.setTableName(name);
		odts.addDinnerTable(d);
		listTable(request,response);
	}

	private void listTable(HttpServletRequest request,HttpServletResponse response) {
		List<DinnerTable> tableList = odts.findAllDinnerTable();
		request.setAttribute("tableList", tableList);
		url ="/sys/boardList.jsp";
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
