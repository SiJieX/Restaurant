package entity;

import java.util.Date;

public class DinnerTable {
	private  int id ;
	private String tableName;
	private int STATUS ;
	public DinnerTable() {
	}
	@Override
	public String toString() {
		return "DinnerTable [id=" + id + ", tableName=" + tableName + ", STATUS=" + STATUS + ", orderTime=" + orderTime
				+ "]";
	}
	private Date orderTime ;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public int getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(int sTATUS) {
		STATUS = sTATUS;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	} 
}
