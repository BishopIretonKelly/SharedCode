import javax.swing.JFrame;

public class Game extends JFrame { 
	Board board;
	public Game() {
		board = new Board();
		add(board);
        setTitle("Game Board");
		setSize(400,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.board.placePawns();
		game.pack();
	}
}
