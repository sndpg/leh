package logic;

public enum GeneratorOptionTypeAttribute {
	DEFAULT_VALUE(String.class),
	DEFAULT_ACTITVITY_STATE(boolean.class),
	CANONICAL_NAME(String.class),
	CATEGORY(GeneratorOptionTypeCategory.class),
	ALPHABET(String.class),
	OCCURRENCE(GeneratorOptionTypeOccurrence.class)
	;
	
	private Class<?> clazz;
	
	private GeneratorOptionTypeAttribute(Class<?> clazz){
		this.clazz = clazz;
	}
	
	public Class<?> getClassRepresentation(){
		return clazz;
	}
}
