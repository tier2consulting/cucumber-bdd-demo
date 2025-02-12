package com.tier2consulting.spring_boot_cucumber.step_defs;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HttpStepDefs {

    private static final Logger LOG = LoggerFactory.getLogger(HttpStepDefs.class);
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private HttpResponse<String> currentResponse;

    @When("I send a GET request to {string}")
    public void sendGetRequestTo(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build();

        LOG.info("Sending GET request to {}", url);

        currentResponse = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        LOG.info("Got response with statusCode: [{}], body [{}]", currentResponse.statusCode(), currentResponse.body());

    }

    @When("I send a POST request to {string} with the following data")
    public void sendPOSTRequestTo(String url, DataTable table) throws IOException, InterruptedException {
        Map<String, String> tableMap = table.asMap();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(
                        String.format("{\"username\":\"%s\",\"password\":\"%s\"}",
                                tableMap.get("username"),
                                tableMap.get("password")
                        ))
                )
                .header("Content-Type","application/json")
                .build();

        LOG.info("Sending POST request to {}", url);

        currentResponse = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        LOG.info("Got response with statusCode: [{}], body [{}]", currentResponse.statusCode(), currentResponse.body());

    }

    @Then("I assert that the response body contains these values")
    public void evaluateCurrentResponse(DataTable table) {
        Type mapType = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> responseMap = new Gson().fromJson(currentResponse.body(), mapType);

        Map<String, String> data = table.asMap();
        for (var entry : data.entrySet()) {
            assertEquals(entry.getValue(), responseMap.get(entry.getKey()));
        }
    }

}
