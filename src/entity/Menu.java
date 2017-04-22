package entity;

public class Menu {
	private int id;
	private String foodName;
	//private int dishesId;
	private Dishes dishes;
	private int price;
	private int memberPrice;
	private String intro;
	private String foodImage;
	@Override
	public String toString() {
		return "Menu [id=" + id + ", foodName=" + foodName + ", dishes=" + dishes + ", price=" + price
				+ ", memberPrice=" + memberPrice + ", intro=" + intro + ", foodImage=" + foodImage + "]";
	}
	
	
	public Menu(int id, String foodName, Dishes dishes, int price, int memberPrice, String intro, String foodImage) {
		super();
		this.id = id;
		this.foodName = foodName;
		this.dishes = dishes;
		this.price = price;
		this.memberPrice = memberPrice;
		this.intro = intro;
		this.foodImage = foodImage;
	}
	public Dishes getDishes() {
		return dishes;
	}
	public void setDishes(Dishes dishes) {
		this.dishes = dishes;
	}
	public Menu() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getMemberPrice() {
		return memberPrice;
	}
	public void setMemberPrice(int memberPrice) {
		this.memberPrice = memberPrice;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getFoodImage() {
		return foodImage;
	}
	public void setFoodImage(String foodImage) {
		this.foodImage = foodImage;
	}
}
