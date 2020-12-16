/*
 * Copyright (c) 2013, 2020, Oracle and/or its affiliates.
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at
 * https://oss.oracle.com/licenses/upl.
 */
package com.oracle.coherence.spring.configuration.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.oracle.coherence.spring.configuration.CoherenceSpringConfiguration;

/**
 *
 * @author Gunnar Hillert
 *
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Import(CoherenceSpringConfiguration.class)
public @interface EnableCoherence {

}
