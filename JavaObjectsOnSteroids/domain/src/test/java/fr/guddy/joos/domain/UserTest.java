package fr.guddy.joos.domain;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;
import static fr.guddy.joos.domain.UserAssert.assertThat;

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

    @Test
    public void Should_Have_Matching_Id() {
        assertThat(
                new User(
                        12,
                        "Romain",
                        "https://avatars2.githubusercontent.com/u/12625928?v=3&s=460"
                )
        ).hasId(12);
    }
}