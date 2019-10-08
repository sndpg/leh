package logic;

public enum GeneratorOptionCountRequirement {
	EXACTLY, // requires given Character-type to be present exactly GeneratorOption.count-times in the result set
	MIN, // requires given Character-type to be present at least GeneratorOption.count-times in the result set
	MAX // requires given Character-type to be present at most GeneratorOption.count-times in the result set
}
