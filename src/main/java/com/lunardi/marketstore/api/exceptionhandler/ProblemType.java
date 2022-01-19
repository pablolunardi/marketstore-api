package com.lunardi.marketstore.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	INVALID_REQUEST("/invalid-request", "Invalid request"),
	RESOURCE_NOT_FOUND("/resource-not-found", "Resource not found");
	
	private String uri;
	private String title;
	
	ProblemType(String path, String title) {
		this.uri = "https://www.lunardimartketstore.com" + path;
		this.title = title;
	}
	
}
