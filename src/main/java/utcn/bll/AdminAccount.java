package utcn.bll;

import java.io.Serializable;

/**
 * Clasa AdminAccount, este clasa care stocheaza conturile de administrator
 */
public class AdminAccount implements Serializable {
    private String username;
    private String password;

    public AdminAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public AdminAccount(){
        this.username="admin";
        this.password="123";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
