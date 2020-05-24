package z.t.b.druid.demo.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;

/**
 * @author Administrator
 * @date 2020-05-24 11:38
 */
public class CustomJdbcException extends DataAccessException {


    public CustomJdbcException(String msg) {
        super(msg);
    }

    public CustomJdbcException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
