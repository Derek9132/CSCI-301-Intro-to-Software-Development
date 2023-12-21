package edu.wm.cs.cs301.wordle.view;

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

import edu.wm.cs.cs301.wordle.model.WordleModel;

public class WordleFrame {
	
	private final JFrame frame;
	
	private final KeyboardPanel keyboardPanel;
	
	private final WordleModel model;
	
	private WordleGridPanel wordleGridPanel;
	

	public WordleFrame(WordleModel model) {
		this.model = model;
		this.keyboardPanel = new KeyboardPanel(this, model);
		int width = keyboardPanel.getPanel().getPreferredSize().width;
		this.wordleGridPanel = new WordleGridPanel(this, model, width);
		this.frame = createAndShowGUI();
	}
	
	private JFrame createAndShowGUI() {
		JFrame frame = new JFrame("Wordle");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setJMenuBar(createMenuBar());
		frame.setResizable(false);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			 public void windowClosing(WindowEvent event) {
				shutdown();
			}
		});
		
		frame.add(createTitlePanel(), BorderLayout.NORTH);
		frame.add(wordleGridPanel, BorderLayout.CENTER); //Change according to difficulty
		frame.add(keyboardPanel.getPanel(), BorderLayout.SOUTH);
		
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		
		System.out.println("Frame size: " + frame.getSize());
		
		return frame;
	}
	
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		
		JMenu difficultyMenu = new JMenu("Choose Difficulty");
		menuBar.add(difficultyMenu);
		
		JMenuItem easyBoard = new JMenuItem("Easy");
		easyBoard.addActionListener(event -> this.frame.dispose());
		easyBoard.addActionListener(event -> new WordleFrame(new WordleModel(4,5)));
		difficultyMenu.add(easyBoard);
		
		JMenuItem mediumBoard = new JMenuItem("Medium");
		mediumBoard.addActionListener(event -> this.frame.dispose());
		mediumBoard.addActionListener(event -> new WordleFrame(new WordleModel(5,6)));
		difficultyMenu.add(mediumBoard);
		
		JMenuItem hardBoard = new JMenuItem("Hard");
		hardBoard.addActionListener(event -> this.frame.dispose());
		hardBoard.addActionListener(event -> new WordleFrame(new WordleModel(6,7)));
		difficultyMenu.add(hardBoard);
		
		JMenuItem instructionsItem = new JMenuItem("Instructions...");
		instructionsItem.addActionListener(event -> new InstructionsDialog(this));
		helpMenu.add(instructionsItem);
		
		JMenuItem aboutItem = new JMenuItem("About...");
		aboutItem.addActionListener(event -> new AboutDialog(this));
		helpMenu.add(aboutItem);
		
		return menuBar;
	}
	
	private JPanel createTitlePanel() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		InputMap inputMap = panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancelAction");
		ActionMap actionMap = panel.getActionMap();
		actionMap.put("cancelAction", new CancelAction());
		
		JLabel label = new JLabel("Wordle");
		label.setFont(AppFonts.getTitleFont());
		panel.add(label);
		
		return panel;
	}
	
	public void shutdown() {
		model.getStatistics().writeStatistics();
		frame.dispose();
		System.exit(0);
	}
	
	public void resetDefaultColors() {
		keyboardPanel.resetDefaultColors();
	}
	
	public void setColor(String letter, Color backgroundColor, Color foregroundColor) {
		keyboardPanel.setColor(letter, backgroundColor, foregroundColor);
	}
	
	public void repaintWordleGridPanel() {
		wordleGridPanel.repaint();
	}

	public JFrame getFrame() {
		return frame;
	}
	
	private class CancelAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent event) {
			shutdown();
		}
		
	}

}
