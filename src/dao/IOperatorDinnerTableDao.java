package dao;

import java.util.List;

import entity.DinnerTable;
import enume.Status;

public interface IOperatorDinnerTableDao {
	//添加
	public void addDinnerTable(DinnerTable t);
	//删除
	public void deleteDinnerTable(int id);
	//更新
	public void updateDinnerTable(DinnerTable t);
	//查询所有
	public List<DinnerTable> findAllDinnerTable();
	//根据id查询
	public DinnerTable findDinnerTableById(int id);
	public void freeTable(int id);
	//预定
	public void reserveTable(int id);
	//查询未预定
	public List<DinnerTable> noReserve(Status S);

}
