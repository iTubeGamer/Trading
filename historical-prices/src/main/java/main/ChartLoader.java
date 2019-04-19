package main;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class ChartLoader {
	private static final String BASE_URL_SEARCH = "https://www.comdirect.de/inf/search/all.html?SEARCH_VALUE=";
	private static final String BASE_URL_LOAD = "https://www.comdirect.de/inf/kursdaten/historic.csv?INTERVALL=16&DATETIME_TZ_END_RANGE_FORMATED=19.04.2019&OFFSET=";
	private static final String BASE_URL_LOAD_2 = "&WITH_EARNINGS=false&DATETIME_TZ_START_RANGE_FORMATED=18.04.2014&ID_NOTATION=";
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");

	private static final String OUTPUT_FOLDER = "data\\";
	
	public static void main(String[] args) throws ParseException {
		
		Options options = new Options();
		HelpFormatter formatter = new HelpFormatter();
	     
	
		Option wknOption = Option.builder().longOpt("wknList").argName("wkns").hasArg().desc("provide comma-seperated list of wkns").build();
		Option startOption = Option.builder().longOpt("startDate").argName("start").hasArg().desc("provide start date for prices to load (YYYY-MM-DD)").build();		
		options.addOption(wknOption);
		options.addOption(startOption);
		options.addOption("h", false, "show help");
		
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = parser.parse( options, args);
		
		if(cmd.hasOption('h') || cmd.getOptions().length == 0) {
			formatter.printHelp("CLITester", options);
		}

		if(cmd.hasOption("wknList") && cmd.hasOption("startDate")) {
			try {
				String wknsString = (String) cmd.getParsedOptionValue("wknList");
				String startDateString = (String) cmd.getParsedOptionValue("startDate");
				LocalDate startDate = LocalDate.parse(startDateString);
				startLoadingPrices(wknsString, startDate);    
				
			} catch (ParseException e) {
				System.out.println("Error in arguments. " + e.getMessage());
				formatter.printHelp("CLITester", options);
				System.exit(1);
			}
			
		}	
	}


	private static void startLoadingPrices(String wknsString, LocalDate startDate) {
		//get Wkns from argument
		String[] wkns = wknsString.split(",");
		
		//for each wkn
		for (int i = 0; i < wkns.length; i++) {
			//get idNotation for wkn
			String idNotation = getIdNotationFromWkn(wkns[i]);
			
			//load, merge and save CSV file
			saveCSVFile(wkns[i], idNotation, startDate);
		}
	}


	
	private static void saveCSVFile(String wkn, String idNotation, LocalDate startDate) {
		

			//delete file if existent
			String filename = OUTPUT_FOLDER + wkn + ".csv";
			new File(filename).delete();
			
			//load batches of csv files to merge
			int i = 0;
			boolean endReached = false;	
			while(!endReached) {
				try {
					//build url to load file
					String url = BASE_URL_LOAD + i + BASE_URL_LOAD_2 + idNotation;
					BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
					BufferedReader br = new BufferedReader(new InputStreamReader(in));
					FileWriter fw = new FileWriter(filename,true);
					
					//read lines from stream, filter and append to file
					String line;
					String last = "";
					
					int count = 0;
					while ((line = br.readLine()) != null) 
				    {
						//skip header and empty lines
						if (count >= 3 && !line.isEmpty()) {
							
							//check if specified date is reached (double because of weekends)
							LocalDate lineDate = LocalDate.parse(line.subSequence(1, 11), dtf);
							if(lineDate.isBefore(startDate)) {
								endReached = true;
								break;
							}
							
							//write line
							fw.write(line + "\n");
							last = line;
							
							
							if(lineDate.equals(startDate)) {
								endReached = true;
								break;
							}
							
						}	
				        count ++;
				    }
					fw.flush();
				    fw.close();
				    
					i++;
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("Loading CSV file for " + wkn + " failed.");
					break;
				}
			}
			
			System.out.println("Finished loading prices for WKN " + wkn);	
}
	


	private static String getIdNotationFromWkn(String wkn) {
		 try {
			//build url 
			String url = BASE_URL_SEARCH + wkn;
			
			//open http connection
			URL obj = new URL(url);
			
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setConnectTimeout(5000);
			con.setRequestMethod("GET");		
			con.setInstanceFollowRedirects(false);
			con.addRequestProperty("User-Agent", "Mozilla/5.0");	
			
			//if response is redirection
			int status = con.getResponseCode();
			if(status == HttpURLConnection.HTTP_MOVED_TEMP) {
				//extract id_notation from redirect url
				String redirectUrl = con.getHeaderField("Location");
				String idNotation = redirectUrl.substring(59, redirectUrl.indexOf('&'));					
				
				return idNotation;
			} else {
				System.out.println("Response for URL '" + url + "' was no redirection. Response Code: " + status);	
			}
		
		 } catch (Exception e) {
			e.printStackTrace();
		 }	
		 return null;
	}

}

