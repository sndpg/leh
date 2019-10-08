package logic;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum GeneratorType {
	NUMERIC_ONLY("numeric", "numericOnly"),
	ALPHANUMERIC("alphanumeric", "alphanumeric"),
	CHARACTERS_ONLY("characters", "charactersOnly"),
	UNRESTRICTED("unrestricted", "alphanumericNumericSpecial"),
	;
	
	private final String key, mappingValue;
	private static final Map<String, String> MAPPING = Collections.unmodifiableMap(initializeMapping());
	
	private GeneratorType(String key, String mappingValue){
		this.key = key;
		this.mappingValue = mappingValue;
	}
	
	public String getKey(){
		return key;
	}
	
	public String getMappingValue(){
		return mappingValue;
	}
	
	public static String getMappingValue(String key){
		return MAPPING.get(key);
	}
	
	private static Map<String, String> initializeMapping(){
		Map<String, String> mMap = new HashMap<String, String>();
		
		for (GeneratorType gt: GeneratorType.values()){
			mMap.put(gt.key, gt.mappingValue);
		}
		
		return mMap;
	}


}
