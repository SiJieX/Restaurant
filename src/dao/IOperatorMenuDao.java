package dao;

import java.util.List;

import entity.Menu;

public interface IOperatorMenuDao {
	//���
	public void addfood(Menu m);
	//ɾ��
	public void deleteFood(Menu m);
	//�޸�
	public void updateFood(Menu m);
	//��ѯ
	public Menu findFoodById(Menu m);
	//��ѯȫ��
	public List<Menu> findAllFoods();
	public List<Menu> searchMenus(String MenuName);
}
