package kingcaesar.math;

import java.util.EnumMap;
import java.util.Set;

public class RomanNumber {
	
	private String romanNumber;
	public static enum SYMBOL {
		M, D, C, L, X, V, I
	}
	
	public RomanNumber(String strNumber) {
		// Roman number validation.
		if(!strNumber.matches("(^M*)(D?C{0,3}|CD|CM)(L?X{0,3}|XL|XC)(V?I{0,3}|IV|IX)$"))
			throw new ArithmeticException("Invalid or unsupported input.");
		this.romanNumber = strNumber;
	}
	
	@Override
	public String toString() {
		return romanNumber;
	}
	
	/**
	 * Return added Roman number.
	 * @param  rNumber	Roman number that will be addition to this.romanNumber .<br>
	 * 					Complete the addition of two Roman number directly, not convert to Arabic number(0-9).<br>
	 * 					Step 1. Uncompact Roman number.<br>
	 * 					Step 2. Concatenate two uncompacted Roman number togeter.<br>
	 * 					Step 3. Grouping and sorting result from step2 by bigger symbol on the left.<br>
	 * 					Step 4. Combine same symbols to bigger symbols.<br>
	 * 					Step 5. Compact the result before return.<br>
	 * @return      	Result of addition.
	 */
	public RomanNumber add(RomanNumber rNumber) {
		String result = unCompact(this.romanNumber) + unCompact(rNumber.romanNumber);
		result = groupAndSortBySymbols(result);
		result = combineSmallerToBigger(result);
		result = compact(result);
		rNumber.romanNumber = result;
		return rNumber;
	}
	
	/**
	 * Return uncompact Roman number.
	 * @param  input  	String of compacted Roman number, 
	 * 					for example: XCIV
	 * @return      	Compacted Roman number, 
	 * 					for example: LXXXXIIII.
	 */
	public static String unCompact(String input) {
		String result = input;
		
		// Uncompact all of subtractive symbols.
		// Start form the biggest symbol.
		result = result.replace("IV", "IIII");
		result = result.replace("IX", "VIIII");
		result = result.replace("XL", "XXXX");
		result = result.replace("XC", "LXXXX");
		result = result.replace("CD", "CCCC");
		result = result.replace("CM", "DCCCC");
		return result;
	}
	
	/**
	 * Return compacted Roman number.
	 * @param  input  	String of Roman number that may not compact, 
	 * 					for example: DCCCCXXXXXLVVIIII
	 * @return      	Compacted Roman number, 
	 * 					for example: MXIV.
	 */
	public static String compact(String input) {
		String result = input;
		
		// Compact by subtractive.
		// Start form the biggest symbol.
		result = result.replace("DCCCC", "CM");
		result = result.replace("CCCC", "CD");
		result = result.replace("LXXXX", "XC");
		result = result.replace("XXXX", "XL");
		result = result.replace("VIIII", "IX");
		result = result.replace("IIII", "IV");
		return result;
	}
	
	public String combineSmallerToBigger(String input) {
		String result = input;
		
		// Combine smaller symbol to bigger symbol.
		// Start form the smallest symbol.
		result = result.replace("IIIII", "V");
		result = result.replace("VV", "X");
		result = result.replace("XXXXX", "L");
		result = result.replace("LL", "C");
		result = result.replace("CCCCC", "D");
		result = result.replace("DD", "M");
		return result;
	}
	
	/**
	 * Return grouped and sorted Roman number by symbols
	 *
	 * @param  input  	String of Roman number, 
	 * 					for example: MMCDXLIXMMDCLXXI (it's from MMDCLXXI+MMDCLXXI)
	 * @return      	Grouped and sorted Roman number by symbols, 
	 * 					for example: MMMMDDCCLLXXXXII.
	 */
	public String groupAndSortBySymbols(String input){
		
		String result = "";
		EnumMap<SYMBOL, String> symbolMap = new EnumMap<SYMBOL, String>(SYMBOL.class);
		
		// Loop input string to group the symbol.
		for (int i = 0; i < input.length(); i++) {
			String symbStr = input.charAt(i)+"";
			SYMBOL symbKey = SYMBOL.valueOf(symbStr);
			String valueSymb = symbolMap.get(symbKey);
			if(valueSymb == null)
				valueSymb = symbStr;
			else
				valueSymb += symbStr;
			symbolMap.put(symbKey, valueSymb);
		}
		
		// Combine all group of symbols to result order by natural sort from enum, 
		// Loop only symbols that contain in input string (Not all of SYMBOL).
		Set<SYMBOL> foundSymbol = symbolMap.keySet();
		for (SYMBOL symb : foundSymbol) {
			result += symbolMap.get(symb);
		}
		return result;
	}

	public String getRomanNumber() {
		return romanNumber;
	}

	public void setRomanNumber(String romanNumber) {
		this.romanNumber = romanNumber;
	}
	
}
