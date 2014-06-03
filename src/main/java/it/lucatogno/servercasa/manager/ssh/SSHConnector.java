package it.lucatogno.servercasa.manager.ssh;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.connection.channel.direct.Session.Command;

import org.springframework.stereotype.Service;

@Service
public class SSHConnector {

	public String sendCommand(String command) throws IOException {

		SSHClient client = new SSHClient();
		String toReturn = "";
		client.loadKnownHosts();
		client.connect("localhost");
		try {
			client.authPassword("root", "bicefalo");
			Session session = client.startSession();
			try {
				Command cmd = session.exec(command);
				cmd.join(1, TimeUnit.SECONDS);
				toReturn = cmd.getExitErrorMessage();
			} finally {
				session.close();
			}
		} finally {
			client.disconnect();
			client.close();
		}
		return toReturn;
	}

}
