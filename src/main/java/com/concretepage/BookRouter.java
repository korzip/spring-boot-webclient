package com.concretepage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class BookRouter {
	@Bean
	public RouterFunction<ServerResponse> root(BookHandler bookHandler) {
		return RouterFunctions.route()
		  .PUT("/update", RequestPredicates.contentType(MediaType.APPLICATION_JSON), bookHandler::updateBook)
		  .DELETE("/books/{id}", RequestPredicates.accept(MediaType.TEXT_PLAIN), bookHandler::deleteBookById)
		  .build();
	}
}