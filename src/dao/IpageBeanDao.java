package dao;

import entity.PageBean;

public interface IpageBeanDao <T> {
	//��ѯ�ܼ�¼��
	public int totalCount(PageBean<T> p);
	//������ѯ
	public void conditionQuery(PageBean<T> p);
	//����id��ѯ
	
	
}
