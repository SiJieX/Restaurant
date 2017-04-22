package service;

import java.util.List;

import dao.IOperatorDinnerTableDao;
import entity.DinnerTable;
import enume.Status;
import factory.BeanFactory;

public class OperatorDinnerTableServiceImpl implements IOperatorDinnerTableService  {
	private IOperatorDinnerTableDao odt = BeanFactory.getInstance("IOperatorDinnerTableDao", IOperatorDinnerTableDao.class);

	@Override
	public void addDinnerTable(DinnerTable t) {
		odt.addDinnerTable(t);
	}

	@Override
	public void deleteDinnerTable(int id) {
		odt.deleteDinnerTable(id);
		
	}

	@Override
	public void updateDinnerTable(DinnerTable t) {

		odt.updateDinnerTable(t);
	}

	@Override
	public List<DinnerTable> findAllDinnerTable() {

		return odt.findAllDinnerTable();
	}

	@Override
	public DinnerTable findDinnerTableById(int id) {

		return odt.findDinnerTableById(id);
	}

	@Override
	public void freeTable(int id) {
		odt.freeTable(id);
	}

	@Override
	public void reserveTable(int id) {
		odt.reserveTable(id);
	}

	@Override
	public List<DinnerTable> noReserve(Status S) {
		return odt.noReserve(S);
	}

}
