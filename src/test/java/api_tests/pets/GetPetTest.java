package api_tests.pets;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GetPetTest extends AbstractPetTest {

    @BeforeEach
    public void preconditions() {
        setUpPreconditions(true);
    }

    @Test
    @DisplayName("Test for check GET pet info")
    public void performTest() {
        Assertions.assertEquals(petInfo, apiPetInfo);
    }
}
