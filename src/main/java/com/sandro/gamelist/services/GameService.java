package com.sandro.gamelist.services;

import com.sandro.gamelist.dto.GameDTO;
import com.sandro.gamelist.dto.GameListDTO;
import com.sandro.gamelist.dto.GameMinDTO;
import com.sandro.gamelist.entities.Game;
import com.sandro.gamelist.projections.GameMinProjection;
import com.sandro.gamelist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll() {
        List<Game> result = gameRepository.findAll();
        return result.stream().map(GameMinDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public GameDTO findById(Long id) {
        var result = gameRepository.findById(id);
        return new GameDTO(result.orElseGet(Game::new));
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findByList(Long listId) {
        List<GameMinProjection> result = gameRepository.searchByList(listId);
        return result.stream().map(GameMinDTO::new).toList();
    }

}
