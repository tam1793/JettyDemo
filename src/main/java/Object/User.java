/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

/**
 *
 * @author tam
 */
public class User {

    private String iD;
    private String password;
    private String info;

    public User(String iD, String password) {
        this.iD = iD;
        this.password = password;
    }

    public User(String iD, String password, String info) {
        this.iD = iD;
        this.password = password;
        this.info = info;
    }

    public String getID() {
        return this.iD;
    }

    public String getPassword() {
        return this.password;
    }
    
    public String getInfo() {
        return this.info;
    }

}
