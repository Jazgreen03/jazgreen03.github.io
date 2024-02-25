package lexicalanalyzer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {

    public static void main(String[] args) {
        // Specify the file path for the input file
        String inputFilePath = "C:\\Users\\jazmi\\OneDrive\\Documents\\COMP 360- Programming Languages Doc\\Project 1\\input.txt";

        // Parse the input file
        boolean isValid = parseInput(inputFilePath);

        // Print the result
        if (isValid) {
            System.out.println("Input file is valid according to the grammar.");
        } else {
            System.out.println("Input file contains syntax errors according to the grammar.");
        }
    }

    private static boolean parseInput(String filePath) {
        try {
            Scanner scanner = new Scanner(new File(filePath));
            scanner.useDelimiter("\\s+|(?=[(){};])|(?<=[(){};])");
            // Parse the program according to the grammar
            if (parseProgram(scanner)) {
                // If the entire program is parsed successfully, return true
                return true;
            } else {
                // If there are syntax errors in the program, return false
                return false;
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            return false;
        }
    }

    private static boolean parseProgram(Scanner scanner) {
    	if (scanner.hasNext("void")) {
    	    System.out.println("Found <keyword>");
    	    scanner.next(); // Consume the keyword token
    	    
    	    boolean identParsed = parseIdent(scanner);
    	    boolean openParenParsed = scanner.next().equals("(");
    	    boolean closeParenParsed = scanner.next().equals(")");
    	    boolean openBraceParsed = scanner.next().equals("{");
    	    boolean declaresParsed = parseDeclares(scanner);
    	    boolean loopParsed = parseLoop(scanner);
    	    boolean Bracket = scanner.next().equals(")");
    	    boolean assignment = parseAssignment(scanner);
    	    boolean closeBraceParsed = scanner.next().equals("}");
    	    
    	    if (!identParsed) {
    	        System.out.println("Failed to parse identifier.");
    	        return false;
    	    } else if (!openParenParsed) {
    	        System.out.println("Failed to parse '('.");
    	        return false;
    	    } else if (!closeParenParsed) {
    	        System.out.println("Failed to parse ')'.");
    	        return false;
    	    } else if (!openBraceParsed) {
    	        System.out.println("Failed to parse '{'.");
    	        return false;
    	    } else if (!declaresParsed) {
    	        System.out.println("Failed to parse declarations.");
    	        return false;
    	    } else if (!loopParsed) {
    	        System.out.println("Failed to parse loop.");
    	        return false;
    	    } else if (!closeBraceParsed) {
    	        System.out.println("Failed to parse '}'.");
    	        return false;
    	    } 
    	    else if (!Bracket){
    	    	System.out.println("Failed to parse ')'");
    	    }
    	    else if (!assignment){
    	    	System.out.println("Failed to parse assignment");
    	    }
    	    else {
    	        return true;
    	    }
    	} else {
    	    return false;
    	}
    	return false;
    }
    private static boolean parseDeclares(Scanner scanner) {
        if (scanner.hasNext("int")) {
            System.out.println("Found keyword 'int'");
            scanner.next(); // Consume the keyword token
            return parseIdent(scanner) && scanner.next().equals("=") && parseConst(scanner) &&
                    scanner.next().equals(";");
        } else {
            System.out.println("Expected keyword 'int'");
            return false;
        }
    }

    private static boolean parseLoop(Scanner scanner) {
        // Check if the next token matches the non-terminal "<loop>"
        if (scanner.hasNext("while")) {
            System.out.println("Found keyword 'while'");
            scanner.next(); // Consume the "while" keyword token
            
            // Check if the next token is "("
            String nextToken = scanner.next();
            if (nextToken.equals("(")) {
                // Consume the "(" token
                // Check if the next tokens are a valid identifier followed by ">=" tokens
                if (parseIdent(scanner)) {
                    String lessthan = scanner.next();
                    System.out.print(lessthan);
                    if (lessthan.equals(">=") && parseConst(scanner)) {
                    	System.out.print("ok");
                    	return true;
                    }
                    }
                    // {
                     //   System.out.println(nextToken2);
                        // Consume the ">=" token and continue parsing...
                   //     return ;
                    }
                }
        System.out.print("false");
        return false;
        
    } 
    

    private static boolean parseAssignment(Scanner scanner) {
        // Check if the next token matches the non-terminal "<assignment>"
        if (scanner.hasNext("[a-z]")) {
            System.out.println("Found identifier7");
            scanner.next(); // Consume the identifier token
            return scanner.next().equals("=") && parseIdent(scanner) &&
                    scanner.next().equals("-") && parseConst(scanner) &&
                    scanner.next().equals(";");
        } else {
            System.out.println("Expected identifier1");
            return false;
        }
    }

    private static boolean parseIdent(Scanner scanner) {
        // Check if the next token matches the non-terminal "<ident>"
    	if (scanner.hasNext("[a-zA-Z0-9]+")){
            System.out.println("Found identifier2");
            scanner.next(); // Consume the identifier token
            return true;
        } else {
            System.out.println("Expected identifier");
            return false;
        }
    }

    private static boolean parseConst(Scanner scanner) {
        // Check if the next token matches the non-terminal "<const>"
        if (scanner.hasNextInt()) {
            System.out.println("Found integer constant3");
            scanner.nextInt(); // Consume the integer token
            return true;
        } else {
            System.out.println("Expected integer constant");
            return false;
        }
    }
}

