package z.t.b.druid.demo.filter;

import com.alibaba.druid.filter.FilterAdapter;
import com.alibaba.druid.filter.FilterChain;
import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.ConnectionProxy;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Administrator
 * @date 2020-05-23 12:36
 */
@Slf4j
public class ConnFilter extends FilterEventAdapter {

    @Override
    public void connection_connectBefore(FilterChain chain, Properties info) {
        log.info("connection is connect before");
    }

    @Override
    public void connection_connectAfter(ConnectionProxy connection) {
        log.info("connection is connect after");
    }

}
