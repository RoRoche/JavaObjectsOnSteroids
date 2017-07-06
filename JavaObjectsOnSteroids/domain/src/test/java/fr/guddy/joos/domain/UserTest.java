package fr.guddy.joos.domain;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class UserTest {

    @Test
    public void Should_Pass_All_User_Tests() {
        assertPojoMethodsFor(
                User.class
        ).testing(
                Method.EQUALS,
                Method.HASH_CODE,
                // Method.SETTER,
                // Method.GETTER,
                // Method.TO_STRING,
                Method.CONSTRUCTOR
        ).areWellImplemented();
    }

}
