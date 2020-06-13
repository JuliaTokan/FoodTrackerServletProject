package ua.external.servlets.mail;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.external.servlets.service.ServiceException;
import ua.external.servlets.util.cоnst.EmailContent;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The {@code SendEmail} class provide sending e-mail.
 */
public class SendEmail {
    private final static Logger logger = LogManager.getLogger(SendEmail.class);
    private static final String PATH_CONFIG = "mail.properties";
    private static final String USER = "mail.user";
    private static final String PASSWORD = "mail.password";

    public void send(String sendTo, String subject, String messageToSend) throws ServiceException {
        Properties properties = loadProperties(PATH_CONFIG);
        final String user = properties.getProperty(USER);
        final String password = properties.getProperty(PASSWORD);

        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(sendTo));
            message.setSubject(subject);
            message.setContent(messageToSend, "text/html");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.error("Error sending email!", e);
            throw new ServiceException(e);
        }
    }

    public void sendWelcomeLetter(String sendTo) throws ServiceException {
        send(sendTo, "Welcome to Eat&Fit", EmailContent.WELCOME_LETTER);
    }

    public void sendWarningLetter(String sendTo) throws ServiceException {
        send(sendTo, "Your limit reached", EmailContent.WARNING_LETTER);
    }

    private Properties loadProperties(String fileName) {
        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            properties.load(inputStream);
        } catch (IOException e) {
            logger.log(Level.FATAL, "Reading file error", e);
            throw new RuntimeException(e);
        }
        return properties;
    }
}