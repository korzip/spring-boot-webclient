package com.concretepage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class BookHandler {
	@Autowired
	private BookService bookService;
	public Mono<ServerResponse> updateBook(ServerRequest request) {
		return request.bodyToMono(Book.class)
				.flatMap(book -> Mono.just(bookService.updateBook(book)))
				.flatMap(book -> ServerResponse.ok()
						.contentType(MediaType.APPLICATION_JSON)
						.body(BodyInserters.fromValue(book)));
	}	
	public Mono<ServerResponse> deleteBookById(ServerRequest request) {
		return Mono.just(bookService.deleteBookById(Integer.parseInt(request.pathVariable("id"))))
				.flatMap(val -> {
					return ServerResponse.noContent().build();
				});
	}
}