package org.dantes.edmon.service;

import org.dantes.edmon.model.User;

public interface UserService {
    User save(User user);
    User findByEmail(String email);
}
