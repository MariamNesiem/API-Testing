package pojo;

public class AccessTokenParam {

	public String code;
	private String client_id;
	private String client_secret;
	private String redirect_uri;
	private String grant_type;
	
	
	
	public AccessTokenParam(String code) {
		this.code = code;
		this.client_id = "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com";
		this.client_secret ="erZOWM9g3UtwNRj340YYaK_W";
		this.redirect_uri = "https://www.googleapis.com/auth/userinfo.email";
		this.grant_type = "authorization_code";
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getClient_secret() {
		return client_secret;
	}
	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}
	public String getRedirect_uri() {
		return redirect_uri;
	}
	public void setRedirect_uri(String redirect_uri) {
		this.redirect_uri = redirect_uri;
	}
	public String getGrant_type() {
		return grant_type;
	}
	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

}
