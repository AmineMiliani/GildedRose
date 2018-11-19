package edu.insightr.gildedrose.steps;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.insightr.gildedrose.Inventory;
import edu.insightr.gildedrose.Item;
import sun.reflect.annotation.EnumConstantNotPresentExceptionProxy;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class Stepdefs {
    private Inventory inventory;
    private Item[] items;
    //private Item vest;
    private Item conjured;
    private Item normal;
    private Item newItem;

    @Given("^I have a new inventory with a conjured item$")
    public void iHaveANewInventoryWithConjuredItem() throws Throwable {
        try {
            inventory = new Inventory();
            items = inventory.getItems();
            conjured = items[5];
        } catch (Exception e) {
            throw new Throwable(e.getMessage());
        }
    }

    @Given("^I have a new inventory$")
    public void iHaveANewInventory() throws Throwable {
        try {
            inventory = new Inventory();
            items = inventory.getItems();
        } catch (Exception e) {
            throw new Throwable(e.getMessage());
        }
    }

    @Given("^I have a new inventory with a normal item$")
    public void iHaveANewInventoryWithNormalItem() throws Throwable {
        try {
            inventory = new Inventory();
            items = inventory.getItems();
            normal = items[0];
        } catch (Exception e) {
            throw new Throwable(e.getMessage());
        }
    }

    @Then("^the quality of the conjured item is (\\d+)$")
    public void theQualityOfTheConjuredIs(int conjuredQuality) throws Throwable {
        try {
            assertThat(conjured.getQuality(), is(conjuredQuality));
        } catch (Exception e) {
            throw new Throwable(e.getMessage());
        }
    }

    @Then("^the quality of the normal item is (\\d+)$")
    public void theQualityOfTheNormalItemIs(int normalQuality) throws Throwable {
        try {
            assertThat(normal.getQuality(), is(normalQuality));
        } catch (Exception e) {
            throw new Throwable(e.getMessage());
        }
    }

    @Then("^the item was added to the inventory$")
    public void theItemWasAddedToTheInventory() throws Throwable {
        try {
            assertThat(items[6].getName(), is("testNewItem"));
        } catch (Exception e) {
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

    @When("^I add a new item$")
    public void iAddANewItem() throws Throwable {
        try {
            inventory.Add("testNewItem", 5, 5);
        } catch (Exception e) {
            throw new Throwable(e.getMessage());
        }
    }
}
