package fr.guddy.joos.domain;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.Property;

public final class User {

    @Property
    private final int id;
    @Property
    private final String login;
    @Property
    private final String avatarUrl;

    public User(final int pId, final String pLogin, final String pAvatarUrl) {
        id = pId;
        login = pLogin;
        avatarUrl = pAvatarUrl;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(final Object pObj) {
        return Pojomatic.equals(this, pObj);
    }

    @Override
    public int hashCode() {
        return Pojomatic.hashCode(this);
    }

    @Override
    public String toString() {
        return Pojomatic.toString(this);
    }
}
