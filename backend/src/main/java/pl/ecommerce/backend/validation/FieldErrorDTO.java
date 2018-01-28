package pl.ecommerce.backend.validation;

import lombok.Value;

@Value
class FieldErrorDTO {
    private final String field;
    private final String message;
}
