package edu.insightr.gildedrose.steps;
//import cucumber.api.PendingException;
//import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.insightr.gildedrose.Inventory;
import edu.insightr.gildedrose.Item;
//import sun.reflect.annotation.EnumConstantNotPresentExceptionProxy;

import javax.swing.*;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class Steps {
    private Inventory inventory;
    private Item[] items;
    private Item conjured;
    private Item normal;

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
            inventory.Add("testNewItem", 5, 5, "27-11-2018");
        } catch (Exception e) {
            throw new Throwable(e.getMessage());
        }
    }
    @When("^I delete an item$")
    public void iDeleteAnItem() throws Throwable {
        try {
            inventory.Delete(6);
        } catch (Exception e) {
            throw new Throwable(e.getMessage());
        }
    }

    @When("^I edit the name of the item with identifier (\\d+) to be (\\s+)$")
    public void iEditTheNameOfAnItemToBe(int id, String name) throws Throwable {
        try {
            items[id].setName(name);
        } catch (Exception e) {
            throw new Throwable(e.getMessage());
        }
    }

    @When("^I edit the sell in value of the item with identifier (\\d+) to be (\\d+)$")
    public void iEditTheSellinOfAnItemToBe(int id, int sellin) throws Throwable {
        try {
            items[id].setSellIn(sellin);
        } catch (Exception e) {
            throw new Throwable(e.getMessage());
        }
    }

    @When("^I edit the quality of the item with identifier (\\d+) to be (\\d+)$")
    public void iEditTheQualityOfAnItemToBe(int id, int quality) throws Throwable {
        try {
            items[id].setQuality(quality);
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

    @Then("^the item was deleted from the inventory$")
    public void theItemWasDeleted() throws Throwable {
        try {
            assertNull(items[6]);
        } catch (Exception e) {
            throw new Throwable(e.getMessage());
        }
    }

    @Then("^the item (\\d+) has name: (\\s+), sellin: (\\d+), quality: (\\d+)$")
    public void theItemHasNameSellinQuality(int id, String name, int sellin, int quality) throws Throwable {
        try {
            assertThat(items[id].getName(), is(name));
            assertThat(items[id].getSellIn(), is(sellin));
            assertThat(items[id].getQuality(), is(quality));
        } catch (Exception e) {
            throw new Throwable(e.getMessage());
        }
    }
}
