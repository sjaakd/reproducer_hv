package nl.reproducer.model;

import java.util.List;

public class BadRequest {

    public BadRequest(List<String> errors) {
        this.errors = errors;
    }

    private List<String> errors;

    public List<String> getErrors() {
        return errors;
    }
}
