package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class RandomSequenceGenerator<T> {
	protected List<GeneratorOption> options = new ArrayList<GeneratorOption>();
	protected List<T> sequence = new ArrayList<T>();
	protected List<Character> alphabet = new ArrayList<Character>();
		
	public void setOptions(GeneratorOption...options){
		this.options = Arrays.asList(options);
	}
	
	public void setOption(GeneratorOptionType type, boolean active, String value){
		options.removeIf(g -> g.getType() == type);
		options.add(new GeneratorOption(type, active, value));
	}
	
	public void setOptions(GeneratorOption go){
		options.removeIf(g -> g.getType() == go.getType() && go.getType().getMappingValue(GeneratorOptionTypeAttribute.OCCURRENCE) == GeneratorOptionTypeOccurrence.SINGLE);
		options.add(go);
	}
	
	public abstract RandomSequence<T> generate(String...options);
	public abstract List<T> generate();
	
	protected boolean checkOption(GeneratorOptionType type){
		try {
			return options.stream().filter(el -> el.getType() == type)
					.findFirst()
					.get()
					.isActive();
		} catch (Exception e) {
			// maybe do something
			return (boolean) type.getMappingValue(GeneratorOptionTypeAttribute.DEFAULT_ACTITVITY_STATE);
		}
	}
	
	protected String getOptionValue(GeneratorOptionType type){
		try {
			return options.stream().filter(el -> el.getType() == type)
					.findFirst()
					.get()
					.getValue();
		} catch (Exception e) {
			// maybe do something
			return (String) type.getMappingValue(GeneratorOptionTypeAttribute.DEFAULT_VALUE);
		}
	}
	
	protected double getNumericOptionValue(GeneratorOptionType type){
		double d = 0;
		try {
			d = Double.parseDouble((String) options.stream().filter(el -> el.getType() == type)
					.findFirst()
					.get()
					.getValue());
		} catch (Exception e) {
			// maybe do something
		}
		return d;
	}
	
	protected GeneratorOption getOption(GeneratorOptionType type){
		try {
			return options.stream().filter(o -> o.getType() == type).findFirst().get();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
}
