package dao;

import java.util.List;

import entity.Dishes;

public interface IOperatorDishes {
	//添加
	public void addDishe(Dishes d);
	//删除
	public void deleteDishe(Dishes d);
	//修改
	public void updateDishe(Dishes d);
	//查询全部
	public List<Dishes> findAllDishes();
	//根据id查询
	public Dishes findDisheById(int id);
	//搜索
	public List<Dishes> searchDishes(String DisheName);

}
