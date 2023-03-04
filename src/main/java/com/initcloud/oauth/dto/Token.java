package com.initcloud.oauth.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Token {

	private String accessToken;
	private String refreshToken;

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Request {
		private String client_id;
		private String client_secret;
		private String code;
		private String state;
		private String redirect_uri;

		public Request(String client_id, String client_secret, String code, String state, String redirect_uri) {
			this.client_id = client_id;
			this.client_secret = client_secret;
			this.code = code;
			this.state = state;
			this.redirect_uri = redirect_uri;
		}
	}
}
