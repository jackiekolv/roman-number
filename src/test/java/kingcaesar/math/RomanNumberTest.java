package kingcaesar.math;

import org.junit.Assert;
import org.junit.Test;

import kingcaesar.math.RomanNumber;

public class RomanNumberTest {

	@Test
	public void romanNumberAddition() throws Exception {
		Assert.assertEquals(
				new RomanNumber("XX").add(new RomanNumber("II")).toString(), "XXII");
		Assert.assertEquals(
				new RomanNumber("II").add(new RomanNumber("II")).toString(), "IV");
		Assert.assertEquals(
				new RomanNumber("XIV").add(new RomanNumber("LX")).toString(), "LXXIV");
		Assert.assertEquals(
				new RomanNumber("V").add(new RomanNumber("V")).toString(), "X");
		Assert.assertEquals(
				new RomanNumber("MCDXLIX").add(new RomanNumber("MCDXLIX")).toString(), "MMDCCCXCVIII");
	}
}
