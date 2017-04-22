package dao;

import java.util.List;

import entity.DinnerTable;
import enume.Status;

public interface IOperatorDinnerTableDao {
	//���
	public void addDinnerTable(DinnerTable t);
	//ɾ��
	public void deleteDinnerTable(int id);
	//����
	public void updateDinnerTable(DinnerTable t);
	//��ѯ����
	public List<DinnerTable> findAllDinnerTable();
	//����id��ѯ
	public DinnerTable findDinnerTableById(int id);
	public void freeTable(int id);
	//Ԥ��
	public void reserveTable(int id);
	//��ѯδԤ��
	public List<DinnerTable> noReserve(Status S);

}
