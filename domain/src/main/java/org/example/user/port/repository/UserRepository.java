package org.example.user.port.repository;

import org.example.user.model.entity.Email;
import org.example.user.model.entity.User;

public interface UserRepository {
    /**
     *
     * @param email An Email instance
     * @return true if the email address was stores before, false otherwise
     */
    boolean exists(Email email);

    Long create(User user);

    User findBy(Email email);
}
