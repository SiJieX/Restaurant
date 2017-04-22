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
			//ScalarHandler<Long>()����������� ���ص�������Long
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
		/*********��ҳ����**********/
		sb.append(" LIMIT ?,?");
		//�Ȳ�ѯ�ܼ�¼��
		int totalCount = totalCount(p);
		// ���÷�ҳbean����֮�ܼ�¼��
		p.setTotalCount(totalCount);
		/*****�жϣ�����ǰҳ< 1�� ���õ�ǰҳΪ1��  ����ǰҳ>��ҳ�������õ�ǰҳΪ��ҳ��******/
		if(p.getCurrentPage()<1){
			p.setCurrentPage(1);
		}else if(p.getCurrentPage()>p.getTotalPage()){
			p.setCurrentPage(p.getTotalPage());
		}
		//����limit�ĵ�һ������������ѯ�ĵ�ǰҳ��-1��*ÿҳ������
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
