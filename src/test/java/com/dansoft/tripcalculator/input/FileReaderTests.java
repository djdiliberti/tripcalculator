package com.dansoft.tripcalculator.input;

import com.dansoft.tripcalculator.domain.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FileReaderTests {

    FileReader subject;

    @Test
    public void testValidInput() throws FileNotFoundException {
        subject = new FileReader("./src/test/resources/sample-expenses.txt");

        List<Person> result = subject.readFile();

        assertTrue(result.size() == 4);

        for (Person p: result) {
            if (p.getName().equals("Dan")) {
                assertTrue(p.getTripExpenses().size() == 4);
                assertTrue(p.getTotalSpent().compareTo(new BigDecimal(70)) == 0);
            } else if (p.getName().equals("Joe")) {
                assertTrue(p.getTripExpenses().size() == 3);
                assertTrue(p.getTotalSpent().compareTo(new BigDecimal(32)) == 0);
            } else if (p.getName().equals("Bob")) {
                assertTrue(p.getTripExpenses().size() == 4);
                assertTrue(p.getTotalSpent().compareTo(new BigDecimal(10)) == 0);
            } else if (p.getName().equals("John")) {
                assertTrue(p.getTripExpenses().size() == 1);
                assertTrue(p.getTotalSpent().compareTo(new BigDecimal(5)) == 0);
            }
        }
    }

    @Test
    public void testFileNotFound() {
        subject = new FileReader("./nofile.txt");
        assertThrows(FileNotFoundException.class, () -> {
            subject.readFile();
        });
    }

    @Test
    public void testMalformedInput() throws FileNotFoundException {
        subject = new FileReader("./src/test/resources/malformed-expenses.txt");

        List<Person> result = subject.readFile();

        assertTrue(result.size() == 2);

        for (Person p: result) {
            if (p.getName().equals("Dan")) {
                assertTrue(p.getTripExpenses().size() == 2);
                assertTrue(p.getTotalSpent().compareTo(new BigDecimal(30)) == 0);
            } else if (p.getName().equals("John")) {
                assertTrue(p.getTripExpenses().size() == 1);
                assertTrue(p.getTotalSpent().compareTo(new BigDecimal(5)) == 0);
            }
        }
    }
}
