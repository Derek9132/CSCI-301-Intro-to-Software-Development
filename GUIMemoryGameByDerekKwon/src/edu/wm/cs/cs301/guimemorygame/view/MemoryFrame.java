package edu.wm.cs.cs301.guimemorygame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import edu.wm.cs.cs301.guimemorygame.model.MemoryModel;
import edu.wm.cs.cs301.guimemorygame.view.KeyboardPanel;
import edu.wm.cs.cs301.guimemorygame.view.MemoryBoard;
import edu.wm.cs.cs301.guimemorygame.view.StartingFrame;


public class MemoryFrame {
	
	private final JFrame frame;
	
	private final KeyboardPanel keyboardPanel;
	
	private final MemoryModel model;
	
	private final StartingFrame start;
	
	private MemoryBoard board;
	
	private TurnBox turn;
	
	public MemoryFrame(MemoryModel model, StartingFrame s)
	{
		this.model = model;
		this.start = s;
		this.keyboardPanel = new KeyboardPanel(this, model);
		this.board = new MemoryBoard(this, model, this.keyboardPanel);
		this.turn = new TurnBox(this, model);
		//this.rowCol = new rowColumn(this, model, width);
		
		this.frame = initializeFrame();
	}
	
	private JFrame initializeFrame()
	{
		
		JFrame frame = new JFrame("GUI Memory Game");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//frame.setJMenuBar(createMenuBar());
		frame.setResizable(false);
		frame.setJMenuBar(createJBar());
		//frame.setSize(1000,1000);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			 public void windowClosing(WindowEvent event) {
				shutdown();
			}
		});
		
		frame.add(createTitlePanel(), BorderLayout.NORTH);
		frame.add(turn, BorderLayout.WEST);
		frame.add(board, BorderLayout.CENTER); //Change according to difficulty
		frame.add(keyboardPanel.getPanel(), BorderLayout.SOUTH);
		
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		
		System.out.println("Frame size: " + frame.getSize());
		
		return frame;
		
	}
	
	private JPanel createTitlePanel() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		InputMap inputMap = panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancelAction");
		ActionMap actionMap = panel.getActionMap();
		actionMap.put("cancelAction", new CancelAction());
		
		JLabel label = new JLabel(start.username + "'s Memory Game");
		label.setFont(AppFonts.getTitleFont());
		panel.add(label);
		
		return panel;
	}
	
	private JMenuBar createJBar()
	{
		JMenuBar menuBar = new JMenuBar();
		
		JMenu difficultyMenuEasy = new JMenu("New game (easy difficulty)");
		menuBar.add(difficultyMenuEasy);
		
		JMenu difficultyMenuMedium = new JMenu("New game (medium difficulty)");
		menuBar.add(difficultyMenuMedium);
		
		JMenu difficultyMenuHard = new JMenu("New game (hard difficulty)");
		menuBar.add(difficultyMenuHard);
		
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		
		JMenuItem easyBoardHiragana = new JMenuItem("Easy game: Hiragana");
		easyBoardHiragana.addActionListener(event -> this.frame.dispose());
		easyBoardHiragana.addActionListener(event -> new MemoryFrame(new MemoryModel(1,3,4), start));
		difficultyMenuEasy.add(easyBoardHiragana);
		
		JMenuItem easyBoardHangul = new JMenuItem("Easy game: Hangul");
		easyBoardHangul.addActionListener(event -> this.frame.dispose());
		easyBoardHangul.addActionListener(event -> new MemoryFrame(new MemoryModel(2,3,4), start));
		difficultyMenuEasy.add(easyBoardHangul);
		
		JMenuItem easyBoardLatin = new JMenuItem("Easy game: Latin");
		easyBoardLatin.addActionListener(event -> this.frame.dispose());
		easyBoardLatin.addActionListener(event -> new MemoryFrame(new MemoryModel(3,3,4), start));
		difficultyMenuEasy.add(easyBoardLatin);
		
		JMenuItem medBoardHiragana = new JMenuItem("Medium game: Hiragana");
		medBoardHiragana.addActionListener(event -> this.frame.dispose());
		medBoardHiragana.addActionListener(event -> new MemoryFrame(new MemoryModel(1,4,7), start));
		difficultyMenuMedium.add(medBoardHiragana);
		
		JMenuItem medBoardHangul = new JMenuItem("Medium game: Hangul");
		medBoardHangul.addActionListener(event -> this.frame.dispose());
		medBoardHangul.addActionListener(event -> new MemoryFrame(new MemoryModel(2,4,7), start));
		difficultyMenuMedium.add(medBoardHangul);
		
		JMenuItem medBoardLatin = new JMenuItem("Medium game: Latin");
		medBoardLatin.addActionListener(event -> this.frame.dispose());
		medBoardLatin.addActionListener(event -> new MemoryFrame(new MemoryModel(3,4,7), start));
		difficultyMenuMedium.add(medBoardLatin);
		
		JMenuItem hardBoardHiragana = new JMenuItem("Hard game: Hiragana");
		hardBoardHiragana.addActionListener(event -> this.frame.dispose());
		hardBoardHiragana.addActionListener(event -> new MemoryFrame(new MemoryModel(1,7,8), start));
		difficultyMenuHard.add(hardBoardHiragana);
		
		JMenuItem hardBoardHangul = new JMenuItem("Hard game: Hangul");
		hardBoardHangul.addActionListener(event -> this.frame.dispose());
		hardBoardHangul.addActionListener(event -> new MemoryFrame(new MemoryModel(2,7,8), start));
		difficultyMenuHard.add(hardBoardHangul);
		
		JMenuItem hardBoardLatin = new JMenuItem("Hard game: Latin");
		hardBoardLatin.addActionListener(event -> this.frame.dispose());
		hardBoardLatin.addActionListener(event -> new MemoryFrame(new MemoryModel(3,7,8), start));
		difficultyMenuHard.add(hardBoardLatin);
		
		JMenuItem instructItem = new JMenuItem("Instructions");
		instructItem.addActionListener(event -> new InstructionsFrame());
		helpMenu.add(instructItem);
		
		JMenuItem exitGame = new JMenuItem("Exit Game");
		exitGame.addActionListener(event -> this.frame.dispose());
		helpMenu.add(exitGame);
		
		return menuBar;
	}
	
	public void repaintMemoryBoard()
	{
		board.repaint();
		turn.repaint();
		keyboardPanel.getPanel().repaint();
	}
	
	public void shutdown() {
		//model.getStatistics().writeStatistics();
		frame.dispose();
		System.exit(0);
	}
	
	private class CancelAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent event) {
			shutdown();
		}
		
	}
	
	public StartingFrame getStart()
	{
		return start;
	}
	
	public MemoryModel getModel()
	{
		return model;
	}
	
	public MemoryBoard getMemoryBoard()
	{
		return board;
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public String user()
	{
		return start.username;
	}
	

}
