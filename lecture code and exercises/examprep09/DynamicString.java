import java.util.ArrayList;
import java.util.Arrays;

class DynamicString {
    ArrayList<Character> vals;

    public int hashCode() {
        int h = 0;
        for (int i = 0; i < vals.size(); i++) {
            h = 31 * h + vals.get(i);
        }
        return h;
    }

    public boolean equals(Object o) {
        DynamicString d = (DynamicString) o;
        return vals.equals(d.vals);
    }
}

class PokeTime {
    int startTime;
    int duration;

    public int getCurrentTime() {
        // Gets the current system clock time
    }

    public int hashCode() {
        return 1021 * (startTime + 1021
                * duration + getCurrentTime());
    }

    public boolean equals(Object o) {
        PokeTime p = (PokeTime) o;
        return p.startTime == startTime
                && p.duration == duration;
    }
}
// Invalid. Non-deterministic because it uses the current time.

class Phonebook {
    List<Human> humans;

    public int hashCode() {
        int h = 0;
        for (Human human : humans) {
            // Assume Human hashcode is correct
            h = (h + human.hashCode()) % 509;
        }
        return h;
    }

    public boolean equals(Object o) {
        Phonebook p = (Phonebook) o;
        return p.humans.equals(humans);
    }
}
// Valid, but poor because the values that the hash code
// function can return are restricted by the modulo 509.

class Person {
    Long id;
    String name;
    Integer age;

    public int hashCode() {
        return id.hashCode() + name.hashCode()
                + age.hashCode();
    }

    public boolean equals(Object o) {
        Person p = (Person) o;
        return p.id == id;
    }
}
// Invalid. If two objects are equal as defined by .equals,
// they should have equal hash codes. You can have two Person objects with
// the same id but different name and ages, causing them to possibly hash to
// different buckets.

class DblCharSeq {
    char[] seq1;
    char[] seq2;

    public int hashCode() {
        int h = 0;
        for (char c1 : seq1) {
            for (char c2 : seq2) {
                h = 31 * (31 * h + c1) + c2;
            }
        }
        return h;
    }

    public boolean equals(Object o) {
        DblCharSeq d = (DblCharSeq) o;
        return Arrays.equals(seq1, d.seq1)
                && Arrays.equals(seq2, d.seq2);
    }
}
// Valid but inefficient, as the hashCode function takes
// quadratic work to calculate when it could easily be done in linear time.
