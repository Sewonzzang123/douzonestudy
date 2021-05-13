package httpd;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.file.Files;

public class RequestHandler2 extends Thread {
	private Socket socket;
	private static final String DOCUMENTROOT = "./webapp";

	public RequestHandler2(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			// get IOStream
			OutputStream os = socket.getOutputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));

			// logging Remote Host IP Address & Port
			InetSocketAddress inetSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			consoleLog("connected from " + inetSocketAddress.getAddress().getHostAddress() + ":"
					+ inetSocketAddress.getPort());

			String request = null;
			while (true) {
				// 요청 읽기
				String line = br.readLine();

				// 브라우저가 연결을 끊으면
				if (line == null) {
					break;
				}

				// request header만 읽음
				if ("".equals(line)) {
					break;
				}

				// 첫 번째 라인만 처리
				if (request == null) {
					request = line;
					break;
				}

			}
			// [RequestHandler#13] GET / HTTP/1.1
			// [RequestHandler#14] GET /favicon.ico HTTP/1.1
			// 요청 처리
			String[] tokens = request.split(" ");
			if ("GET".equals(tokens[0])) {
				consoleLog("request: " + tokens[1]);
				responseStaticResource(os, tokens[1], tokens[2]);

			} else {// methods: put,post,delet,head,connect
				/* Simple Http server에서는 무시 */
				// 작성
				consoleLog("request: "+tokens[1]);
				response400Error(os, tokens[1], tokens[2]);
				// 응답 예시
				// HTTP/1.1 400 Bad Request\r\n
				// Content-Type:text/html; charset=utf-8\r\n
				// \r\n
				// HTML 에러 문서 (./webapp/error/400.html)

			}

			// 예제 응답입니다.
			// 서버 시작과 테스트를 마친 후, 주석 처리 합니다.
			// os.write("HTTP/1.1 200 OK\r\n".getBytes("UTF-8"));
			// os.write("Content-Type:text/html; charset=utf-8\r\n".getBytes("UTF-8"));
			// os.write("\r\n".getBytes());
			// os.write("<h1>이 페이지가 잘 보이면 실습과제 SimpleHttpServer를 시작할 준비가 된
			// 것입니다.</h1>".getBytes("UTF-8"));

		} catch (Exception ex) {
			consoleLog("error:" + ex);
		} finally {
			// clean-up
			try {
				if (socket != null && socket.isClosed() == false) {
					socket.close();
				}

			} catch (IOException ex) {
				consoleLog("error:" + ex);
			}
		}
	}

	private void response400Error(OutputStream os, String url, String protocol) throws IOException {
		url = "/error/400.html";
		File file = new File(DOCUMENTROOT + url);

		// n(ew)io
		byte[] body;
		body = Files.readAllBytes(file.toPath());
		String contentType = Files.probeContentType(file.toPath());
		os.write((protocol + " 400 Bad Request\r\n").getBytes("UTF-8"));
		os.write(("Content-Type:" + contentType + "; charset=utf-8\r\n").getBytes("UTF-8"));
		os.write("\r\n".getBytes());
		os.write(body);

	}

	private void responseStaticResource(OutputStream os, String url, String protocol) throws IOException {
		// welcome file set
		if ("/".equals(url)) {
			url = "./index.html";
		}

		File file = new File(DOCUMENTROOT + url);
		if (file.exists() == false) {
			// 응답 예시
			// HTTP/1.1 404 File Not Found\r\n
			// Content-Type:text/html; charset=utf-8\r\n
			// \r\n
			// HTML 에러 문서 (./webapp/error/404.html)
			// 404응답
			
			//response404Error(os, url, protocol);
			System.out.println("file not found:"+file.getAbsolutePath());
			return;
		}

		// n(ew)io
		byte[] body = Files.readAllBytes(file.toPath());
		String contentType = Files.probeContentType(file.toPath());
		os.write((protocol + " 200 OK\r\n").getBytes("UTF-8"));
		os.write(("Content-Type:" + contentType + "; charset=utf-8\r\n").getBytes("UTF-8"));
		os.write("\r\n".getBytes());
		os.write(body);
	}

	private void response404Error(OutputStream os, String url, String protocol) throws IOException{
		url = "/error/404.html";
		File file = new File(DOCUMENTROOT + url);

		// n(ew)io
		byte[] body;
		body = Files.readAllBytes(file.toPath());
		String contentType = Files.probeContentType(file.toPath());
		os.write((protocol + " 404 File Not Found\r\n").getBytes("UTF-8"));
		os.write(("Content-Type:" + contentType + "; charset=utf-8\r\n").getBytes("UTF-8"));
		os.write("\r\n".getBytes());
		os.write(body);
		
	}

	public void consoleLog(String message) {
		// thread를 상속받아서 getId()
		System.out.println("[RequestHandler#" + getId() + "] " + message);
	}
}
