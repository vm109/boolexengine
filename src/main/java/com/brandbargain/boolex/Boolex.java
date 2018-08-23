package com.brandbargain.boolex;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/*
Ex: [( connections > decidedvalue ) | ( likes > value ) & !private]
 */
public class Boolex {

    private Deque<Object> expressionQueue = new LinkedList<>();
    private final String NotBoolean = "notBoolean";
    private final String NotCondition = "notCondition";

    private Map<String, Object> wordDictionary = new HashMap<>();

    public boolean evaluate(String booleanExpressionString) throws BoolexExceptions {
        String[] tokens = booleanExpressionString.split(" ");
        int variableCount =0;
        for (String token : tokens) {

            if (skipSymbol(token)) {
                continue;
            }
            if (ifWordPresentInDictionary(token)) {
                variableCount++;
                if (variableCount>=2) {
                    doConditionEvaluation((String) expressionQueue.remove());
                    variableCount = 0;
                }
                continue;
            }
            try {
                if (checkIfBoolean(token) || true) {
                    expressionQueue.add(token);
                    if (expressionQueue.size()>=3) {
                        doConditionEvaluation((String) expressionQueue.remove());
                    }
                }
            } catch (ClassCastException e) {
                if (e.getMessage().equalsIgnoreCase(NotBoolean)) {
                    expressionQueue.addFirst(token);
                }
            }
        }

        while (expressionQueue.size() > 1) {
            doConditionEvaluation((String) expressionQueue.remove());
        }

        return Boolean.valueOf(Boolean.valueOf((String)expressionQueue.remove()));
    }

    private boolean checkIfBoolean(String mayBeBooleanToken) throws ClassCastException {
        boolean returnValue = false;
        switch (mayBeBooleanToken.toLowerCase()) {
            case "true":
                returnValue = true;
                break;
            case "false":
                returnValue = false;
                break;
            default:
                throw new ClassCastException(NotBoolean);
        }
        return returnValue;
    }

    private void doConditionEvaluation(String mayBeCondition) {
        boolean returnValue = false;
        switch (mayBeCondition.toLowerCase()) {
            case "and":
                if (expressionQueue.size() > 1) {

                    expressionQueue.addLast(String.valueOf(checkIfBoolean((String) expressionQueue.removeLast()) & checkIfBoolean((String) expressionQueue.removeLast())));
                }

                break;

            case "or":
                if (expressionQueue.size() > 1) {

                    expressionQueue.addLast(String.valueOf(checkIfBoolean((String) expressionQueue.removeLast()) | checkIfBoolean((String) expressionQueue.removeLast())));
                }

                break;
            case ">":
                if (expressionQueue.size() > 1) {
                    expressionQueue.addLast(String.valueOf((Integer) expressionQueue.removeLast() < (Integer) expressionQueue.removeLast()));
                }
                break;
            case "<":
                if (expressionQueue.size() > 1) {
                    expressionQueue.addLast(String.valueOf((Integer) expressionQueue.removeLast() > (Integer) expressionQueue.removeLast()));
                }
                break;
            case "not":
                if(expressionQueue.size()>=1){
                    expressionQueue.addLast(String.valueOf(!Boolean.valueOf((String)expressionQueue.removeLast())));
                }
                break;
            default:
                throw new ClassCastException(NotCondition);
        }
    }

    private boolean ifWordPresentInDictionary(String word) throws BoolexExceptions {
        if (this.wordDictionary == null || this.wordDictionary.size()<1) {
            throw BoolexExceptions.throwWOrdNotFoundINDictionary();
        } else if(wordDictionary.containsKey(word)){
            this.expressionQueue.addLast(wordDictionary.get(word));
            return true;
        }else{
            return false;
        }
    }

    private boolean skipSymbol(String symbol) {
        boolean returnValue = false;
        switch (symbol) {
            case "(":
                returnValue = true;
                break;
            case ")":
                returnValue = true;
                break;

        }
        return returnValue;
    }

    public void setWordDictionary(Map<String, Object> dictionaryMap) {
        this.wordDictionary = dictionaryMap;
    }

}
