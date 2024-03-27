package org.example.db;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.Map;
import java.util.Objects;
import java.util.Properties;

@ConfigurationProperties(prefix = "app.datasource")
public class MasterSlaveDataSourceProperties {

    // 主库
    private final Properties master;

    // 从库
    private final Map<String, Properties> slave;

    @ConstructorBinding // 通过构造函数注入配置文件中的值
    public MasterSlaveDataSourceProperties(Properties master, Map<String, Properties> slave) {
        super();

        Objects.requireNonNull(master);
        Objects.requireNonNull(slave);

        this.master = master;
        this.slave = slave;
    }

    public Properties master() {
        return master;
    }

    public Map<String, Properties> slave() {
        return slave;
    }
}