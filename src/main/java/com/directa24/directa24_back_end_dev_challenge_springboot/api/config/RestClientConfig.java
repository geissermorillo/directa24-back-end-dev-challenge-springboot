package com.directa24.directa24_back_end_dev_challenge_springboot.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClient;

import java.util.List;

@Configuration
public class RestClientConfig {
    @Bean
    public RestClient movieApiRestClient(@Value("${movie.api.search.url}") String baseUrl) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(List.of(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
        return RestClient.builder()
                .baseUrl(baseUrl)
                .messageConverters(converters -> {
                            converters.removeIf(conv -> conv instanceof MappingJackson2HttpMessageConverter);
                            converters.add(converter);
                        }
                )
                .build();
    }
}
