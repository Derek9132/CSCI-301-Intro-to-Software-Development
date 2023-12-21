package edu.wm.cs.cs301.guimemorygame.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import edu.wm.cs.cs301.guimemorygame.model.MemoryModel;
import edu.wm.cs.cs301.guimemorygame.controller.KeyBoardButtonAction;

public class KeyboardPanel {
	
	private int buttonIndex, buttonCount;

	private final JButton[] buttons;

	private final JPanel panel;

	private final KeyBoardButtonAction action;

	private final MemoryModel model;
	
	private ArrayList<Integer> guess;
	
	public KeyboardPanel(MemoryFrame view, MemoryModel model)
	{
		this.model = model;
		this.buttons = new JButton[getLength()];
		this.action = new KeyBoardButtonAction(view, model, this);
		this.panel = createMainPanel();
		//System.out.println("RowCol " + action.guess);
	}
	
	public void setList(ArrayList <Integer> g)
	{
		this.guess = g;
	}
	

	
	private JPanel createMainPanel()
	{
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 5));
		
		panel.add(numPanel());
		panel.add(enterAndClear());
		return panel;
	}
		
	
	private JPanel numPanel()
	{
		JPanel num = new JPanel(new GridLayout(0, 1, 0, 0));
		if (this.model.getRows() * this.model.getColumns() == 12)
		{
			num.add(firstRow());
		}
		else if(this.model.getRows() * this.model.getColumns() == 28)
		{
			num.add(firstRow());
			num.add(secondRowMedium());
		}
		else
		{
			num.add(firstRow());
			num.add(secondRowHard());
		}
		return num;
	}
	
	private int getLength()
	{
		if (this.model.getRows() * this.model.getColumns() == 12)
		{
			return 4;
		}
		else if (this.model.getRows() * this.model.getColumns() == 28)
		{
			return 7;
		}
		else if (this.model.getRows() * this.model.getColumns() == 56)
		{
			return 8;
		}
		else
		{
			return 7;
		}
	}
	
	private JPanel enterAndClear()
	{
		Font textfont = AppFonts.getTextFont();
		JPanel panel = new JPanel();
		JButton enter =  new JButton("Enter");
		JButton backspace =  new JButton("Backspace");
		setKeyBinding(enter, "Enter");
		setKeyBinding(backspace, "Backspace");
		enter.setFont(textfont);
		backspace.setFont(textfont);
		enter.addActionListener(action);
		backspace.addActionListener(action);
		panel.add(enter);
		panel.add(backspace);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		return panel;
	}
	
	
	
	private JPanel firstRow()
	{
		String[] num = {"1","2","3","4"};
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		Font textfont = AppFonts.getTextFont();
		
		for (int index = 0; index < num.length; index++) {
			JButton button = new JButton(num[index]);
			setKeyBinding(button, num[index]);
			button.addActionListener(action);
			button.setFont(textfont);
			buttons[buttonIndex++] = button;
			panel.add(button);
		}
		
		return panel;
		
	}
	
	public void lockButtons()
	{
		for (int i = 0; i < buttons.length; i++)
		{
			buttons[i].setEnabled(false);
		}
	}
	
	public void unlockButtons()
	{
		for (int i = 0; i < buttons.length; i++)
		{
			buttons[i].setEnabled(true);
		}
	}
	
	private JPanel secondRowMedium()
	{
		String[] num = {"5","6","7"};
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		Font textfont = AppFonts.getTextFont();
		
		for (int index = 0; index < num.length; index++) {
			JButton button = new JButton(num[index]);
			setKeyBinding(button, num[index]);
			button.addActionListener(action);
			button.setFont(textfont);
			buttons[buttonIndex++] = button;
			panel.add(button);
		}
		
		return panel;
	}
	
	private JPanel secondRowHard()
	{
		String[] num = {"5","6","7","8"};
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		Font textfont = AppFonts.getTextFont();
		
		for (int index = 0; index < num.length; index++) {
			JButton button = new JButton(num[index]);
			setKeyBinding(button, num[index]);
			button.addActionListener(action);
			button.setFont(textfont);
			buttons[buttonIndex++] = button;
			panel.add(button);
		}
		return panel;
		
	}
	
	
	private void setKeyBinding(JButton button, String text) {
		InputMap inputMap = button.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW);
		if (text.equalsIgnoreCase("Backspace")) {
			inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0),
					"action");
		} else {
			inputMap.put(KeyStroke.getKeyStroke(text.toUpperCase()), "action");
		}
		ActionMap actionMap = button.getActionMap();
		actionMap.put("action", action);
	}
	
	
	public JPanel getPanel()
	{
		return panel;
	}
	

}
