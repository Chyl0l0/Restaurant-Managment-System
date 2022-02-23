package utcn.bll;

import java.io.Serializable;

/**
 * Clasa ClientAccount, este clasa care stocheaza conturile de client
 */
public class ClientAccount implements Serializable {
    private String username;
    private String password;
    private int clientID;
    private static int idGen;

    public ClientAccount(String username, String password) {
        this.username = username;
        this.password = password;
        clientID=idGen++;
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

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public static int getIdGen() {
        return idGen;
    }

    public static void setIdGen(int idGen) {
        ClientAccount.idGen = idGen;
    }

    @Override
    public String toString() {
        return "ClientAccount{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", clientID=" + clientID +
                '}';
    }
}
