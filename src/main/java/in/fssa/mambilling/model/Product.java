package in.fssa.mambilling.model;

public class Product {
	private String productName;
	private int quantity;
	private QuantityType quantityType;
	private String specialName;
	private Price price;

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price new_price) {
		price = new_price;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public QuantityType getQuantityType() {
		return quantityType;
	}

	public void setQuantityType(QuantityType quantityType) {
		this.quantityType = quantityType;
	}

	public String getSpecialName() {
		return specialName;
	}

	public void setSpecialName(String specialName) {
		this.specialName = specialName;
	}

	public Product(String productName, int quantity, QuantityType quantityType, String specialName, Price price) {
		this.productName = productName;
		this.quantity = quantity;
		this.quantityType = quantityType;
		this.specialName = specialName;
		this.price = price;
	}

	public Product() {

	}

	@Override
	public String toString() {
		return "[Product " + "Product Name = '" + productName + '\'' + ", Quantity = " + quantity + ", Quantity Type = "
				+ quantityType + ", Special Name = '" + specialName + '\'' + ']';
	}

	public enum QuantityType {
		g, ml, nos
	}
}
