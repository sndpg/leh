package logic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CharacterSequenceGenerator extends RandomSequenceGenerator<Character> {
	protected int length, forcedLowercase, forcedUppercase, forcedNumeric, forcedSpecial;
	protected long positionalReqsCount;
	protected List<GeneratorOption> posOptions;
	
	@Override
	public List<Character> generate() {
		// BigInteger(130, random).toString(32); <<--- quick random sequence
		buildAlphabetBase();
		evalOptions();
		
		GeneratorOption go;
		LinkedList<Character> li = new LinkedList<Character>();
		for(int i = 0; i < length; i++){
		//	sequence.add(i);
		/* TODO: create classes which hold the information needed to retrieve a character per OptionType in variables, so that its possible to loop over all
		 * OptionTypes of Category ENFORCING and call a method which requires the alphabet-set as a parameter and returns a random character from this set.
		 * 
		 * e.g. something like this
		 * 
		 * for(EnforcingOptionType eot: options){
		 *  while(eot.getCount > 0){
		 * 		li.add((int) Math.random ..., getRandomCharacter(eot.getAlphabet);
		 * 		eot.decreaseCounter;
		 * 	}
		 * }
		 */
			// apply positional requirements (specific character type at position n)
			if((long) (length - i) == positionalReqsCount){
				/* apply all requirements starting with the requirement which holds the lowest positional value first, so that the insertion positions won't be altered
				 * by following posReq-applications.
				 * The stream posStream has been sorted in the method evalOptions(), so we can just iterate through the stream here.
				 */
				go = posOptions.remove(0);
				li.add((int)go.getNumericValue(NumericGeneratorOptionVariableType.POSITION), getNextCharacter(go.getAlphabet()));
				positionalReqsCount--;
			}
			else{
				
				try {
					go = options.stream().filter(o -> o.getOptionTypeCategory() == GeneratorOptionTypeCategory.ENFORCING && o.getCount() > 0).findFirst().get();
				} catch (Exception e) {
					go = null;
				}
				
				if(go != null){
					li.add((int) (Math.random() * 100000) % (i + 1), getNextCharacter(go.getAlphabet()));
					go.decrementCount();
				}
/*
				if(forcedLowercase-- > 0){
					li.add((int) (Math.random() * 100000) % (i + 1), Constants.ALPHANUMERIC.charAt((int) (Math.random() * 100000) % Constants.ALPHANUMERIC.length()));
				}
				else if(forcedUppercase-- > 0){
					li.add((int) (Math.random() * 100000) % (i + 1), Constants.ALPHANUMERIC.toUpperCase().charAt((int) (Math.random() * 100000) % Constants.ALPHANUMERIC.length()));
				}
				else if(forcedNumeric-- > 0){
					li.add((int) (Math.random() * 100000) % (i + 1), Constants.NUMERIC.charAt((int) (Math.random() * 100000) % Constants.NUMERIC.length()));
				}
				else if(forcedSpecial-- > 0){
					li.add((int) (Math.random() * 100000) % (i + 1), Constants.SPECIAL.charAt((int) (Math.random() * 100000) % Constants.SPECIAL.length()));
				}
*/
				else
					li.add((int) (Math.random() * 100000) % (i + 1), alphabet.get((int) (Math.random() * 100000) % alphabet.size()));
			}
		}
		return new ArrayList<Character>(li);
	}

	@Override
	public RandomSequence<Character> generate(String... options) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Character getNextCharacter(String alphabet){
		return alphabet.charAt((int) (Math.random() * 100000) % alphabet.length());
	}
	
	private void buildAlphabetBase(){
		

		// multiple try ... catch-blocks, because e.g. even though if the first one catches an exception, the second one HAS TO BE run through
		try {
			
			options.stream().filter(o -> o.getOptionTypeCategory() == GeneratorOptionTypeCategory.RESTRICTING_ONLY && o.isActive()).findFirst()
				.get().getAlphabet().chars().mapToObj(c -> (char) c).collect(Collectors.toList());
			
		} catch (Exception e) {
				// TODO: handle exception
		}
		
		try {
			
			if(alphabet.size() == 0){
				alphabet.addAll(Constants.ALPHANUMERIC.toLowerCase().chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
				alphabet.addAll(Constants.ALPHANUMERIC.toUpperCase().chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			
			if(!checkOption(GeneratorOptionType.OMIT_NUMERIC)){
				alphabet.addAll(Constants.NUMERIC.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			if(!checkOption(GeneratorOptionType.OMIT_SPECIAL)){
				alphabet.addAll(Constants.SPECIAL.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			// remove all characters from alphabet which are defined in an any of the OMIT_ANY-options. These characters are prohibited to be used in the result-String.
			List<GeneratorOption> forbiddenChars = options.stream().filter(o -> o.getType() == GeneratorOptionType.OMIT).collect(Collectors.toList());
			forbiddenChars.forEach(go -> alphabet.removeIf(c -> go.getAlphabet().contains(String.valueOf(c))));
		} catch (Exception e) {
			// TODO: handle exception
		}
			
			
		/* old impl.
		try {		
			//.forEach(o -> o.getType());
			// ALPHANUMERIC chars are allowed per default due to GeneratorType
			if(checkOption(GeneratorOptionType.LOWERCASE_ONLY)){
				alphabet.addAll(((String) GeneratorOptionType.LOWERCASE_ONLY.getMappingValue(GeneratorOptionTypeAttribute.ALPHABET)).chars()
						.mapToObj(c -> (char) c).collect(Collectors.toList()));
				/* less verbose variant, but with explicit declaration of the desired alphabet-base
				alphabet.addAll(Constants.ALPHANUMERIC.toLowerCase().chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
				//*//*
			}
			else if(checkOption(GeneratorOptionType.UPPERCASE_ONLY)){
				alphabet.addAll(Constants.ALPHANUMERIC.toUpperCase().chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
			}
			else{
				alphabet.addAll(Constants.ALPHANUMERIC.toLowerCase().chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
				alphabet.addAll(Constants.ALPHANUMERIC.toUpperCase().chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
			}
			
			if(!checkOption(GeneratorOptionType.OMIT_NUMERIC)){
				alphabet.addAll(Constants.NUMERIC.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
			}
			
			if(!checkOption(GeneratorOptionType.OMIT_SPECIAL)){
				alphabet.addAll(Constants.SPECIAL.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		//*/
	}
	
	private void evalOptions(){
		length = Integer.parseInt(getOptionValue(GeneratorOptionType.LENGTH));
/*
		forcedLowercase = checkOption(GeneratorOptionType.FORCE_LOWERCASE) ? (int) getNumericOptionValue(GeneratorOptionType.FORCE_LOWERCASE) : 0;
		forcedUppercase = checkOption(GeneratorOptionType.FORCE_UPPERCASE) ? (int) getNumericOptionValue(GeneratorOptionType.FORCE_UPPERCASE) : 0;
		forcedNumeric = checkOption(GeneratorOptionType.FORCE_NUMERIC) ? (int) getNumericOptionValue(GeneratorOptionType.FORCE_NUMERIC) : 0;
		forcedSpecial = checkOption(GeneratorOptionType.FORCE_SPECIAL) ? (int) getNumericOptionValue(GeneratorOptionType.FORCE_SPECIAL) : 0;
*/
/*
		forcedLowercase = checkOption(GeneratorOptionType.FORCE_LOWERCASE) ? Integer.parseInt(getOptionValue(GeneratorOptionType.FORCE_LOWERCASE)) : 0;
		forcedUppercase = checkOption(GeneratorOptionType.FORCE_UPPERCASE) ? Integer.parseInt(getOptionValue(GeneratorOptionType.FORCE_UPPERCASE)) : 0;
		forcedNumeric = checkOption(GeneratorOptionType.FORCE_NUMERIC) ? Integer.parseInt(getOptionValue(GeneratorOptionType.FORCE_NUMERIC)) : 0;
		forcedSpecial = checkOption(GeneratorOptionType.FORCE_SPECIAL) ? Integer.parseInt(getOptionValue(GeneratorOptionType.FORCE_SPECIAL)) : 0;
*/
		int forcedMinLength = options.stream()
				.filter(o -> o.getOptionTypeCategory() == GeneratorOptionTypeCategory.ENFORCING && o.isActive())
				.mapToInt(o -> o.getCount())
				.sum();
		
		if (forcedMinLength > length){
			// no sequence which fulfills the given conditions can be created
			// do something Exception
		}

		posOptions = options.stream().filter(o -> o.getOptionTypeCategory() == GeneratorOptionTypeCategory.POSITIONAL && o.isActive())
				.sorted((o1, o2) -> Integer.compare((int) o1.getNumericValue(NumericGeneratorOptionVariableType.POSITION), 
						(int) o2.getNumericValue(NumericGeneratorOptionVariableType.POSITION)))
				.collect(Collectors.toCollection(ArrayList::new));
		
		if(posOptions != null){
			positionalReqsCount = posOptions.size();
			
			if(positionalReqsCount > 1L){
				// check for positional collisions (two or more options specify a character on the same position of the result string
				long distinctPosOptionsCount = posOptions.stream().filter(distinctByKey(o -> o.getNumericValue((NumericGeneratorOptionVariableType.POSITION)))).count();
				
				if (distinctPosOptionsCount != (long) positionalReqsCount){
					//duplicate Positions found, do something !!!
				}
					
			}
		}

	}
	
	public static <T> Predicate<T> distinctByKey(Function<? super T,Object> keyExtractor) {
	    Map<Object,Boolean> seen = new ConcurrentHashMap<>();
	    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
}
