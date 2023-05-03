package io.frankmayer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;
import javax.swing.*;

public class UI extends JFrame implements ActionListener {
  private static final Color background = Color.WHITE;
  private static final Color foreground = Color.BLACK;
  private final JPanel grid;
  private final IDataProvider dataProvider;
  private Optional<Integer> selected = Optional.empty();

  public UI(IDataProvider dataProvider) {
    this.dataProvider = dataProvider;
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.grid = new JPanel();
    this.add(grid);
    grid.setLayout(new GridLayout(10, 9));
    grid.setBackground(UI.background);

    for (var y = 1; y <= 10; y++) {
      for (var x = 1; x <= 9; x++) {
        final var pos = x << 4 | y;
        final var field = new Field(pos);
        field.setBounds(x * 50, y * 50, 50, 50);
        field.addActionListener(this);
        grid.add(field);
      }
    }

    this.pack();
    this.setVisible(true);

    this.render();
  }

  private class Field extends JButton {
    private static final Dimension prefSize = new Dimension(50, 50);
    private final int pos;
    private Optional<Typ> typ = Optional.empty();
    private Optional<Side> side = Optional.empty();

    public Field(final int pos) {
      this.setPreferredSize(Field.prefSize);
      this.pos = pos;
    }

    public void setTyp(final Typ typ) {
      this.typ = Optional.ofNullable(typ);
    }

    public void setSide(final Side side) {
      this.side = Optional.ofNullable(side);
    }

    public void clear() {
      this.typ = Optional.empty();
      this.side = Optional.empty();
    }

    // custom render draw
    @Override
    public void paintComponent(Graphics g) {
      final var width = this.getWidth();
      final var height = this.getHeight();
      final var halfWidth = width / 2;
      final var halfHeight = height / 2;
      final var yPos = Xiangqi.y(this.pos);
      g.setColor(UI.foreground);
      g.drawLine(0, halfHeight, width, halfHeight);
      switch (yPos) {
        case 0b101 -> g.drawLine(halfWidth, 0, halfWidth, halfHeight);
        case 0b110 -> g.drawLine(halfWidth, halfHeight, halfWidth, height);
        default -> g.drawLine(halfWidth, 0, halfWidth, height);
      }

      if (UI.this.selected.isPresent() && UI.this.selected.get() == this.pos) {
        g.setColor(Color.BLUE);
        g.drawRect(0, 0, width - 1, height - 1);
      }

      if (this.typ.isPresent() && this.side.isPresent()) {
        final var typ = this.typ.get();
        final var side = this.side.get();
        final var fm = g.getFontMetrics();
        final var text = typ.getSymbolForSide(side);
        final var textWidth = fm.stringWidth(text);
        final var textHeight = fm.getHeight();
        g.setColor(UI.background);
        g.fillOval(halfWidth - textWidth, halfHeight - textWidth, textWidth << 1, textWidth << 1);
        g.setColor(side == Side.RED ? Color.RED : Color.BLACK);
        g.drawOval(halfWidth - textWidth, halfHeight - textWidth, textWidth << 1, textWidth << 1);
        g.drawString(text, halfWidth - textWidth / 2, halfHeight + textHeight / 3);
      } else {
        g.fillOval(halfWidth - 5, halfHeight - 5, 10, 10);
      }
    }

    public boolean hasCharacter() {
      return this.typ.isPresent() && this.side.isPresent();
    }
  }

  private void render() {
    final var characters = this.dataProvider.getCharacters();
    for (var child : grid.getComponents()) {
      if (child instanceof Field field) {
        final Character character = characters.get(field.pos);
        if (character == null) {
          field.clear();
          continue;
        }

        field.setTyp(character.getTyp());
        field.setSide(character.getSide());
      }
    }
    this.repaint();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() instanceof Field field) {
      System.out.println("Field " + field.pos + " clicked");
      if (this.selected.isPresent()) {
        this.dataProvider.move(this.selected.get(), field.pos);
        this.selected = Optional.empty();
        this.render();
      } else if (field.hasCharacter()) {
        this.selected = Optional.of(field.pos);
      }
    }
  }
}