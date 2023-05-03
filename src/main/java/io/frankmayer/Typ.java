package io.frankmayer;

import java.util.Optional;

public enum Typ {
    KAISER("帥", "將"),
    MANDARIN("仕", "士"),
    ELEFANT("相", "象"),
    REITER("傌", "馬"),
    TURM("俥", "車"),
    GESCHÜTZ("炮", "砲"),
    BAUER("兵", "卒");

    private final String symbolRed;
    private final String symbolBlack;
    public final Optional<Typ> opt;

    Typ(String symbolRed, String symbolBlack) {
        this.symbolRed = symbolRed;
        this.symbolBlack = symbolBlack;
        this.opt = Optional.of(this);
    }

    public String getSymbolRed() {
        return this.symbolRed;
    }

    public String getSymbolBlack() {
        return this.symbolBlack;
    }

    public String getSymbolForSide(Side side) {
        return switch (side) {
            case RED -> this.getSymbolRed();
            case BLACK -> this.getSymbolBlack();
        };
    }
}
