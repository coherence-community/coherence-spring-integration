/*
 * Copyright (c) 2013, 2021, Oracle and/or its affiliates.
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at
 * https://oss.oracle.com/licenses/upl.
 */
package com.oracle.coherence.spring.boot.autoconfigure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oracle.coherence.spring.boot.autoconfigure.support.LogType;
import com.oracle.coherence.spring.configuration.SessionConfigurationBean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

/**
 * Configuration properties for the Coherence Spring integration.
 *
 * @author Gunnar Hillert
 * @since 3.0
 */
@ConfigurationProperties(prefix = "coherence")
public class CoherenceProperties {

	/**
	 * Default service name prefix. Defaults recursively to ${code coherence.role}.
	 */
	private static final String SPRING_COHERENCE_PROPERTIES_PREFIX = "coherence.properties.";

	/**
	 * Default service name prefix. Defaults recursively to ${code coherence.role}.
	 */
	private static final String SERVICE_NAME_PREFIX = "coherence.service.prefix";

	/**
	 * The name of the config property for the character limit.
	 */
	private static final String LOG_LIMIT = "coherence.log.limit";

	/**
	 * The name of the config property for logging destination.
	 */
	private static final String LOG_DESTINATION = "coherence.log";

	/**
	 * The name of the config property for log level.
	 */
	private static final String LOG_LEVEL = "coherence.log.level";

	/**
	 * The name of the config property for logger name.
	 */
	private static final String LOG_LOGGER_NAME = "coherence.log.logger";

	/**
	 * The name of the config property for message format.
	 */
	private static final String LOG_MESSAGE_FORMAT = "coherence.log.format";

	/**
	 * Additional native properties passed to Coherence.
	 */
	private Map<String, String> properties = new HashMap<>();

	/**
	 * Coherence Logging configuration.
	 */
	private LoggingProperties logging;

	/**
	 * Session configuration.
	 */
	private List<SessionConfigurationBean> sessions;


	public List<SessionConfigurationBean> getSessions() {
		return this.sessions;
	}

	public void setSessions(List<SessionConfigurationBean> sessions) {
		this.sessions = sessions;
	}

	public Map<String, String> getProperties() {
		return this.properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	public LoggingProperties getLogging() {
		return this.logging;
	}

	public void setLogging(LoggingProperties logging) {
		this.logging = logging;
	}

	/**
	 * Returns a {@link Map} of Coherence properties using the format {@code coherence.properties.*}.
	 * @return the Coherence properties as a {@link Map}. Never returns null.
	 */
	final Map<String, Object> getCoherencePropertiesAsMap() {
		final Map<String, Object> coherenceProperties = new HashMap<>();

		if (this.logging != null) {

			if (this.logging.destination != null) {
				coherenceProperties.put(SPRING_COHERENCE_PROPERTIES_PREFIX + LOG_DESTINATION, this.logging.destination.getKey());
			}

			if (StringUtils.hasText(this.logging.loggerName)) {
				coherenceProperties.put(SPRING_COHERENCE_PROPERTIES_PREFIX + LOG_LOGGER_NAME, this.logging.loggerName);
			}

			if (this.logging.severityLevel != null) {
				coherenceProperties.put(SPRING_COHERENCE_PROPERTIES_PREFIX + LOG_LEVEL, this.logging.severityLevel);
			}

			if (StringUtils.hasText(this.logging.messageFormat)) {
				coherenceProperties.put(SPRING_COHERENCE_PROPERTIES_PREFIX + LOG_MESSAGE_FORMAT, this.logging.messageFormat);
			}

			if (this.logging.characterLimit != null) {
				coherenceProperties.put(SPRING_COHERENCE_PROPERTIES_PREFIX + LOG_LIMIT, this.logging.characterLimit);
			}
		}
		return coherenceProperties;
	}

	/**
	 * Coherence Logging Configuration.
	 */
	public static class LoggingProperties {

		/**
		 * The type of the logging destination. Default to "stderr" if not set.
		 */
		private LogType destination;

		/**
		 * Specifies which logged messages are emitted to the log destination. The legal values are -1 to 9.
		 * No messages are emitted if -1 is specified. More log messages are emitted as the log level is increased.
		 */
		private Integer severityLevel;

		/**
		 * Specifies a logger name within chosen logging system that logs Coherence related messages. This value is
		 * used by the JDK, log4j, log4j2, and slf4j logging systems. The default value is "Coherence".
		 */
		private String loggerName;

		/**
		 * Specifies how messages that have a logging level specified are formatted before passing them to the log destination.
		 * The format can include static text and any of the following replaceable parameters: {date}, {uptime}, {product},
		 * {version}, {level}, {thread}, {member}, {location}, {role}, {text}, and {ecid}.
		 *
		 * The default value is:
		 *
		 * {date}/{uptime} {product} {version} &lt;{level}&gt; (thread={thread}, member={member}): {text}
		 */
		private String messageFormat;

		/**
		 * Specifies the maximum number of characters that the logger daemon processes from the message queue before discarding
		 * all remaining messages in the queue. All messages that are discarded are summarized by the logging system with
		 * a single log entry that details the number of messages that were discarded and their total size. Legal values
		 * are positive integers or 0. Zero implies no limit. The default value in production mode is 1048576 and 2147483647
		 * in development mode.
		 */
		private Integer characterLimit;

		public LogType getDestination() {
			return this.destination;
		}

		public void setDestination(LogType destination) {
			this.destination = destination;
		}

		public Integer getSeverityLevel() {
			return this.severityLevel;
		}

		public void setSeverityLevel(Integer severityLevel) {
			this.severityLevel = severityLevel;
		}

		public String getLoggerName() {
			return this.loggerName;
		}

		public void setLoggerName(String loggerName) {
			this.loggerName = loggerName;
		}

		public String getMessageFormat() {
			return this.messageFormat;
		}

		public void setMessageFormat(String messageFormat) {
			this.messageFormat = messageFormat;
		}

		public Integer getCharacterLimit() {
			return this.characterLimit;
		}

		public void setCharacterLimit(Integer characterLimit) {
			this.characterLimit = characterLimit;
		}
	}
}
