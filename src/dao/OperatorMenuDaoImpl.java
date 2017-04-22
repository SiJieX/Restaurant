package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import entity.Dishes;
import entity.Menu;
import factory.BeanFactory;
import service.IOperatorDishesService;
import utils.JdbcUtil;

public class OperatorMenuDaoImpl implements IOperatorMenuDao {
	private QueryRunner qr = JdbcUtil.getQueryRunner();
	private IOperatorDishesService ods = BeanFactory.getInstance("IOperatorDishesService", IOperatorDishesService.class);
	@Override
	public void addfood(Menu m) {
		String sql = "INSERT INTO Menu (foodName,dishesId,price ,memberPrice ,intro, foodImage) VALUE(?,?,?,?,?,?);";
		try {
			qr.update(sql, m.getFoodName(), m.getDishes().getId(), m.getPrice(), m.getMemberPrice(), m.getIntro(),
					m.getFoodImage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteFood(Menu m) {
		String sql = "DELETE FROM Menu WHERE id=?;";
		try {
			qr.update(sql, m.getId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public void updateFood(Menu m) {
		String sql = "UPDATE  Menu SET foodName=?, dishesId = ?,price = ?, memberPrice = ?, intro = ?,foodImage=? WHERE id = ?;";
		try {
			qr.update(sql, m.getFoodName(), m.getDishes().getId(), m.getPrice(), m.getMemberPrice(), m.getIntro(),
					m.getFoodImage(), m.getId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	@Override
	public List<Menu> findAllFoods() {
		String sql = "SELECT * FROM Menu ORDER BY dishesId;";
		try {
			List<Menu> list = qr.query(sql, new ResultSetHandler <List<Menu>>() {

				@Override
				public List<Menu> handle(ResultSet rs) throws SQLException {
					ArrayList<Menu> list = new ArrayList<Menu>();
					while(rs.next()){
						Menu m = new Menu();
						m.setId(rs.getInt("id"));
						m.setFoodImage(rs.getString("foodImage"));
						m.setFoodName(rs.getString("foodName"));
						m.setIntro(rs.getString("intro"));
						m.setPrice(rs.getInt("price"));
						m.setMemberPrice(rs.getInt("memberPrice"));
						Dishes dishes = ods.findDisheById(rs.getInt("dishesId"));
						m.setDishes(dishes);
						list.add(m);
					}
					return list;
				}
			});
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public Menu findFoodById(Menu m) {
		String sql = "SELECT * FROM Menu WHERE id = ? ;";
		try {
			Menu menu = qr.query(sql, new BeanHandler<Menu>(Menu.class), m.getId());
			return menu;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	@Override
	public List<Menu> searchMenus(String MenuName) {
		String sql = "SELECT * FROM Menu WHERE foodName LIKE ?;";
		try {
			List<Menu> list = qr.query(sql, new ResultSetHandler<List<Menu>>() {

				@Override
				public List<Menu> handle(ResultSet rs) throws SQLException {
					
					ArrayList<Menu> list = new ArrayList<Menu>();
					while(rs.next()){
						Menu m = new Menu();
						m.setId(rs.getInt("id"));
						m.setFoodImage(rs.getString("foodImage"));
						m.setFoodName(rs.getString("foodName"));
						m.setIntro(rs.getString("intro"));
						m.setPrice(rs.getInt("price"));
						m.setMemberPrice(rs.getInt("memberPrice"));
						Dishes dishes = ods.findDisheById(rs.getInt("dishesId"));
						m.setDishes(dishes);
						list.add(m);
					}
					return list;
				}
			}, "%" + MenuName + "%");
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
