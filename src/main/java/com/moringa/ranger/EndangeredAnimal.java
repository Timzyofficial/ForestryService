package com.moringa.ranger;

public class EndangeredAnimal extends Animal  {

    private String Health;
    private String Age;
    public EndangeredAnimal(int id, String name, String health, String age) {
        super(id, name);
        this.setHealth( health);
        this.setAge(age);
    }

    public EndangeredAnimal(Animal animal, String ill, String young) {
    }

    public String getHealth() {
        return Health;
    }

    public void setHealth(String health) {
        Health = health;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }


}



