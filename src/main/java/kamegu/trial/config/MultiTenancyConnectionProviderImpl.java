package kamegu.trial.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.service.spi.Configurable;
import org.hibernate.service.spi.ServiceRegistryAwareService;
import org.hibernate.service.spi.ServiceRegistryImplementor;
import org.hibernate.service.spi.Stoppable;
import org.springframework.stereotype.Component;

@Component
public class MultiTenancyConnectionProviderImpl implements MultiTenantConnectionProvider 
		, Configurable, Stoppable, ServiceRegistryAwareService {

	private static final long serialVersionUID = 8168907057647334460L;

	private DriverManagerConnectionProviderImpl provider = new DriverManagerConnectionProviderImpl();


	@Override
	public void injectServices(ServiceRegistryImplementor serviceRegistry) {
		this.provider.injectServices(serviceRegistry);
	}

	@Override
	public void stop() {
		this.provider.stop();
	}

	@Override
	public void configure(@SuppressWarnings("rawtypes") Map configurationValues) {
		this.provider.configure(configurationValues);
	}

	@Override
	public boolean isUnwrappableAs(@SuppressWarnings("rawtypes") Class unwrapType) {
		return this.provider.isUnwrappableAs(unwrapType);
	}

	@Override
	public <T> T unwrap(Class<T> unwrapType) {
		return this.provider.unwrap(unwrapType);
	}

	@Override
	public Connection getAnyConnection() throws SQLException {
		return this.provider.getConnection();
	}

	@Override
	public void releaseAnyConnection(Connection connection) throws SQLException {
		this.provider.closeConnection(connection);
	}

	@Override
	public Connection getConnection(String tenantIdentifier) throws SQLException {
		Connection conn = getAnyConnection();
		conn.createStatement().execute("use " + tenantIdentifier);
		return conn;
	}

	@Override
	public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
		this.provider.closeConnection(connection);
	}

	@Override
	public boolean supportsAggressiveRelease() {
		return this.provider.supportsAggressiveRelease();
	}
}
