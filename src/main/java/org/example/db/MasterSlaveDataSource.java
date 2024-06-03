package org.example.db;

import org.example.db.helper.MasterSlaveDataSourceMarker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MasterSlaveDataSource extends AbstractRoutingDataSource {

    private static final Logger log = LoggerFactory.getLogger(MasterSlaveDataSource.class);

    // 可用的从库 Key 列表
    private volatile List<Object> availableSlaveKeys;

    // 从库 key 列表的索引
    private AtomicInteger index = new AtomicInteger(0);

    @Override
    protected Object determineCurrentLookupKey() {

        try {
            // 当前线程的主从标识
            Boolean master = MasterSlaveDataSourceMarker.get();

            if (master == null || master) {
                // 主库，返回 null，使用默认数据源
                log.info("数据库路由：主库");
                return null;
            }

            if(!this.availableSlaveKeys.isEmpty()) {
                // 从库，从 slaveKeys 中选择一个 Key
                int index = this.index.getAndIncrement() % this.availableSlaveKeys.size();

                if (this.index.get() > 9999999) {
                    this.index.set(0);
                }

                Object key = availableSlaveKeys.get(index);
                log.info("数据库路由：从库 = {}", key);
                return key;
            } else {
                // 没有可用的从库，返回 null，使用默认数据源
                log.info("数据库路由：没有可用的从库，使用默认数据源，即主库");
                return null;
            }
        } catch(Exception e) {
            log.error("determineCurrentLookupKey error", e);
        } finally {
            // 一定要清除当前线程的主从标识，否则如果上游使用不当可能会有线程安全问题
            MasterSlaveDataSourceMarker.clean();
        }

        return null;
    }

    public void updateDataSourceStatus(Map<String, Boolean> dataSourceStatus) {
        availableSlaveKeys.clear();
        for (Map.Entry<String, Boolean> entry : dataSourceStatus.entrySet()) {
            if (entry.getValue()) {
                availableSlaveKeys.add(entry.getKey());
            }
        }
        log.info("更新可用的从库数据源列表: {}", availableSlaveKeys);
    }

    public List<Object> getAvailableSlaveKeys() {
        return availableSlaveKeys;
    }

    public void setAvailableSlaveKeys(List<Object> availableSlaveKeys) {
        this.availableSlaveKeys = availableSlaveKeys;
    }
}