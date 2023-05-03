package io.frankmayer;

import java.util.Map;

public interface IDataProvider {
    Map<Integer, Character> getCharacters();

    void move(int source, int target);
}