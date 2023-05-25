package com.jason;

import io.github.bucket4j.Bucket;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Stack;

public class ResponseObject {

    private ResponseEntity<String> response;
    private final RequestObject request;
    private final Stack<String> stack;
    private final Bucket bucket;

    public ResponseObject(RequestObject request, Stack<String> stack, Bucket bucket) {

        response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request.");
        this.request = request;
        this.stack = stack;
        this.bucket = bucket;

        determineResponse();

    }

    private void determineResponse() {
        if (stack.contains(request.id())) {
            handleCreateRequests();
        } else {
            handleConflictRequests();
        }
    }

    private void handleConflictRequests() {
        stack.push(request.id());
        if (bucket.tryConsume(stack.search(request.id()))) {
            setResponse(
                    HttpStatus.CREATED,
                    "{\"requestId”: \""
                            + request.id() + "\", \"created\": true, \"content\": \""
                            + request.getAlphanumericString() + "\"}");
        } else {
            tooManyRequestsResponse();
        }
    }

    private void handleCreateRequests() {
        if (bucket.tryConsume(stack.search(request.id()))) {
            setResponse(
                    HttpStatus.CONFLICT,
                    "{\"requestId”: \""
                            + request.id() + "\", \"created\": false}");
        } else {
            tooManyRequestsResponse();
        }
    }

    public void tooManyRequestsResponse() {
        setResponse(HttpStatus.TOO_MANY_REQUESTS, "");
    }

    public void setResponse(HttpStatus status, String message) {
        response = ResponseEntity.status(status).body(message);
    }

    public ResponseEntity<String> generate() {
        return response;
    }
}
