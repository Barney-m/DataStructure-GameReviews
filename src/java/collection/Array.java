package java.collection;

public class Array {

	private Array() {}
	
	public static Object newInstance(Class<?> componentType, int length) throws NegativeArraySizeException {
		return newArray(componentType, length);
	}

	public static Object newInstance(Class<?> componentType, int... dimensions) throws IllegalArgumentException, NegativeArraySizeException{
		return multiNewArray(componentType, dimensions);
	}
	
	private static native Object newArray(Class componentType, int length) throws NegativeArraySizeException;
	
	private static native Object multiNewArray(Class componentType,int[] dimensions) throws IllegalArgumentException, NegativeArraySizeException;
}