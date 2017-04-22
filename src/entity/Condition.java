package entity;

public class Condition {
	private int dishesId;
	private String foodName;
	@Override
	public String toString() {
		return "Condition [dishesId=" + dishesId + ", foodName=" + foodName + "]";
	}
	public int getDishesId() {
		return dishesId;
	}
	public void setDishesId(int dishesId) {
		this.dishesId = dishesId;
	}
	public Condition() {
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
}
