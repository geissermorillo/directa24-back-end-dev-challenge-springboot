package com.directa24.directa24_back_end_dev_challenge_springboot.api.dto.director;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class DirectorResultDTO {
    @JsonProperty
    Set<String> directors;
}
