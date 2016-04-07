package com.jfse.stonesgame.manager;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by joaofilipesabinoesperancinha on 07-04-16.
 */
@Service("boardManagerService")
@Component
public class BoardEnterpriseImpl implements BoardEnterprise{
    private Map<String, BoardManager> boardManagerMap = new HashMap<>();

    @Override
    public BoardManager getBoardManagerByBoardID(String id)
    {
        return boardManagerMap.get(id);
    }

    @Override
    public void addBoardManager(String boardId, BoardManagerImpl boardManager)
    {
        boardManagerMap.put(boardId, boardManager);
    }
}
