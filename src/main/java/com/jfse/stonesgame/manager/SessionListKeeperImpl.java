package com.jfse.stonesgame.manager;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by joaofilipesabinoesperancinha on 07-04-16.
 */
@Component
@Scope(value = "sessionListKeeper", proxyMode= ScopedProxyMode.TARGET_CLASS)
public class SessionListKeeperImpl implements SessionListKeeper {
    private List<SessionInformation> sessionList;

    @Override
    public List<SessionInformation> getSessionList() {
        return sessionList;
    }

    @Override
    public void setSessionList(List<SessionInformation> sessionList) {
        this.sessionList = sessionList;
    }
}
