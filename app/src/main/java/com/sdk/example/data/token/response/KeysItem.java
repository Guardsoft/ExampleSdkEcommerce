package com.sdk.example.data.token.response;

public class KeysItem{
	private String type;
	private String iv;
	private String token;
	private String base;

	public String getType(){
		return type;
	}

	public String getIv(){
		return iv;
	}

	public String getToken(){
		return token;
	}

	public String getBase(){
		return base;
	}

	@Override
	public String toString() {
		return "KeysItem{" +
				"type='" + type + '\'' +
				", iv='" + iv + '\'' +
				", token='" + token + '\'' +
				", base='" + base + '\'' +
				'}';
	}
}
