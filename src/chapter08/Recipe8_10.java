package chapter08;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * The following example creates a representation of the Game of Life. The code uses the Fork/Join framework to speed up
 * the calculation of each iteration when advancing from one generation to the next.
 */
public class Recipe8_10 {
    private volatile boolean shouldRun = true;

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        Recipe8_10 recipe = new Recipe8_10();
        recipe.start();
    }

    private void start()  throws InvocationTargetException, InterruptedException {
        boolean[][] lifeBoard = new boolean[50][50];
        final LifeTableModel model = new LifeTableModel(lifeBoard);
        final JTable lifeTable  = new JTable();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Game of Life");
                lifeTable.setModel(model);
                lifeTable.setDefaultRenderer(Boolean.class, new LifeTableCellRenderer());
                frame.setContentPane(new JScrollPane(lifeTable));
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.addWindowListener(new WindowListener() {
                    @Override
                    public void windowOpened(WindowEvent e) {

                    }

                    @Override
                    public void windowClosing(WindowEvent e) {
                        shouldRun = false;
                    }

                    @Override
                    public void windowClosed(WindowEvent e) {

                    }

                    @Override
                    public void windowIconified(WindowEvent e) {

                    }

                    @Override
                    public void windowDeiconified(WindowEvent e) {

                    }

                    @Override
                    public void windowActivated(WindowEvent e) {

                    }

                    @Override
                    public void windowDeactivated(WindowEvent e) {

                    }
                });
                frame.setSize(1000, 1000);
                frame.setVisible(true);
            }
        });

        // first things first
        populateRandomBoard(lifeBoard);
        model.fireTableDataChanged();

        ForkJoinPool pool = new ForkJoinPool();
        long i = 0;

        while (shouldRun) {
            i++;
            final boolean[][] newBoard = new boolean[lifeBoard.length][lifeBoard[0].length];
            long startTime = System.nanoTime();
            GameOfLifeAdvancer advancer = new GameOfLifeAdvancer(lifeBoard, 0, 0,
                    lifeBoard.length - 1, lifeBoard[0].length - 1, newBoard);
            pool.invoke(advancer);
            long endTime = System.nanoTime();
            if (i % 100 == 0)
                System.out.println("Taking " + (endTime - startTime)/1000 + "ms");

            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    model.setBoard(newBoard);
                    lifeTable.repaint();
                }
            });
            lifeBoard = newBoard;
        }
    }

    Random random = new Random();

    private void populateRandomBoard(boolean[][] lifeBoard){
        for (int row = 0; row < lifeBoard.length; row++) {
            for (int col = 0; col < lifeBoard[0].length; col++) {
                lifeBoard[row][col] = random.nextBoolean();
            }
        }
    }

    class LifeTableCellRenderer extends JLabel implements TableCellRenderer {

        public LifeTableCellRenderer() { this. setOpaque(true); }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            if ((Boolean)value) {
                this.setBackground(Color.black);
            } else {
                this.setBackground(Color.white);
            }
            return this;
        }
    }

    class LifeTableModel extends AbstractTableModel {
        private boolean[][] lifeBoard;

        public LifeTableModel(boolean[][] lifeBoard) { this.lifeBoard = lifeBoard; }

        @Override
        public String getColumnName(int column) {
            return null;
        }

        @Override
        public int getRowCount() {
            return lifeBoard.length;
        }

        @Override
        public int getColumnCount() {
            return lifeBoard[0].length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return lifeBoard[rowIndex][columnIndex];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return Boolean.class;
        }

        public void setBoard(boolean[][] newBoard) { this.lifeBoard = newBoard; }
    }

    class GameOfLifeAdvancer extends RecursiveAction {

        private boolean[][] originalBoard;
        private boolean[][] destinationBoard;
        private int startRow;
        private int endRow;
        private int startCol;
        private int endCol;

        GameOfLifeAdvancer(boolean[][] originalBoard, int startRow, int startCol, int endRow,
                           int endCol, boolean[][] destinationBoard) {
            this.originalBoard = originalBoard;
            this.destinationBoard = destinationBoard;
            this.startRow = startRow;
            this.endRow = endRow;
            this.startCol = startCol;
            this.endCol = endCol;
        }

        private void computeDirectly() {
            for (int row = startRow; row < endRow; row++) {
                for (int col = startCol; col < endCol; col++) {
                    int numberOfNeighbors = getNumberOfNeighbors(row, col);
                    if (originalBoard[row][col]) {
                        destinationBoard[row][col] = true;
                        if (numberOfNeighbors < 2) destinationBoard[row][col] = false;
                        if (numberOfNeighbors > 3) destinationBoard[row][col] = false;
                    } else {
                        destinationBoard[row][col] = false;
                        if (numberOfNeighbors == 3) destinationBoard[row][col] = true;
                    }
                }
            }
        }

        private int getNumberOfNeighbors(int row, int col) {
            int neighborCount = 0;
            for (int leftIndex = -1; leftIndex < 2; leftIndex++) {
                for (int topIndex = -1; topIndex < 2; topIndex++) {
                    if ((leftIndex == 0) && (topIndex == 0)) continue; // skip own
                    int neighborRowIndex = row + leftIndex;
                    int neighborColIndex = col + topIndex;
                    if (neighborRowIndex < 0) neighborRowIndex = originalBoard.length + neighborRowIndex;
                    if (neighborColIndex < 0) neighborColIndex = originalBoard[0].length + neighborColIndex;
                    boolean neighbor = originalBoard[neighborRowIndex % originalBoard.length][neighborColIndex % originalBoard[0].length];
                    if (neighbor) neighborCount++;
                }
            }
            return neighborCount;
        }

        private int getArea() { return (endRow - startRow) * (endCol - startCol); }

        @Override
        protected void compute() {
            if (getArea() < 20) {
                computeDirectly();
                return;
            }
            int halfRows = (endRow - startRow) / 2;
            int halfCols = (endCol - startCol) / 2;
            if (halfRows > halfCols) {
                // split the rows
                invokeAll(new GameOfLifeAdvancer(originalBoard, startRow, startCol, startRow + halfRows, endCol, destinationBoard),
                          new GameOfLifeAdvancer(originalBoard, startRow + halfRows + 1, startCol, endRow, endCol, destinationBoard));
            } else {
                // split the columns
                invokeAll(new GameOfLifeAdvancer(originalBoard, startRow, startCol, endRow, startCol + halfCols, destinationBoard),
                          new GameOfLifeAdvancer(originalBoard, startRow, startCol + halfCols + 1, endRow, endCol, destinationBoard));
            }
        }
    }
}
