package logic;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum GeneratorOptionType {
	/* TODO-maybe:
	 * provide default-implementation for each OptionType, which can be overridden by specific/concrete Generators
	 * ~> Assign a Function-object (void return type) which manipulates the variables of the abstract RandomSequenceGenerator-class an will be called
	 * upon initialization of a concrete Generator
	 */
	LOWERCASE_ONLY("lowercase_only", "lowercaseOnly", GeneratorOptionTypeCategory.RESTRICTING_ONLY, "", 
			Constants.ALPHANUMERIC.toLowerCase(), GeneratorOptionTypeOccurrence.SINGLE, false),
	UPPERCASE_ONLY("uppercase_only", "uppercaseOnly", GeneratorOptionTypeCategory.RESTRICTING_ONLY, "", 
			Constants.ALPHANUMERIC.toUpperCase(), GeneratorOptionTypeOccurrence.SINGLE, false),
	FORCE_LOWERCASE("force_lowercase", "forceLowercase", GeneratorOptionTypeCategory.ENFORCING, String.valueOf(Constants.DEFAULT_FORCED_LOWERCASE_COUNT), 
			Constants.ALPHANUMERIC.toLowerCase(), GeneratorOptionTypeOccurrence.SINGLE, false),
	FORCE_UPPERCASE("force_uppercase", "forceUppercase", GeneratorOptionTypeCategory.ENFORCING, String.valueOf(Constants.DEFAULT_FORCED_UPPERCASE_COUNT), 
			Constants.ALPHANUMERIC.toUpperCase(), GeneratorOptionTypeOccurrence.SINGLE, false),
	FORCE_SPECIAL("force_special", "forceSpecialSigns", GeneratorOptionTypeCategory.ENFORCING, String.valueOf(Constants.DEFAULT_FORCED_SPECIAL_COUNT), 
			Constants.SPECIAL, GeneratorOptionTypeOccurrence.SINGLE, false),
	FORCE_NUMERIC("force_numeric", "forceNumeric", GeneratorOptionTypeCategory.ENFORCING, "",
			Constants.NUMERIC, GeneratorOptionTypeOccurrence.SINGLE, false),
	OMIT("omit", "omit", GeneratorOptionTypeCategory.OMITTING, "", "", GeneratorOptionTypeOccurrence.MULTIPLE, false),
	OMIT_SPECIAL("omit_special", "omitSpecial", GeneratorOptionTypeCategory.OMITTING, "",
			Constants.SPECIAL, GeneratorOptionTypeOccurrence.SINGLE, false), // providing an alphabet (Constants.SPECIAL) is unnecessary for this type of attribute, because the implementing class will/should always use Constants.SPECIAL anyways
	OMIT_NUMERIC("omit_numeric", "omitNumeric", GeneratorOptionTypeCategory.OMITTING, "",
			Constants.NUMERIC, GeneratorOptionTypeOccurrence.SINGLE, false),
	LENGTH("length", "numberOfSigns", GeneratorOptionTypeCategory.UNSPECIFIED, String.valueOf(Constants.DEFAULT_SEQUENCE_LENGTH),
			"", GeneratorOptionTypeOccurrence.SINGLE, false),
	FORBID_REPETITIONS("forbid_repetitions", "forbidRepetitions", GeneratorOptionTypeCategory.UNSPECIFIED, "",
			"", GeneratorOptionTypeOccurrence.SINGLE, false),
	TYPE_AT_POSITION("type_at_position", "typeAtPosition", GeneratorOptionTypeCategory.POSITIONAL, "",
			"", GeneratorOptionTypeOccurrence.MULTIPLE, false)
	;
	
	private final String key;
	private final Map<GeneratorOptionTypeAttribute, Object> mappingValue;
	private static final Map<String, Map<GeneratorOptionTypeAttribute, Object>> MAPPING = Collections.unmodifiableMap(initializeMapping());
	
	private GeneratorOptionType(String key, String canonicalName, GeneratorOptionTypeCategory category, String defaultValue, String alphabet, 
			GeneratorOptionTypeOccurrence occurrence,boolean defaultActivityState){
		this.key = key;
		HashMap<GeneratorOptionTypeAttribute, Object> hm = new HashMap<GeneratorOptionTypeAttribute, Object>();
		hm.put(GeneratorOptionTypeAttribute.CANONICAL_NAME, canonicalName);
		hm.put(GeneratorOptionTypeAttribute.DEFAULT_VALUE, defaultValue);
		hm.put(GeneratorOptionTypeAttribute.DEFAULT_ACTITVITY_STATE, defaultActivityState);
		hm.put(GeneratorOptionTypeAttribute.CATEGORY, category);
		hm.put(GeneratorOptionTypeAttribute.ALPHABET, alphabet);
		hm.put(GeneratorOptionTypeAttribute.OCCURRENCE, occurrence);
		mappingValue = Collections.unmodifiableMap(hm);
	}
	
	public String getKey(){
		return key;
	}
	
	/**
	 * Get the Map itself inside of MAPPING
	 * @param key
	 * @return
	 */
	public Map<GeneratorOptionTypeAttribute, Object> getMappingValue(String key){
		return MAPPING.get(key);
	}
	
	/**
	 * Get value of Map inside MAPPING
	 * @param key
	 * @return
	 */
	public Object getMappingValue(GeneratorOptionTypeAttribute key){
		return mappingValue.get(key);
	}
	
	/**
	 * Get value of Map inside MAPPING
	 * @param key
	 * @return
	 */
	public Object getMappingValue(GeneratorOptionType type, GeneratorOptionTypeAttribute gota){
		return MAPPING.get(type).get(gota);
	}
	
	public GeneratorOptionTypeCategory getCategory(){
		return (GeneratorOptionTypeCategory) mappingValue.get(GeneratorOptionTypeAttribute.CATEGORY);
	}
	
	public String getAlphabet(){
		return (String) mappingValue.get(GeneratorOptionTypeAttribute.ALPHABET);
	}
	
	
	private static Map<String, Map<GeneratorOptionTypeAttribute, Object>> initializeMapping(){
		Map<String, Map<GeneratorOptionTypeAttribute, Object>> mMap = new HashMap<String, Map<GeneratorOptionTypeAttribute, Object>>();
		
		for (GeneratorOptionType go: GeneratorOptionType.values()){
			mMap.put(go.key, go.mappingValue);
		}
		
		return mMap;
	}


}
