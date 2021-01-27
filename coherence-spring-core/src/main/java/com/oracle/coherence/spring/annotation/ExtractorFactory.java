/*
 * Copyright (c) 2013, 2021, Oracle and/or its affiliates.
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at
 * https://oss.oracle.com/licenses/upl.
 */
package com.oracle.coherence.spring.annotation;

import java.lang.annotation.Annotation;

import com.tangosol.util.ValueExtractor;

/**
 * A factory that produces instances of {@link com.tangosol.util.ValueExtractor}
 * for a given {@link java.lang.annotation.Annotation}.
 * <p>
 * A {@link ExtractorFactory} is normally a CDI
 * bean that is also annotated with a {@link ExtractorBinding}
 * annotation. Whenever an injection point annotated with the corresponding
 * {@link ExtractorBinding} annotation is
 * encountered the {@link ExtractorFactory} bean's
 * {@link ExtractorFactory#create(java.lang.annotation.Annotation)}
 * method is called to create an instance of a {@link com.tangosol.util.ValueExtractor}.
 *
 * @param <A> the annotation type that the factory supports
 * @param <T> the type of the value to extract from
 * @param <E> the type of value that will be extracted
 *
 * @author Gunnar Hillert
 * @since 3.0
 */
public interface ExtractorFactory<A extends Annotation, T, E> {
	/**
	 * Create a {@link com.tangosol.util.ValueExtractor} instance.
	 * @param annotation the {@link java.lang.annotation.Annotation} that
	 *                   defines the ValueExtractor
	 * @return a {@link com.tangosol.util.ValueExtractor} instance
	 */
	ValueExtractor<T, E> create(A annotation);
}
