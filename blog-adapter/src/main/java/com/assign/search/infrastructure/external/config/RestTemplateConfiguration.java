package com.assign.search.infrastructure.external.config;

import static java.util.concurrent.TimeUnit.MINUTES;

import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.BasicHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    private final int ONE = 1;

    @Bean
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();

        BasicHttpClientConnectionManager connectionManager = getBasicHttpClientConnectionManager();
        RequestConfig requestConfig = getRequestConfig();

        CloseableHttpClient httpClient = HttpClientBuilder.create()
            .setConnectionManager(connectionManager)
            .setDefaultRequestConfig(requestConfig)
            .build();

        factory.setHttpClient(httpClient);

        return new RestTemplate(factory);
    }

    private BasicHttpClientConnectionManager getBasicHttpClientConnectionManager() {
        BasicHttpClientConnectionManager connectionManager = new BasicHttpClientConnectionManager();
        ConnectionConfig connConfig = ConnectionConfig.custom()
            .setConnectTimeout(ONE, MINUTES)
            .setSocketTimeout(ONE, MINUTES)
            .build();
        connectionManager.setConnectionConfig(connConfig);
        return connectionManager;
    }

    private RequestConfig getRequestConfig() {
        return RequestConfig.custom()
            .setConnectionRequestTimeout(ONE, MINUTES)
            .build();
    }
}
