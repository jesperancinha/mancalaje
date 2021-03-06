package com.jofisaes.mancala.services;

import com.jofisaes.mancala.cache.BoardManager;
import com.jofisaes.mancala.cache.Hole;
import com.jofisaes.mancala.cache.Player;
import com.jofisaes.mancala.services.game.GameManagerService;
import com.jofisaes.mancala.services.room.RoomsManagerService;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class GameManagerServiceTest {

    private GameManagerService gameManagerService = new GameManagerService(20, new RoomsManagerService());

    @Test
    public void createBoard() {
        Player mockPlayer = mock(Player.class);
        gameManagerService.createBoard(mockPlayer, "dev");

        List<BoardManager> boardManagers = gameManagerService.listAllGames();

        assertThat(boardManagers).hasSize(1);
        Optional<BoardManager> first = boardManagers.stream().findFirst();
        assertThat(first.isPresent()).isTrue();
        BoardManager boardManager = first.orElse(null);
        assertThat(boardManager).isNotNull();
        assertThat(boardManager.getBoardManagerId()).isEqualTo(1);
        assertThat(boardManager.getOwner()).isSameAs(mockPlayer);
        assertThat(boardManager.getBoard().getPlayer1()).isNull();
        assertThat(boardManager.getBoard().getPlayer2()).isNull();
    }

    @Test
    public void swayStonesFromHole() throws InterruptedException {
        Player mockPlayer1 = Player.builder().name("player 1").email("playone@mail.com").build();
        Player mockPlayer2 = Player.builder().name("player 2").email("playtwo@mail.com").build();
        BoardManager boardManager = gameManagerService.createBoard(mockPlayer1, "test");
        gameManagerService.joinPlayer(boardManager.getBoardManagerId(), mockPlayer1);
        gameManagerService.joinPlayer(boardManager.getBoardManagerId(), mockPlayer2);

        gameManagerService.swayStonesFromHole(mockPlayer1, 0);

        List<Hole> allHoles = mockPlayer1.getBoardManager().getBoard().getAllHoles();

        assertThat(allHoles.get(0).getStones()).isEqualTo(0);
        assertThat(allHoles.get(1).getStones()).isEqualTo(5);
        assertThat(allHoles.get(2).getStones()).isEqualTo(5);
        assertThat(allHoles.get(3).getStones()).isEqualTo(5);
        assertThat(allHoles.get(4).getStones()).isEqualTo(5);
        assertThat(allHoles.get(5).getStones()).isEqualTo(4);
        assertThat(allHoles.get(6).getStones()).isEqualTo(0);
        assertThat(allHoles.get(7).getStones()).isEqualTo(4);
        assertThat(boardManager.getCurrentPlayer()).isSameAs(mockPlayer2);
    }

    @Test
    public void swayStonesFromHoleLastOne() throws InterruptedException {
        Player mockPlayer1 = Player.builder().name("player 1").email("playone@mail.com").build();
        Player mockPlayer2 = Player.builder().name("player 2").email("playtwo@mail.com").build();
        BoardManager boardManager = gameManagerService.createBoard(mockPlayer1, "test");
        gameManagerService.joinPlayer(boardManager.getBoardManagerId(), mockPlayer1);
        gameManagerService.joinPlayer(boardManager.getBoardManagerId(), mockPlayer2);

        gameManagerService.swayStonesFromHole(mockPlayer1, 5);

        List<Hole> allHoles = mockPlayer1.getBoardManager().getBoard().getAllHoles();

        assertThat(allHoles.get(1).getStones()).isEqualTo(4);
        assertThat(allHoles.get(2).getStones()).isEqualTo(4);
        assertThat(allHoles.get(3).getStones()).isEqualTo(4);
        assertThat(allHoles.get(4).getStones()).isEqualTo(4);
        assertThat(allHoles.get(5).getStones()).isEqualTo(0);
        assertThat(allHoles.get(6).getStones()).isEqualTo(1);
        assertThat(allHoles.get(7).getStones()).isEqualTo(5);
        assertThat(allHoles.get(8).getStones()).isEqualTo(5);
        assertThat(allHoles.get(9).getStones()).isEqualTo(5);
        assertThat(allHoles.get(10).getStones()).isEqualTo(4);
        assertThat(boardManager.getCurrentPlayer()).isSameAs(mockPlayer2);
    }
}