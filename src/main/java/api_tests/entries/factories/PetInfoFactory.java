package api_tests.entries.factories;

import api_tests.entries.PetInfo;
import api_tests.utils.RandomStringGenerator;

import static api_tests.entries.Status.AVAILABLE;

public class PetInfoFactory {
    public static PetInfo getPetInfo() {
        PetInfo petInfo = new PetInfo();
        petInfo.setId(RandomStringGenerator.getRandomNumber(10));
        petInfo.setName(RandomStringGenerator.getRandomText(10));
        petInfo.setStatus(AVAILABLE);
        return petInfo;
    }
}
