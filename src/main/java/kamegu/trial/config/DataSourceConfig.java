package kamegu.trial.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

	@Bean(name = { "dataSource", "demoDataSource" })
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "dataSource1")
	@ConfigurationProperties(prefix = "demo1.datasource")
	public DataSource dataSource1() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "dataSource2")
	@ConfigurationProperties(prefix = "demo2.datasource")
	public DataSource dataSource2() {
		return DataSourceBuilder.create().build();
	}
}
