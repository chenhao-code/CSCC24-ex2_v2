package booleanoofunc;

import java.util.Map;
/**
 * A unary negation.
 */
public class Negation extends UnaryExpression{

  /**
   * Creates a new "not operand".
   *
   * @param operand the operand of this Negation
   */
  public Negation(BooleanExpression operand) {
    super(x -> !x, operand, Negation::simplifyNot);
  }

  private static BooleanExpression simplifyNot(BooleanExpression expr,
      Map<String, Boolean> context) {
    BooleanExpression simplifiedOperand = expr.simplifyOnce(context);

    if (simplifiedOperand instanceof BooleanValue) {
      // Directly access the 'value' field of BooleanValue to determine the logical negation.
      Boolean value = ((BooleanValue) simplifiedOperand).getValue(); // Access the private field directly assuming this is an inner class or you have access to it.
      return new BooleanValue(!value);
    } else if (simplifiedOperand instanceof Negation) {
      // Simplify double negation: NOT(NOT(X)) => X.
      return ((Negation) simplifiedOperand).getOperand();
    }

    // If no simplification is possible, return a new negation of the simplified operand.
    return new Negation(simplifiedOperand);
  }

  @Override
  public String toStringOp() {
    return Constants.NOT;
  }
}
