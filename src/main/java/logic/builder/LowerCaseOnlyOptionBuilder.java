package logic.builder;

import logic.GeneratorOption;
import logic.GeneratorOptionType;

public class LowerCaseOnlyOptionBuilder extends AbstractGeneratorOptionBuilder {

	public GeneratorOption build() {
		setType();
		return go;
	}

	protected LowerCaseOnlyOptionBuilder setType() {
		go.setType(GeneratorOptionType.LOWERCASE_ONLY);
		return this;
	}
	
	protected LowerCaseOnlyOptionBuilder setAlphabet(){
		// default is set to Constants.ALPHANUMERIC.toLowerCase() through GeneratorOptionType-enum-properties;
		return this;
	}

}
