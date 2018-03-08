package org.hdivsamples.bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;

public class FileUntrustedParent {

	String username;

	public FileUntrustedParent() {
		System.out.println("ConstructorParent");

		// Add 10 minutes to date
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, 10);
		Date teenMinutesFromNow = now.getTime();

		try {
			Runtime rt = Runtime.getRuntime();

			String[] command = new String[] { "sudo", "date", "-s", teenMinutesFromNow.toString() };

			// Not working: String command = "sudo date -s \'" + teenMinutesFromNow.toString() + "\"";
			System.out.println(command);
			Process proc = rt.exec(command);

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

			// read the output from the command
			System.out.println("Here is the standard output of the command:\n");
			String s = null;
			while ((s = stdInput.readLine()) != null) {
				System.out.println(s);
			}

			// read any errors from the attempted command
			System.out.println("Here is the standard error of the command (if any):\n");
			while ((s = stdError.readLine()) != null) {
				System.out.println(s);
			}
			int exitVal = proc.waitFor();

			System.out.println("Process exitValue: " + exitVal);

		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public FileUntrustedParent(final String username) {
		this.username = username;
	}

	@Override
	public String toString() {

		return "This is: " + username;

	}
}
