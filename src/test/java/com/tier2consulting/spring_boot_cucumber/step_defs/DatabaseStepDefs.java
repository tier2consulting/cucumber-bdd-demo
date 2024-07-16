package com.tier2consulting.spring_boot_cucumber.step_defs;

import com.tier2consulting.spring_boot_cucumber.model.User;
import com.tier2consulting.spring_boot_cucumber.repository.UserRepository;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class DatabaseStepDefs {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseStepDefs.class);

    private final UserRepository userRepository;

    public DatabaseStepDefs(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Given("I insert a User record with the following fields")
    public void insertRecordWithFields(DataTable dataTable) {

        Map<String, String> asMap = dataTable.asMap();

        User savedUser = userRepository.save(new User(
            null,
            asMap.get("username"),
            asMap.get("password")
        ));

        LOG.info(
            "Successfully saved User record with fields: ID: {}, Username: {}, Password: {}",
            savedUser.getId(), savedUser.getUsername(), savedUser.getPassword()
        );
    }

    @When("^I select the User record with ID of .* and compare with these field values$")
    public void selectRecordsWithFields(Long id, DataTable dataTable) {
        Map<String, String> asMap = dataTable.asMap();
        User retrievedUser = userRepository.findById(id).orElseThrow();

        Assert.assertEquals(id, retrievedUser.getId());
        Assert.assertEquals(asMap.get("username"), retrievedUser.getUsername());
        Assert.assertEquals(asMap.get("password"), retrievedUser.getPassword());
    }
}
