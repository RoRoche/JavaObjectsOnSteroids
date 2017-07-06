package fr.guddy.joos.domain;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Repo {
    public abstract int id();

    public abstract String name();

    public abstract String description();

    public abstract String url();

    public static Repo create(final int pId, final String pName, final String pDescription, final String pUrl) {
        return new AutoValue_Repo(pId, pName, pDescription, pUrl);
    }
}
