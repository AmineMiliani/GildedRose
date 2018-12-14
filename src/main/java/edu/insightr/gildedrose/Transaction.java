package edu.insightr.gildedrose;
import gherkin.lexer.Tr;

import java.time.LocalDate;


public class Transaction {

    private String action ;
    private String transactionDate;
    private Item item;

    Transaction(String action, Item item)
    {
        this.action = action;
        this.item = item;
        this.transactionDate= LocalDate.now().toString();
    }
    String getAction()
    {
        return action;
    }
    String getTransactionDate()
    {
        return transactionDate;
    }
    
    @Override
    public String toString ()
    {
        if(action.contains("sold")) {
            return action + "      | " + item.getName() + " at " + item.getSellIn() + "$ the " + transactionDate;
        }
        else
        {
            return action+ " | "+ item.getName() + " at " + item.getSellIn() + "$ the " + transactionDate;

        }
    }

}
