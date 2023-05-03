package io.frankmayer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Xiangqi implements IDataProvider {
  private final UI ui;
  private final LinkedList<Character> characters;

  public static void main(String[] args) {
    new Xiangqi();
  }

  public Xiangqi() {
    this.characters = new LinkedList<Character>();
    this.characters.add(new Character(0b10001, Typ.TURM, Side.BLACK));
    this.characters.add(new Character(0b10010001, Typ.TURM, Side.BLACK));
    this.characters.add(new Character(0b11010, Typ.TURM, Side.RED));
    this.characters.add(new Character(0b10011010, Typ.TURM, Side.RED));
    this.characters.add(new Character(0b100001, Typ.REITER, Side.BLACK));
    this.characters.add(new Character(0b10000001, Typ.REITER, Side.BLACK));
    this.characters.add(new Character(0b101010, Typ.REITER, Side.RED));
    this.characters.add(new Character(0b10001010, Typ.REITER, Side.RED));
    this.characters.add(new Character(0b110001, Typ.ELEFANT, Side.BLACK));
    this.characters.add(new Character(0b1110001, Typ.ELEFANT, Side.BLACK));
    this.characters.add(new Character(0b111010, Typ.ELEFANT, Side.RED));
    this.characters.add(new Character(0b1111010, Typ.ELEFANT, Side.RED));
    this.characters.add(new Character(0b1000001, Typ.MANDARIN, Side.BLACK));
    this.characters.add(new Character(0b1100001, Typ.MANDARIN, Side.BLACK));
    this.characters.add(new Character(0b1001010, Typ.MANDARIN, Side.RED));
    this.characters.add(new Character(0b1101010, Typ.MANDARIN, Side.RED));
    this.characters.add(new Character(0b1010001, Typ.KAISER, Side.BLACK));
    this.characters.add(new Character(0b1011010, Typ.KAISER, Side.RED));
    this.characters.add(new Character(0b100011, Typ.GESCHÜTZ, Side.BLACK));
    this.characters.add(new Character(0b10000011, Typ.GESCHÜTZ, Side.BLACK));
    this.characters.add(new Character(0b101000, Typ.GESCHÜTZ, Side.RED));
    this.characters.add(new Character(0b10001000, Typ.GESCHÜTZ, Side.RED));
    this.characters.add(new Character(0b00010100, Typ.BAUER, Side.BLACK));
    this.characters.add(new Character(0b00110100, Typ.BAUER, Side.BLACK));
    this.characters.add(new Character(0b01010100, Typ.BAUER, Side.BLACK));
    this.characters.add(new Character(0b01110100, Typ.BAUER, Side.BLACK));
    this.characters.add(new Character(0b10010100, Typ.BAUER, Side.BLACK));
    this.characters.add(new Character(0b00010111, Typ.BAUER, Side.RED));
    this.characters.add(new Character(0b00110111, Typ.BAUER, Side.RED));
    this.characters.add(new Character(0b01010111, Typ.BAUER, Side.RED));
    this.characters.add(new Character(0b01110111, Typ.BAUER, Side.RED));
    this.characters.add(new Character(0b10010111, Typ.BAUER, Side.RED));

    this.ui = new UI(this);
  }

  public static int y(int pos) {
    return pos & 0b1111;
  }

  public static int x(int pos) {
    return pos >> 4;
  }

  @Override
  public Map<Integer, Character> getCharacters() {
    final var charactersToRender = new HashMap<Integer, Character>();
    for (final var character : characters) {
      charactersToRender.put(character.getPos(), character);
    }
    return charactersToRender;
  }

  @Override
  public void move(int source, int target) {
    for (final var character : characters) {
      if (character.getPos() == source) {
        character.setPos(target);
        break;
      }
    }
  }
}