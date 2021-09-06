package com.concretepage;

import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class BookWebClient {
	private WebClient client = WebClient.create("http://localhost:8080");

	public void updateBookDemo() {
		  client.put()
				.uri("/update")
				.bodyValue(new Book(101, "Android"))
				.exchange()
		        .flatMap(res -> res.bodyToMono(Book.class))
				.subscribe(book -> System.out.println("PUT: " + book.getId() + ", " + book.getName())); 
	}

	public void deleteBookByIdDemo() {
//		  int id = 102;
//		  client.delete()
//				.uri("/books/" + id)
//				.exchange()	
//		        .subscribe(res -> System.out.println("DELETE: " + res.statusCode()));

//		int id = 102;
//		client.delete()
//				.uri("/books/" + id)
//				.retrieve()
//				.onStatus(httpStatus -> httpStatus.is2xxSuccessful(), clientResponse -> {
//					return Mono.empty();
//				})
//				.onStatus(httpStatus -> httpStatus.isError(), clientResponse -> {
//					return Mono.empty();
//				})
//				.toEntity(String.class)
//				.map(s -> {
//					System.out.println("delete map: " + s);
//					return s;
//				})
//				.subscribe(res -> System.out.println("DELETE: " + res.getStatusCode()));


		int id = 102;
		client.delete()
				.uri("/books2/" + id)
				.exchangeToMono(clientResponse -> {
					if (clientResponse.statusCode().is2xxSuccessful()) {

						return clientResponse.toBodilessEntity();
					} else {

						return client.delete()
								.uri("/books/" + id).exchangeToMono(clientResponse1 -> {
									if (clientResponse1.statusCode().is2xxSuccessful()) {
										
										return clientResponse1.toBodilessEntity();
									} else {
										return clientResponse1.toBodilessEntity();
									}

								})
								.map(s -> {
									System.out.println("delete map: " + s);
									return s;
								});
//								.subscribe(res -> System.out.println("DELETE: " + res.getStatusCode()));
					}
				})
				.subscribe(res -> System.out.println("DELETE: " + res.getStatusCode()));;

	}
}