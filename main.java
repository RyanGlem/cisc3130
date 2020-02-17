import java.util.*;
import java.io.*;

// Ryan Glemaud
public class main {

	public static void main(String[] args) throws Exception {
		
		File topCharts = new File ("Global-Charts.txt");
		PrintStream dFile = new PrintStream (new File ("TopStreamers.txt"));
		Scanner cFile = new Scanner (topCharts);
		
		StreamingArtists [] streams = new StreamingArtists [201];
		
		readData (cFile, streams);
		displayStreamers (streams);
		sortAlphabetical (streams, dFile);
		displayStreamers (streams);
		cFile.close();
		dFile.close();
	}
	
	public static void readData (Scanner sc, StreamingArtists [] sa) throws Exception {
		
		String line;
		int count = 0;
		
		while (sc.hasNext()) {
			
			StreamingArtists strArt = new StreamingArtists();
			
			line = sc.nextLine();
			String [] tokens = line.split (",");
			
			strArt.setPosition (Integer.parseInt (tokens [0]));
			strArt.setTrackName (tokens [1]);
			strArt.setArtist (tokens [2]);
			strArt.setStreams (Integer.parseInt (tokens [3]));
			strArt.setUrl (tokens [4]);
			sa [count] = strArt;
			count++;
		}
	}
	
	public static void sortAlphabetical (StreamingArtists [] sa, PrintStream ps) {
		
		StreamingArtists [] streamers = new StreamingArtists [201];
		streamers = sa;
		
		for (int i = 0; i < sa.length - 1; i++) {
			
			char temp = streamers[i].getArtist().toLowerCase().charAt(0);
			String artist = streamers[i].getArtist();
			int j = i;
			
			while (j > 0 && streamers[j - 1].getArtist().toLowerCase().charAt(0) > temp) {
				
				if (streamers[j - 1].getArtist().toLowerCase().charAt(0) != streamers[j].getArtist().toLowerCase().charAt(0)) {
				
					streamers [j].setArtist (streamers [j - 1].getArtist());
				} 
				
				j--;

			}
			streamers[j].setArtist (artist); 
		}
		
		for (int i = 0; i < sa.length - 1; i++) {
			
			ps.println (streamers[i].getArtist());
		}
	}
	
	public static void displayStreamers (StreamingArtists [] sa) {
		
		for (int i = 0; i < sa.length - 1; i++) {
			
			System.out.printf("%d \t %-70s %30s \t\t %d %70s %n", sa[i].getPosition(), sa[i].getTrackName(), sa[i].getArtist(), sa[i].getStreams(), sa[i].getUrl());
			
		}
	}
}
