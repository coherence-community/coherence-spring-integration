/*
 * Copyright (c) 2013, 2021, Oracle and/or its affiliates.
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at
 * https://oss.oracle.com/licenses/upl.
 */
package com.oracle.coherence.spring.boot.tests;

import java.util.List;

import com.oracle.coherence.spring.boot.autoconfigure.CoherenceProperties;
import com.oracle.coherence.spring.boot.autoconfigure.support.LogType;
import com.oracle.coherence.spring.configuration.session.GrpcSessionConfigurationBean;
import com.oracle.coherence.spring.configuration.session.SessionConfigurationBean;
import com.tangosol.net.Coherence;
import com.tangosol.net.SessionConfiguration;
import com.tangosol.net.SessionConfiguration.ConfigurableCacheFactorySessionConfig;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = CoherencePropertiesTests.class)
@EnableConfigurationProperties(CoherenceProperties.class)
@ActiveProfiles({"coherencePropertiesTests"})
public class CoherencePropertiesTests {

	@Autowired
	private CoherenceProperties coherenceProperties;

	@Test
	void testCoherencePropertiesWithSessions() {
		final List<SessionConfigurationBean> serverSessions = this.coherenceProperties.getSessions().getServer();
		final List<SessionConfigurationBean> clientSessions = this.coherenceProperties.getSessions().getClient();
		final List<GrpcSessionConfigurationBean> grpcSessions = this.coherenceProperties.getSessions().getGrpc();

		assertThat(serverSessions).hasSize(3);
		assertThat(serverSessions.get(0).getName()).isEqualTo("default");
		assertThat(serverSessions.get(0).getConfig()).isEqualTo("coherence-cache-config.xml");
		assertThat(serverSessions.get(0).getScopeName()).isEqualTo("fooscope");
		assertThat(serverSessions.get(0).getPriority()).isEqualTo(1);
		assertThat(serverSessions.get(1).getName()).isEqualTo("test");
		assertThat(serverSessions.get(1).getConfig()).isEqualTo("test-coherence-config.xml");
		assertThat(serverSessions.get(1).getScopeName()).isEqualTo("barscope");
		assertThat(serverSessions.get(1).getPriority()).isEqualTo(2);
		assertThat(serverSessions.get(2).getName()).isNull();
		assertThat(serverSessions.get(2).getConfig()).isEqualTo("test-coherence-config.xml");
		assertThat(serverSessions.get(2).getScopeName()).isEqualTo("myscope");
		assertThat(serverSessions.get(2).getPriority()).isEqualTo(0);

		assertThat(clientSessions).hasSize(1);
	}

	@Test
	void testCoherenceConfiguration() {
		final List<SessionConfigurationBean> serverSessions = this.coherenceProperties.getSessions().getServer();

		assertThat(serverSessions.get(0).getConfiguration()).isNotNull();
		assertThat(serverSessions.get(1).getConfiguration()).isNotNull();
		assertThat(serverSessions.get(2).getConfiguration()).isNotNull();

		final SessionConfiguration sessionConfiguration1 = serverSessions.get(0).getConfiguration().get();
		final SessionConfiguration sessionConfiguration2 = serverSessions.get(1).getConfiguration().get();
		final SessionConfiguration sessionConfiguration3 = serverSessions.get(2).getConfiguration().get();

		assertThat(sessionConfiguration1.getName()).isEqualTo(Coherence.DEFAULT_NAME);
		validateConfigUri("coherence-cache-config.xml", sessionConfiguration1);
		assertThat(sessionConfiguration1.getScopeName()).isEqualTo("fooscope");
		assertThat(sessionConfiguration1.getPriority()).isEqualTo(1);
		assertThat(sessionConfiguration2.getName()).isEqualTo("test");
		validateConfigUri("test-coherence-config.xml", sessionConfiguration2);
		assertThat(sessionConfiguration2.getScopeName()).isEqualTo("barscope");
		assertThat(sessionConfiguration2.getPriority()).isEqualTo(2);
		assertThat(sessionConfiguration3.getName()).isEqualTo(Coherence.DEFAULT_NAME);
		validateConfigUri("test-coherence-config.xml", sessionConfiguration3);
		assertThat(sessionConfiguration3.getScopeName()).isEqualTo("myscope");
		assertThat(sessionConfiguration3.getPriority()).isEqualTo(SessionConfiguration.DEFAULT_PRIORITY);
	}

	@Test
	void testCoherenceLoggingProperties() {
		assertThat(this.coherenceProperties.getLogging()).isNotNull();
		assertThat(this.coherenceProperties.getLogging().getCharacterLimit()).isEqualTo(123);
		assertThat(this.coherenceProperties.getLogging().getDestination()).isEqualTo(LogType.SLF4J);
		assertThat(this.coherenceProperties.getLogging().getLoggerName()).isEqualTo("testing");
		assertThat(this.coherenceProperties.getLogging().getMessageFormat()).isEqualTo("Testing: {date}/{uptime} {product} {version} <{level}> (thread={thread}, member={member}): {text}");
	}

	@Test
	void testNativeCoherenceProperties() {
		assertThat(this.coherenceProperties.getProperties()).hasSize(5);
		assertThat(this.coherenceProperties.getProperties().get("coherence.log.limit")).isEqualTo("444");
		assertThat(this.coherenceProperties.getProperties().get("coherence.log.level")).isEqualTo("1");
		assertThat(this.coherenceProperties.getProperties().get("coherence.log.logger")).isEqualTo("CoherenceSpring");
		assertThat(this.coherenceProperties.getProperties().get("coherence.log")).isEqualTo("log4j");
		assertThat(this.coherenceProperties.getProperties().get("coherence.log.format")).isEqualTo("foobar");
	}

	private void validateConfigUri(String expectedConfigUri, SessionConfiguration sessionConfiguration) {
		final ConfigurableCacheFactorySessionConfig configurableCacheFactorySessionConfig =
			(ConfigurableCacheFactorySessionConfig) sessionConfiguration;

		final String actualConfigUri = configurableCacheFactorySessionConfig.getConfigUri().get();

		assertThat(actualConfigUri).isNotBlank();
		assertThat(expectedConfigUri).isEqualTo(actualConfigUri);
	}
}
