package org.example.db;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MasterSlaveDataSource extends AbstractRoutingDataSource {

    static final Logger log = LoggerFactory.getLogger(MasterSlaveDataSource.class);

    // 从库的 Key 列表
    private List<Object> slaveKeys;

    // 从库 key 列表的索引
    private AtomicInteger index = new AtomicInteger(0);

    @Override
    protected Object determineCurrentLookupKey() {

        // 当前线程的主从标识
        Boolean master = MasterSlaveDataSourceMarker.get();

        if (master == null || master || this.slaveKeys.isEmpty()) {
            // 主库，返回 null，使用默认数据源
            log.info("数据库路由：主库");
            return null;
        }

        // 从库，从 slaveKeys 中选择一个 Key
        int index = this.index.getAndIncrement() % this.slaveKeys.size();

        if (this.index.get() > 9999999) {
            this.index.set(0);
        }

        Object key = slaveKeys.get(index);

        log.info("数据库路由：从库 = {}", key);

        return key;
    }


    public List<Object> getSlaveKeys() {
        return slaveKeys;
    }
    public void setSlaveKeys(List<Object> slaveKeys) {
        this.slaveKeys = slaveKeys;
    }
}