package com.sdk.example.data.authorization.request;

public class AuthorizationRequest{
	private Header header;
	private Object cryptography;
	private Merchant merchant;
	private Device device;
	private Order order;

	public void setHeader(Header header){
		this.header = header;
	}

	public Header getHeader(){
		return header;
	}

	public void setCryptography(Object cryptography){
		this.cryptography = cryptography;
	}

	public Object getCryptography(){
		return cryptography;
	}

	public void setMerchant(Merchant merchant){
		this.merchant = merchant;
	}

	public Merchant getMerchant(){
		return merchant;
	}

	public void setDevice(Device device){
		this.device = device;
	}

	public Device getDevice(){
		return device;
	}

	public void setOrder(Order order){
		this.order = order;
	}

	public Order getOrder(){
		return order;
	}
}
