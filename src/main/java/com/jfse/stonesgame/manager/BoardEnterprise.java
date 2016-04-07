package com.jfse.stonesgame.manager;

/**
 * Created by joaofilipesabinoesperancinha on 07-04-16.
 */
public interface BoardEnterprise {
    BoardManager getBoardManagerByBoardID(String id);

    void addBoardManager(String boardId, BoardManagerImpl boardManager);
}
