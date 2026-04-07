/**

        * File: Problem Set Unit 3: Email Validator and Parser

        * Author: Naren Nades

        * Date Created: March 27, 2026

        * Date Last Modified: April 7, 2026

        */

import java.util.Scanner;

public class ProblemSet {

	public static void main(String args[]) {
		Scanner s = new Scanner(System.in);
		
		System.out.print("Input two emails: "); // asks user for two emails
		String email = s.nextLine(); // collects the input
		email = email.trim();
		if (!email.contains(",") || email.indexOf(",")==0 || email.indexOf(",")==email.length() || !(email.indexOf(",") == email.lastIndexOf(","))) { // checks if the format of the input is correct 
			System.out.println("ERROR: Input must contain a comma seperating two emails");
		}
		else { // if format is correct, it calls the function status to check if it is a valid or invalid email
			String firstEmail = email.substring(0, email.indexOf(",")).trim();
			String secondEmail = email.substring(email.indexOf(",") + 1).trim();

			System.out.println(firstEmail+": "+status(firstEmail)); // the output
			System.out.println(secondEmail+": "+status(secondEmail)); // the output
		}
		s.close();
	}


	public static String local(String email) { // a function the creates of substring of the local part of the email
		return email.substring(0, email.indexOf("@"));
	}
	public static String domain(String email) { // a function the creates of substring of the domain part of the email
		return email.substring(email.indexOf("@") + 1);
	}
	public static String domainExtension(String email) { // a function the creates of substring of the domain extension part of the email
		return email.substring(email.lastIndexOf(".") + 1);
	}


	public static String status(String email) { // a function that goes through the rules and regulations that make a email valid or invalid
		if (!email.contains("@")) { // if the email doesn't have an @
			return ("Invalid: Missing @");
		}
		if (email.indexOf("@") != email.lastIndexOf("@")) { // if there are multiple @
			return ("Invalid: Multiple @");
		}
		if (email.startsWith(".") || email.endsWith(".")) { // if there are dots at the start or end of the email
			return ("Invalid: Starts or ends with dot");
		}
		if (email.contains(" ")) { // if the email contains any spaces
			return ("Invalid: Contains spaces");
		}
		if (local(email).length() < 1) { // if the local part of the email has the amount of characters less than 1
			return ("Invalid: Local part too short");
		}
		if (local(email).length() > 64) { // if the local part of the email has the amount of characters greater than 64
			return ("Invalid: Local part too long");
		}
		if (!domain(email).contains(".")) { // if the domain doesn't contain a dot
			return ("Invalid: No dot in domain");
		}
		if (!(domainExtension(email).length()>=2 && domainExtension(email).length()<=6)) { // if the domain extension doesn't have the amount of characters in between and equal to 2 and 6
			return ("Invalid: Invalid domain extension length");
		}

		// Exceptions and Edge Cases

		if (domain(email).contains("+") || domain(email).contains("_")) { // if the domain contains a + or a _
			return ("Invalid: Domain contains + or _");
		}
		if (email.startsWith("+") || email.endsWith("+") || email.startsWith("_") || email.endsWith("_")) { // if the email starts or ends with + or _
			return ("Invalid: Starts or ends with + or _");
		}
		if ((domain(email).equalsIgnoreCase("gmail.com"))) { // if the domain of the email equals to gmail.com
			String normalizedGmail = local(email).replace(".","");
			normalizedGmail = normalizedGmail.replace("_", "");
			normalizedGmail = normalizedGmail.replace("+", "");
			normalizedGmail = normalizedGmail+"@"+domain(email); // creates a normalized version of the email (the local part has no dots, + or _)
			return "Valid (Gmail normalized) | Local: " + local(email) + " | Domain: " + domain(email); // if it passes through all these regulations and has a domain that equals to gmail.com it is considered a valid email that has been gmail normalized
		}
		return "Valid | Local: " + local(email) + " | Domain: " + domain(email); // if it passes through all these regulations it is considered a valid email
	}
}
