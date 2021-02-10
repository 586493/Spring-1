package aui.lab.sobanski.book.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookDatabaseApplicationGateway {

    @Value("${books.uri}") private String booksUri;
    @Value("${authors.uri}") private String authorsUri;
    @Value("${categories.uri}") private String categoriesUri;
    @Value("${gateway.pattern}") private String gatewayPattern;

    public static void main(String[] args) {
        SpringApplication.run(BookDatabaseApplicationGateway.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("authors",
                        predicateSpec -> predicateSpec.host(gatewayPattern)
                                .and()
                                .path("/api/authors/{id}", "/api/authors")
                                .uri(authorsUri))
                .route("categories",
                        predicateSpec -> predicateSpec.host(gatewayPattern)
                                .and()
                                .path("/api/categories/{id}", "/api/categories")
                                .uri(categoriesUri))
                .route("books",
                        predicateSpec -> predicateSpec.host(gatewayPattern)
                                .and()
                                .path("/api/books/{id}", "/api/books")
                                .uri(booksUri))
                .build();
    }

}
