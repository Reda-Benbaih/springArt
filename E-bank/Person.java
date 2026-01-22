package projects.ebank;

public class Person {
    private String fullname;
    private int age;
    private String id;

    public Person(String fullname ,int age,String id){
        this.fullname = fullname;
        this.age = age;
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public int getAge() {
        return age;
    }

    public String getId() {
        return id;
    }

    public void setFullname(String fullname){
        this.fullname = fullname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(String id) {
        this.id = id;
    }
}