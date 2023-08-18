package in.fssa.mambilling.model;

public class BillItems {

	private int productId;
	private int priceId;
	private int quantity;



	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getPriceId() {
		return priceId;
	}

	public void setPriceId(int priceId) {
		this.priceId = priceId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "BillDetails [Product Id = " + productId + ", Price Id = " + priceId
				+ ", Quantity = " + quantity + "]";
	}

}
