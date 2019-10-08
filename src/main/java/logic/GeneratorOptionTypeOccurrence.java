package logic;

/**
 * Specifies if a GeneratorOptionType is allowed to have more than one occurrence in a RandomSequenceGenerator's options-list.
 * GeneratortOptionTypes which are declared as SINGLE-occurrence-attribute will be overriden upon calling the 	
 * {@link #logic.RandomSequenceGenerator.setOptions(GeneratorOption)}-method.
 * @author Sandro
 *
 */
public enum GeneratorOptionTypeOccurrence {
	SINGLE,
	MULTIPLE
}
