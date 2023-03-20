package com.assign.search.config;

import static java.util.concurrent.TimeUnit.MINUTES;

import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
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

        PoolingHttpClientConnectionManager connectionManager = getPoolingHttpClientConnectionManager();
        RequestConfig requestConfig = getRequestConfig();

        CloseableHttpClient httpClient = HttpClientBuilder.create()
            .setConnectionManager(connectionManager)
            .setDefaultRequestConfig(requestConfig)
            .build();

        factory.setHttpClient(httpClient);

        return new RestTemplate(factory);
    }

    private PoolingHttpClientConnectionManager getPoolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
            .build();
        ConnectionConfig connConfig = ConnectionConfig.custom()
            .setConnectTimeout(ONE, MINUTES)
            .setSocketTimeout(ONE, MINUTES)
            .build();
        connectionManager.setDefaultConnectionConfig(connConfig);
        return connectionManager;
    }

    private RequestConfig getRequestConfig() {
        return RequestConfig.custom()
            .setConnectionRequestTimeout(ONE, MINUTES)
            .build();
    }
}
