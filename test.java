package lexicalanalyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class test {
	 /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {        
        //Take code input from file
    	Scanner reader = new Scanner(new File("C:\\Users\\jazmi\\OneDrive\\Documents\\COMP 360- Programming Languages Doc\\Project 1\\input.txt"));
        //ArrayList for lexemes and HashTable for the Symbol Table
        ArrayList<String> lines = new ArrayList<>();
        Map<String, List<String>> symbolTable = new HashMap<String, List<String>>();
        
        //Reads every line and then splits everything into lexemes
        while (reader.hasNextLine()) {
            String str = reader.nextLine(); //Reads the lines
            if (!(str.length() == 0)) {     //If no blank lines detected, then continue
            	//String[] strSplit = str.trim().split("\\s+|\\s*,\\s*|\\;+|\\\"+|\\:+|\\[+|\\]+");
            	String[] strSplit = str.trim().split("\\s+|(?=[(){};])|(?<=[(){};])");
   
            	//Add the Container List to the ArrayList 
            	for (String lexemes: strSplit) {
            		lines.add(lexemes);
            	}
            }
        }
       
    
        System.out.print("The Lexemes: ");
        System.out.println(lines);
        System.out.println("TOKENS:");
        //Add values from ArrayList to HashMap Key keywords
        List<String> keywords = new ArrayList<String>(); 
        String [] keywordArray = lines.toArray(new String [0]);
        if (lines.contains("int") || lines.contains("while") || lines.contains("void")) {         
        	 for (int count = 0;  count < keywordArray.length; count++) {
             	if (keywordArray[count].equals("float")) {
                     // Add a descriptive label for left parenthesis
                     keywords.add("float-Keyword \n");
                 } else if (keywordArray[count].equals("void")) {
                     // Add a descriptive label for right parenthesis
                     keywords.add("void-Keyword \n");
                 } else if (keywordArray[count].equals("while")) {
                     // Add a descriptive label for left brace
                     keywords.add("while-Keyword \n");
                 } else if (keywordArray[count].equals("}")) {
                     // Add a descriptive label for right brace
                     keywords.add("}-Right Bracket \n");
                 }
             
        	 }
        }
       
        for (String keyword : keywords) {
            System.out.println(keyword);
        }
        //Add values from ArrayList to HashMap Key keywords
        List<String> operators = new ArrayList<String>();   
        String [] OperationArray = lines.toArray(new String [0]);
        if (lines.contains("=") || lines.contains("-") || lines.contains("+") || lines.contains("*") || lines.contains(">=") || lines.contains("<=")) {         
        	for (int count = 0;  count < keywordArray.length; count++) {
             	if (OperationArray[count].equals("=")) {
                     // Add a descriptive label for left parenthesis
                     operators.add("=- Assignment operator \n");
                 } else if (OperationArray[count].equals("+")) {
                     // Add a descriptive label for right parenthesis
                     operators.add("+-Add operator- \n");
                 } else if (OperationArray[count].equals("-")) {
                     // Add a descriptive label for left brace
                     operators.add("- Minus operator \n");
                 } else if (OperationArray[count].equals("*")) {
                     // Add a descriptive label for right brace
                     operators.add("*- Multiply operator \n");
                 }
                 else if (OperationArray[count].equals(">=")) {
                     // Add a descriptive label for right brace
                     operators.add(">=- Greater than or equal to operator \n");
                 }
                 else if (OperationArray[count].equals(">=")) {
                     // Add a descriptive label for right brace
                     operators.add("<=- Less than or equal to operator \n");
                 }
             	
             
        	 }           
        }
        
        for (String symbol: operators) {
        	System.out.println(symbol);
        }
        String [] linesArray = lines.toArray(new String [0]);
        
        //Add values from ArrayList to HashMap Key keywords
        //List<String> digits = new ArrayList<String>();     
        for (int count = 0;  count < linesArray.length; count++) {
            if (linesArray[count].matches("\\d+|\\d+\\.\\d+")) {  //Use regex here for numbers                 
                // digits.add(linesArray[count]);     
            	System.out.println(linesArray[count]+ "- Integer literal \n");
            }        
        }
        
        //Put the ArrayLists in HashMap for particular Keys
        //Add values from ArrayList to HashMap Key keywords
        //List<String> identifiers = new ArrayList<String>(); 
        String [] identifierArray = lines.toArray(new String [0]);
        
        for (int count = 0;  count < linesArray.length; count++) {
            if (identifierArray[count].matches("\\w+") && !identifierArray[count].matches("\\d+") && !linesArray[count].matches("int|float|if|else")) {  //Use regex here for variables  
                System.out.println(identifierArray[count] + "- Identifier \n");
            }        
        } 
        //Put the ArrayLists in HashMap for particular Keys
        //symbolTable.put("Identifiers", identifiers);
        //Add values from ArrayList to HashMap Key keywords
        List<String> others = new ArrayList<String>();     
        for (int count = 0;  count < linesArray.length; count++) {
        	if (linesArray[count].equals("(")) {
                // Add a descriptive label for left parenthesis
                others.add("(- Left Parenthesis \n");
            } else if (linesArray[count].equals(")")) {
                // Add a descriptive label for right parenthesis
                others.add(")-Right Parenthesis \n");
            } else if (linesArray[count].equals("{")) {
                // Add a descriptive label for left brace
                others.add("{-Left Bracket \n");
            } else if (linesArray[count].equals("}")) {
                // Add a descriptive label for right brace
                others.add("}-Right Bracket \n");
            }
        } 
        //Put the ArrayLists in HashMap for particular Keys
        for (String symbol: others) {
        	System.out.println(symbol);
        }
        System.out.println();
        
    String inputFilePath = "C:\\Users\\jazmi\\OneDrive\\Documents\\COMP 360- Programming Languages Doc\\Project 1\\input.txt";

    // Parse the input file
    boolean isValid = parseInput(inputFilePath);

    // Print the result
    if (isValid) {
        System.out.println("The test program is generated by the EBNF grammar for Simple Function");
    } else {
        System.out.println("The test program cannot be generated by the EBNF Grammar for Simple Function.");
        System.out.println("The first syntax error is");
    }
 }
    //END OF MAIN
    // PARSER
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
    // Check if the next token matches the start symbol "<program>"
    if (scanner.hasNext("void")) {
        System.out.println("Found <keyword>");
        scanner.next(); // Consume the keyword token
        return parseIdent(scanner) && scanner.next().equals("(") && scanner.next().equals(")") &&
                scanner.next().equals("{") && parseDeclares(scanner) && parseLoop(scanner) &&
                scanner.next().equals(")") && parseAssignment(scanner) && scanner.next().equals("}");
    } else {
        return false;
    }
}

    private static boolean parseDeclares(Scanner scanner) {
    // Check if the next token matches the non-terminal "<declares>"
    if (scanner.hasNext("int")) {
        System.out.println("Found <keyword>");
        scanner.next(); // Consume the keyword token
        return parseIdent(scanner) && scanner.next().equals("=") && parseConst(scanner) &&
                scanner.next().equals(";");
    } else {
        System.out.println("Expected <keyword>");
        return false;
    }
}

    private static boolean parseLoop(Scanner scanner) {
    // Check if the next token matches the non-terminal "<loop>"
    if (scanner.hasNext("while")) {
        System.out.println("Found <keyword>");
        scanner.next(); // Consume the "while" keyword token
        
        // Check if the next token is "("
        String nextToken = scanner.next();
        if (nextToken.equals("(")) {
            // Consume the "(" token
            // Check if the next tokens are a valid identifier followed by ">=" tokens
            if (parseIdent(scanner)) {
                String lessthan = scanner.next();
                //System.out.print(lessthan);
                if (lessthan.equals(">=") && parseConst(scanner)) {
                	System.out.println("Found <loop>");
                	return true;
                }
                else {
                	System.out.print("Expected <loop>");
                }
                }
                // {
                 //   System.out.println(nextToken2);
                    // Consume the ">=" token and continue parsing...
               //     return ;
                }
            }
    //System.out.print("false");
    return false;
    
} 

    private static boolean parseAssignment(Scanner scanner) {
    // Check if the next token matches the non-terminal "<assignment>"
    if (scanner.hasNext("[a-z]")) {
        System.out.println("Found <ident>");
        scanner.next(); // Consume the identifier token
        return scanner.next().equals("=") && parseIdent(scanner) &&
                scanner.next().equals("-") && parseConst(scanner) &&
                scanner.next().equals(";");
    } else {
        System.out.println("Expected <ident>");
        return false;
    }
}

    private static boolean parseIdent(Scanner scanner) {
    // Check if the next token matches the non-terminal "<ident>"
	if (scanner.hasNext("[a-zA-Z0-9]+")){
        System.out.println("Found <keyword>");
        scanner.next(); // Consume the identifier token
        return true;
    } else {
        System.out.println("Expected <keyword>");
        return false;
    }
}

    private static boolean parseConst(Scanner scanner) {
    // Check if the next token matches the non-terminal "<const>"
    if (scanner.hasNextInt()) {
        System.out.println("Found <const>");
        scanner.nextInt(); // Consume the integer token
        return true;
    } else {
        System.out.println("Expected <const>");
        return false;
    }
}
}





