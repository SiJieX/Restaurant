package service;

import java.util.List;


import dao.IOperatorMenuDao;
import entity.Menu;
import factory.BeanFactory;

public class OperatorMenuServiceImpl implements IOperatorMenuService {
	private IOperatorMenuDao omd = BeanFactory.getInstance("IOperatorMenuDao", IOperatorMenuDao.class);

	@Override
	public void addfood(Menu m) {
		
		omd.addfood(m);
	}

	@Override
	public void deleteFood(Menu m) {
		omd.deleteFood(m);
		
	}

	@Override
	public void updateFood(Menu m) {

		omd.updateFood(m);
	}

	@Override
	public Menu findFoodById(Menu m) {

		return omd.findFoodById(m);
	}

	@Override
	public List<Menu> findAllFoods() {

		return omd.findAllFoods();
	}

	@Override
	public List<Menu> searchDishes(String MenuName) {

		return omd.searchMenus(MenuName);
	}
	

}
