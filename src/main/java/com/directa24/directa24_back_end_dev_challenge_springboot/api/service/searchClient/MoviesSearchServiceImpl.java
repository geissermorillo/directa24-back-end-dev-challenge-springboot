package com.directa24.directa24_back_end_dev_challenge_springboot.api.service.searchClient;

import com.directa24.directa24_back_end_dev_challenge_springboot.api.dto.movie.MovieRecord;
import com.directa24.directa24_back_end_dev_challenge_springboot.api.dto.movie.MoviesSearchResults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.IntStream;

@Service
public class MoviesSearchServiceImpl implements ISearchService<MovieRecord> {

    @Value("${movie.api.search.pageNumber.paramName:page}")
    private String pageParamName;

    public static final int PAGE_NUMBER_FOR_LIGHT_REQUEST = 0;

    private final RestClient restClient;

    public MoviesSearchServiceImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public Set<MovieRecord> searchAllMovies() {
        LinkedHashSet<MovieRecord> movies = new LinkedHashSet<>();
        // This is a light request to determine the total amount of pages for querying then all in order to get all
        // the records, we pass 0 because with this page we get pagination information without bringing data, which
        // improves the performance.

        MoviesSearchResults moviesSearchResults = getMoviesByPageNumber(PAGE_NUMBER_FOR_LIGHT_REQUEST);

        IntStream.range(1, moviesSearchResults.getTotalPages() + 1)
                .forEach(pageNumber -> {
                    MoviesSearchResults response = this.getMoviesByPageNumber(pageNumber);
                    movies.addAll(response.getMovies());
                });

        return movies;
    }

    private MoviesSearchResults getMoviesByPageNumber(int pageNumber) {
        return this.restClient
                .get().uri(uriBuilder -> uriBuilder.queryParam(this.pageParamName, pageNumber).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(MoviesSearchResults.class);
    }
}
