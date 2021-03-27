package pojo;

public class OAuthParam {
	private String GrantType;
	private String ClientId;
	private String ClientSecret;
	private String UserName;
	private String Password;

	public OAuthParam() {

	}
	public void setGrantType() {
		this.GrantType = "password";
	}

	public void setClientId() {
		this.ClientId = "3MVG99OxTyEMCQ3hiXKYd.bH445nCkKS7kdI87_1tw9GXPyOcp566tuK4k3gVd2fBKED.aSBEBykQ_hkR36wK";
	}

	public void setClientSecret() {
		this.ClientSecret = "8269931119696158993";
	}

	public void setUserName() {
		this.UserName = "aam-integrantdevgroup@aamlive.com";
	}

	public void setPassword() {
		this.Password = ".NetCore@999";
	}
	public String getGrantType() {
		return GrantType;
	}
	public String getClientId() {
		return ClientId;
	}
	public String getClientSecret() {
		return ClientSecret;
	}
	public String getUserName() {
		return UserName;
	}
	public String getPassword() {
		return Password;
	}
}
