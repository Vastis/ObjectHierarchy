package io;

import hierachy.SimObject;

import java.util.ArrayList;

public interface InputManager<T> {
    T getSource() throws Exception;
    ArrayList<SimObject> read() throws Exception;
    void save(T source, ArrayList<SimObject> data) throws Exception;
}
