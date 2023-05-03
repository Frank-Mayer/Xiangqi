package io.frankmayer;

import java.util.Map;
import java.util.Set;

public interface IDataProvider {
  Map<Integer, Character> getCharacters();

  void move(int source, int target);

  Set<Integer> getMoves(int source);
}