package lambda_expressions.method_references;

/**
 * Created by lukasz on 18.06.17.
 */
@FunctionalInterface
public interface ThreeFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}
