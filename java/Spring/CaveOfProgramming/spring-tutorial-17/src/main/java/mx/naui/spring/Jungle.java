package mx.naui.spring;

import java.util.HashMap;
import java.util.Map;

public class Jungle {
	private Map<String, String> foods = new HashMap<String, String>();

	public void setFoods(Map<String, String> foods) {
		this.foods = foods;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Map.Entry<String, String> entry : foods.entrySet()) {
			sb.append(entry.getKey());
			sb.append(": ");
			sb.append(entry.getValue());
			sb.append("\n");
		}

		return sb.toString();
	}
}
