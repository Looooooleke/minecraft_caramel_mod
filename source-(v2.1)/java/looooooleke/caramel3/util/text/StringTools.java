package looooooleke.caramel3.util.text;

import java.util.ArrayList;
import java.util.List;

public class StringTools {
	public static String[] splits(String input, char trigger) {
		List<String> LIST = new ArrayList<String>();
		String copy = input;
		int locatie = copy.indexOf(trigger);
		while(locatie !=-1) {
			LIST.add(copy.substring(0,locatie));
			copy =copy.substring(locatie+1);
			locatie=copy.indexOf(trigger);
		}
		LIST.add(copy);
		return LIST.toArray(new String[0]);
	}
	
	public static String[] splits(String input, String trigger) {
		List<String> LIST = new ArrayList<String>();
		String copy = input;
		int locatie = copy.indexOf(trigger);
		while(locatie !=-1) {
			LIST.add(copy.substring(0,locatie));
			copy =copy.substring(locatie+1);
			locatie=copy.indexOf(trigger);
		}
		LIST.add(copy);
		return LIST.toArray(new String[0]);
	}
}
