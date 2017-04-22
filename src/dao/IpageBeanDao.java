package dao;

import entity.PageBean;

public interface IpageBeanDao <T> {
	//查询总记录数
	public int totalCount(PageBean<T> p);
	//条件查询
	public void conditionQuery(PageBean<T> p);
	//根据id查询
	
	
}
