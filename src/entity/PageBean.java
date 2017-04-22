package entity;

import java.util.List;

public class PageBean <T> {
	//��ǰҳ
	private  int currentPage;
	//�ܼ�¼��
	private int totalCount;
	//��ҳ��
	private int totalPage;
	//ÿҳ����
	private int perCount;
	//�洢ÿҳ�Ķ���ļ���
	private List<T> beanList;
	//����
	private  Condition condition;
	public PageBean() {
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		if(totalCount/perCount==0){
			totalPage = totalCount/perCount;
		}else{
			totalPage = totalCount/perCount+1;
		}
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPerCount() {
		return perCount;
	}
	public void setPerCount(int perCount) {
		this.perCount = perCount;
	}
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
	public Condition getCondition() {
		return condition;
	}
	public void setCondition(Condition condition) {
		this.condition = condition;
	}
}
