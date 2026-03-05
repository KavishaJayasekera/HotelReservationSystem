
package com.hotel.controller;

import com.hotel.dao.UserDAO;

public class LoginController {
    private UserDAO userDAO;

    public LoginController() {
        this.userDAO = new UserDAO();
    }

    public Object authenticate(String username, String password) {
        if (username == null || password == null) {
            return null;
        }
        return userDAO.authenticate(username.trim(), password.trim());
    }
}