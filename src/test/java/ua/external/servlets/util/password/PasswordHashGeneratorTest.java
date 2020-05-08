package ua.external.servlets.util.password;

import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordHashGeneratorTest {
    PasswordHashGenerator generator = new PasswordHashGenerator();
    @Test
    public void hash() {
        String actual = generator.hash("2016-Om-7");
        String expected = "e84ceafb2fadee8f4290972263face2f1d7887b9";
        assertEquals(actual, expected);
    }
}