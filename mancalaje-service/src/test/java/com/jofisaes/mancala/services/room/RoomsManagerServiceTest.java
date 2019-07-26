package com.jofisaes.mancala.services.room;

import com.jofisaes.mancala.cache.BoardManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RoomsManagerServiceTest {

    @InjectMocks
    private RoomsManagerService roomsManagerService;

    @Test
    public void removeRoom() {
        final BoardManager testBoardManager = mock(BoardManager.class);
        roomsManagerService.addBoard(1L, testBoardManager);

        final BoardManager boardManager = roomsManagerService.removeRoom(1L);

        assertThat(boardManager).isSameAs(testBoardManager);
        assertThat(roomsManagerService.getBoardManagerMap()).isEmpty();
        assertThat(roomsManagerService.getBoardManagers()).isEmpty();
    }

    @Test
    public void forceRemoveRoom() {
        final BoardManager testBoardManager = mock(BoardManager.class);
        when(testBoardManager.getBoardManagerId()).thenReturn(1L);
        roomsManagerService.addBoard(1L, testBoardManager);

        final BoardManager boardManager = roomsManagerService.forceRemoveRoom(1L);

        verify(testBoardManager, only()).getBoardManagerId();
        assertThat(boardManager).isSameAs(testBoardManager);
        assertThat(roomsManagerService.getBoardManagerMap()).isEmpty();
        assertThat(roomsManagerService.getBoardManagers()).isEmpty();
    }

    @Test
    public void forceRemoveRoom_notExists_null() {
        final BoardManager boardManager = roomsManagerService.removeRoom(1L);

        assertThat(boardManager).isNull();
        assertThat(roomsManagerService.getBoardManagerMap()).isEmpty();
        assertThat(roomsManagerService.getBoardManagers()).isEmpty();
    }

    @Test
    public void getBoardManagers() {
        final BoardManager testBoardManager = mock(BoardManager.class);
        when(testBoardManager.getBoardManagerId()).thenReturn(1L);
        roomsManagerService.addBoard(1L, testBoardManager);

        final List<BoardManager> boardManagers = roomsManagerService.getBoardManagers();

        assertThat(boardManagers).isNotNull();
        assertThat(boardManagers).contains(testBoardManager);
        BoardManager boardManager = boardManagers.get(0);
        assertThat(boardManager).isNotNull();
        assertThat(boardManager.getBoardManagerId()).isEqualTo(1L);
    }

    @Test
    public void getBoardManagerMap() {
        final BoardManager testBoardManager = mock(BoardManager.class);
        when(testBoardManager.getBoardManagerId()).thenReturn(1L);
        roomsManagerService.addBoard(1L, testBoardManager);

        final Map<Long, BoardManager> boardManagerMap = roomsManagerService.getBoardManagerMap();

        assertThat(boardManagerMap).isNotNull();
        assertThat(boardManagerMap).containsKey(1L);
        final BoardManager boardManagerResult = boardManagerMap.get(1L);
        assertThat(boardManagerResult).isSameAs(testBoardManager);
        assertThat(boardManagerResult).isNotNull();
        assertThat(boardManagerResult.getBoardManagerId()).isEqualTo(1L);
    }
}