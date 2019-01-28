package delivery.model.vo;

public class Delivery implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private int num;
	private String foodName;
	private int price;
	private int count;
	private int totPrice;
	
	private int quantity;
	
	public Delivery() {}

	public Delivery(int num, String foodName, int price) {
		super();
		this.num = num;
		this.foodName = foodName;
		this.price = price;
	}


	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
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
	
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
	public int getTotPrice() {
		return totPrice;
	}

	public void setTotPrice(int totPrice) {
		this.totPrice = totPrice;
	}

	@Override
	public String toString() {
		return this.num+","+this.foodName+","+this.price;
	}
}
