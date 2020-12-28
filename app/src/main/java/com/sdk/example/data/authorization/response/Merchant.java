package com.sdk.example.data.authorization.response;

import com.google.gson.annotations.SerializedName;

public class Merchant{

	@SerializedName("merchantId")
	private String merchantId;

	@SerializedName("merchantName")
	private String merchantName;

	public void setMerchantId(String merchantId){
		this.merchantId = merchantId;
	}

	public String getMerchantId(){
		return merchantId;
	}

	public void setMerchantName(String merchantName){
		this.merchantName = merchantName;
	}

	public String getMerchantName(){
		return merchantName;
	}
}