package api_tests.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {
    AVAILABLE("available");
    private final String statusName;
}
