package z.t.b.druid.demo.tx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Administrator
 * @date 2020-05-24 11:04
 */
@Slf4j
@Service
public class FooServiceImpl implements FooService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void insertRecord() {
        jdbcTemplate.execute("insert into foo(bar) values('taaa')");
    }

    @Override
    @Transactional(rollbackFor = RollbackException.class)
    public void insertThenRollback() throws RollbackException {
        jdbcTemplate.execute("insert into foo(bar) values('tbbb')");
        throw new RollbackException();
    }
    @Autowired
    private FooService fooService;

    @Override
    public void invokeInsertThenRollback() throws RollbackException{
//        insertThenRollback();// 无事务
        fooService.insertThenRollback();// 有事务
//        ((FooService)AopContext.currentProxy()).insertThenRollback();
    }
}
