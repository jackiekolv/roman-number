

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import kingcaesar.math.RomanNumber;

public class App {

	/**
	 * Read file input.txt and print out the result of addition sequential.
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String line;
        
        readline:
        while ( (line = br.readLine()) != null) {
        	if(line.startsWith("//")) continue readline;
        	String [] strArr = line.split("\\+");
        	RomanNumber [] rNumberArr = new RomanNumber[strArr.length];
        	for (int i = 0; i < strArr.length; i++) {
        		try {
            		rNumberArr[i] = new RomanNumber(strArr[i]);
				} catch (ArithmeticException e) {
					System.out.println(e.getMessage());
					continue readline;
				}
			}
    		RomanNumber result = rNumberArr[0];
        	for (int i = 1; i < rNumberArr.length; i++) {
        		result = result.add(rNumberArr[i]);
			}
        	System.out.println(result);
        }
	}

}
