package logic;

import java.util.HashMap;
import java.util.Map;

import logic.builder.AbstractGeneratorOptionBuilder;

/**
 * 
 * @author Sandro
 *
 */
public class GeneratorOption {
	// TODO: static factory for GeneratorOptions distinguished by GeneratorOptionType ??? or factory-method pattern with a GeneratorOption-Interface  
	protected GeneratorOptionType type;
	protected GeneratorOptionCountRequirement countReq; // nyi
	protected boolean active = false;
	protected String value = "", alphabet = "";
	protected int count;
	protected Map<NumericGeneratorOptionVariableType, Number> numericValues = new HashMap<>(); // TODO: fully implement
	protected Map<AlphanumericGeneratorOptionVariableType, String> alphanumericValues = new HashMap<>(); // TODO: fully implement
	
	public GeneratorOption(){
		
	}
	
	public GeneratorOption(GeneratorOptionType type){
		this.type = type;
		alphabet = (String) type.getMappingValue(GeneratorOptionTypeAttribute.ALPHABET);
		
		String s = "";
		count = (s = (String) type.getMappingValue(GeneratorOptionTypeAttribute.DEFAULT_VALUE)).matches("^[+-]?\\d+$") ? Integer.parseInt(s) : 0;
		numericValues.put(NumericGeneratorOptionVariableType.COUNT, count);
		
		value = (String) type.getMappingValue(GeneratorOptionTypeAttribute.DEFAULT_VALUE);
		active = (boolean) type.getMappingValue(GeneratorOptionTypeAttribute.DEFAULT_ACTITVITY_STATE);
	}
	
	public GeneratorOption(GeneratorOptionType type, String value){
		this(type);
		this.value = value;
	}
	
	public GeneratorOption(GeneratorOptionType type, boolean active, String value){
		this(type);
		this.active = active;
		this.value = value;
	}
	
	public GeneratorOption(GeneratorOptionType type, boolean active, String value, int count){
		this(type);
		this.active = active;
		this.value = value;
		this.count = count;
	}
	
	public GeneratorOption(GeneratorOptionType type, boolean active){
		this(type);
		this.active = active;
	}
	
	public void setType(GeneratorOptionType type){
		this.type = type;
	}
	
	public boolean isActive(){
		return active;
	}
	
	public GeneratorOptionType getType(){
		return type;
	}
	
	public String getValue(){
		return value;
	}
	
	public void setActive(boolean active){
		this.active = active;
	}
	
	public void setValue(String value){
		this.value = value;
	}
	
	public int getCount(){
		return count;
	}
	
	public void setCount(int count){
		this.count = count;
	}
	
	public void decrementCount(){
		count--;
	}
	
	public void increaseCount(){
		count++;
	}
	
	public GeneratorOptionTypeCategory getOptionTypeCategory(){
		return (GeneratorOptionTypeCategory) type.getMappingValue(GeneratorOptionTypeAttribute.CATEGORY);
	}
	
	public GeneratorOption setNumericValue(NumericGeneratorOptionVariableType key, Number value){
		numericValues.put(key, value);
		return this;
	}
	
	public GeneratorOption setAlphabet(String alphabet){
		this.alphabet = alphabet;
		return this;
	}
	
	public String getAlphabet(){
		return alphabet;
	}
	
	public Number getNumericValue(NumericGeneratorOptionVariableType attributeKey){
		return numericValues.get(attributeKey);
	}
}
