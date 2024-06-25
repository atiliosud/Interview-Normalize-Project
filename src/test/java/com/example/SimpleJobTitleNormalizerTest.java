package com.example.normalizer;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class SimpleJobTitleNormalizerTest {

    @Test
    void testNormalization() {
        SimpleJobTitleNormalizer normalizer = new SimpleJobTitleNormalizer(Arrays.asList(
            "Architect", "Software engineer", "Quantity surveyor", "Accountant"
        ));

        assertEquals("Software engineer", normalizer.normalize("Java engineer"));
        assertEquals("Software engineer", normalizer.normalize("C# engineer"));
        assertEquals("Accountant", normalizer.normalize("Accountant"));
        assertEquals("Accountant", normalizer.normalize("Chief Accountant"));
    }
}
