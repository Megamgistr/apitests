package api_tests.entries;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {
    AVAILABLE("available");
    private String statusName;
}
