package com.sdk.example.data.savetoken;

public class SaveTokenRequest {
	private String familyGroup;
	private String maskCardNumber;
	private String lastFourDigits;
	private String referenceNumber;
	private String bin;
	private String cardExpirationMonth;
	private String cardExpirationYear;
	private String token;

	public void setFamilyGroup(String familyGroup){
		this.familyGroup = familyGroup;
	}

	public String getFamilyGroup(){
		return familyGroup;
	}

	public void setMaskCardNumber(String maskCardNumber){
		this.maskCardNumber = maskCardNumber;
	}

	public String getMaskCardNumber(){
		return maskCardNumber;
	}

	public void setLastFourDigits(String lastFourDigits){
		this.lastFourDigits = lastFourDigits;
	}

	public String getLastFourDigits(){
		return lastFourDigits;
	}

	public void setReferenceNumber(String referenceNumber){
		this.referenceNumber = referenceNumber;
	}

	public String getReferenceNumber(){
		return referenceNumber;
	}

	public void setBin(String bin){
		this.bin = bin;
	}

	public String getBin(){
		return bin;
	}

	public void setCardExpirationMonth(String cardExpirationMonth){
		this.cardExpirationMonth = cardExpirationMonth;
	}

	public String getCardExpirationMonth(){
		return cardExpirationMonth;
	}

	public void setCardExpirationYear(String cardExpirationYear){
		this.cardExpirationYear = cardExpirationYear;
	}

	public String getCardExpirationYear(){
		return cardExpirationYear;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}
}
