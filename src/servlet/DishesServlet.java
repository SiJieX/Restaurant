package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import entity.Dishes;
import factory.BeanFactory;
import service.IOperatorDishesService;

/**
 * Servlet implementation class DishesServlet
 */
public class DishesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IOperatorDishesService ds = BeanFactory.getInstance("IOperatorDishesService", IOperatorDishesService.class);
	private String url;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String method = request.getParameter("method");
		if("add".equals(method)){
			//添加
			try {
				Dishes d = new Dishes();
				String disheName = request.getParameter("disheName");
				d.setDishesName(disheName);
				ds.addDishe(d);
				url="/DishesServlet?method=list";
			} catch (Exception e) {
				e.printStackTrace();
				//url="sys/cuisineList.jsp";
			}
		}else if("list".equals(method)){
			//展示
			List<Dishes> list = ds.findAllDishes();
			request.setAttribute("list", list);
			url ="sys/cuisineList.jsp";
		}
		else if("viewUpdate".equals(method)){
			//发送id到更新页面
			String id = request.getParameter("id");
			Dishes d = ds.findDisheById(Integer.parseInt(id));
			String dishesName = d.getDishesName();
			request.setAttribute("dishesName", dishesName);
			request.setAttribute("id", id);
			url = "sys/updateCuisine.jsp";
		}
		else if("update".equals(method)){
			//更新
			String dishesName = request.getParameter("dishesName");
			String id = request.getParameter("id");
			Dishes d = new Dishes();
			d.setId(Integer.parseInt(id));
			d.setDishesName(dishesName);
			ds.updateDishe(d);
			url="/DishesServlet?method=list";
		}else if("delete".equals(method)){
			String id = request.getParameter("id");
			Dishes d = new Dishes();
			d.setId(Integer.parseInt(id));
			ds.deleteDishe(d);
			url="/DishesServlet?method=list";
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
