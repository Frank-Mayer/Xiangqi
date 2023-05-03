package io.frankmayer;

public class Character {
    private int pos;
    private Typ typ;
    private Side side;

    public Character(int pos, Typ typ, Side side) {
        this.pos = pos;
        this.typ = typ;
        this.side = side;
    }

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public Typ getTyp() {
		return typ;
	}

	public void setTyp(Typ typ) {
		this.typ = typ;
	}

	public Side getSide() {
		return side;
	}

	public void setSide(Side side) {
		this.side = side;
	}
}
