package com.mirs.model.services.login;

import com.mirs.model.domain.User;

public interface ILoginService {
    public boolean authenticateUser(User user);
}
