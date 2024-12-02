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
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ChatWindow {
	private Socket socket;
	private PrintWriter pw;
	private BufferedReader br;

	private Frame frame;
	private Panel pannel;
	private Button buttonSend;
	private TextField textField;
	private TextArea textArea;

	public ChatWindow(String name, Socket socket) {
		frame = new Frame(name);
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);
		this.socket=socket;
		// writer
		try {
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"), true);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void show() {
		// Button
		buttonSend.setBackground(Color.GRAY);
		buttonSend.setForeground(Color.WHITE);
		buttonSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				sendMessage();
			}
		});
		// buttonSend.addActionListener(actionEvent -> { });


		// Textfield
		textField.setColumns(80);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if(keyChar == KeyEvent.VK_ENTER) {
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
		
		// 4. 스레드 생성
		new ChatClientWindowThread().start();
	}
	
	private void sendMessage() {
		String message = textField.getText();
		if(message.length()==0) { // 빈 문자열 입력 시 전송x
			return;
		}
		if("quit".equals(message)) {
			finish();
		} else {
			// Base64 인코딩
			byte[] lineBytes = message.getBytes(StandardCharsets.UTF_8);
			String encodedLine = Base64.getEncoder().encodeToString(lineBytes);
			pw.println("msg "+encodedLine);
		}
		
		textField.setText("");
		textField.requestFocus();
	}
	
	private void updateTextArea(String message) {
		textArea.append(message);
		textArea.append("\n");
	}
	
	private void finish() {
		pw.println("quit");
		
		// exit java application
		System.exit(0);
	}
	
	private class ChatClientWindowThread extends Thread {
		@Override
		public void run() {
			try {
				// reader
				br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
				
				while(true) {
					// read
					String data = br.readLine();
					if(data==null) {
						ChatClientApp.log("closed by server");
						break;
					} else if("2".equals(data)) {
						break; 
					}
					
					updateTextArea(data);
				}
			} catch (SocketException e) {
				// 퇴장 예외 처리
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}