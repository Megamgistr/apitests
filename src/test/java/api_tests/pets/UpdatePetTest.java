package api_tests.pets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.RandomStringGenerator;

public class UpdatePetTest extends AbstractPetTest {
    private String newName;

    @BeforeEach
    public void preconditions() {
        newName = RandomStringGenerator.getRandomText(10);
        setUpPreconditions(true);
    }

    @Test
    @DisplayName("Test for check UPDATE pet info")
    public void performTest() {
        petInfo.setName(newName);
        API_CLIENT.updatePet(petInfo);
        API_CLIENT.waitPetUpdated(petInfo);
    }
}
