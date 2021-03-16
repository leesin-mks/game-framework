package com.game;

public class People
{
    protected int age;

    public People(int age)
    {
        this.age = age;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public void printAge()
    {
        System.out.println(age);
    }
}
