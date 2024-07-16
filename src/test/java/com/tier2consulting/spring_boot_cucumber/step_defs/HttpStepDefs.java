package com.tier2consulting.spring_boot_cucumber.step_defs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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

    @Then("I assert that the response body contains these values")
    public void evaluateCurrentResponse(DataTable data) {


    }

}
