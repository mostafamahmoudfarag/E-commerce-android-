package com.example.mostafa.projecte_commerce;


public class listitem {
        int image;
        String name;
        String salary;
        String num;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public listitem(int image, String name, String salary, String num) {
            this.image = image;
            this.name = name;
            this.salary = salary;
            this.num=num;

        }
    }





