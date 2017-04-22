package dao.basedao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import utils.JdbcUtil;

public class BaseDao<T> {
	//根据主键查询的方法：
	
	private Class clazz;
	
	private String name;
	//构造函数初始化(用于子类初始化)
	public BaseDao() {
		//获得父类的类型
		Type superclass = this.getClass().getGenericSuperclass();
		//将类型转化为参数化类型
		ParameterizedType parameterType =  (ParameterizedType) superclass;
		//获得所有参数的类型数组,由于只有一个参数，数组的第一个元素就是需要的数据
		Type[] actualTypeArguments = parameterType.getActualTypeArguments();
		//获得泛型的参数类型的字节码对象,由于Type接口实现了Calss接口，因此可以转化为class对象
		clazz =(Class) actualTypeArguments[0];
		//泛型字节码对象的简短类名，数据库表名必须和其一致；
		name=clazz.getSimpleName();
		System.out.println(name);
	}
	//根据id查询
	public T findById(int id){
		//name不能作为查询的参数
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
