/**
 * @Description
 * @Author everforcc
 * @Date 2023-02-13 11:25
 * Copyright
 */

package jdk.lambda;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * list排序
 */
public class ListSort {

    public static void main(String[] args) {
        List<Person> list = new ArrayList<Person>();
        list.add(new Person(1, "jack", 18));
        list.add(new Person(2, "paul", 28));
        list.add(new Person(3, "zion", 20));
        list.add(new Person(4, "jone", 30));
        list.forEach(System.out::println);
        //利用Collections.sort方法按照年龄排序，默认升序
//        Collections.sort(list, (a, b) -> {
//            return a.getAge() - b.getAge();
//        });
        list.sort(Comparator.comparingInt(Person::getAge));
        System.out.println("================================");
        list.forEach(System.out::println);
        //直接使用List集合sort方法按照姓名排序，默认字母升序
//        list.sort((a, b) -> a.getName().compareTo(b.getName()));
        list.sort(Comparator.comparing(Person::getName));
        System.out.println("================================");
        list.forEach(System.out::println);
    }

}

class Person {
    private int id;
    private String name;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", age=" + age + "]";
    }

    public Person() {

    }

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

}
