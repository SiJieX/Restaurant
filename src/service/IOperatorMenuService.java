package service;

import java.util.List;

import entity.Menu;

public interface IOperatorMenuService {
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
		public List<Menu> searchDishes(String MenuName);
}
