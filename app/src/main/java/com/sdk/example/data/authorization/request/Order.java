package com.sdk.example.data.authorization.request;

public class Order{
	private String transactionToken;
	private double amount;
	private int installment;
	private String channel;
	private String currency;
	private boolean countable;
	private String purchaseNumber;

	public void setTransactionToken(String transactionToken){
		this.transactionToken = transactionToken;
	}

	public String getTransactionToken(){
		return transactionToken;
	}

	public void setAmount(double amount){
		this.amount = amount;
	}

	public double getAmount(){
		return amount;
	}

	public void setInstallment(int installment){
		this.installment = installment;
	}

	public int getInstallment(){
		return installment;
	}

	public void setChannel(String channel){
		this.channel = channel;
	}

	public String getChannel(){
		return channel;
	}

	public void setCurrency(String currency){
		this.currency = currency;
	}

	public String getCurrency(){
		return currency;
	}

	public void setCountable(boolean countable){
		this.countable = countable;
	}

	public boolean isCountable(){
		return countable;
	}

	public void setPurchaseNumber(String purchaseNumber){
		this.purchaseNumber = purchaseNumber;
	}

	public String getPurchaseNumber(){
		return purchaseNumber;
	}
}
