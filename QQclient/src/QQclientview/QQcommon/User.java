package QQclientview.QQcommon;

import java.io.Serializable;

public class User implements Serializable {

    public static final long serialVersionUID = 1L;
    private String userid;
    private String password;

    public User() {

    }

    public User(String password, String userid) {
        this.password = password;
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
