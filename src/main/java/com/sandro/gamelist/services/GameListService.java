package com.sandro.gamelist.services;

import com.sandro.gamelist.dto.GameListDTO;
import com.sandro.gamelist.dto.GameMinDTO;
import com.sandro.gamelist.entities.GameList;
import com.sandro.gamelist.projections.GameMinProjection;
import com.sandro.gamelist.repositories.GameListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {

    @Autowired
    private GameListRepository gameListRepository;

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll() {
        List<GameList> result = gameListRepository.findAll();
        return result.stream().map(GameListDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public GameListDTO findById(Long id) {
        var result = gameListRepository.findById(id);
        return new GameListDTO(result.orElseGet(GameList::new));
    }

}
