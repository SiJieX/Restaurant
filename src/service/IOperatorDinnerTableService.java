package service;

import java.util.List;

import entity.DinnerTable;
import enume.Status;

public interface IOperatorDinnerTableService {
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
		//退桌
		public void freeTable(int id);
		public void reserveTable(int id);
		public List<DinnerTable> noReserve(Status S);

}
