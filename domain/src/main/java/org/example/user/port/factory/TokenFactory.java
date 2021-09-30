package org.example.user.port.factory;

import org.example.user.model.entity.User;

public interface TokenFactory {
    String create(User user);
}
