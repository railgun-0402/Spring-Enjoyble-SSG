package com.example.samuraitravel.event;

import com.example.samuraitravel.entity.User;
import com.example.samuraitravel.service.VerificationTokenService;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

/*
 * Listener
 */
@Component
public class SignupEventListener {
    private final VerificationTokenService verificationTokenService;
    private final JavaMailSender javaMailSender;

    public SignupEventListener(VerificationTokenService verificationTokenService, JavaMailSender javaMailSender) {
        this.verificationTokenService = verificationTokenService;
        this.javaMailSender = javaMailSender;
    }

    @EventListener
    private void onSignupEvent(SignupEvent signupEvent) {
        // SignupEventクラスから通知を受けたときに実行
        User user = signupEvent.getUser();
        String token = UUID.randomUUID().toString();
        verificationTokenService.create(user, token);

        String sendAddress = "springboot.samuraitravel@example.com";
        String recipientAddress = user.getEmail();
        String subject = "メール認証";
        String confirmationUrl = signupEvent.getRequestUrl() + "/verify?token=" + token;
        String message = "以下のリンクをクリックして会員登録を完了してください。";

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sendAddress);
        mailMessage.setTo(recipientAddress);
        mailMessage.setSubject(subject);
        mailMessage.setText(message + "\n" + confirmationUrl);
        javaMailSender.send(mailMessage);
    }
}
