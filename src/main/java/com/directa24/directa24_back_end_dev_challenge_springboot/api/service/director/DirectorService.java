package com.directa24.directa24_back_end_dev_challenge_springboot.api.service.director;

import com.directa24.directa24_back_end_dev_challenge_springboot.api.dto.director.DirectorResultDTO;
import com.directa24.directa24_back_end_dev_challenge_springboot.api.dto.movie.MovieRecord;
import com.directa24.directa24_back_end_dev_challenge_springboot.api.service.searchClient.MoviesSearchServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class DirectorService {

    private final MoviesSearchServiceImpl moviesSearchService;

    public DirectorService(MoviesSearchServiceImpl moviesSearchService) {
        this.moviesSearchService = moviesSearchService;
    }

    public Set<String> getDirectors(int threshold) {
        Set<MovieRecord> moviesSearchResults = moviesSearchService.searchAllMovies();

        if (moviesSearchResults.isEmpty()) {
            return new TreeSet<>();
        }

        Map<String, Integer> amountOfMoviesByDirector = new HashMap<>();

        moviesSearchResults.stream()
                .map(MovieRecord::getDirector)
                .forEach(director ->
                        amountOfMoviesByDirector.put(director,
                                amountOfMoviesByDirector.getOrDefault(director, 0) + 1)
                );

        return amountOfMoviesByDirector.entrySet().stream()
                .filter(entry -> entry.getValue() > threshold)
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(TreeSet::new));
    }
}
