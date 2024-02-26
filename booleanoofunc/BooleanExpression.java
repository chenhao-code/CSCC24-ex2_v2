package booleanoofunc;

import java.util.Map;
/**
 * A boolean expression: the top of our hierarchy.
 */
public abstract class BooleanExpression {

    // This method is abstract and must be implemented in the subclass.
    public abstract boolean evaluate(Map<String, Boolean> context);

    // This method calls simplifyOnce and returns its result.
    // Further simplifications are handled by the caller.
    public BooleanExpression simplify(Map<String, Boolean> context) {
        return simplifyOnce(context);
    }

    // This method is abstract and must be implemented in the subclass.
    public abstract BooleanExpression simplifyOnce(Map<String, Boolean> context);

    // This method is abstract and must be implemented in the subclass.
    @Override
    public abstract boolean equals(Object other);

    // This method is abstract and must be implemented in the subclass.
    @Override
    public abstract String toString();
}
