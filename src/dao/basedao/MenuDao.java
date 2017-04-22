package dao.basedao;

import org.junit.Test;

import entity.Menu;

public class MenuDao  extends BaseDao<Menu>{
	@Test
	public void test(){
		MenuDao menuDao = new MenuDao();
		Menu m = menuDao.findById(8);
		System.out.println(m);
	}
}
