package com.onAir.organiser.exception;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ExceptionHandlerResponse extends DataFetcherExceptionResolverAdapter {
    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if (ex instanceof ErrorResponse) {
            ErrorResponse errorResponse = ((ErrorResponse) ex);
            Integer errorCode = errorResponse.getCode();

            return GraphqlErrorBuilder.newError()
                    .message("message: " + ex.getMessage() + " "+ "code: " + ((ErrorResponse) ex).getCode())
                    .errorType(ErrorClassification.errorClassification(HttpStatus.valueOf(errorCode).toString()))
                    .location(env.getField().getSourceLocation())
                    .build();
        } else {
            return null;
        }
    }
}