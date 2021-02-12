package es.ewic.frontend.util;

public class StringUtils {

	public static String stringToUnicode(String originalString) {

		System.out.println();
		System.out.println();
		System.out.println(originalString);
		System.out.println();

		originalString = originalString.replaceAll("á", "\u00e1");
		originalString = originalString.replaceAll("é", "\u00e9");
		originalString = originalString.replaceAll("í", "\u00ed");
		originalString = originalString.replaceAll("ó", "\u00f3");
		originalString = originalString.replaceAll("ú", "\u00fa");
		originalString = originalString.replaceAll("Á", "\u00c1");
		originalString = originalString.replaceAll("É", "\u00c9");
		originalString = originalString.replaceAll("Í", "\u00cd");
		originalString = originalString.replaceAll("Ó", "\u00d3");
		originalString = originalString.replaceAll("Ú", "\u00da");
		originalString = originalString.replaceAll("ñ", "\u00f1");
		originalString = originalString.replaceAll("Ñ", "\u00d1");

		return originalString;

	}
}
