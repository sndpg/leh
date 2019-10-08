package test;

import static org.junit.Assert.*;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import logic.CharacterSequenceGenerator;
import logic.Constants;
import logic.GeneratorOption;
import logic.GeneratorOptionType;
import logic.NumericGeneratorOptionVariableType;
import logic.RandomSequenceGenerator;

public class LehApplicationTests {

	@Ignore
	@Test
	public void test() {
		RandomSequenceGenerator<Character> rsg = new CharacterSequenceGenerator();
		int length = 20;
		rsg.setOption(GeneratorOptionType.LENGTH, true, String.valueOf(length));
		List<Character> seq = rsg.generate();
		seq.forEach(System.out::println);
		assertTrue(seq.size() == length);
	}

	@Ignore
	@Test
	public void test2() {
		RandomSequenceGenerator<Character> rsg = new CharacterSequenceGenerator();
		int length = 20;
		rsg.setOptions(new GeneratorOption(GeneratorOptionType.LENGTH, true, String.valueOf(length), 0));
		rsg.setOptions(new GeneratorOption(GeneratorOptionType.FORCE_LOWERCASE, true, "",  4));
		rsg.setOptions(new GeneratorOption(GeneratorOptionType.FORCE_SPECIAL, true, "",  2));
		rsg.setOptions(new GeneratorOption(GeneratorOptionType.FORCE_UPPERCASE, true, "",  4));
		rsg.setOptions(new GeneratorOption(GeneratorOptionType.FORCE_NUMERIC, true, "",  4));
		List<Character> seq = rsg.generate();
		seq.forEach(System.out::println);
		assertTrue(seq.size() == length);
	}
	
	@Ignore
	@Test
	public void testPositional() {
		RandomSequenceGenerator<Character> rsg = new CharacterSequenceGenerator();
		int length = 20;
		rsg.setOptions(new GeneratorOption(GeneratorOptionType.LENGTH, true, String.valueOf(length), 0));
		rsg.setOptions(new GeneratorOption(GeneratorOptionType.FORCE_LOWERCASE, true, "",  4));
		rsg.setOptions(new GeneratorOption(GeneratorOptionType.FORCE_SPECIAL, true, "",  2));
		rsg.setOptions(new GeneratorOption(GeneratorOptionType.FORCE_UPPERCASE, true, "",  4));
		rsg.setOptions(new GeneratorOption(GeneratorOptionType.FORCE_NUMERIC, true, "",  4));
		rsg.setOptions(new GeneratorOption(GeneratorOptionType.TYPE_AT_POSITION, true, "", 0).setNumericValue(NumericGeneratorOptionVariableType.POSITION, 1).setAlphabet("@"));
		rsg.setOptions(new GeneratorOption(GeneratorOptionType.TYPE_AT_POSITION, true, "", 0).setNumericValue(NumericGeneratorOptionVariableType.POSITION, 0).setAlphabet("Ä"));
		List<Character> seq = rsg.generate();
		seq.forEach(System.out::println);
		assertTrue(seq.size() == length);
		assertTrue(seq.get(0).equals('Ä'));
		assertTrue(seq.get(1).equals('@'));
	}
	
	@Ignore
	@Test
	public void testOmitting() {
		RandomSequenceGenerator<Character> rsg = new CharacterSequenceGenerator();
		int length = 20;
		rsg.setOptions(new GeneratorOption(GeneratorOptionType.LENGTH, true, String.valueOf(length), 0));
		//rsg.setOptions(new GeneratorOption(GeneratorOptionType.FORCE_LOWERCASE, true, "",  4));
		rsg.setOptions(new GeneratorOption(GeneratorOptionType.FORCE_SPECIAL, true, "",  2));
		rsg.setOptions(new GeneratorOption(GeneratorOptionType.FORCE_UPPERCASE, true, "",  4));
		rsg.setOptions(new GeneratorOption(GeneratorOptionType.FORCE_NUMERIC, true, "",  4));
		rsg.setOptions(new GeneratorOption(GeneratorOptionType.OMIT, true).setAlphabet(Constants.ALPHANUMERIC.toLowerCase()));
		List<Character> seq = rsg.generate();
		seq.forEach(System.out::println);
		assertTrue(seq.size() == length);
		assertTrue(!seq.stream().anyMatch(c -> Constants.ALPHANUMERIC.toLowerCase().contains(String.valueOf(c))));
	}
	
	@Test
	public void testOmittingUpperCase() {
		RandomSequenceGenerator<Character> rsg = new CharacterSequenceGenerator();
		int length = 20;
		rsg.setOptions(new GeneratorOption(GeneratorOptionType.LENGTH, true, String.valueOf(length), 0));
		//rsg.setOptions(new GeneratorOption(GeneratorOptionType.FORCE_LOWERCASE, true, "",  4));
		rsg.setOptions(new GeneratorOption(GeneratorOptionType.FORCE_SPECIAL, true, "",  2));
		//rsg.setOptions(new GeneratorOption(GeneratorOptionType.FORCE_UPPERCASE, true, "",  4));
		rsg.setOptions(new GeneratorOption(GeneratorOptionType.FORCE_NUMERIC, true, "",  4));
		rsg.setOptions(new GeneratorOption(GeneratorOptionType.OMIT, true).setAlphabet(Constants.ALPHANUMERIC.toUpperCase()));
		List<Character> seq = rsg.generate();
		seq.forEach(System.out::println);
		assertTrue(seq.size() == length);
		assertTrue(!seq.stream().anyMatch(c -> Constants.ALPHANUMERIC.toUpperCase().contains(String.valueOf(c))));
	}
	
}
