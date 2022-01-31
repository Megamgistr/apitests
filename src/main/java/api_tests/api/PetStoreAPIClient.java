package api_tests.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import api_tests.entries.PetInfo;
import java.net.HttpURLConnection;
import lombok.SneakyThrows;
import okhttp3.*;
import org.awaitility.core.ConditionTimeoutException;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static api_tests.configuration.LaunchConfiguration.BASE_URL;
import static api_tests.constants.Timeouts.*;
import static org.awaitility.Awaitility.await;

public class PetStoreAPIClient {
    private static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    private static final String API_VERSION = "/v2/";
    private static final String PET_ENDPOINT = API_VERSION + "pet/";


    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final OkHttpClient httpClient;

    public PetStoreAPIClient() {
        this.httpClient = new OkHttpClient();
    }

    public PetInfo getPet(String petId) {
        try {
            Response response = sendRequest(Method.GET, PET_ENDPOINT + petId, null, false, 0);
            int statusCode = response.code();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                return null;
            }
            return OBJECT_MAPPER.readValue(Objects.requireNonNull(response.body()).string(), PetInfo.class);
        } catch (IOException e) {
            throw new AssertionError("Failed to map info " + e.getMessage(), e);
        }
    }

    @SneakyThrows
    public void createPet(PetInfo petInfo) {
        sendRequest(Method.POST, PET_ENDPOINT, petInfo);
    }

    public void updatePet(PetInfo petInfo) {
        sendRequest(Method.PUT, PET_ENDPOINT, petInfo);
    }

    public void tryToDeletePet(String petId) {
        try {
            await().atMost(DELETE_PET_TIMEOUT, TimeUnit.SECONDS)
                    .pollInterval(DEFAULT_POLLING_INTERVAL, TimeUnit.SECONDS)
                    .until(() -> {
                        Response response = sendRequest(Method.DELETE, PET_ENDPOINT + petId, null, false, 0);
                        return response.code() == HttpURLConnection.HTTP_OK;
                    });
        } catch (ConditionTimeoutException e) {
            throw new AssertionError("Pet " + petId + " hasn't delete within " + DELETE_PET_TIMEOUT + " seconds", e);
        }
    }

    public PetInfo waitPetCreated(String petId) {
        try {
            return await().atMost(CREATE_PET_TIMEOUT, TimeUnit.SECONDS)
                    .pollInterval(DEFAULT_POLLING_INTERVAL, TimeUnit.SECONDS)
                    .until(() -> getPet(petId), Objects::nonNull);
        } catch (ConditionTimeoutException e) {
            throw new AssertionError("Pet " + petId + " hasn't create within " + CREATE_PET_TIMEOUT + " seconds", e);
        }
    }

    public void waitPetUpdated(PetInfo expectedPetInfo) {
        try {
            await().atMost(UPDATE_PET_TIMEOUT, TimeUnit.SECONDS)
                    .pollInterval(DEFAULT_POLLING_INTERVAL, TimeUnit.SECONDS)
                    .until(() -> {
                        PetInfo petInfo = getPet(expectedPetInfo.getId());
                        return Objects.equals(expectedPetInfo, petInfo);
                    });
        } catch (ConditionTimeoutException e) {
            throw new AssertionError("Pet " + expectedPetInfo.getId() + " hasn't update within " + UPDATE_PET_TIMEOUT + " seconds", e);
        }
    }

    private Response sendRequest(Method method, String endpoint, Object value) {
        return sendRequest(method, endpoint, value, true, HttpURLConnection.HTTP_OK);
    }

    private Response sendRequest(Method method, String endpoint, Object value, boolean checkStatusCode, int expectedStatus) {
        try {
            RequestBody body = null;
            if (value != null) {
                String jsonBody = OBJECT_MAPPER.writeValueAsString(value);
                body = RequestBody.create(jsonBody, JSON);
            }
            Request request = new Request.Builder()
                    .url(BASE_URL + endpoint)
                    .method(method.name(), body)
                    .build();

            Response response = httpClient.newCall(request).execute();
            int code = response.code();
            if (checkStatusCode && code != expectedStatus) {
                throw new AssertionError("Wasn't to " + method + ": " + code +
                        ": " + response);
            }
            return response;
        } catch (IOException e) {
            throw new AssertionError("Failed to" + method + ": " + e.getMessage(), e);
        }
    }


    private enum Method {
        GET,
        POST,
        PUT,
        DELETE
    }
}


