package service;

import java.util.List;

import org.junit.Test;

import dao.IOperatorDishes;
import entity.Dishes;
import factory.BeanFactory;

public class OperatorDishesServiceImpl implements IOperatorDishesService {
	//private IOperatorDishes od = new OperatorDishesImpl();
	private IOperatorDishes od = BeanFactory.getInstance("IOperatorDishes", IOperatorDishes.class);
	@Override
	public void addDishe(Dishes d) {
		od.addDishe(d);
	}
	@Test
	public void test(){
		Dishes d = new Dishes();
		d.setDishesName("¹ã¶«²Ë");
		addDishe(d);
	}

	@Override
	public void deleteDishe(Dishes d) {
		od.deleteDishe(d);
	}

	@Override
	public void updateDishe(Dishes d) {
		od.updateDishe(d);
	}

	@Override
	public List<Dishes> findAllDishes() {
		
		return od.findAllDishes();
	}

	@Override
	public Dishes findDisheById(int id) {
		return od.findDisheById(id);
	}

	@Override
	public List<Dishes> searchDishes(String DisheName) {
		return od.searchDishes(DisheName);
	}

}
