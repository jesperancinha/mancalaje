package org.jesperancinha.games.kalagameservice.service

import java.lang.RuntimeException
import lombok.NoArgsConstructor
import lombok.AllArgsConstructor
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import org.jesperancinha.games.kalagameservice.model.PitType
import org.jesperancinha.games.kalagameservice.model.Pit
import org.jesperancinha.games.kalagameservice.model.Player
import lombok.EqualsAndHashCode
import javax.persistence.FetchType
import org.jesperancinha.games.kalagameservice.model.Board
import org.springframework.data.jpa.repository.JpaRepository
import kotlin.Throws
import org.jesperancinha.games.kalagameservice.dto.BoardDto
import org.jesperancinha.games.kalagameservice.repository.KalaBoardRepository
import org.jesperancinha.games.kalagameservice.repository.KalaPitRepository
import org.jesperancinha.games.kalagameservice.repository.KalaPlayerRepository
import org.jesperancinha.games.kalagameservice.service.GameService
import org.jesperancinha.games.kalagameservice.exception.PlayerNotJoinedYetException
import org.jesperancinha.games.kalagameservice.exception.GameOverException
import org.jesperancinha.games.kalagameservice.exception.InvalidPitException
import org.jesperancinha.games.kalagameservice.exception.NotOwnedPitException
import org.jesperancinha.games.kalagameservice.exception.WrongTurnException
import org.jesperancinha.games.kalagameservice.service.BoardService
import org.jesperancinha.games.kalagameservice.exception.BoardDoesNotExistException
import java.util.stream.Collectors
import org.jesperancinha.games.kalagameservice.service.PlayerService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.ApplicationArguments
import kotlin.jvm.JvmStatic
import org.springframework.boot.SpringApplication
import org.jesperancinha.games.kalagameservice.KalaGameServiceApplication
import org.jesperancinha.games.kalagameservice.dto.converters.BoardConverter.toDto
import org.springframework.stereotype.Service
import java.util.function.Function
import java.util.function.Supplier

@Service
class BoardServiceImpl(private val boardRepository: KalaBoardRepository) : BoardService {
    @Throws(Throwable::class)
    override fun findBoardById(id: Long): Board? {
        return boardRepository.findById(id).orElseThrow { BoardDoesNotExistException() }
    }

    override fun findBoardsByPlayer(player: Player): MutableList<BoardDto>? {
        return boardRepository.findBoardsByPlayerOneEquals(player).stream()
            .map { obj: Board? -> toDto(obj) }.collect(Collectors.toList())
    }

    override fun findAvailableBoards(): MutableList<BoardDto>? {
        return boardRepository.findBoardsByPlayerTwoIsNull().stream()
            .map { obj: Board? -> toDto(obj) }.collect(Collectors.toList())
    }
}