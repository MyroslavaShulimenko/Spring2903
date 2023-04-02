package com.example.spring2903;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppMain {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        HelloWorld helloWorld = (HelloWorld) context.getBean("helloWorld");
        helloWorld.setMessage("Hello World!");

        System.out.println(helloWorld.getMessage());

        HelloWorld helloWorld2 = (HelloWorld) context.getBean("helloWorld");
        helloWorld2.setMessage("Another message");

        System.out.println(helloWorld.getMessage());
        System.out.println(helloWorld2.getMessage());

        Country country = (Country) context.getBean("france");
        Person personOne = (Person) context.getBean("person1");
        Person personTwo = (Person) context.getBean("person2");

        System.out.println(country);
        System.out.println(personOne);
        System.out.println(personTwo);


    }


}