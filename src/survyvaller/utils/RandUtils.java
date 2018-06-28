package survyvaller.utils;

import java.util.List;
import java.util.Random;

public class RandUtils {
	
	public static Random RAND = new Random();
	
	/**
	 * Get random thing from list.
	 * @param list
	 * @return
	 */
	public static <T> T fromList(List<T> list) {
		if (! list.isEmpty()) {
			return list.get(RAND.nextInt(list.size()));
		} else {
			return null;
		}
	}
	
}
