package entity;

import java.util.List;

public class PageBean <T> {
	//当前页
	private  int currentPage;
	//总记录数
	private int totalCount;
	//总页数
	private int totalPage;
	//每页条数
	private int perCount;
	//存储每页的对象的集合
	private List<T> beanList;
	//条件
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
