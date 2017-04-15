package com.example;

import java.util.ArrayList;
import java.util.Random;

public class JokeJava {

    private ArrayList<String> jokeList;
    private Random random;

    public JokeJava() {
        jokeList = new ArrayList<>();
        jokeList.add("Joke 1");
        jokeList.add("Joke 2");
        jokeList.add("Joke 3");
        jokeList.add("Joke 4");
        jokeList.add("Joke 5");
        random = new Random();
    }

    public String getJoke() {
        int index = random.nextInt(jokeList.size());
        return jokeList.get(index);
    }

}
