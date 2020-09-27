import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JButton;

public class Tile extends JButton { 

	Point location;
	private static int fontSize = 32;
	private static Font font = new Font("TimesRoman", Font.PLAIN, fontSize);
	String moving;
	
	Tile() {
		this("empty", Color.RED, Color.YELLOW, new Point(0,0));
	}
	
	Tile(String strPiece) {
		this(strPiece, Color.GREEN, Color.RED, new Point(0,0));
	}
	
	Tile (String strPiece, Point location) {
		this(strPiece, Color.BLUE, Color.GREEN, location);
	}
	
	Tile(Color back, Color fore) {
		this("", back, fore, new Point(0,0));
	}
	
	Tile(String strPiece, Color back, Color fore, Point point) {
		setText(strPiece);
		setBackground(back);
		setForeground(fore);
		setFont(font);
		location = point;
	//	int width = fontSize + fontSize/10;
	//	int height = fontSize + fontSize/10;
	//	setPreferredSize(new Dimension(width, height));
	//	setMinimumSize(new Dimension(width, height));
	//	setMaximumSize(new Dimension(width, height));	
	}
}
