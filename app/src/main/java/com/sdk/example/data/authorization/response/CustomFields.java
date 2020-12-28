package com.sdk.example.data.authorization.response;

import com.google.gson.annotations.SerializedName;

public class CustomFields{

	@SerializedName("CARD_EXPIRATION_YEAR")
	private String cARDEXPIRATIONYEAR;

	@SerializedName("FAST_PAYMENT")
	private String fASTPAYMENT;

	@SerializedName("CARD_EXPIRATION_MONTH")
	private String cARDEXPIRATIONMONTH;

	@SerializedName("COUNTABLE")
	private String cOUNTABLE;

	@SerializedName("ROW_IDENTIFIER")
	private String rOWIDENTIFIER;

	@SerializedName("RELOAD_PARAMS")
	private String rELOADPARAMS;

	@SerializedName("RELOAD_KEYS")
	private String rELOADKEYS;

	@SerializedName("BRAND")
	private String bRAND;

	public void setCARDEXPIRATIONYEAR(String cARDEXPIRATIONYEAR){
		this.cARDEXPIRATIONYEAR = cARDEXPIRATIONYEAR;
	}

	public String getCARDEXPIRATIONYEAR(){
		return cARDEXPIRATIONYEAR;
	}

	public void setFASTPAYMENT(String fASTPAYMENT){
		this.fASTPAYMENT = fASTPAYMENT;
	}

	public String getFASTPAYMENT(){
		return fASTPAYMENT;
	}

	public void setCARDEXPIRATIONMONTH(String cARDEXPIRATIONMONTH){
		this.cARDEXPIRATIONMONTH = cARDEXPIRATIONMONTH;
	}

	public String getCARDEXPIRATIONMONTH(){
		return cARDEXPIRATIONMONTH;
	}

	public void setCOUNTABLE(String cOUNTABLE){
		this.cOUNTABLE = cOUNTABLE;
	}

	public String getCOUNTABLE(){
		return cOUNTABLE;
	}

	public void setROWIDENTIFIER(String rOWIDENTIFIER){
		this.rOWIDENTIFIER = rOWIDENTIFIER;
	}

	public String getROWIDENTIFIER(){
		return rOWIDENTIFIER;
	}

	public void setRELOADPARAMS(String rELOADPARAMS){
		this.rELOADPARAMS = rELOADPARAMS;
	}

	public String getRELOADPARAMS(){
		return rELOADPARAMS;
	}

	public void setRELOADKEYS(String rELOADKEYS){
		this.rELOADKEYS = rELOADKEYS;
	}

	public String getRELOADKEYS(){
		return rELOADKEYS;
	}

	public void setBRAND(String bRAND){
		this.bRAND = bRAND;
	}

	public String getBRAND(){
		return bRAND;
	}
}