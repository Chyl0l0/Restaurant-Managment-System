package utcn.bll;

import utcn.presentation.Employee;

import java.io.Serializable;

/**
 * Clasa EmployeeAccount, este clasa care stocheaza conturile de angajat
 */
public class EmployeeAccount implements Serializable {
    private String username;
    private String password;

    public EmployeeAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public EmployeeAccount(){
        this.username="emp";
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

    @Override
    public String toString() {
        return "EmployeeAccount{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
