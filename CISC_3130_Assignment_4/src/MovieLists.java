import java.util.*;
import java.io.*;

public class MovieLists {

	public static void main(String[] args) throws Exception {
		
		File movies = new File ("movies.txt");
		PrintStream dFile = new PrintStream (new File ("movielist.txt"));
		Scanner cFile = new Scanner (movies);
		
		Movies m = new Movies ();
		
		// dFile.println ("Unsorted Movies");
		m.readData (cFile, dFile);
		dFile.println ();
		dFile.println ();
		dFile.println ();
		
		/*
		dFile.println ("Subset Unsorted Movies");
		m.subsetList ("Headshot (2011)", "Inbetweeners 2 The (2014)", dFile);
		dFile.println ();
		dFile.println ();
		dFile.println ();
		
		dFile.println ("Sorted Movies");
		m.sortMovies (dFile);
		dFile.println ();
		dFile.println ();
		dFile.println ();
		dFile.println ("Subset Sorted Movies");
		m.subsetList ("Headshot (2011)", "Inbetweeners 2 The (2014)", dFile); */
		
		m.parseGenres();
		m.getGenres (dFile);
		m.getGenres (5, dFile);
		
		dFile.close();
		cFile.close ();
	}
	
}
