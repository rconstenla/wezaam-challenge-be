package com.wezaam.withdrawal.dto;

import java.util.List;

public class UserDTO {

	private Long id;
	private String firstName;
	private List<PaymentMethodDTO> paymentMethods;
	private Double maxWithdrawalAmount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public List<PaymentMethodDTO> getPaymentMethods() {
		return paymentMethods;
	}

	public void setPaymentMethods(List<PaymentMethodDTO> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}

	public Double getMaxWithdrawalAmount() {
		return maxWithdrawalAmount;
	}

	public void setMaxWithdrawalAmount(Double maxWithdrawalAmount) {
		this.maxWithdrawalAmount = maxWithdrawalAmount;
	}
}
