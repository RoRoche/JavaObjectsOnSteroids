# Java objects on steroids

## Introduction

* Many ways to see "objects": POJO, bean
* Many concepts: encapsulation, inheritance, polymorphism, immutability
* Who is an object? <http://www.yegor256.com/2016/07/14/who-is-object.html>
* Seven virtues of a good object: <http://www.yegor256.com/2014/11/20/seven-virtues-of-good-object.html>

## Write simple immutable object

```java
public final class User {

    private final int id;
    private final String login;
    private final String avatarUrl;

    public User(final int pId, final String pLogin, final String pAvatarUrl) {
        id = pId;
        login = pLogin;
        avatarUrl = pAvatarUrl;
    }
    
}
```

## Improve pojo methods testing with [pojo-tester](http://www.pojo.pl/)

* <http://www.pojo.pl/>

* Gradle installation:

```groovy
repositories {
    jcenter()
}

dependencies {
    testCompile 'pl.pojo:pojo-tester:${latest-version}'
}
```

* Write unit test:

```java
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
```

* Why no getter/setter testing?
    * Because getter/setter are evil <http://www.yegor256.com/2014/09/16/getters-and-setters-are-evil.html>
* Why no `toString`?
    * Because it expects a specific format that is not extensible

* Result:

```
Class fr.guddy.joos.domain.User has bad 'hashCode' method implementation.
The hashCode method should return same hash code for equal objects.
Current implementation returns different values.
Object:
fr.guddy.joos.domain.User@7946e1f4
and
fr.guddy.joos.domain.User@5cc7c2a6
have two different hash codes:
2034688500
and
1556595366
```

## Improve pojo methods writing with [pojomatic](http://www.pojomatic.org/)

* <http://www.pojomatic.org/>

* Why pojomatic instead of Commons Lang, Guava or Lombok?
* Because pojomatic is only focused on the `equals(Object)`, `ashCode()` and `toString()` methods
* Because Commons Lang are verbose
* Because Guava has many other features
* Lombok needs an extra plugin and 

* Gradle install:

```java
compile 'org.pojomatic:pojomatic:2.0.1'
```

* Configure object:

```java
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
```

## Improve immutable writing with [auto-value](https://github.com/google/auto/tree/master/value)

* <https://github.com/google/auto/tree/master/value>

* Gradle installation

```groovy
compile 'com.google.auto.value:auto-value:1.2'
annotationProcessor 'com.google.auto.value:auto-value:1.2'
```

* Configure object:

```java
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
```

## Improve object testing with [AssertJ Assertions Generator](http://joel-costigliola.github.io/assertj/assertj-assertions-generator.html)

* <http://joel-costigliola.github.io/assertj/assertj-assertions-generator.html>

* Inspired by: <http://www.yegor256.com/2017/05/17/single-statement-unit-tests.html>, for the following benefits:
    * Reusability
    * Brevity
    * Readability
    * Immutability
    * Fluent test result

* Gradle installation:

```groovy
buildscript {
    repositories {
        maven {
            url "https://plugins.gradle.org/m2/"
        }
    }
    dependencies {
        classpath "gradle.plugin.com.github.opengl-8080:assertjGen-gradle-plugin:1.1.3"
    }
}

apply plugin: "com.github.opengl-BOBO.assertjGen"
```

```groovy
assertjGen {
    // specify target class or package names by array. (default is empty array)
    classOrPackageNames = ['fr.guddy.joos.domain']

    // specify output dir(String path or File object). (default is 'src/test/java-gen')
    outputDir = 'src/test/java/fr/guddy/joos/domain/matchers'

    // specify AssertJ Assertions Generator dependency. (default is ver 2.0.0)
    assertjGenerator = 'org.assertj:assertj-assertions-generator:2.0.0'
}
```

* Run the `assertjGen` Gradle task to generate assertion classes

## Conclusion

* Focus on the objects
* Less boilerplate
* On the way to DDD
