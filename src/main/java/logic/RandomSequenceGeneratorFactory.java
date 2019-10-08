package logic;

public class RandomSequenceGeneratorFactory {
	
	public RandomSequenceGenerator<?> getCharacterSequenceGenerator(){
		return new CharacterSequenceGenerator();
	}
}
