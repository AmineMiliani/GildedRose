package edu.insightr.gildedrose.steps;
//import cucumber.api.PendingException;
//import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.insightr.gildedrose.Inventory;
import edu.insightr.gildedrose.Item;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class Stepdefs {
    private Inventory inventory;
    private Item[] items;
    //private Item vest;
    private Item conjured;

    @Given("^I have a new inventory$")
    public void iHaveANewInventory() throws Throwable {
        try {
            inventory = new Inventory();
            items = inventory.getItems();
            conjured = items[5];
        }
        catch(Exception e){
            throw new Throwable(e.getMessage());
        }
    }

    @Then("^the quality of the conjured item is (\\d+)$")
    public void theQualityOfTheConjuredIs(int conjuredQuality) throws Throwable {
        try {
            assertThat(conjured.getQuality(), is(conjuredQuality));
        }
        catch(Exception e){
            throw new Throwable(e.getMessage());
        }
    }

    @When("^I update the inventory$")
    public void iUpdateTheInventory() throws Throwable {
        try{
            inventory.updateQuality();
        }
        catch(Exception e){
            throw new Throwable(e.getMessage());
        }
    }

}
