package factory;

import java.util.ResourceBundle;

public class BeanFactory {
	private static  ResourceBundle rb;
	static {
		rb=ResourceBundle.getBundle("instance");
	}
	@SuppressWarnings("unchecked")
	public static <T> T getInstance(String key,Class<T> clazz){
		String className = rb.getString(key);
		T t;
		try {
			t = (T) Class.forName(className).newInstance();
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
}
