package booleanoofunc;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

/**
 * A binary if and only if of BooleanExpression's.
 */
public class IffExpression extends BinaryExpression {
  public IffExpression(BooleanExpression left, BooleanExpression right) {
    super((a, b) -> a == b, left, right, IffExpression::simplifyIff);
  }

  private static BooleanExpression simplifyIff(List<BooleanExpression> expressions, Map<String, Boolean> context) {
    BooleanExpression left = expressions.get(0).simplifyOnce(context);
    BooleanExpression right = expressions.get(1).simplifyOnce(context);

    // If both sides simplify to BooleanValue, directly evaluate the IFF logic
    if (left instanceof BooleanValue && right instanceof BooleanValue) {
      boolean leftVal = left.evaluate(context);
      boolean rightVal = right.evaluate(context);
      return new BooleanValue(leftVal == rightVal);
    }

    // If one side is the negation of the other, return false, as they cannot both be true or false at the same time
    if (left instanceof Negation && ((Negation) left).getOperand().equals(right) ||
            right instanceof Negation && ((Negation) right).getOperand().equals(left)) {
      return new BooleanValue(false);
    }

    // In cases where simplification to a constant value is not possible, return the simplified expressions in a new IffExpression
    return new IffExpression(left, right);
  }

  @Override
  public String toStringOp() {
    return Constants.IFF;
  }
}
