package corso.java.webmodel;

import java.util.ArrayList;
import java.util.List;

public abstract class ViewModel {
	private List<String> errors;

	public ViewModel() {
		errors = new ArrayList<>();
	}

	public String[] getErrors() {
		return errors.toArray(new String[errors.size()]);
	}

	public void clearErrors() {
		errors.clear();
	}

	public void addError(String error) {
		errors.add(error);
	}

	public boolean isValid() {
		return errors.isEmpty();
	}
}
