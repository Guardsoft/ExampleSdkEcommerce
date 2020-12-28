package com.sdk.example.data.token.response;

import java.util.List;

public class AccessTokenResponse{
	private String publishableKey;
	private int expiresIn;
	private List<KeysItem> keys;
	private String accessToken;
	private String refreshToken;

	public String getPublishableKey(){
		return publishableKey;
	}

	public int getExpiresIn(){
		return expiresIn;
	}

	public List<KeysItem> getKeys(){
		return keys;
	}

	public String getAccessToken(){
		return accessToken;
	}

	public String getRefreshToken(){
		return refreshToken;
	}

	@Override
	public String toString() {
		return "AccessTokenResponse{" +
				"publishableKey='" + publishableKey + '\'' +
				", expiresIn=" + expiresIn +
				", keys=" + keys +
				", accessToken='" + accessToken + '\'' +
				", refreshToken='" + refreshToken + '\'' +
				'}';
	}
}