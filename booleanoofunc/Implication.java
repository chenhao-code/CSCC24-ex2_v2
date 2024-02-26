package booleanoofunc;

import java.util.List;
import java.util.Map;


/**
 * A binary implication of BooleanExpression's.
 */
public class Implication extends BinaryExpression{

  public Implication(BooleanExpression left, BooleanExpression right) {
    super(Implication::implies, left, right, Implication::simplifyImplication);
  }

  private static boolean implies(boolean a, boolean b) {
    // In logic, A -> B is equivalent to !A || B
    return !a || b;
  }

  private static BooleanExpression simplifyImplication(List<BooleanExpression> expressions, Map<String, Boolean> context) {
    BooleanExpression left = expressions.get(0).simplifyOnce(context);
    BooleanExpression right = expressions.get(1).simplifyOnce(context);

    // If the left side simplifies to BooleanValue(true), the implication simplifies to the right side
    if (left instanceof BooleanValue && left.evaluate(context)) {
      return right;
    }

    // If the left side simplifies to BooleanValue(false), the implication is always true
    if (left instanceof BooleanValue && !left.evaluate(context)) {
      return new BooleanValue(true);
    }

    // If the right side simplifies to BooleanValue(true), the implication is always true
    if (right instanceof BooleanValue && right.evaluate(context)) {
      return new BooleanValue(true);
    }

    // If both sides simplify to the same expression, the implication is always true
    if (left.equals(right)) {
      return new BooleanValue(true);
    }

    // In cases where simplification to a constant value is not possible, return the simplified expressions in a new Implication
    return new Implication(left, right);
  }

  @Override
  public String toStringOp() {
    return Constants.IMPLIES;
  }
}

