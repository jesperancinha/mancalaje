package com.steelzack.mancalaje.manager;

import org.springframework.security.core.session.SessionInformation;

import java.util.List;

/**
 * Created by joaofilipesabinoesperancinha on 07-04-16.
 */
public interface SessionListKeeper {
    List<SessionInformation> getSessionList();

    void setSessionList(List<SessionInformation> sessionList);
}
