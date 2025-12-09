package org.cis1200.twentyfortyeight;

import java.awt.*;

public class Tile {
        private int value;

        public Tile(int val) {
            value = val;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int val) {
            value = val;
        }

        public void draw(Graphics g, int x, int y) {
            g.setColor(getColor());
            g.fillRoundRect(x + 10, y + 10, 80, 80, 10, 10);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            if (value != 0) {
                g.drawString("" + value, x + 40, y + 55);
            }
        }

        private Color getColor() {
            return switch (value) {
                case 2 -> new Color(0xeee4da);
                case 4 -> new Color(0xede0c8);
                case 8 -> new Color(0xf2b179);
                case 16 -> new Color(0xf59563);
                case 32 -> new Color(0xf67c5f);
                case 64 -> new Color(0xf65e3b);
                case 128 -> new Color(0xedcf72);
                case 256 -> new Color(0xedcc61);
                case 512 -> new Color(0xedc850);
                case 1024 -> new Color(0xedc53f);
                case 2048 -> new Color(0xedc22e);
                default -> new Color(0xcdc1b4);
            };
        }
}
