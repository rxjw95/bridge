package com.assign.search.exception;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private String message;

    private List<FieldViolations> fieldViolations;

    public static ErrorResponse of(ErrorCode code, BindingResult bindingResult) {
        return new ErrorResponse(code, FieldViolations.from(bindingResult));
    }

    public static ErrorResponse from(ErrorCode code) {
        return new ErrorResponse(code);
    }

    private ErrorResponse(ErrorCode code, List<FieldViolations> fieldViolations) {
        this.message = code.getMessage();
        this.fieldViolations = fieldViolations;
    }

    private ErrorResponse(ErrorCode code) {
        this.message = code.getMessage();
        this.fieldViolations = new ArrayList<>();
    }

    @Getter
    public static class FieldViolations {

        private final String field;
        private final String value;
        private final String reason;

        public static List<FieldViolations> of(String field, String value, String reason) {
            return List.of(new FieldViolations(field, value, reason));
        }

        public static List<FieldViolations> from(BindingResult bindingResult) {
            return bindingResult.getFieldErrors()
                .stream()
                .map(error -> new FieldViolations(
                    error.getField(),
                    error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                    error.getDefaultMessage()))
                .toList();
        }

        private FieldViolations(String field, String value, String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }
    }
}