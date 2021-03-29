package basics;

import java.util.Collection;

public class Person {

    private Long id;
    private String name;
    private Collection<Hobby> hobbies;

    public Person() {
    }

    public Person(Long id, String name, Collection<Hobby> hobbies) {
        this.id = id;
        this.name = name;
        this.hobbies = hobbies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Collection<Hobby> hobbies) {
        this.hobbies = hobbies;
    }
}
