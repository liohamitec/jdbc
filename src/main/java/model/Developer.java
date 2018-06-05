package model;

public class Developer
        extends NamedEntity {
    private int age;
    private int salary;

    public Developer(Long id, String name) {
        super(id, name);
    }

    public Developer(String name) {
        super(name);
    }

    public Developer(Long id, String name, int age) {
        super(id, name);
        this.age = age;
    }

    public Developer(Long id, String name, int age, int salary) {
        super(id, name);
        this.age = age;
        this.salary = salary;
    }

    public Developer(String name, int age, int salary) {
        super(name);
        this.age = age;
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return  "{" +
                "id='" + getId() + "\' " +
                "name='" + getName() + "\' " +
                "age='" + getAge() + "\' " +
                "salary='" + getSalary() + "\' " +
                '}';
    }
}
