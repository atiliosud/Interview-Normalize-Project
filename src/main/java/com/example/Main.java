package com.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        String jt = "Java engineer";
        SimpleJobTitleNormalizer normalizer = new SimpleJobTitleNormalizer(List.of(
            "Architect", "Software engineer", "Quantity surveyor", "Accountant"
        ));
        String normalizedTitle = normalizer.normalize(jt);
        System.out.println("Normalized Title: " + normalizedTitle);

        jt = "C# engineer";
        normalizedTitle = normalizer.normalize(jt);
        System.out.println("Normalized Title: " + normalizedTitle);

        jt = "Chief Accountant";
        normalizedTitle = normalizer.normalize(jt);
        System.out.println("Normalized Title: " + normalizedTitle);
    }
}

interface JobTitleNormalizer {
    String normalize(String input);
}

class SimpleJobTitleNormalizer implements JobTitleNormalizer {

    private final List<String> idealJobTitles;

    public SimpleJobTitleNormalizer(List<String> idealJobTitles) {
        this.idealJobTitles = idealJobTitles;
    }

    @Override
    public String normalize(String input) {
        double maxScore = 0.0;
        String bestMatch = "";

        for (String ideal : idealJobTitles) {
            double score = computeMatchingScore(input, ideal);
            if (score > maxScore) {
                maxScore = score;
                bestMatch = ideal;
            }
        }

        return bestMatch;
    }

    private double computeMatchingScore(String input, String ideal) {
        String[] inputWords = input.toLowerCase().split("\\s+");
        String[] idealWords = ideal.toLowerCase().split("\\s+");
        
        int matches = 0;
        for (String inputWord : inputWords) {
            for (String idealWord : idealWords) {
                if (inputWord.equals(idealWord)) {
                    matches++;
                }
            }
        }

        return (double) matches / idealWords.length;
    }
}
