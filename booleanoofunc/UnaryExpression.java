package booleanoofunc;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

/**
 * A unary boolean expression.
 */
public abstract class UnaryExpression extends BooleanExpression {

  protected BooleanExpression operand;
  protected UnaryOperator<Boolean> operator;
  protected BiFunction<BooleanExpression, Map<String, Boolean>, BooleanExpression> simplifier;

  public UnaryExpression(UnaryOperator<Boolean> operator, BooleanExpression operand,
                         BiFunction<BooleanExpression, Map<String, Boolean>, BooleanExpression> simplifier) {
    this.operator = operator;
    this.operand = operand;
    this.simplifier = simplifier;
  }

  @Override
  public boolean evaluate(Map<String, Boolean> context) {
    return operator.apply(operand.evaluate(context));
  }

  @Override
  public BooleanExpression simplifyOnce(Map<String, Boolean> context) {
    return simplifier.apply(operand, context);
  }

  public abstract String toStringOp();

  @Override
  public boolean equals(Object other) {
    return other != null && other.getClass().equals(this.getClass())
        && ((UnaryExpression) other).operand.equals(operand);
  }

  @Override
  public String toString() {
    return String.format("(%s %s)", toStringOp(), operand.toString());
  }

  public final BooleanExpression getOperand() {
    return operand;
  }
}
