package booleanoofunc;

import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;

public class Conjunction extends BinaryExpression {

  public Conjunction(BooleanExpression left, BooleanExpression right) {
    super((a, b) -> a && b, left, right, Conjunction::simplifyConjunction);
  }

  private static BooleanExpression simplifyConjunction(List<BooleanExpression> expressions, Map<String, Boolean> context) {
    BooleanExpression left = expressions.get(0).simplifyOnce(context);
    BooleanExpression right = expressions.get(1).simplifyOnce(context);

    // If either side simplifies to a BooleanValue, use it to potentially simplify the conjunction
    if (left instanceof BooleanValue) {
      boolean leftVal = left.evaluate(context);
      // If left is false, the whole conjunction is false
      if (!leftVal) return new BooleanValue(false);
      // If left is true, the whole conjunction simplifies to the right side
      return right;
    }

    if (right instanceof BooleanValue) {
      boolean rightVal = right.evaluate(context);
      // If right is false, the whole conjunction is false
      if (!rightVal) return new BooleanValue(false);
      // If right is true, the whole conjunction simplifies to the left side
      return left;
    }

    // If no further simplification is possible, return a new Conjunction with the simplified operands
    return new Conjunction(left, right);
  }

  @Override
  public String toStringOp() {
    return Constants.AND;
  }
}
