/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB.Repo;

import DB.ConnectDB;
import Object.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tam
 */
public class UserRepo {

    public User checkUser(String iD, String pW) {
        User user = null;
        // SQL  SELECT * FROM `user` WHERE `id` = "" AND `password` =""
        String sql = "SELECT* FROM `user` WHERE `id` = \"" + iD + "\" AND `password` = \"" + pW + "\"";

        ConnectDB connectDB = new ConnectDB();
        Connection cnn = connectDB.getConnection();

        try {
            PreparedStatement pst = cnn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println("Login success");
                String idUser = rs.getString(1);
                String pUser = rs.getString(2);
                String infoUser = rs.getString(3);

                user = new User(idUser, pUser, infoUser);
            } else {
                System.out.println("Login Fail");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserRepo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                cnn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserRepo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return user;
    }

    public boolean checkID(String iD) {
        //SQL SELECT * FROM `user` WHERE `id` = ""
        String sql = "SELECT * FROM `user` WHERE `id` = \"" + iD + "\"";

        ConnectDB connectDB = new ConnectDB();
        Connection cnn = connectDB.getConnection();

        try {
            PreparedStatement pst = cnn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserRepo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                cnn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserRepo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;
    }

    public boolean createUser(String iD, String pW) {
        //SQL INSERT INTO `user`(`id`, `password`, `about`) VALUES ([value-1],[value-2])
        String sql = "INSERT INTO `user`(`id`, `password`) VALUES (\"" + iD + "\",\"" + pW + "\")";
        ConnectDB connectDB = new ConnectDB();
        Connection cnn = connectDB.getConnection();

        try {
            PreparedStatement pts = cnn.prepareStatement(sql);
            int result = pts.executeUpdate();
            if (result == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserRepo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                cnn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserRepo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;
    }
}
