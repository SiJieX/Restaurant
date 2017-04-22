package servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import entity.Dishes;
import entity.Menu;
import factory.BeanFactory;
import service.IOperatorDishesService;
import service.IOperatorMenuService;

/**
 * Servlet implementation class FoodServlet
 */
public class FoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IOperatorMenuService ims = BeanFactory.getInstance("IOperatorMenuService", IOperatorMenuService.class);
	private IOperatorDishesService ods = BeanFactory.getInstance("IOperatorDishesService", IOperatorDishesService.class);
	private String url;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset= utf-8");
		String method = request.getParameter("method");
		if("list".equals(method)){
			//查询所有
			findAllFoods(request,response);
		}else if("addBefore".equals(method)){
			//添加前查询数据
			addBefore(request,response);
		}else if("add".equals(method)){
			//添加菜
			addFood(request,response);
		}else if("updateBefore".equals(method)){
			//更新之前
			updateBefore(request,response);
		}else if("update".equals(method)){
			//更新
			updateFood(request,response);
		}else if("delete".equals(method)){
			//删除
			deleteFood(request,response);
		}
		
		
		request.getRequestDispatcher(url).forward(request, response);
	}


	private void deleteFood(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Menu m = new Menu();
		m.setId(Integer.parseInt(id));
		ims.deleteFood(m);
		findAllFoods(request,response);
	}


	private void updateFood(HttpServletRequest request, HttpServletResponse response) {
		DiskFileItemFactory dfif = new DiskFileItemFactory();
		String path = request.getRealPath("/sys/upload/images/");
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		dfif.setRepository(file);
		ServletFileUpload upload = new ServletFileUpload(dfif);
		upload.setSizeMax(10*1024*1024);
		upload.setFileSizeMax(2*1024*1024);
		if(upload.isMultipartContent(request)){
			Menu m = new Menu();
			try {
				List<FileItem> fileItemList = upload.parseRequest(request);
				for (FileItem fileItem : fileItemList) {
					if(fileItem.isFormField()){
						//普通表单
						String nameValue = fileItem.getFieldName();
						String value = fileItem.getString("utf-8");
						
						if("dishes".equals(nameValue)){
							Dishes d = ods.findDisheById(Integer.parseInt(value));
							BeanUtils.setProperty(m, nameValue, d);
						}else{
							BeanUtils.setProperty(m, nameValue, value);
						}
					}else {
						//文件表单
						String fieldName = fileItem.getFieldName();
						String name = fileItem.getName();
						//判断是否改变图片
						if(!("".equals(name.trim())||name==null)){
							BeanUtils.setProperty(m, fieldName, "sys/upload/images/"+name);
							File f = new File(file, name);
							fileItem.write(f);
							fileItem.delete();
						}else{
							String foodImage = ims.findFoodById(m).getFoodImage();
							BeanUtils.setProperty(m, fieldName, foodImage);
						}
					}
				}
				ims.updateFood(m);
				findAllFoods(request,response);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
		}
		
	}


	private void updateBefore(HttpServletRequest request, HttpServletResponse response) {
			String id = request.getParameter("id");
			Menu m = new Menu();
			m.setId(Integer.parseInt(id));
			Menu menu = ims.findFoodById(m);
			List<Dishes> dishesList = ods.findAllDishes();
			request.setAttribute("dishesList", dishesList);
			request.setAttribute("menu", menu);
			url="/sys/updateFood.jsp";
			
	}

	private void addFood(HttpServletRequest request, HttpServletResponse response) {
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			String path = request.getRealPath("/sys/upload/images");
			File file = new File(path);
			if(!file.exists()){
				file.mkdirs();
			}
			factory.setRepository(file);
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(3*1024*1024);
			upload.setSizeMax(10*1024*1024);
			if(upload.isMultipartContent(request)){
				Menu m = new Menu();
				
				List<FileItem> fileItemList = upload.parseRequest(request);
				for (FileItem fileItem : fileItemList) {
					if(fileItem.isFormField()){
						//普通表单
						String name = fileItem.getFieldName();
						if("dishes".equals(name)){
							String id = fileItem.getString("utf-8");
							Dishes dishes = ods.findDisheById(Integer.parseInt(id));
							BeanUtils.setProperty(m, name, dishes);
						}else{
							String value = fileItem.getString("utf-8");
							BeanUtils.setProperty(m, name, value);
						}
						
						
					}else{
						//文件表单
						String fileName = fileItem.getName();
						String foodImage = fileItem.getFieldName();
						if(!(fileName==null||"".equals(fileName.trim()))){
							BeanUtils.setProperty(m, foodImage, "sys/upload/images/"+fileName);
							
							fileItem.write(new File(file, fileName));
							fileItem.delete();
						}else {
							BeanUtils.setProperty(m, foodImage, "sys/upload/images/timg.jpg");
						}
						
					}
				}
				ims.addfood(m);
			}
			
			findAllFoods(request,response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void addBefore(HttpServletRequest request, HttpServletResponse response) {
		List<Dishes> listDishes = ods.findAllDishes();
		request.setAttribute("listDishes", listDishes);
		url="/sys/saveFood.jsp";
	}

	private void findAllFoods(HttpServletRequest request, HttpServletResponse response) {
		List<Menu> listFoods = ims.findAllFoods();
		request.setAttribute("listFoods", listFoods);
		
		url="/sys/foodList.jsp";
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
