package se.liu.thela038.tetris;

import javax.swing.*;
import java.awt.*;

public class TetrisViewer
{
    public TetrisComponent mainPanel;
    private Board board;
    private JFrame frame = null;

    public TetrisViewer(final Board board) {
	this.mainPanel = new TetrisComponent(board);
	this.board = board;
    }

    public void init(){
	frame = new JFrame("Tetris");
	frame.setLayout(new BorderLayout());
    }

    public void show() {
        frame.dispose();
        init();
	frame.getContentPane().add(mainPanel);
	JMenuBar menuBar = createMenu();
	frame.setJMenuBar(menuBar);
	frame.pack();
	frame.setVisible(true);
    }

    public void showPicture(){
        init();
	frame.getContentPane().add(new TetrisViewer.IconPainter());
	frame.pack();
	frame.setVisible(true);
    }

    private JMenuBar createMenu(){
	final JMenuBar menuBar = new JMenuBar();
	final JMenu file = new JMenu("Options");

	final AbstractButton quit = new JMenuItem("Quit");
	quit.addActionListener(ev -> System.exit(0));
	file.add(quit);
	menuBar.add(file);

	final AbstractButton pause = new JMenuItem("Pause");
	pause.addActionListener(ev -> board.setGameState(Board.GAMESTATES.PAUSE));
	file.add(pause);
	menuBar.add(file);

	final AbstractButton unPause = new JMenuItem("Unpause");
	unPause.addActionListener(ev -> board.setGameState(Board.GAMESTATES.GAME));
	file.add(unPause);
	menuBar.add(file);

	return menuBar;
    }

    //Borrowed code
    public class IconPainter extends JComponent
    {
	private final ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("images/super_secret_pic.jpg"));

	@Override public Dimension getPreferredSize() {
	    return new Dimension(icon.getIconWidth(),icon.getIconHeight());
	}

	public void paintComponent(final Graphics g) {
	    final Graphics2D g2d = (Graphics2D) g;
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	    icon.paintIcon(this, g, 0, 0);
	}
    }
}
