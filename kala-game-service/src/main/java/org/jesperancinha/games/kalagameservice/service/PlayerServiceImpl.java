package org.jesperancinha.games.kalagameservice.service;

import org.jesperancinha.games.kalagameservice.model.Board;
import org.jesperancinha.games.kalagameservice.model.Player;
import org.jesperancinha.games.kalagameservice.repository.KalaPlayerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final KalaPlayerRepository playerRepository;

    public PlayerServiceImpl(KalaPlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player createPlayer(String username) {
        return playerRepository.save(Player.builder().username(username).build());
    }

    @Override
    @Transactional
    public Player createOrFindPlayerByName(String username) {
        var playerByUsernameEquals = playerRepository.findPlayerByUsernameEquals(username);
        if (Objects.isNull(playerByUsernameEquals)) {
            return createPlayer(username);
        }
        return playerByUsernameEquals;
    }

    @Override
    public void leaveCurrentGame(String name) {
        final Player player = playerRepository.findPlayerByUsernameEquals(name);
        final Board currentBoard = player.getCurrentBoard();
        if(Objects.nonNull(currentBoard)) {
            final Player opponent = player.getOpponent();
            if(Objects.nonNull(opponent)){
                opponent.setCurrentBoard(null);
                playerRepository.save(opponent);
            }
            player.setCurrentBoard(null);
            playerRepository.save(player);
        }
    }
}
