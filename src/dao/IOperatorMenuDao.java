package dao;

import java.util.List;

import entity.Menu;

public interface IOperatorMenuDao {
	//添加
	public void addfood(Menu m);
	//删除
	public void deleteFood(Menu m);
	//修改
	public void updateFood(Menu m);
	//查询
	public Menu findFoodById(Menu m);
	//查询全部
	public List<Menu> findAllFoods();
	public List<Menu> searchMenus(String MenuName);
}
