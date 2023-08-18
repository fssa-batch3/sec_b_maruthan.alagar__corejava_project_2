package in.fssa.mambilling.model;

import java.time.LocalDateTime;
import java.util.List;

public class Bill {
	
	private LocalDateTime timeStamp;
	private int billId;
	
	public Bill(LocalDateTime timeStamp, int billId) {
		this.timeStamp = timeStamp;
		this.billId = billId;
	}
	

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}


	@Override
	public String toString() {
		return "Bill [ Time Stamp = " + timeStamp + " , Bill ID = "+billId+ "]";
	}

}
