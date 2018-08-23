package com.brandbargain.boolex;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class BoolexTests {
    private Boolex boolexpression;
    @Before
    public void init(){
         boolexpression = new Boolex();
    }
    @Test
    public void Test_Boolex() throws BoolexExceptions {
        Map<String,Object> wordDictionary = new HashMap<>();
        wordDictionary.put("numberOfConnections",25);
        wordDictionary.put("baseNumberOfConnections",5);
        boolexpression.setWordDictionary(wordDictionary);
        Assert.assertFalse(boolexpression.evaluate("true and false"));
        Assert.assertTrue(boolexpression.evaluate("true and true"));
        Assert.assertTrue(boolexpression.evaluate("true and true and true"));
        Assert.assertFalse(boolexpression.evaluate("true and false and true and true"));
    }

    @Test
    public void Test_When_Passed_WithVariable_Names() throws BoolexExceptions {
        Map<String,Object> wordDictionary = new HashMap<>();
        wordDictionary.put("numberOfConnections",25);
        wordDictionary.put("baseNumberOfConnections",5);
        boolexpression.setWordDictionary(wordDictionary);
         Assert.assertTrue(boolexpression.evaluate("( numberOfConnections > baseNumberOfConnections )"));
    }


    @Test
    public void Test_Expression_With_Not_Symbol() throws BoolexExceptions {
        Map<String,Object> wordDictionary = new HashMap<>();
        wordDictionary.put("numberOfConnections",25);
        wordDictionary.put("baseNumberOfConnections",5);
        boolexpression.setWordDictionary(wordDictionary);
        Assert.assertFalse(boolexpression.evaluate("not true"));
    }

    @Test
    public void Test_Little_Complex_Expression() throws BoolexExceptions {
        Map<String,Object> wordDictionary = new HashMap<>();
        wordDictionary.put("numberOfConnections",25);
        wordDictionary.put("baseNumberOfConnections",5);
        boolexpression.setWordDictionary(wordDictionary);
        Assert.assertTrue(boolexpression.evaluate("( numberOfConnections > baseNumberOfConnections ) and ( numberOfConnections > baseNumberOfConnections and not true ) or ( not false )"));
        Assert.assertFalse(boolexpression.evaluate("( numberOfConnections < baseNumberOfConnections ) and ( numberOfConnections < baseNumberOfConnections and not true ) or ( not false )"));
        Assert.assertFalse(boolexpression.evaluate("( numberOfConnections > baseNumberOfConnections ) and ( numberOfConnections > baseNumberOfConnections and not true ) or ( not true )"));
    }

}
