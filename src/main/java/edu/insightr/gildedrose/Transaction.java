package edu.insightr.gildedrose;
import gherkin.lexer.Tr;

import java.time.LocalDate;


public class Transaction {

    private String action ;
    private String transactionDate;
    private Item item;

    public Transaction (String action, Item item)
    {
        this.action = action;
        this.item = item;
        this.transactionDate= LocalDate.now().toString();
    }
    public String getAction()
    {
        return action;
    }
    public String getTransactionDate()
    {
        return transactionDate;
    }
    
    @Override
    public String toString ()
    {
        return action+ " I "+ item.getName() + " at " + item.getSellIn() + "$ the " + transactionDate;
    }

}
