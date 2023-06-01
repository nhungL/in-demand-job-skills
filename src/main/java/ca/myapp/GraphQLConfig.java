package ca.myapp;

import ca.myapp.resolvers.JobResolver;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class GraphQLConfig {

    private final JobResolver jobResolver;

    public GraphQLConfig(JobResolver jobResolver) {
        this.jobResolver = jobResolver;
    }

    @Bean
    public GraphQL graphQL() throws IOException {
        return GraphQL.newGraphQL(graphQLSchema()).build();
    }

    @Bean
    public GraphQLSchema graphQLSchema() throws IOException {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(new ClassPathResource("graphql/schema.graphqls").getFile());
        RuntimeWiring runtimeWiring = buildRuntimeWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", builder -> builder.dataFetcher("getJob", (DataFetcher) jobResolver))
                .build();
    }

    // Other bean definitions and configuration methods
}

