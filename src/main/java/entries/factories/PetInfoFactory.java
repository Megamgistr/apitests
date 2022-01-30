package entries.factories;

import entries.PetInfo;
import utils.RandomStringGenerator;

import static entries.Status.AVAILABLE;

public class PetInfoFactory {
    public static PetInfo getPetInfo() {
        PetInfo petInfo = new PetInfo();
        petInfo.setId(RandomStringGenerator.getRandomNumber(10));
        petInfo.setName(RandomStringGenerator.getRandomText(10));
        petInfo.setStatus(AVAILABLE.getStatusName());
        return petInfo;
    }
}
