package org.example.service;

import org.example.aop.Transactional;
import org.example.aop.UserLoggedIn;

public class AopToolImpl implements AopTool {
    @Override
    @UserLoggedIn
    public <T> T userLoggedIn(Applyable<T> proc) {
        return proc.apply();
    }

    @Override
    @Transactional
    public <T> T transactional(Applyable<T> proc) {
        return proc.apply();
    }
}
