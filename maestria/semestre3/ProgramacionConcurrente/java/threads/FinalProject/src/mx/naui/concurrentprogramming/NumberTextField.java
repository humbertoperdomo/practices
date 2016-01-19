package mx.naui.concurrentprogramming;

import javafx.scene.control.TextField;

/**
 *
 * @author humberto
 */
public class NumberTextField extends TextField {

  private final int limit;
  
  public NumberTextField(String text, int limit) {
    super(text);
    this.limit = limit;
  }
  
  public NumberTextField(int limit) {
    this.limit = limit;
  }

  @Override
  public void replaceText(int start, int end, String text) {
    if (validate(text)) {
      super.replaceText(start, end, text);
    }
  }

  @Override
  public void replaceSelection(String text) {
    if (validate(text)) {
      super.replaceSelection(text);
    }
  }

  private boolean validate(String text) {
    return text.matches("[0-9]*");
  }
}
