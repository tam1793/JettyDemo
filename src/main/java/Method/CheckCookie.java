/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Method;

import DB.Repo.UserRepo;
import Object.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;

/**
 *
 * @author tam
 */
public class CheckCookie {

    public static User checkUserInCookie(HttpServletRequest request) {
        User user = null;
        String iD = "";
        String pW = "";

        for (Cookie ck : request.getCookies()) {
            if (ck.getName().equals("ckID")) {
                iD = ck.getValue();
            }
            if (ck.getName().equals("ckPassword")) {
                pW = ck.getValue();
            }
        }

        if (!iD.isEmpty() && !pW.isEmpty()) {
            UserRepo repo = new UserRepo();
            user = repo.checkUser(iD, pW);
        }

        return user;
    }

}
