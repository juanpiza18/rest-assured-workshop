package com.restassured.examplespojo;

public class Pokemons {
    int count;
    String next;
    String previous;
    Pokemon[] results;

    public Pokemons() {
    }

    public Pokemons(int count, String next, String previous, Pokemon[] results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public Pokemon[] getResults() {
        return results;
    }

    public void setResults(Pokemon[] results) {
        this.results = results;
    }
}