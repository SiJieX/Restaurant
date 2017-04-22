package frontservlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Condition;
import entity.DinnerTable;
import entity.Dishes;
import entity.Menu;
import entity.PageBean;
import enume.Status;
import factory.BeanFactory;
import service.IOperatorDinnerTableService;
import service.IOperatorDishesService;
import service.IpageBeanService;

/**
 * Servlet implementation class FrontListServlet
 */
public class FrontListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private IOperatorDinnerTableService odt = BeanFactory.getInstance("IOperatorDinnerTableService", IOperatorDinnerTableService.class);
	private IpageBeanService<Menu> pbs = BeanFactory.getInstance("IpageBeanService", IpageBeanService.class);
	private IOperatorDishesService ds = BeanFactory.getInstance("IOperatorDishesService", IOperatorDishesService.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String method = request.getParameter("method");
		if(method==null){
			//进入首页前
			listFree(request,response);
			
		}else if("listfood".equals(method)){
			//进入菜品展示页
			listFood(request,response);
		}
	}

	private void listFood(HttpServletRequest request, HttpServletResponse response) {
		
		PageBean<Menu> pb = new PageBean<Menu>();
		String page = request.getParameter("page");
		String dishesId = request.getParameter("dishesId");
		String flag = request.getParameter("flag");
		if(page==null&&dishesId==null&flag==null){
			pb.setCurrentPage(1);
			String tableId = request.getParameter("tableId");
			DinnerTable dinnerTable = odt.findDinnerTableById(Integer.parseInt(tableId));
			HttpSession session = request.getSession();
			session.setAttribute("dinnerTable", dinnerTable);
		}
		if(page!=null){
			pb.setCurrentPage(Integer.parseInt(page));
		}
		pb.setPerCount(6);
		Condition condition = new Condition();
		if(dishesId!=null){
			condition.setDishesId(Integer.parseInt(dishesId));
		}
		pb.setCondition(condition);
		pbs.conditionQuery(pb);
		
		List<Dishes> dishesList = ds.findAllDishes();
		request.setAttribute("pageBean", pb);
		request.setAttribute("dishesList", dishesList);
		String url = "/app/detail/caidan.jsp";
		try {
			request.getRequestDispatcher(url).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	private void listFree(HttpServletRequest request, HttpServletResponse response) {
		List<DinnerTable> listFree = odt.noReserve(Status.Free);
		request.setAttribute("listFree", listFree);
		String url = "/app/index.jsp";
		try {
			request.getRequestDispatcher(url).forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
