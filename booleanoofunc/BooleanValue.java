package booleanoofunc;

import java.util.Map;
/**
 * A boolean value: true or false.
 */
public class BooleanValue extends BooleanExpression{

  private final Boolean value;

  public BooleanValue(Boolean val) {
    this.value = val;
  }

  // Evaluates this BooleanValue by simply returning its value.
  @Override
  public boolean evaluate(Map<String, Boolean> context) {
    return value;
  }

  public Boolean getValue() {
    return this.value;
  }

  // A BooleanValue cannot be simplified further, so return itself.
  @Override
  public BooleanExpression simplifyOnce(Map<String, Boolean> context) {
    return this;
  }
  @Override
  public String toString() {
    return value.toString();
  }

  @Override
  public boolean equals(Object other) {
    return other != null && other.getClass().equals(BooleanValue.class)
        && ((BooleanValue) other).value.equals(value);
  }
}
