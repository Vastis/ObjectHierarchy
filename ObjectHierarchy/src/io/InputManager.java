package io;

import environment.SimObject;

import java.util.ArrayList;

public interface InputManager<T> {
    T getSource() throws Exception;
    ArrayList<SimObject> read();
    void save(T source, ArrayList<SimObject> data);
}
