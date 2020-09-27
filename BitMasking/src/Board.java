import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import javax.swing.JPanel;

public class Board extends JPanel implements DragGestureListener, DropTargetListener {
	private DragSource ds = DragSource.getDefaultDragSource();
	private Tile[][] tiles;
	private Tile move;
	Color primary = Color.BLUE;
	Color alternate = Color.YELLOW;
	Color foreground = Color.BLACK;
	Board() {
		this (8,8);
	}

	Board(int rows, int cols) {
		boolean first = true;
		setBackground(Color.BLACK);
		setLayout(new GridLayout(rows, cols, 1, 1));
		tiles = new Tile[rows][cols];
		for (int r = 0; r < rows; r++) {
			for (int c=0; c < cols; c++) {
				Tile tile;
				if (first) 
					tile = new Tile("", alternate, foreground, new Point(r, c));
				else
					tile = new Tile("", primary, foreground, new Point(r, c));
				first = !first;
				new DropTarget(tile, DnDConstants.ACTION_COPY_OR_MOVE, this, true, null);
				ds = new DragSource();
				ds.createDefaultDragGestureRecognizer(tile, DnDConstants.ACTION_COPY_OR_MOVE, this);
				add(tile);
				tiles[r][c] = tile;
			}
			first = !first;
		}
	}

	public void placePawns() {
		for (int c = 0; c < tiles[0].length; c++) {
			tiles[1][c].setText(Piece.BLACK_PAWN);
			tiles[6][c].setText(Piece.WHITE_PAWN);
		}
	}

	@Override
	public void dragEnter(DropTargetDragEvent arg0) {
	}

	@Override
	public void dragExit(DropTargetEvent arg0) {
	}

	@Override
	public void dragOver(DropTargetDragEvent arg0) {
	}

	@Override
	public void drop(DropTargetDropEvent event) {
		System.out.println(event.getDropAction());
		try {
			Transferable tr = event.getTransferable();
			Tile movingTile = (Tile) tr.getTransferData(TransferableTile.tileFlavor);
			if (event.isDataFlavorSupported(TransferableTile.tileFlavor)) {
				if (event.getDropTargetContext().getComponent() instanceof Tile) {
					event.acceptDrop(DnDConstants.ACTION_COPY);
					Tile tile = (Tile) event.getDropTargetContext().getComponent();
					tile.setText(movingTile.getText());
					move.setText("");
					event.dropComplete(true);
					return;
				}					
			}
			event.rejectDrop();
		} catch (Exception e) {
			e.printStackTrace();
			event.rejectDrop();
		}
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent arg0) {
	}

	@Override
	public void dragGestureRecognized(DragGestureEvent event) {
		Cursor cursor = null;
		if (event.getComponent() instanceof Tile) {
			Tile tile = (Tile) event.getComponent();
			move = tile;
			if (event.getDragAction() == DnDConstants.ACTION_COPY) {
				cursor = DragSource.DefaultCopyDrop;
			}
			event.startDrag(cursor, new TransferableTile(tile));		
		}
	}	
}
/*
class TransferableText implements Transferable {
	protected static DataFlavor textFlavor = new DataFlavor(String.class, "A String Object");
	protected static DataFlavor[] supportedFlavors = { textFlavor };
	String text;
	public TransferableText(String text) {
		this.text = text;
	}

	public DataFlavor[] getTransferDataFlavors() {
		return supportedFlavors;
	}

	public boolean isDataFlavorSupported(DataFlavor flavor) {
		if (flavor.equals(textFlavor) || flavor.equals(DataFlavor.stringFlavor))
			return true;
		return false;
	}

	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
		if (flavor.equals(textFlavor) || (flavor.equals(DataFlavor.stringFlavor)))
			return text;
		else
			throw new UnsupportedFlavorException(flavor);
	}

}
 */
class TransferableTile implements Transferable {
	protected static DataFlavor tileFlavor = new DataFlavor(Tile.class, "A Tile Object");
	protected static DataFlavor[] supportedFlavors = { tileFlavor };
	Tile tile;
	public TransferableTile(Tile tile) {
		this.tile = tile;
	}

	public DataFlavor[] getTransferDataFlavors() {
		return supportedFlavors;
	}

	public boolean isDataFlavorSupported(DataFlavor flavor) {
		if (flavor.equals(tileFlavor))
			return true;
		return false;
	}

	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
		if (flavor.equals(tileFlavor))
			return tile;
		else
			throw new UnsupportedFlavorException(flavor);
	}

}
