package logic.builder;

import logic.GeneratorOption;

public abstract class AbstractGeneratorOptionBuilder {
	protected GeneratorOption go = new GeneratorOption();
	
	public abstract GeneratorOption build();
	protected abstract AbstractGeneratorOptionBuilder setType();
	
}
