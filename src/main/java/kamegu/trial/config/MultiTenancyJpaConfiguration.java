package kamegu.trial.config;

import java.util.LinkedHashMap;
import java.util.Map;

import kamegu.trial.entity.City;

import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
public class MultiTenancyJpaConfiguration {

//	@Autowired
//	private DataSource dataSource;

	@Autowired
	private MultiTenantConnectionProvider multiTenantConnectionProvider;

	@Autowired
	private CurrentTenantIdentifierResolver currentTenantIdentifierResolver;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		Map<String, Object> hibernateProps = new LinkedHashMap<>();

		hibernateProps.put(Environment.URL, "jdbc:mysql://192.168.33.10:3306/demo");
		hibernateProps.put(Environment.USER, "root");
		hibernateProps.put(Environment.PASS, "root");
		hibernateProps.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
		hibernateProps.put(Environment.AUTOCOMMIT, "true");
		
		hibernateProps.put(Environment.MULTI_TENANT, MultiTenancyStrategy.SCHEMA);
		hibernateProps.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
		hibernateProps.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolver);
		hibernateProps.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");

		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setPersistenceUnitName("demo");
		factoryBean.setJpaPropertyMap(hibernateProps);
		factoryBean.setPackagesToScan(City.class.getPackage().getName());
		return factoryBean;
	}
}
