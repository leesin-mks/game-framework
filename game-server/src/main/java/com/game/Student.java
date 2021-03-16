package com.game;

public class Student extends People
{
    protected int age = 1;

    public Student(int age)
    {
        super(age);
    }

    @Override
    public int getAge()
    {
        return age;
    }

    @Override
    public void setAge(int age)
    {
        this.age = age;
    }

    public static void main(String[] args) {
        Student s = new Student(2);
        s.age = 5;
        s.printAge();
        System.out.println(s.age);
    }
}
