package org.jesperancinha.games.kalagameservice.service;

import org.jesperancinha.games.kalagameservice.dto.BoardDto;
import org.jesperancinha.games.kalagameservice.dto.converters.BoardConverter;
import org.jesperancinha.games.kalagameservice.model.Board;
import org.jesperancinha.games.kalagameservice.model.Player;
import org.jesperancinha.games.kalagameservice.repository.KalaBoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {

    private final KalaBoardRepository boardRepository;

    public BoardServiceImpl(KalaBoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public Board findBoardById(Long id) {
        return boardRepository.findById(id).orElse(null);
    }

    @Override
    public List<BoardDto> findBoardsByPlayer(Player player) {
        return boardRepository.findBoardsByPlayerOneEquals(player).stream().map(BoardConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public List<BoardDto> findAvailableBoards() {
        return boardRepository.findBoardsByPlayerTwoIsNull().stream().map(BoardConverter::toDto).collect(Collectors.toList());
    }
}
