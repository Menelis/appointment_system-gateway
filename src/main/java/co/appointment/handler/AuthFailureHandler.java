package co.appointment.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class AuthFailureHandler {

    public static Mono<Void> buildAuthFailureMessage(final ServerHttpResponse response,
                                                     final ObjectMapper objectMapper,
                                                     final HttpStatus status,
                                                     final String error,
                                                     final String message,
                                                     final String path) {
        response.getHeaders()
                .add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.setStatusCode(status);
        final Map<String, Object> body = new HashMap<>();
        body.put("status", status.value());
        body.put("error", error);
        body.put("message", message);
        body.put("path", path);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append(objectMapper.writeValueAsString(body));
        } catch (JsonProcessingException ignored) {}
        String responseBody = stringBuilder.toString();
        byte[] responseBodyBytes = responseBody.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(responseBodyBytes);
        return response.writeWith(Mono.just(buffer));
    }
}
