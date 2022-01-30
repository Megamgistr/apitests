package api_tests.pets;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class DeletePetTest extends AbstractPetTest {

    @BeforeEach
    public void preconditions() {
        setUpPreconditions(true);
    }

    @Test
    @DisplayName("Test for check DELETE pet info")
    public void performTest() {
        API_CLIENT.tryToDeletePet(petInfo.getId());
        Assertions.assertTrue(Objects.isNull(API_CLIENT.getPet(petInfo.getId())));
    }
}
