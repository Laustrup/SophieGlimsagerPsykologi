package laustrup.sophieglimsagerpsykologi;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import laustrup.sophieglimsagerpsykologi.repositories.DbLibrary;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource.hikari")
public class CustomHikariConfig extends HikariConfig {

    /**
     * Sets driverClassName, Schema, JdbcURL, User, Password and PoolName of this configuration.
     */
    private void setup() {
        setDriverClassName(DbLibrary.get_instance().get_driverClassName());
        setSchema(Defaults.get_instance().get_dbSchema());
        setJdbcUrl(DbLibrary.get_instance().get_path());
        setUsername(DbLibrary.get_instance().get_user());
        setPassword(DbLibrary.get_instance().get_password());
        setPoolName("HikariCP");
    }

    /**
     * Will add additional properties needed for the application to use its right setup.
     * Also depends on whether it is in testing mode or not.
     * @return Properties that has been put.
     */
    private Properties addProperties() {
        Properties properties = new Properties();

        if (Program.get_instance().get_state().equals(Program.State.TESTING))
            properties.put("spring.jpa.database-platform","org.hibernate.dialect.H2Dialect");

        return properties;
    }

    @Bean
    public DataSource dataSource() {
        setup();
        setDataSourceProperties(addProperties());
        return new HikariDataSource(this);
    }
}
