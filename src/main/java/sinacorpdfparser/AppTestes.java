package sinacorpdfparser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class AppTestes {
	
	public static void main(String args[]) throws ParseException {
		
		DateTimeFormatter fIn = DateTimeFormatter.ofPattern( "dd/MM/uuuu" ); 
		LocalDate ld = LocalDate.parse( "10/07/1987" , fIn );
		
		DateTimeFormatter fOut = DateTimeFormatter.ofPattern( "uuuu-MM-dd" );
		String output = ld.format( fOut );
		
		System.out.println(output);
	}

}
