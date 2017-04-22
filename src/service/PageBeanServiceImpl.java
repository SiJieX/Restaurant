package service;

import dao.IpageBeanDao;
import entity.Menu;
import entity.PageBean;
import factory.BeanFactory;

public class PageBeanServiceImpl  implements IpageBeanService<Menu>{
	private IpageBeanDao<Menu> pbd = BeanFactory.getInstance("IpageBeanDao", IpageBeanDao.class);
	@Override
	public int totalCount(PageBean<Menu> p) {
		return pbd.totalCount(p);
	}

	@Override
	public void conditionQuery(PageBean<Menu> p) {
		pbd.conditionQuery(p);
		
	}
	
}
