package dao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import entity.Dishes;
import utils.JdbcUtil;

public class OperatorDishesImpl implements IOperatorDishes{
	@Override
	public void addDishe(Dishes d) {
		String sql = "INSERT INTO dishes(dishesName) VALUES(?);";
		try {
			JdbcUtil.getQueryRunner().update(sql, d.getDishesName());
		} catch (Exception e) {
			new RuntimeException(e);
		}
		
	}

	@Override
	public void deleteDishe(Dishes d) {
		String sql = "DELETE FROM dishes WHERE id = ?;";
		try {
			JdbcUtil.getQueryRunner().update(sql, d.getId());
		} catch (Exception e) {
		throw new RuntimeException(e);
		}
		
	}

	@Override
	public void updateDishe(Dishes d) {
		String sql = "UPDATE  dishes SET dishesName = ? WHERE id=?;";
		try {
			JdbcUtil.getQueryRunner().update(sql, d.getDishesName(),d.getId());
		} catch (Exception e) {
		throw	new RuntimeException(e);
		}
	}

	@Override
	public List<Dishes> findAllDishes() {
		String sql = "SELECT * FROM dishes;";
		try {
			return JdbcUtil.getQueryRunner().query(sql, new BeanListHandler<Dishes>(Dishes.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Dishes findDisheById(int id) {
		String sql = "SELECT * FROM dishes WHERE id = ?;";
		try {
			return JdbcUtil.getQueryRunner().query(sql, new BeanHandler<Dishes>(Dishes.class), id);
		} catch (Exception e) {
		throw	new RuntimeException(e);
		}
	}

	@Override
	public List<Dishes> searchDishes(String DisheName) {
		String sql = "SELECT * FROM dishes WHERE id LIKE ?;";
		try {
			return JdbcUtil.getQueryRunner().query(sql, new BeanListHandler<Dishes>(Dishes.class), "%"+DisheName+"%");
		} catch (Exception e) {
		throw	new RuntimeException(e);
		}
	}

}
