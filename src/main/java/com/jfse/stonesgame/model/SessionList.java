package com.jfse.stonesgame.model;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.springframework.security.core.session.SessionInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joaofilipesabinoesperancinha on 07-04-16.
 */
@JsonAutoDetect
public class SessionList {
    private List<Session> sessions = new ArrayList<>();

    public SessionList(List<SessionInformation> sessionList, String sessionId) {
        int id = 0;
        for (SessionInformation sessionInformation : sessionList) {
            if (!sessionInformation.getSessionId().equals(sessionId)){
                sessions.add(new Session(id++, (String) sessionInformation.getPrincipal()));
            }
        }
    }

    public List<Session> getSessions() {
        return sessions;
    }
}
