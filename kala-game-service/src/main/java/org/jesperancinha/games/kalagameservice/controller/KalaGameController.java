package org.jesperancinha.games.kalagameservice.controller;

import org.jesperancinha.games.kalagameservice.dto.BoardDto;
import org.jesperancinha.games.kalagameservice.dto.converters.BoardConverter;
import org.jesperancinha.games.kalagameservice.model.Player;
import org.jesperancinha.games.kalagameservice.service.BoardService;
import org.jesperancinha.games.kalagameservice.service.GameService;
import org.jesperancinha.games.kalagameservice.service.PlayerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController("api")
public class KalaGameController {

    private final GameService gameService;
    private final BoardService boardService;
    private final PlayerService playerService;

    public KalaGameController(GameService gameService,
                              BoardService boardService, PlayerService playerService) {
        this.gameService = gameService;
        this.boardService = boardService;
        this.playerService = playerService;
    }

    @GetMapping("{id}")
    public BoardDto getCurrentBoard(Principal principal,
                                    @PathVariable
                                            Long id) {
        return BoardConverter.toDto(boardService.findBoardById(id));
    }

    @PostMapping("create")
    public BoardDto createBoard(Principal principal) {
        var player = playerService.createOrFindPlayerByName(principal.getName());
        final var newBoard = gameService.createNewBoard(player);
        return BoardConverter.toDto(newBoard);
    }

    @GetMapping
    public List<BoardDto> getAllBoardsPerPlayer(Principal principal) {
        final Player player = playerService.createOrFindPlayerByName(principal.getName());
        return boardService.findBoardsByPlayer(player);
    }
    @GetMapping("available")
    public List<BoardDto> getAvailableBoards() {
        return boardService.findAvailableBoards();
    }
}

