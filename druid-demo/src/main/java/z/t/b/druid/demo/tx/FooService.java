package z.t.b.druid.demo.tx;


/**
 * @author Administrator
 * @date 2020-05-24 10:59
 */
public interface FooService {

    void insertRecord();
    void insertThenRollback() throws RollbackException;
    void invokeInsertThenRollback() throws RollbackException;
}
