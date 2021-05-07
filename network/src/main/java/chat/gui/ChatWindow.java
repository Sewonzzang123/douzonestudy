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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.SocketException;

import chat.ChatClientThread;

public class ChatWindow {

	private Frame frame;
	private Panel pannel;
	private Button buttonSend;
	private TextField textField;
	private TextArea textArea;
	
	private PrintWriter pw;
	private BufferedReader br;
	private Socket socket;
	

	public ChatWindow(String nickname, Socket socket) {
		frame = new Frame(nickname);
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);
		this.socket = socket;
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
		try {
			/**
			 * 2.IOStream생성 (코드작성)
			 */
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);

			/**
			 * 3. Chat client Thread 생성(Receive Thread)(코드작성)
			 */
			new ChatClientThread().start();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void finish() {
		try {
			if (socket != null && socket.isClosed() == false) {
				socket.close();
				System.exit(0);
			}
		} catch (SocketException e) {
			System.out.println(" suddenly closed by server");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sendMessage() {
		String message = textField.getText();
		if("quit".equals(message)==true) {
			finish();
		}else {
			pw.println("message:"+message);
		}
		//System.out.println("프로토콜 구현: " + message);
		// pr
		// test chatClientThread에서 만들어야댐
//		updateTextArea("마이콜: " + message);
		// 프로토콜 구현
		textField.setText("");
		textField.requestFocus();
	}

	private void updateTextArea(String message) {		
		textArea.append(message);
		textArea.append("\n");
	}

	// class구현
	private class ChatClientThread extends Thread {
		@Override
		public void run() {
			// updateTextArea("...");
			try {
				br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
				while (true) {
					String message = br.readLine();
					if (message == null) {
						break;
					} else {
						//System.out.println(message);
						updateTextArea(message);
					}
				}
			} catch (SocketException e) {
				System.out.println(e);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				System.exit(0);
			}
		}
	}
}
