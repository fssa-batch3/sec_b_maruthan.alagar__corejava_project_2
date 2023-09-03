package in.fssa.mambilling.dto;

import in.fssa.mambilling.model.Product.QuantityType;

public class ProductDTO {
//	 DATA TRANFOMATION MODAL

	private String productName;
	private int quantity;
	private QuantityType quantityType;
	private String specialName;
	private int totalQuantity;
	private double mrp;
	private double tax;
	private double discount;

	
	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
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

	public double getMrp() {
		return mrp;
	}

	public void setMrp(double mrp) {
		this.mrp = mrp;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return " [ Product Details Product Name = " + productName + " Quantity = " + quantity + "Quantity Type = "
				+ quantityType + "Special Name = " + specialName + " MRP = " + mrp + " Tax = " + tax + " Discount="
				+ discount + "]";
	}
}
