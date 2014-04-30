package it.lucatogno.servercasa.manager.ssh;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

@Service
public class SSHConnector {

	private JSch jsch;

	private Session session;
	private ChannelShell channel;

	private Logger logger = LogManager.getLogger(getClass());

	public String connect() {
		jsch = new JSch();

		String user = "root";
		String host = "localhost";
		int port = 22;

		try {
			session = jsch.getSession(user, host, port);
			/**
			 * Setto la classe UserInfo per recupare la password dell'utente
			 */
			UserInfo ui = new SshUserInfo();
			
			session.setUserInfo(ui);

			/**
			 * Eseguo la connessione della sessione
			 */
			session.connect();

			/**
			 * Creo il channel shell
			 */
			channel = (ChannelShell) session.openChannel("shell");
		} catch (JSchException e) {
			String errorCode = "connection.failure";
			logger.error(errorCode, e);
			return errorCode;
		}

		return "connected";
	}

	public String disconnect() {
		return "disconnected";
	}

	public String receive() {
		/**
		 * Associo stdin e stdout
		 */
		channel.setInputStream(System.in);

		/**
		 * per Windows
		 * 
		 * channel.setInputStream(new FilterInputStream(System.in) { public
		 * int read(byte[] b, int off, int len) throws IOException { return
		 * in.read(b, off, (len > 1024 ? 1024 : len)); } });
		 */
		channel.setOutputStream(System.out);

		/**
		 * Eseguo la connessione con un timeout di 3 secondi
		 */
		try {
			channel.connect(3000);
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "nuovo mex";
	}

	public void send(String command) {
		OutputStream commandAsStream = null;
		try {
			OutputStreamWriter stream = new OutputStreamWriter(commandAsStream, command);
			channel.setOutputStream(commandAsStream);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
