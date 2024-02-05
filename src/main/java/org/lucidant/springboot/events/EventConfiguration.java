package org.lucidant.springboot.events;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class EventConfiguration {

    @Bean
    public WebClient webClient(@Value("${base.url:defaultUrl}") String baseUrl) {
        final var httpClient = HttpClient.create()
            .responseTimeout(Duration.ofSeconds(30));

        return WebClient.builder()
            .baseUrl(baseUrl)
            .clientConnector(new ReactorClientHttpConnector(httpClient))
            .build();
    }
}
