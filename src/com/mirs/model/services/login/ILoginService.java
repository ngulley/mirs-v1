package com.mirs.model.services.login;

import com.mirs.model.domain.User;
import com.mirs.model.services.IService;

public interface ILoginService extends IService {
    public boolean authenticateUser(User user);
}
