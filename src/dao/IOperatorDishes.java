package dao;

import java.util.List;

import entity.Dishes;

public interface IOperatorDishes {
	//���
	public void addDishe(Dishes d);
	//ɾ��
	public void deleteDishe(Dishes d);
	//�޸�
	public void updateDishe(Dishes d);
	//��ѯȫ��
	public List<Dishes> findAllDishes();
	//����id��ѯ
	public Dishes findDisheById(int id);
	//����
	public List<Dishes> searchDishes(String DisheName);

}
