package api_tests.entities.factories;

import api_tests.entities.PetInfo;
import api_tests.utils.RandomStringGenerator;

import static api_tests.entities.Status.AVAILABLE;

public class PetInfoFactory {
    public static PetInfo getPetInfo() {
        PetInfo petInfo = new PetInfo();
        petInfo.setId(RandomStringGenerator.getRandomNumber(10));
        petInfo.setName(RandomStringGenerator.getRandomText(10));
        petInfo.setStatus(AVAILABLE);
        return petInfo;
    }
}
