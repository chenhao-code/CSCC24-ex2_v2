package booleanoofunc;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * Working on lists of BooleanExpression's.
 */
public abstract class BooleanExpressions {

  /**
   * Evaluate each BooleanExpression under context and return a List of the results.
   *
   * TODO: -- Do NOT use any loops. Use Java Streams and the method map. --
   *
   * @param expressions a List of BooleanExpressions to evaluate
   * @param context     truth assignment for all variables in expressions
   * @return a list of the results of evaluating all expressions
   */
  public static List<Boolean> evaluateAll(List<BooleanExpression> expressions,
                                          Map<String, Boolean> context) {
      // Use stream to map each expression to its evaluation result.
      return expressions.stream()
              .map(expression -> expression.evaluate(context))
              .collect(Collectors.toList());
  }


    /**
   * Evaluate each BooleanExpression under context and return the conjunction of
   * the results.
   *
   * TODO: -- Do NOT use any loops. Use Java Streams and the method reduce ONLY. --
   *
   * @param expressions a List of BooleanExpressions to evaluate
   * @param context     truth assignment for all variables in expressions
   * @return conjunction of the results of evaluating all expressions
   */
    public static Boolean evaluateAndReduce(List<BooleanExpression> expressions,
                                            Map<String, Boolean> context) {
        // Use stream to reduce the evaluation results with a logical AND.
        return expressions.stream()
                .map(expression -> expression.evaluate(context))
                .reduce(true, (a, b) -> a && b); // The identity for AND operation is true.
    }

  /**
   * Evaluate each BooleanExpression under context and return the conjunction of
   * the results.
   *
   * TODO: -- Do NOT use any loops. Use Java Streams and the methods map and reduce. --
   *
   * @param expressions a List of BooleanExpressions to evaluate
   * @param context     truth assignment for all variables in expressions
   * @return conjunction of the results of evaluating all expressions
   */
  public static Boolean evaluateAndMapReduce(List<BooleanExpression> expressions,
                                             Map<String, Boolean> context) {
      // Same implementation as evaluateAndReduce since map is used before reduce.
      return evaluateAndReduce(expressions, context);
  }

  /**
   * Evaluate each BooleanExpression under context and return the disjunction of
   * the results.
   *
   * TODO: -- Do NOT use any loops. Use Java Streams and the methods map and reduce. --
   *
   * @param expressions a List of BooleanExpressions to evaluate
   * @param context     truth assignment for all variables in expressions
   * @return disjunction of the results of evaluating all expressions
   */
  public static Boolean evaluateOrMapReduce(List<BooleanExpression> expressions,
                                            Map<String, Boolean> context) {
      // Use stream to map each expression to its evaluation result, then reduce with a logical OR.
      return expressions.stream()
              .map(expression -> expression.evaluate(context))
              .reduce(false, (a, b) -> a || b); // The identity for OR operation is false.
  }


    /**
   * Evaluate each BooleanExpression under context and return the reduction of
   * the results using the reduction function func and the identity element identity.
   *
   * TODO: -- Do NOT use any loops. Use Java Streams and the methods map and reduce. --
   *
   * @param expressions a List of BooleanExpressions to evaluate
   * @param context     truth assignment for all variables in expressions
   * @return reduction using func and identity of the results of evaluating all expressions
   */
  public static Boolean evaluateMapReduce(BiFunction<Boolean, Boolean, Boolean> func,
      Boolean identity, List<BooleanExpression> expressions, Map<String, Boolean> context) {
      BinaryOperator<Boolean> operator = func::apply;

      return expressions.stream()
              .map(expression -> expression.evaluate(context))
              .reduce(identity, operator);
  }
}
