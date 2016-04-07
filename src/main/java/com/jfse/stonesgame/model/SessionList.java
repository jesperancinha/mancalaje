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

    private boolean gamestarted;

    public SessionList(List<SessionInformation> sessionList, String sessionId, boolean gamestarted) {
        int id = 0;
        for (SessionInformation sessionInformation : sessionList) {
            if (!sessionInformation.getSessionId().equals(sessionId)){
                sessions.add(new Session(id++, (String) sessionInformation.getPrincipal()));
            }
        }

        this.gamestarted = gamestarted;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setGamestarted(boolean gamestarted) {
        this.gamestarted = gamestarted;
    }

    public boolean isGamestarted() {
        return gamestarted;
    }
}
