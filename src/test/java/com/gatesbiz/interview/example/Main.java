package com.gatesbiz.interview.example;

import org.hsqldb.server.Server;
import org.hsqldb.util.DatabaseManagerSwing;

public class Main {

	public static void main(String[] args) {

		// start the server
		Server.main(new String[] {
				"--database.0", "file:db/example", "--dbname.0", "example"
		});

		// start the GUI manager
		DatabaseManagerSwing.main(new String[] {
				"--url", "jdbc:hsqldb:hsql://localhost/example"
		});
	}
}
