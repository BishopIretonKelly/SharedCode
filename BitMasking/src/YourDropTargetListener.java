import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;

import javax.swing.JPanel;

class YourDropTargetListener extends DropTargetAdapter {
    private DropTarget dropTarget;
    private JPanel panel;

    public YourDropTargetListener(JPanel panel) {
      this.panel = panel;
      dropTarget = new DropTarget(panel, DnDConstants.ACTION_COPY, this, true, null);
    }

    public void drop(DropTargetDropEvent event) {
    	System.out.println("Attempting drop");
      
    }
  }
