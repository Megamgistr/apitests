package api_tests.pets;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CreatePetTest extends AbstractPetTest {
    @BeforeEach
    public void preconditions() {
        setUpPreconditions(false);
    }

    @Test
    @DisplayName("Test for check POST pet info")
    public void performTest() {
        API_CLIENT.createPet(petInfo);
        apiPetInfo = API_CLIENT.waitPetCreated(petInfo.getId());
        Assertions.assertEquals(petInfo.getName(), apiPetInfo.getName());
        Assertions.assertEquals(petInfo.getId(), apiPetInfo.getId());
        Assertions.assertEquals(petInfo.getStatus(), apiPetInfo.getStatus());
        Assertions.assertEquals(petInfo, apiPetInfo);
    }
}
