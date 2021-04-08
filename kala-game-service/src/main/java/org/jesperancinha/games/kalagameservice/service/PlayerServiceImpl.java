package org.jesperancinha.games.kalagameservice.service;

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
}
