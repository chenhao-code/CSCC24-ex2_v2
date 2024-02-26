package booleanoofunc;

import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;

public class Disjunction extends BinaryExpression {

  public Disjunction(BooleanExpression left, BooleanExpression right) {
    super((a, b) -> a || b, left, right, Disjunction::simplifyDisjunction);
  }

  private static BooleanExpression simplifyDisjunction(List<BooleanExpression> expressions, Map<String, Boolean> context) {
    BooleanExpression left = expressions.get(0).simplifyOnce(context);
    BooleanExpression right = expressions.get(1).simplifyOnce(context);

    // If either side simplifies to a BooleanValue, use it to potentially simplify the disjunction
    if (left instanceof BooleanValue) {
      boolean leftVal = left.evaluate(context);
      // If left is true, the whole disjunction is true
      if (leftVal) return new BooleanValue(true);
      // If left is false, the whole disjunction simplifies to the right side
      return right;
    }

    if (right instanceof BooleanValue) {
      boolean rightVal = right.evaluate(context);
      // If right is true, the whole disjunction is true
      if (rightVal) return new BooleanValue(true);
      // If right is false, the whole disjunction simplifies to the left side
      return left;
    }

    // If no further simplification is possible, return a new Disjunction with the simplified operands
    return new Disjunction(left, right);
  }

  @Override
  public String toStringOp() {
    return Constants.OR;
  }
}
