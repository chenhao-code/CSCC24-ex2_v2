package booleanoofunc;

import java.util.Map;

/**
 * A boolean-values variable.
 */
public class Variable extends BooleanExpression{

  private final String id;

  public Variable(String id) {
    this.id = id;
  }

  @Override
  public boolean evaluate(Map<String, Boolean> context) {
    Boolean value = context.get(id);
    if (value == null) {
      throw new UnassignedVariableException("Variable " + id + " is not defined in the context.");
    }
    return value;
  }


  @Override
  public BooleanExpression simplifyOnce(Map<String, Boolean> context) {
    // Check if the variable is present in the context
    if (context.containsKey(id)) {
      // If present, return a BooleanValue with its value from the context
      return new BooleanValue(context.get(id));
    } else {
      // If not present, return this variable unchanged
      return this;
    }
  }

  @Override
  public boolean equals(Object other) {

    return other != null && other.getClass().equals(Variable.class)
        && id.equals(((Variable) other).id);
  }

  @Override
  public String toString() {
    return id;
  }
}
