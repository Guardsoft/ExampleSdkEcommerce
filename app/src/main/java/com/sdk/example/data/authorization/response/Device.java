package com.sdk.example.data.authorization.response;

import com.google.gson.annotations.SerializedName;

public class Device{

	@SerializedName("reloadParams")
	private boolean reloadParams;

	@SerializedName("unattended")
	private boolean unattended;

	@SerializedName("fastPayment")
	private boolean fastPayment;

	@SerializedName("captureType")
	private String captureType;

	@SerializedName("terminalId")
	private String terminalId;

	@SerializedName("reloadKeys")
	private boolean reloadKeys;

	@SerializedName("fingerPrint")
	private String fingerPrint;

	public void setReloadParams(boolean reloadParams){
		this.reloadParams = reloadParams;
	}

	public boolean isReloadParams(){
		return reloadParams;
	}

	public void setUnattended(boolean unattended){
		this.unattended = unattended;
	}

	public boolean isUnattended(){
		return unattended;
	}

	public void setFastPayment(boolean fastPayment){
		this.fastPayment = fastPayment;
	}

	public boolean isFastPayment(){
		return fastPayment;
	}

	public void setCaptureType(String captureType){
		this.captureType = captureType;
	}

	public String getCaptureType(){
		return captureType;
	}

	public void setTerminalId(String terminalId){
		this.terminalId = terminalId;
	}

	public String getTerminalId(){
		return terminalId;
	}

	public void setReloadKeys(boolean reloadKeys){
		this.reloadKeys = reloadKeys;
	}

	public boolean isReloadKeys(){
		return reloadKeys;
	}

	public void setFingerPrint(String fingerPrint){
		this.fingerPrint = fingerPrint;
	}

	public String getFingerPrint(){
		return fingerPrint;
	}
}