package dao.basedao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import utils.JdbcUtil;

public class BaseDao<T> {
	//����������ѯ�ķ�����
	
	private Class clazz;
	
	private String name;
	//���캯����ʼ��(���������ʼ��)
	public BaseDao() {
		//��ø��������
		Type superclass = this.getClass().getGenericSuperclass();
		//������ת��Ϊ����������
		ParameterizedType parameterType =  (ParameterizedType) superclass;
		//������в�������������,����ֻ��һ������������ĵ�һ��Ԫ�ؾ�����Ҫ������
		Type[] actualTypeArguments = parameterType.getActualTypeArguments();
		//��÷��͵Ĳ������͵��ֽ������,����Type�ӿ�ʵ����Calss�ӿڣ���˿���ת��Ϊclass����
		clazz =(Class) actualTypeArguments[0];
		//�����ֽ������ļ�����������ݿ�����������һ�£�
		name=clazz.getSimpleName();
		System.out.println(name);
	}
	//����id��ѯ
	public T findById(int id){
		//name������Ϊ��ѯ�Ĳ���
		String sql = "SELECT * FROM "+name+" WHERE id = ? ";
		try{
			QueryRunner qr = JdbcUtil.getQueryRunner();
			T t = qr.query(sql, new BeanHandler<T>(clazz),id);
			return t;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
