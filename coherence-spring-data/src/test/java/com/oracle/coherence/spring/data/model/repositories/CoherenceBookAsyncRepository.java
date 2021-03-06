package com.oracle.coherence.spring.data.model.repositories;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.oracle.coherence.spring.data.config.CoherenceMap;
import com.oracle.coherence.spring.data.model.Author;
import com.oracle.coherence.spring.data.model.Book;
import com.oracle.coherence.spring.data.repository.CoherenceAsyncRepository;
import com.tangosol.util.UUID;

import org.springframework.scheduling.annotation.Async;

@CoherenceMap("book")
public interface CoherenceBookAsyncRepository extends CoherenceAsyncRepository<Book, UUID> {

	@Async
	CompletableFuture<List<Book>> findByAuthor(Author author);
}
