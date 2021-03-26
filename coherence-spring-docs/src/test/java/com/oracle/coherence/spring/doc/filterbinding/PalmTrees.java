// tag::hide[]
/*
 * Copyright (c) 2013, 2021, Oracle and/or its affiliates.
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at
 * https://oss.oracle.com/licenses/upl.
 */
package com.oracle.coherence.spring.doc.filterbinding;

import com.oracle.coherence.spring.annotation.FilterBinding;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
// end::hide[]
@FilterBinding
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface PalmTrees {
	int value() default 0;
}
