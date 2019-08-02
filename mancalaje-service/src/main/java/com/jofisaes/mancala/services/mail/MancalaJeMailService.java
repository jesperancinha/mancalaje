package com.jofisaes.mancala.services.mail;

import com.jofisaes.mancala.entities.User;
import com.jofisaes.mancala.exception.RegistrationMailNotSentException;
import com.jofisaes.mancala.exception.UnregistrationMailNotSentException;
import com.jofisaes.mancala.game.UserDto;
import com.jofisaes.mancala.services.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
public class MancalaJeMailService {

    private final JavaMailSender sender;

    private final UserService userService;

    private String noReplyEmail;

    public MancalaJeMailService(JavaMailSender sender,
                                UserService userService,
                                @Value("${mancalaje.mail.no-reply:#{null}}")
                                        String noReplyEmail) {
        this.sender = sender;
        this.userService = userService;
        this.noReplyEmail = noReplyEmail;
    }

    public void sendRegistrationMail(UserDto userDto) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom(noReplyEmail);
            helper.setTo(userDto.getEmail());
            helper.setSubject("Mancala JE - Registration");
            helper.setText(String.format(
                    "Hello there %s!\n\n" +
                            "Thank you for trying this demo version of Mancala JE. As you may have noticed, there are still lots of improvements to be made.\n\n" +
                            "I hope you can enjoy this version and I would love to hear from you. Please tweet me at @jofisaes or mail me at jofisaes@gmail.com\n\n" +
                            "you have registered with user/password %s/%s\n\n" +
                            "Looking forward to be hearing from you soon\n\n" +
                            "The Mancala JE (TM) (... It's just me)\n" +
                            "João Esperancinha\n" +
                            "http://joaofilipesabinoesperancinha.nl/\n\n\n" +
                            "-----------------------------\n\n\n" +
                            "Demo version: Please note that this app will be updated with no warning on needed occasions on periods from 19h to 23h CET/CEST depending on current DST. Your current game in progress will be removed, but your user will not. This means that you can login again, create rooms and play online again once the services are back online. In this current version you cannot change password. You will in future versions. Remember that your user will be available for a maximum idle period of 5 hours and that there is a current limitation of 100 users and 50 rooms. Expect bugs, conflicts and issues given that this is only an alfa version. Remember to use passwords you don't use for anything critical. I don't keep actual passwords anywaya. Only their ciphered version but remember to use something unique and specific to this application just in case.",
                    userDto.getName(), userDto.getEmail(), userDto.getPassword())
            );
            sender.send(message);
        } catch (MailException | MessagingException e) {
            log.error("Failed to send email", e);
            userService.remove(userDto.toUser());
            throw new RegistrationMailNotSentException();
        }
    }

    public void sendUnregistrationMail(User user) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setFrom(noReplyEmail);
            helper.setTo(user.getEmail());
            helper.setSubject("Mancala JE - Unregistration");
            helper.setText(String.format(
                    "Hello there %s!\n\n" +
                            "It looks like you haven't been playing this game for 5 hours straight! This means we say goodbye for now...\n\n " +
                            "Thank you for bravely trying my MancalaJE game application. You may have noticed quite a few improvement points.\n\n" +
                            "I hope you enjoyed this demo and I would love to hear from you. Please tweet me at @jofisaes or mail me at jofisaes@gmail.com\n\n" +
                            "You were registered with user %s\n\n" +
                            "Your details have been removed. If you'd like to play again please re-register\n\n" +
                            "Looking forward to be hearing from you soon\n\n" +
                            "The Mancala JE (TM) (... It's just me)\n" +
                            "João Esperancinha\n" +
                            "http://joaofilipesabinoesperancinha.nl/\n\n\n",
                    user.getName(), user.getEmail())
            );
            sender.send(message);
        } catch (MailException | MessagingException e) {
            log.error(String.format("Failed to send email! User %s not remobed", user.getEmail()), e);
            throw new UnregistrationMailNotSentException();
        }
    }
}
