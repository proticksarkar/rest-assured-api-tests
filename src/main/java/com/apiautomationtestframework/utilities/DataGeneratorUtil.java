package com.apiautomationtestframework.utilities;

import net.datafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

public class DataGeneratorUtil {

    public static Faker faker = new Faker();

    public static String getRandomNumber(int count) {
        return faker.number().digits(count);
    }

    public static int getRandomNumber(int min, int max) {return faker.number().numberBetween(min, max);}

    public static String getRandomAlphabets(int count) {
        return RandomStringUtils.randomAlphabetic(count);
    }

    public static String getRandomAlphanumeric(int count) {return RandomStringUtils.randomAlphanumeric(count);}

}
