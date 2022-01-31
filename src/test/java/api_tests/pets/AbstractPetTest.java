package api_tests.pets;

import api_tests.api.PetStoreAPIClient;
import api_tests.entries.PetInfo;
import api_tests.entries.factories.PetInfoFactory;

public abstract class AbstractPetTest {
    protected final PetStoreAPIClient API_CLIENT = new PetStoreAPIClient();
    protected PetInfo petInfo;
    protected PetInfo apiPetInfo;

    protected void setUpPreconditions(boolean withCreatePet) {
        petInfo = PetInfoFactory.getPetInfo();
        if (withCreatePet) {
            API_CLIENT.createPet(petInfo);
            apiPetInfo = API_CLIENT.waitPetCreated(petInfo.getId());
        }
    }
}
