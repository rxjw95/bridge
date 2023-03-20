package com.assign.search.infrastructure.event;

import com.assign.search.application.out.event.SearchEventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SearchEventPublisherAdapter implements SearchEventPublisher {

    private final ApplicationEventPublisher publisher;

    public SearchEventPublisherAdapter(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void publish(String keyword) {
        publisher.publishEvent(keyword);
    }
}
