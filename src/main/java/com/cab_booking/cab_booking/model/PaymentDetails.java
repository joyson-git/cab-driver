package com.cab_booking.cab_booking.model;

import com.cab_booking.cab_booking.model.domain.PaymentStatus;

public class PaymentDetails {
	
	
	
	private PaymentStatus paymentStatus;
	private String paymentId;
	private String razorpayPaymentLinkId;
	private String razorpayPaymentLinkReferenceId;
	private String razorpayPaymentLinkStatus;
	private String razorpayPaymentId;
	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getRazorpayPaymentLinkId() {
		return razorpayPaymentLinkId;
	}
	public void setRazorpayPaymentLinkId(String razorpayPaymentLinkId) {
		this.razorpayPaymentLinkId = razorpayPaymentLinkId;
	}
	public String getRazorpayPaymentLinkReferenceId() {
		return razorpayPaymentLinkReferenceId;
	}
	public void setRazorpayPaymentLinkReferenceId(String razorpayPaymentLinkReferenceId) {
		this.razorpayPaymentLinkReferenceId = razorpayPaymentLinkReferenceId;
	}
	public String getRazorpayPaymentLinkStatus() {
		return razorpayPaymentLinkStatus;
	}
	public void setRazorpayPaymentLinkStatus(String razorpayPaymentLinkStatus) {
		this.razorpayPaymentLinkStatus = razorpayPaymentLinkStatus;
	}
	public String getRazorpayPaymentId() {
		return razorpayPaymentId;
	}
	public void setRazorpayPaymentId(String razorpayPaymentId) {
		this.razorpayPaymentId = razorpayPaymentId;
	}
	public PaymentDetails(PaymentStatus paymentStatus, String paymentId, String razorpayPaymentLinkId,
			String razorpayPaymentLinkReferenceId, String razorpayPaymentLinkStatus, String razorpayPaymentId) {
		super();
		this.paymentStatus = paymentStatus;
		this.paymentId = paymentId;
		this.razorpayPaymentLinkId = razorpayPaymentLinkId;
		this.razorpayPaymentLinkReferenceId = razorpayPaymentLinkReferenceId;
		this.razorpayPaymentLinkStatus = razorpayPaymentLinkStatus;
		this.razorpayPaymentId = razorpayPaymentId;
	}
	@Override
	public String toString() {
		return "PaymentDetails [paymentId=" + paymentId + ", razorpayPaymentLinkId=" + razorpayPaymentLinkId
				+ ", razorpayPaymentLinkReferenceId=" + razorpayPaymentLinkReferenceId + ", razorpayPaymentLinkStatus="
				+ razorpayPaymentLinkStatus + ", razorpayPaymentId=" + razorpayPaymentId + "]";
	}
	public PaymentDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
