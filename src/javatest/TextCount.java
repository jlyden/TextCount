package javatest;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lydenj
 * @since 10/31/2017
 * This is an answer to 'Pre-screening Java test.txt'
 */
public class TextCount {
    public static void main(String[] args) throws IOException {
        // Check parameters
        if (argsAreValid(args)) {
            try {
                // Read in the file
                List<String> sourceLineArray = readSourceToLineArray(args[0]);

                // Prepare variables
                // We need to distinguish between not searching and a search that turns up nothing
                int linesWithSearchString = -1;
                int sourceSearchStringCount = -1;
                String stringToFind = "";

                // If a search string was provided ...
                if (args.length > 1) {
                    stringToFind = args[1];
                    linesWithSearchString = 0;
                    sourceSearchStringCount = 0;
                }

                Boolean caseInsensitive = false;
                // If caseInsensitive was specified ...
                if (args.length == 3) {
                    caseInsensitive = Boolean.parseBoolean(args[2]);
                }

                // First output
                int lineCount = sourceLineArray.size();
                // Prep for second output
                int sourceWordCount = 0;

                // For each line, get word count and search String counts if necessary
                for (String oneLine : sourceLineArray) {
                    List<String> lineWordArray = convertToArrayOfWords(oneLine);
                    int lineWordCount = lineWordArray.size();
                    // Second output
                    sourceWordCount += lineWordCount;
                    // Search for String if necessary
                    if (!stringToFind.isEmpty()) {
                        int countOfSearchStringInLine = countSearchString(lineWordArray, stringToFind, caseInsensitive);
                        if (countOfSearchStringInLine > 0) {
                            // Fourth output
                            linesWithSearchString += 1;
                            // Fifth output
                            sourceSearchStringCount += countOfSearchStringInLine;
                        }
                    }
                }
                // Third output
                int avgWordsPerLine = sourceWordCount / lineCount;

                // Return results
                List<Integer> results = new ArrayList<>(Arrays.asList(lineCount,
                                                                      sourceWordCount,
                                                                      avgWordsPerLine,
                                                                      linesWithSearchString,
                                                                      sourceSearchStringCount));
                displayResults(results);
            } catch (NoSuchFileException e) {
                System.out.println("ERROR: Unable to find file");
            }

        }
    }

    private static boolean argsAreValid(String[] args) {
        boolean passValidation = false;
        int numberOfArgs = args.length;
        switch (numberOfArgs) {
            case 0:
                System.out.println("ERROR: Invalid entry; no parameters provided");
                System.out.println("Proper Usage: TextCount <REQUIRED path-to-source> <OPTIONAL text-to-find> <OPTIONAL case-insensitive-search>");
                break;
            case 1:
            case 2:
                passValidation = true;
                break;
            case 3:
                // Confirm case-insensitive is boolean
                if (args[2].equals("true") || args[2].equals("false")) {
                    passValidation = true;
                } else {
                    System.out.println("ERROR: Invalid entry");
                    System.out.println("Proper Usage: TextCount <path-to-source> <text-to-find> <case-insensitive-search>");
                    System.out.println("Parameter Help: <case-insensitive-search> is Optional Boolean (true or false)");
                }
                break;
            default:
                System.out.println("ERROR: Invalid entry");
                System.out.println("Proper Usage: TextCount <path-to-source> <text-to-find> <case-insensitive-search>");
                System.out.println("Parameter Help: <path-to-source> is Required String");
                System.out.println("Parameter Help: <text-to-find> is Optional String");
                System.out.println("Parameter Help: <case-insensitive-search> is Optional Boolean (true or false)");
                break;
        }
        return passValidation;
    }

    private static List<String> readSourceToLineArray(String path) throws IOException {
        // Reference: https://stackoverflow.com/questions/326390/how-do-i-create-a-java-string-from-the-contents-of-a-file
        return Files.readAllLines(Paths.get(path), Charset.defaultCharset());
    }

    private static List<String> convertToArrayOfWords(String oneLine) {
        return Arrays.asList(oneLine.trim().split(" "));
    }

    private static int countSearchString(List<String> lineWordArray, String stringToFind, Boolean caseInsensitive) {
        int searchStringCount = 0;
        for (String word:lineWordArray) {
            // Remove non-alpha-numerical characters from word
            // Reference: https://stackoverflow.com/questions/11149759/remove-all-non-alphabetic-characters-from-a-string-array-in-java
            String cleanWord = word.replaceAll("[^a-zA-Z0-9]", "");
            if (caseInsensitive) {
                if (cleanWord.equalsIgnoreCase(stringToFind)) {
                    searchStringCount += 1;
                }
            } else {
                if (cleanWord.equals(stringToFind)) {
                    searchStringCount += 1;
                }
            }
        }
        return searchStringCount;
    }

    private static void displayResults(List<Integer> results) {
        System.out.println("Total number of lines is " + results.get(0));
        System.out.println("Total number of words is " + results.get(1));
        System.out.println("Average words per line is " + results.get(2));
        int sourceSearchStringCount = results.get(4);
        if (sourceSearchStringCount > 0){
            System.out.println("Total number of lines with search string is " + results.get(3));
            System.out.println("Total number of instances of search string is " + sourceSearchStringCount);
        } else if (sourceSearchStringCount == 0) {
            System.out.println("Search string not found.");
        } else {
            System.out.println("No search string specified.");
        }
    }
}
