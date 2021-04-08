package org.jesperancinha.games.kalagameservice.service;

import org.jesperancinha.games.kalagameservice.model.Board;
import org.jesperancinha.games.kalagameservice.repository.KalaBoardRepository;
import org.springframework.stereotype.Service;

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
}
