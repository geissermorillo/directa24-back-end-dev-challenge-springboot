package com.directa24.directa24_back_end_dev_challenge_springboot.api.contoller;

import com.directa24.directa24_back_end_dev_challenge_springboot.api.dto.director.DirectorResultDTO;
import com.directa24.directa24_back_end_dev_challenge_springboot.api.service.director.DirectorService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/director")
public class DirectorController {

    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public DirectorResultDTO getDirectorsByThreshold(@RequestParam("threshold") int threshold) {
        Set<String> directors = this.directorService.getDirectors(threshold);
        DirectorResultDTO directorResultDTO = new DirectorResultDTO();
        directorResultDTO.setDirectors(directors);
        return directorResultDTO;
//        return directors;
    }
}
