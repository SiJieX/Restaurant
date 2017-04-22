package dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import entity.Condition;
import entity.Menu;
import entity.PageBean;
import utils.JdbcUtil;

public class PageBeanDaoImpl implements IpageBeanDao<Menu> {
	private QueryRunner qr = JdbcUtil.getQueryRunner();
	
	@Override
	public int totalCount(PageBean<Menu> p) {
		int dishesId = p.getCondition().getDishesId();
		String foodName = p.getCondition().getFoodName();
		ArrayList<Object> list = new ArrayList<>();
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT"); 
		sb.append("		COUNT(m.id)"); 
		sb.append(" FROM"); 
		sb.append(" 	Menu m,Dishes d"); 
		sb.append(" WHERE 1=1"); 
		sb.append(" 	AND m.dishesId=d.id"); 
		if(dishesId>0){
			sb.append(" AND m.dishesId=?" );
			list.add(dishesId);
		}
		if(foodName!=null&&!"".equals(foodName.trim())){
			sb.append(" AND m.foodName like ?");
			list.add(foodName);
		}
		try{
			//ScalarHandler<Long>()返回数量结果 返回的类型是Long
			Long count = qr.query(sb.toString(), new ScalarHandler<Long>(),list.toArray());
			return count.intValue();
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);

		}
	}
	@Test
	public void test(){
		Condition c = new Condition();
		PageBean<Menu> pageBean = new PageBean<>();
		pageBean.setCondition(c);
		pageBean.setCurrentPage(0);
		pageBean.setPerCount(6);
		conditionQuery(pageBean);
		System.out.println(pageBean.getBeanList());
	}
	@Override
	public void conditionQuery(PageBean<Menu> p) {
		int dishesId = p.getCondition().getDishesId();
		String foodName = p.getCondition().getFoodName();
		ArrayList<Object> list = new ArrayList<>();
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT");	
		sb.append("		m.id,");
		sb.append("		m.foodName,");
		sb.append("		m.dishesId,");
		sb.append("		m.price,");
		sb.append("		m.memberPrice,");
		sb.append("		m.foodImage ");
		sb.append(" FROM ");
		sb.append("		Menu m,");
		sb.append("		Dishes d");
		sb.append("	WHERE 1=1");
		sb.append("		AND m.dishesId=d.id");
		if(dishesId>0){
			sb.append("		AND m.dishesId = ?");
			list.add(dishesId);
		}
		if(foodName!=null&&!"".equals(foodName.trim())){
			sb.append("		AND m.foodName = ?");
			list.add(foodName);
		}
		/*********分页条件**********/
		sb.append(" LIMIT ?,?");
		//先查询总记录数
		int totalCount = totalCount(p);
		// 设置分页bean参数之总记录数
		p.setTotalCount(totalCount);
		/*****判断：当当前页< 1， 设置当前页为1；  当当前页>总页数，设置当前页为总页数******/
		if(p.getCurrentPage()<1){
			p.setCurrentPage(1);
		}else if(p.getCurrentPage()>p.getTotalPage()){
			p.setCurrentPage(p.getTotalPage());
		}
		//设置limit的第一个参数：（查询的当前页数-1）*每页的行数
		list.add((p.getCurrentPage()-1)*p.getPerCount());
		list.add(p.getPerCount());
		
		try{
			List<Menu> menuList = qr.query(sb.toString(), new BeanListHandler<>(Menu.class),list.toArray());
			p.setBeanList(menuList);
		}catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
	}


}
