package com.dansoft.tripcalculator.input;

import com.dansoft.tripcalculator.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

public class FileReader {

    String fileLocation;
    int lineCounter = 0;

    Logger logger = LoggerFactory.getLogger(FileReader.class);

    public FileReader(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public List<Person> readFile() throws FileNotFoundException {

        HashMap<String, Person> personHashMap = new HashMap<>();
        try (Scanner scanner = new Scanner(new File(fileLocation))) {

            while(scanner.hasNextLine()) {
                lineCounter++;
                processLine(personHashMap, scanner);
            }
        } catch (FileNotFoundException e) {
            logger.error("File not found", e);
            throw e;
        }

        List<Person> finalList = new ArrayList<>();

        for(Map.Entry<String, Person> entry : personHashMap.entrySet()){
            finalList.add(entry.getValue());
        }

        return finalList;
    }

    private void processLine(HashMap<String, Person> personHashMap, Scanner scanner) {
        String line = scanner.nextLine();

        // line format:
        // name,expense,expense,...
        String[] parts = line.split(",");
        String name = parts[0];
        String[] expenses = Arrays.copyOfRange(parts, 1, parts.length);

        Person person = personHashMap.get(name);
        if (person == null) {
            // not found, create a new person
            person = new Person();
            person.setName(name);
            personHashMap.put(name, person);
        }

        for (String expenseString : expenses) {
            // parse expense to BigDecimal, add to expenses
            BigDecimal expense = null;
            try {
                expense = new BigDecimal(expenseString);
                person.getTripExpenses().add(expense);
                person.setTotalSpent(person.getTotalSpent().add(expense));
            } catch (NumberFormatException e) {
                logger.error("Could not parse expense '" + expenseString + "' on line " + lineCounter, e);
            }
        }
    }
}
