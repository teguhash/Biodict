package com.hift.biodict;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TeguhAS on 30-Jul-17.
 */

public class WordsList {
    private List<String> bahasaWordsList = new ArrayList<>();
    private List<String> englishWordsList = new ArrayList<>();
    private List<String> latinWordsList = new ArrayList<>();

    private Map<String, Integer> bahasaEntryMap = new HashMap<>();
    private Map<String, Integer> englishEntryMap = new HashMap<>();
    private Map<String, Integer> latinEntryMap = new HashMap<>();

    private String[] termsArray;
    private String mode = ""; //BAHASA, LATIN, ENGLISH

    public WordsList(String mode) {
        this.mode = mode;
    }

    public List<String> getBahasaWordsList() {
        return bahasaWordsList;
    }

    public void setBahasaWordsList(List<String> bahasaWordsList) {
        this.bahasaWordsList = bahasaWordsList;
    }

    public List<String> getEnglishWordsList() {
        return englishWordsList;
    }

    public void setEnglishWordsList(List<String> englishWordsList) {
        this.englishWordsList = englishWordsList;
    }

    public List<String> getLatinWordsList() {
        return latinWordsList;
    }

    public void setLatinWordsList(List<String> latinWordsList) {
        this.latinWordsList = latinWordsList;
    }

    public String[] getTermsArray() {
        String[] terms;
        switch (getMode()) {
            case "BAHASA": {
                terms = new String[bahasaWordsList.size()];
                terms = bahasaWordsList.toArray(terms);
                this.termsArray = terms;
                return terms;
            }
            case "ENGLISH": {
                terms = new String[englishWordsList.size()];
                terms = englishWordsList.toArray(terms);
                this.termsArray = terms;
                return terms;
            }
            case "LATIN": {
                terms = new String[latinWordsList.size()];
                terms = latinWordsList.toArray(terms);
                this.termsArray = terms;
                return terms;
            }
            default: {
                terms = new String[bahasaWordsList.size()];
                terms = bahasaWordsList.toArray(terms);
                this.termsArray = terms;
                return terms;
            }
        }
    }

    public void setTermsArray(String[] termsArray) {
        this.termsArray = termsArray;
    }

    public String getMode() {
        return mode;
    }

    public Map<String, Integer> getBahasaEntryMap() {
        return bahasaEntryMap;
    }

    public void setBahasaEntryMap(Map<String, Integer> bahasaEntryMap) {
        this.bahasaEntryMap = bahasaEntryMap;
    }

    public Map<String, Integer> getEnglishEntryMap() {
        return englishEntryMap;
    }

    public void setEnglishEntryMap(Map<String, Integer> englishEntryMap) {
        this.englishEntryMap = englishEntryMap;
    }

    public Map<String, Integer> getLatinEntryMap() {
        return latinEntryMap;
    }

    public void setLatinEntryMap(Map<String, Integer> latinEntryMap) {
        this.latinEntryMap = latinEntryMap;
    }

    public Map<String, Integer> getEntryMap() {
        switch (getMode()) {
            case "BAHASA": {
                return bahasaEntryMap;
            }
            case "ENGLISH": {
                return englishEntryMap;
            }
            case "LATIN": {
                return latinEntryMap;
            }
            default: {
                return bahasaEntryMap;
            }
        }
    }
}
