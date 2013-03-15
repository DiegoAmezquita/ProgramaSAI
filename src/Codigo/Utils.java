package Codigo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Utils {

	static FileHandler fileTxt;
	static SimpleFormatter formatterTxt;

	static FileHandler fileHTML;
	static Formatter formatterHTML;

	private final static Logger LOGGER = Logger.getLogger("ProgramaSAI");

	static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private static boolean setupOK = false;

	static boolean debugging = false;

	public static void setup() {

		try {
			if (debugging) {
				logger.setLevel(Level.INFO);
				fileTxt = new FileHandler(getDate() + ".txt");

				fileHTML = new FileHandler(getDate() + ".html");

				// Create txt Formatter
				formatterTxt = new SimpleFormatter();
				fileTxt.setFormatter(formatterTxt);
				logger.addHandler(fileTxt);

				// Create HTML Formatter
				formatterHTML = new MyHtmlFormatter();
				fileHTML.setFormatter(formatterHTML);
				logger.addHandler(fileHTML);
				setupOK = true;
			}

		} catch (Exception e) {
			writeLog(e);
		}
	}

	public static void writeLog(Exception e) {
		if (debugging) {
			if (!setupOK) {
				setup();
			}
			StackTraceElement[] stack = e.getStackTrace();
			String theTrace = "";
			for (StackTraceElement line : stack) {
				theTrace += line.toString();
			}
			logger.warning(theTrace);
		}
	}

	public static String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date date = new Date();
		String dateToReturn = dateFormat.format(date);
		return dateToReturn;
	}

}
