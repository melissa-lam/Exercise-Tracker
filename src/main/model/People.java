package model;

import java.util.ArrayList;
import java.util.List;

public class People {
    private List<Person> people;

    public People() {
        this.people = new ArrayList<>();
    }

    public void addPerson(Person p) {
        people.add(p);
    }

    public Person getPerson(String name) {
        Person person = new Person("",0,0,0);
        for (Person p: people) {
            if (name.equals(p.getName())) {
                person = p;
            }
        }
        return person;
    }
}
