package ua.external.servlets.mail;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.external.servlets.service.ServiceException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.FileReader;
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
        final String WELCOME_LETTER = getWelcomeContent();
        send(sendTo, "Welcome to Eat&Fit", WELCOME_LETTER);
    }

    public void sendWarningLetter(String sendTo) throws ServiceException {
        final String WARNING_LETTER = getWarningContent();
        send(sendTo, "Your limit reached", WARNING_LETTER);
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

    private String getWelcomeContent(){
        return readFromFile("src/main/webapp/email/welcome_letter.html");
    }

    private String getWarningContent(){
        return readFromFile("src/main/webapp/email/warning_letter.html");
    }

    private String readFromFile(String path){
        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(path));
            String str;
            while ((str = in.readLine()) != null) {
                contentBuilder.append(str);
            }
            in.close();
        } catch (IOException e) {
            logger.log(Level.FATAL, "Reading file error", e);
        }
        String content = contentBuilder.toString();
        return content;
    }
}