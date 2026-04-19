package epsilongtmyon.rest.app.common.util;

public class ObjectUtil {

	public static <T> T defaultValue(T value, T ifNull) {
		return value != null ? value : ifNull;
	}
}
