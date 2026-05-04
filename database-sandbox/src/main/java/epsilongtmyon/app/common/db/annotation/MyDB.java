package epsilongtmyon.app.common.db.annotation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.inject.Qualifier;

/**
 * データソースを注入するときにつける限定子
 */
@Target({ TYPE, METHOD, FIELD })
@Retention(RUNTIME)
@Documented
@Qualifier
public @interface MyDB {

}
