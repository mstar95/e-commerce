package pl.ecommerce.backend.validation;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
class ValidationErrorDTO {
    private final List<FieldErrorDTO> fieldErrors = new ArrayList<>();

    void addFieldError(String path, String message) {
        FieldErrorDTO error = new FieldErrorDTO(path, message);
        fieldErrors.add(error);
    }
}
