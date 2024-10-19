package com.example.samuraitravel.event;

import com.example.samuraitravel.entity.User;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SignupEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public SignupEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishSignupEvent(User user, String requestUrl) {
        applicationEventPublisher.publishEvent(new SignupEvent(this, user, requestUrl));
    }
}
