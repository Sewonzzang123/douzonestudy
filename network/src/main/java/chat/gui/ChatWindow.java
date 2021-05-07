package chat.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChatWindow {

	private Frame frame;
	private Panel pannel;
	private Button buttonSend;
	private TextField textField;
	private TextArea textArea;

	// name->닉네임, +소켓추가해서 세팅
	public ChatWindow(String name) {
		frame = new Frame(name);
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);
	}

	public void show() {
		/**
		 * UI초기화 시키는 부분 건들지마
		 */

		// Button
		buttonSend.setBackground(Color.GRAY);
		buttonSend.setForeground(Color.WHITE);
		buttonSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				sendMessage();
			}
		});

		// Textfield
		textField.setColumns(80);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char keyCode = e.getKeyChar();
				if (keyCode == KeyEvent.VK_ENTER) {
					sendMessage();
				}
			}
		});

		// Pannel
		pannel.setBackground(Color.LIGHT_GRAY);
		pannel.add(textField);
		pannel.add(buttonSend);
		frame.add(BorderLayout.SOUTH, pannel);

		// TextArea
		textArea.setEditable(false);
		frame.add(BorderLayout.CENTER, textArea);

		// Frame
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				finish();			
			}
		});
		frame.setVisible(true);
		frame.pack();

		/**
		 * 2.IOStream생성 (코드작성)
		 */

		/**
		 * 3. Chat client Thread 생성(Receive Thread)(코드작성)
		 */

	}
	private void finish() {
		System.out.println("소켓 닫기 or 방나가기 프로토콜 구현");
		System.exit(0);
	}
	private void sendMessage() {
		String message = textField.getText();
		System.out.println("프로토콜 구현: " + message);
		// pr
		//test chatClientThread에서 만들어야댐
		updateTextArea("마이콜: "+message);
		// 프로토콜 구현
		textField.setText("");
		textField.requestFocus();
	}
	
	private void updateTextArea(String message) {
		textArea.append(message);
		textArea.append("\n");
	}
	
	//class구현
	private class ChatClinetThread extends Thread{
		
		@Override
		public void run() {
			//updateTextArea("...");
		}
	}
}

