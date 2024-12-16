package com.directa24.directa24_back_end_dev_challenge_springboot.api.service.searchClient;

import java.io.Serializable;
import java.util.Set;

public interface ISearchService<T extends Serializable> {
    Set<T> searchAllMovies();
}
