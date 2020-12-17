# Trip Expenses Calculator

This application calculates expense sharing for a group of people. There is no limit to the number of people in the application, but they must all have unique names. I have included a pre-built jar file in this main directory, but if you wish to build the application from scratch, the steps are below.

### How to build
To build the application, run
```
./gradlew build
```
on Mac/Linux or
```
gradlew build
```
on Windows.
### How to run
Once the application has been packaged into a jar file (if built in the above steps, it is located in ```build/libs```), invoke it as follows:
```
java -jar ./tripcalculator-0.1.jar [file-to-process]
```

There is a sample file included in ```src/test/resources/sample-expenses.txt```.

To run the included jar with the included sample file, run ```java -jar ./tripcalculator-0.1.jar ./src/test/resources/sample-expenses.txt``` in the main directory of this project.

### Expense file format
The application expects a file formatted as follows:
```
Person1Name,expense1,expense2,expense3,...
Person2Name,expense1,expense2,expense3,...
```
There can be multiple expenses per line, or one expense at a time, so this is also acceptable:
```
Person1Name,expense
Person2Name,expense
Person1Name,expense
```
