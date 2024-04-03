package org.example.db.helper;

import org.example.db.MasterSlaveDataSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Map;

@Configuration
@EnableScheduling
public class MasterSlaveDataSourceHealthCheckScheduler {

    private final MasterSlaveDataSourceHealthChecker masterSlaveDataSourceHealthChecker;
    private final MasterSlaveDataSource masterSlaveDataSource;

    public MasterSlaveDataSourceHealthCheckScheduler(MasterSlaveDataSourceHealthChecker masterSlaveDataSourceHealthChecker, MasterSlaveDataSource masterSlaveDataSource) {
        this.masterSlaveDataSourceHealthChecker = masterSlaveDataSourceHealthChecker;
        this.masterSlaveDataSource = masterSlaveDataSource;
    }

    @Scheduled(fixedDelay = 10000)
    public void scheduleDataSourceHealthCheck() {
        Map<String, Boolean> dataSourceStatus = masterSlaveDataSourceHealthChecker.checkDataSourcesStatus();
        masterSlaveDataSource.updateDataSourceStatus(dataSourceStatus);
    }
}
