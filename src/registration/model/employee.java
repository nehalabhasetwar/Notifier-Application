package registration.model;

import java.io.Serializable;

public class employee implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 
     */
    private String username;
    private String mobile;
    private String email;
    private String password;
    
    public String getusername() {
        return username;
    }
    public void setusername(String username) {
        this.username = username;
    }
    public String getemail() {
        return email;
    }
    public void setemail(String email) {
        this.email = email;
    }
    public String getpassword() {
        return password;
    }
    public void setpassword(String password) {
        this.password = password;
    }
    public String getmobile() {
        return mobile;
    }
    public void setmobile(String mobile) {
        this.mobile = mobile;
    }
   
}
