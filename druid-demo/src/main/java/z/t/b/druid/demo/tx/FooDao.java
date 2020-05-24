package z.t.b.druid.demo.tx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author Administrator
 * @date 2020-05-23 17:55
 */
@Repository
@Slf4j
public class FooDao {
    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void doIt(){
        log.info("count before transaction:{}", getCount());
        transactionTemplate.execute(new TransactionCallbackWithoutResult(){
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                jdbcTemplate.execute("insert into foo(id,bar) values (1, 'aaa')");
                log.info("count in transaction:{}", getCount());
                transactionStatus.setRollbackOnly();
            }
        });
        log.info("count after transaction:{}", getCount());
    }

    public long getCount(){
        return (long) jdbcTemplate.queryForList("select count(*) as cont from foo").get(0).get("cont");
    }
}
