package com.example.tarefas.dio;

public class SingletonEager {

    private static SingletonEager instancia = new SingletonEager();

    public SingletonEager() {
    }

    public static SingletonEager getInstance() {
        return instancia;
    }
}
