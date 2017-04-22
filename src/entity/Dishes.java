package entity;

/*id INT PRIMARY KEY AUTO_INCREMENT,
dishesName VARCHAR(20)*/
public class Dishes {
	private int id;
	private String dishesName;

	public int getId() {
		return id;
	}

	public Dishes(int id, String dishesName) {
		this.id = id;
		this.dishesName = dishesName;
	}

	public Dishes() {
	}

	@Override
	public String toString() {
		return "Dishes [id=" + id + ", dishesName=" + dishesName + "]";
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDishesName() {
		return dishesName;
	}

	public void setDishesName(String dishesName) {
		this.dishesName = dishesName;
	}
}
