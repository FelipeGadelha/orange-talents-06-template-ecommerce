package br.com.zupacademy.felipe.gadelha.mercadolivre.api.handler;

public enum ExceptionStatus {

	MESSAGE_NOT_READABLE("/message-not-readable", "Message not readable"),
	INVALID_FORMAT("/invalid-format", "Invalid format"),
	INVALID_PARAMETER("/invalid-parameter", "Invalid parameter"),
	ILLEGAL_STATE("/illegal-state", "Illegal State"),
	ILLEGAL_ARGUMENT("/illegal-argument", "Illegal argument"),
	ARGUMENT_NOT_VALID("/argument-not-valid", "Argument not valid");
	
	private String title;
	private String uri;
	
	ExceptionStatus(String path, String title) {
		this.uri = "https://localhost:8080" + path;
		this.title = title;
	}

	public String getUri() {
		return uri;
	}
	
	public String getTitle() {
		return title;
	}
}
