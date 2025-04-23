package com.A4Team.GamesShop.services.games;

import com.A4Team.GamesShop.entities.GameWithPlatform;
import com.A4Team.GamesShop.repository.GameWithPlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Service
public class GameWithPlatformService {

    @Autowired
    private GameWithPlatformRepository gameWithPlatformRepository;

    // @Cacheable("allGamesWithPlatform")
    public List<GameWithPlatform> findAll() {
        return gameWithPlatformRepository.findAll();
    }

    @Cacheable(value = "gameWithPlatform", key = "#gameId")
    public List<Object[]> findByGameId(int gameId) {
        return gameWithPlatformRepository.findByGameIdNative(gameId);
    }

    @Cacheable(value = "gamesByPlatform", key = "#platformName + '_' + #limit")
    public List<Object[]> findByPlatformName(String platformName, int limit) {
        return gameWithPlatformRepository.findByPlatformName(platformName, PageRequest.of(0, limit));
    }      

}
