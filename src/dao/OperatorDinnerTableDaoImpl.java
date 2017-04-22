package dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import entity.DinnerTable;
import enume.Status;
import utils.JdbcUtil;

public class OperatorDinnerTableDaoImpl implements IOperatorDinnerTableDao{
	private QueryRunner qr = JdbcUtil.getQueryRunner();
	@Test
	public void test(){
		DinnerTable t = new DinnerTable();
		t.setTableName("Å¦Ô¼");
		addDinnerTable(t);
	}
	@Override
	public void addDinnerTable(DinnerTable t) {
		String sql = "INSERT INTO DinnerTable(tableName) VALUES(?);";
		try{
			qr.update(sql, t.getTableName());
		}catch (Exception e){
			e.printStackTrace();
			throw  new RuntimeException(e);
		}
	}

	@Override
	public void deleteDinnerTable(int id) {
		String sql = "DELETE FROM DinnerTable WHERE id = ?;";
		try{
			qr.update(sql,id );
			
		}catch (Exception e){
			e.printStackTrace();
			throw  new RuntimeException(e);
		}
		
	}

	@Override
	public void updateDinnerTable(DinnerTable t) {
	
		String sql = "UPDATE  DinnerTable SET tableName=?,STATUS=?,orderTime=? WHERE id = ?;";
		try{
			qr.update(sql, t.getTableName(),t.getSTATUS(),t.getOrderTime(),t.getId());
			
		}catch (Exception e){
			e.printStackTrace();
			throw  new RuntimeException(e);
		}
	}

	@Override
	public List<DinnerTable> findAllDinnerTable() {
		String sql = "SELECT * FROM DinnerTable;";
		try{
			List<DinnerTable> listTable = qr.query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class));
			return listTable;
			
		}catch (Exception e){
			e.printStackTrace();
			throw  new RuntimeException(e);
		}
	}
	@Override
	public DinnerTable findDinnerTableById(int id) {
		String sql = "SELECT * FROM DinnerTable WHERE id = ?;";
		try{
			DinnerTable t = qr.query(sql, new BeanHandler<DinnerTable>(DinnerTable.class),id);
			return t;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	//ÍË×À
	@Override
	public void freeTable(int id) {
		String sql = "UPDATE DinnerTable SET STATUS=?, orderTime = ? WHERE id = ?;";
		try{
			qr.update(sql, 0,null,id);
		}catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	@Override
	public void reserveTable(int id) {
		String sql = "UPDATE DinnerTable SET STATUS=?, orderTime = ? WHERE id = ?";
		try{
			qr.update(sql, 1,null,id);
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	@Override
	public List<DinnerTable> noReserve(Status s) {
		String sql = "SELECT * FROM DinnerTable WHERE STATUS = ?;";
			int status =-1;
			if(s==Status.Free){
				status=0;
			}else if(s==Status.Reserve){
				status=1;
			}
		try{
			List<DinnerTable> list = qr.query(sql, new BeanListHandler<>(DinnerTable.class),status);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	@Test
	public void test2(){
		List<DinnerTable> list = noReserve(Status.Free);
		System.out.println(list);
	}

}
