package com.exciting.utils;


public class ChangeTEXT {
	public static String ToHTML(String str) {

		String returnStr = str;

		returnStr = returnStr.replaceAll(">", "&gt;");

		returnStr = returnStr.replaceAll("<", "&lt;");

		returnStr = returnStr.replaceAll("\r\n", "<br>");

		returnStr = returnStr.replaceAll("\n", "<br>");

		// returnStr = returnStr.replaceAll("", "&quot;");

		returnStr = returnStr.replaceAll(" ", "&nbsp;");

		returnStr = returnStr.replaceAll("&", "&amp;");

		return returnStr;

	}

	public static String ToJAVA(String str) {

		String returnStr = str;

		returnStr = returnStr.replaceAll("<br>", "\r\n");

		returnStr = returnStr.replaceAll("&gt;", ">");

		returnStr = returnStr.replaceAll("&lt;", "<");

//	    returnStr = returnStr.replaceAll("&quot;", "");

		returnStr = returnStr.replaceAll("&nbsp;", " ");

		returnStr = returnStr.replaceAll("&amp;", "&");

		return returnStr;

	}

	public static String ToTextarea(String str) {

		String returnStr = str;

<<<<<<< HEAD

=======
>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816
		returnStr = returnStr.replaceAll("&gt;", ">");

		returnStr = returnStr.replaceAll("&lt;", "<");

//	    returnStr = returnStr.replaceAll("&quot;", "");

		returnStr = returnStr.replaceAll("&nbsp;", " ");

		returnStr = returnStr.replaceAll("&amp;", "&");

<<<<<<< HEAD
		returnStr = returnStr.replaceAll("<br>", "\r\n");

=======
>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816
		return returnStr;

	}
}
