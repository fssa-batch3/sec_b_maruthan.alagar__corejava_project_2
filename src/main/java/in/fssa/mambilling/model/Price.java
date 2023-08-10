package in.fssa.mambilling.model;

import java.util.Objects;

public class Price {

	private double mrp;

	private double tax;
	private double discount;

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
	public int hashCode() {
		return Objects.hash(discount, mrp, tax);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Price other = (Price) obj;
		return Double.doubleToLongBits(discount) == Double.doubleToLongBits(other.discount)
				&& Double.doubleToLongBits(mrp) == Double.doubleToLongBits(other.mrp)
				&& Double.doubleToLongBits(tax) == Double.doubleToLongBits(other.tax);
	}

	public Price(double mrp, double tax, double discount) {
		this.mrp = mrp;
		this.tax = tax;
		this.discount = discount;
	}

	public Price() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "[ Price : MRP = " + mrp + " Tax =" + tax + " Discount = " + discount +"]";
	} 
}
