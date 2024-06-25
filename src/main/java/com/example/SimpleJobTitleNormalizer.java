package com.example.normalizer;

import java.util.*;

public class SimpleJobTitleNormalizer implements JobTitleNormalizer {

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
        if (input.toLowerCase().contains(ideal.toLowerCase())) {
            return 1.0;
        } else {
            return 0.0;
        }
    }

    public static void main(String[] args) {
        String jt = "Java engineer";
        SimpleJobTitleNormalizer normalizer = new SimpleJobTitleNormalizer(Arrays.asList(
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
