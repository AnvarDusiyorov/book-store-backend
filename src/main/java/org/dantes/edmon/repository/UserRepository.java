package org.dantes.edmon.repository;

import org.dantes.edmon.model.User;

public interface UserRepository {
    User save(User user);
    User findByEmail(String email);
}
