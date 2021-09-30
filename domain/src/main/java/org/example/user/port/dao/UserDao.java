package org.example.user.port.dao;

import org.example.user.model.entity.Email;
import org.example.user.model.entity.User;

public interface UserDao {
    boolean matches(User user);

    boolean exists(Email email);
}
