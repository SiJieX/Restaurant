package service;

import entity.PageBean;

public interface IpageBeanService<T> {
	// ��ѯ�ܼ�¼��
	public int totalCount(PageBean<T> p);

	// ������ѯ
	public void conditionQuery(PageBean<T> p);
	// ����id��ѯ
}
