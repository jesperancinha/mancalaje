package org.jesperancinha.games.kalagameservice.service;

import org.jesperancinha.games.kalagameservice.exception.GameOverException;
import org.jesperancinha.games.kalagameservice.exception.InvalidPitException;
import org.jesperancinha.games.kalagameservice.exception.NotOwnedPitException;
import org.jesperancinha.games.kalagameservice.exception.PlayerNotJoinedYetException;
import org.jesperancinha.games.kalagameservice.exception.WrongTurnException;
import org.jesperancinha.games.kalagameservice.model.Board;
import org.jesperancinha.games.kalagameservice.model.Pit;
import org.jesperancinha.games.kalagameservice.model.Player;
import org.jesperancinha.games.kalagameservice.repository.KalaBoardRepository;
import org.jesperancinha.games.kalagameservice.repository.KalaPitRepository;
import org.jesperancinha.games.kalagameservice.repository.KalaPlayerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

import static org.jesperancinha.games.kalagameservice.model.PitType.LARGE;
import static org.jesperancinha.games.kalagameservice.model.PitType.SMALL;

@Service
public class GameServiceImpl implements GameService {

    private final KalaBoardRepository boardRepository;

    private final KalaPitRepository pitRepository;

    private final KalaPlayerRepository playerRepository;

    public GameServiceImpl(KalaBoardRepository boardRepository, KalaPitRepository pitRepository, KalaPlayerRepository playerRepository) {
        this.boardRepository = boardRepository;
        this.pitRepository = pitRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public Board createNewBoard(Player player) {
        final Board board = new Board();
        final ArrayList<Pit> pits = new ArrayList<>();
        board.setPits(pits);
        var lastPit = Pit.builder().player(player).pitType(SMALL).stones(6).build();
        lastPit = pitRepository.save(lastPit);
        board.setPitOne(lastPit);
        final var firstPit = lastPit;
        for (int i = 0; i < 5; i++) {
            var pit = Pit.builder().player(player).pitType(SMALL).stones(6).build();
            lastPit.setNextPit(pit);
            pits.add(lastPit);
            lastPit = pit;
            lastPit = pitRepository.save(lastPit);
        }
        pits.add(lastPit);
        final var kalahPit = Pit.builder().player(player).pitType(LARGE).stones(0).build();
        lastPit.setNextPit(kalahPit);
        lastPit = kalahPit;
        lastPit = pitRepository.save(lastPit);
        for (int i = 0; i < 6; i++) {
            var pit = Pit.builder().pitType(SMALL).stones(6).build();
            final Pit oppositePit = pits.get(i);
            pit.setOppositePit(oppositePit);
            oppositePit.setOppositePit(pit);
            if (i == 0) {
                board.setPitTwo(pit);
            }
            lastPit.setNextPit(pit);
            pits.add(lastPit);
            lastPit = pit;
            lastPit = pitRepository.save(lastPit);

        }
        pits.add(lastPit);
        final var kalahPit2 = Pit.builder().pitType(LARGE).stones(0).build();
        lastPit.setNextPit(kalahPit2);
        lastPit = kalahPit2;
        lastPit = pitRepository.save(lastPit);
        pits.add(lastPit);
        lastPit.setNextPit(firstPit);
        board.setPits(pits);
        pits.forEach(pitRepository::save);
        board.setCurrentPlayer(player);
        board.setPlayerOne(player);
        if (Objects.isNull(player.getBoards())) {
            player.setBoards(new ArrayList<>());
        }
        board.setKalahOne(kalahPit);
        board.setKalahTwo(kalahPit2);
        final Board registeredBoard = boardRepository.save(board);
        player.getBoards().add(board);
        playerRepository.save(player);
        return registeredBoard;
    }

    @Override
    public Board sowStonesFromPit(Player player, Pit pit, Board board) {
        if (Objects.isNull(board.getPlayerTwo())) {
            throw new PlayerNotJoinedYetException();
        }
        if (Objects.nonNull(board.getWinner())) {
            throw new GameOverException();
        }
        if (pit.getPitType() == LARGE) {
            throw new InvalidPitException();
        }
        if (!pit.getPlayer().getUsername().equals(player.getUsername())) {
            throw new NotOwnedPitException();
        }
        if (!player.getUsername().equals(board.getCurrentPlayer().getUsername())) {
            throw new WrongTurnException();
        }
        var stones = pit.getStones();
        pit.setStones(0);
        var currentPit = pit.getNextPit();
        while (stones >= 0) {
            currentPit.setStones(currentPit.getStones() + 1);
            stones--;
            if (stones == 0) {
                if (currentPit.getPlayer().getUsername().equals(player.getUsername())) {
                    if (currentPit.getPitType() == LARGE) {
                        board.getPits().forEach(pitRepository::save);
                        return board;
                    }
                    int total = currentPit.getStones() + currentPit.getOppositePit().getStones();
                    currentPit.setStones(0);
                    currentPit.getOppositePit().setStones(0);
                    if (player.getUsername().equals(board.getPlayerOne().getUsername())) {
                        board.getKalahOne().setStones(board.getKalahOne().getStones() + total);
                    } else if (player.getUsername().equals(board.getPlayerTwo().getUsername())) {
                        board.getKalahTwo().setStones(board.getKalahOne().getStones() + total);
                    }
                    checkWinner(board);
                    board.getPits().forEach(pitRepository::save);
                    return board;
                }
                break;
            }
            currentPit = currentPit.getNextPit();
            if (currentPit.getPitType() == LARGE && !currentPit.getPlayer().getUsername().equals(player.getUsername())) {
                currentPit = currentPit.getNextPit();
            }
        }
        board.setCurrentPlayer(player.getOpponent());
        board.getPits().forEach(pitRepository::save);
        checkWinner(board);
        return boardRepository.save(board);
    }

    private void checkWinner(Board board) {
        if (isWinner(board.getPitOne())) {
            board.setWinner(board.getPlayerOne());
        } else if (isWinner(board.getPitTwo())) {
            board.setWinner(board.getPlayerTwo());
        }
    }

    private boolean isWinner(Pit pit) {
        boolean winnerone = true;
        while (pit.getPitType() != LARGE) {
            if (pit.getStones() > 0) {
                winnerone = false;
                break;
            }
            pit = pit.getNextPit();
        }
        return winnerone;
    }

    @Override
    public Board joinPlayer(Player playerTwo, Board board) {
        var pitTwo = board.getPitTwo();
        while (Objects.isNull(pitTwo.getPlayer())) {
            pitTwo.setPlayer(playerTwo);
            pitTwo = pitTwo.getNextPit();
        }
        board.getPits().forEach(pitRepository::save);
        board.setPlayerTwo(playerTwo);
        playerTwo.setOpponent(board.getPlayerOne());
        playerTwo.setCurrentBoard(board);

        final Player playerOne = board.getPlayerOne();
        playerOne.setOpponent(playerTwo);
        playerOne.setCurrentBoard(board);

        playerRepository.save(playerOne);
        playerRepository.save(playerTwo);
        return boardRepository.save(board);
    }
}
