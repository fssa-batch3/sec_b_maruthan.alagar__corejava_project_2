package in.fssa.mambilling.model;

public class Shop {
	
	private String shopName;
	private String licenseNumber;
	private String GSTNNumber;
	private long phoneNumber;
	private String email;
	private String Address;
	private String printName;
	private String ownerName;
	private int id;
	private String password;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Shop() {
		
	}

	@Override
	public String toString() {
		return "Shop [shopName=" + shopName + ", licenceNumber=" + licenseNumber + ", GSTNNumber=" + GSTNNumber
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + ", Address=" + Address + ", printName="
				+ printName + ", ownerName=" + ownerName + ", id=" + id + "]";
	}
	
	public Shop(String shopName, String licenceNumber, String gSTNNumber, long phoneNumber, String email,
			String address, String printName, String ownerName, int id) {
		this.shopName = shopName;
		this.licenseNumber = licenceNumber;
		GSTNNumber = gSTNNumber;
		this.phoneNumber = phoneNumber;
		this.email = email;
		Address = address;
		this.printName = printName;
		this.ownerName = ownerName;
		this.id = id;
	}

	
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	
	
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenceNumber) {
		this.licenseNumber = licenceNumber;
	}
	public String getGSTNNumber() {
		return GSTNNumber;
	}
	public void setGSTNNumber(String gSTNNumber) {
		GSTNNumber = gSTNNumber;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getPrintName() {
		return printName;
	}
	public void setPrintName(String printName) {
		this.printName = printName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	

}
