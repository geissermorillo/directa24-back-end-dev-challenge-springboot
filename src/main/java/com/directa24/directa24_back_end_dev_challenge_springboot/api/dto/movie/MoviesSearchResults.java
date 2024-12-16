package com.directa24.directa24_back_end_dev_challenge_springboot.api.dto.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class MoviesSearchResults extends AbstractSearchResults implements Serializable {
    private static final long serialVersionUID = 5699164016726939895L;

    @JsonProperty("data")
    private List<MovieRecord> movies;
}
