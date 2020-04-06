import java.util.*;
import java.io.*;
import java.util.Map.Entry;

public class Movies {
	
	public final int total = 10000;
	public int tcount;
	public int movie_id;
	public int year;
	public String title;
	public String genre;
	

	public Movies [] movies = new Movies [total];
	public String [] genreList = new String [tcount];
	
	//HashMap for genres
	Map <String, Integer> genreTable = new HashMap<>();
	
	public Movies () {
		
		movie_id = 0;
		year = 0;
		title = "";
		genre = "";
	}
	
	public Movies (int mi, int y, String t, String g) {
		
		year = y;
		movie_id = mi;
		title = t;
		genre = g;
	}
	
	public int totalMovies () {
		
		return movies.length;
	}
	
	public int readData (Scanner sc, PrintStream ps) throws Exception {
		
		String line;
		int count = 0;
		int y = 0;
		
		while (sc.hasNext()) {
			
			Movies mov = new Movies();
			
			line = sc.nextLine();
			String [] tokens = line.split (",");
			
			mov.setMovie_id (Integer.parseInt (tokens [0]));
			mov.setTitle (tokens [1]);
			mov.setGenre (tokens [2]);
			movies [count] = mov;
			
			// Extracting the movie years
			int stringLength = movies [count].getTitle().length();
			int lastIndex = movies [count].getTitle().lastIndexOf ( ")", stringLength - 1);
			int firstIndex = movies [count].getTitle().indexOf ("(", lastIndex - 5);
			y = Integer.parseInt (movies [count].getTitle().substring (firstIndex + 1, lastIndex));
			
			movies [count].setYear (y);
			count++;
			
			//ps.printf ("%d %s \t %s \n", movies [count - 1].getMovie_id(), movies [count - 1].getTitle(), movies [count - 1].getGenre());
		}
		return tcount = count;
	}
	
	public void getMovie (int key) {
		
		System.out.printf ("%s \n %s", movies [key].getTitle(), 
									   movies [key].getGenre());
						    
	}
	
	public void sortMovies (PrintStream ps) {
		
			String temp;
			boolean swapped = true;
			int pass = 0;
			do {
			
				swapped = false;
				pass++;
				for (int j = 0; j < tcount - pass; j++) {
					if (movies[j].getTitle().toLowerCase().charAt(0) > movies[j + 1].getTitle().toLowerCase().charAt(0)) {
						swapped = true;
						temp = movies [j].getTitle();
						movies [j].setTitle (movies [j + 1].getTitle());
						movies [j + 1].setTitle (temp);
					}
				}
			} while (swapped);
			
			for (int i = 0; i < tcount; i++) {
				
				ps.println (movies [i].getTitle());
			}
		
	}
	
	public void subsetList (String movie1, String movie2, PrintStream ps) {
		
		Movies [] mov = new Movies [total];
		// method to get a subset of the list ordered or unordered
		int start = 0;
		int end = 0;
		for (int i = 0; i < tcount; i++) {
			
			mov = movies;
			
			if (mov [i].getTitle().equalsIgnoreCase (movie1)) {
				
				start = i;
			}
				
			if (mov [i].getTitle().equalsIgnoreCase (movie2)) {
				
				end = i;
			}
		}
					
		if (start < end) {
						
			while (start <= end) {			
				ps.println (mov [start].getTitle());
				start++;
			}
			
		} else if (end < start) {
						
			while (end <= start) {
				ps.println (mov [end].getTitle());
				end++;
					}
				}
			}

	public int getYear (int count) {
		
		int stringLength = movies [count].getTitle().length();
		int lastIndex = movies [count].getTitle().lastIndexOf ( ")", stringLength - 1);
		int firstIndex = movies [count].getTitle().indexOf ("(", lastIndex - 5);
		
		int year = Integer.parseInt (movies [count].getTitle().substring (firstIndex + 1, lastIndex));
		return year;
	}
	
	public void parseGenres () {
		
		String tempGenre;
	
		for (int x = 0; x < tcount; x++) {
			
			tempGenre = movies [x].getGenre();
			String [] tempArr = tempGenre.split (" ");
			
			//for-each loop
			for (String g : tempArr) {
				
				Integer count = genreTable.get (g);
				
				if (count == null) {
					genreTable.put (g, 1);
				} else {
					genreTable.put (g, ++count);
				}
			}
		}
		
		genreTable.remove ("(no");
		genreTable.remove ("genres");
		genreTable.remove ("listed)");
		genreTable.remove ("");
	}
	
	public void getGenres (int year, PrintStream ps) {
		
		String tempGenre;
		int [] years = new int [tcount];
		int maxYear = 0;
		int minYear;
		int [] scan = new int [tcount];
		
		Map <Integer, ArrayList<String>> genreMapYears = new HashMap<>();
		ArrayList <String> genres = new ArrayList <String>();
		
		
		for (int i = 0; i < tcount; i++) {
			
			tempGenre = movies [i].getGenre();
			
			if (!tempGenre.equals("(no genres listed)")) {
				
				String [] tempArr = tempGenre.split (" ");
			
				years [i] = movies [i].getYear();
			
				for (int j = 0 ; j < tempArr.length; j++) {
					
					genres.add (tempArr [j]);
					genreMapYears.put (years [i], genres);
					scan [j]++;
				}
			
				maxYear = years [0];
			
				if (years [i] > maxYear) {
					maxYear = years [i] + 1;
				}
			}
		}
		
		ps.println ("Years of each genre release: " + genreMapYears.keySet());
	}
	public void getGenres (PrintStream ps) {
		
		int totalGenres = 0;
		
		String [] genres = {"Action", "Adventure", "Animation", "Horror", "Crime", "Comedy", "Thriller", "Drama", "Musical", "Children", 
							"Documentary", "Sci-Fi", "Western", "Fantasy", "Mystery", "Romance", "War","Film-Noir", "IMAX" };
		
		ps.println ("Movies Per Genre");
		for (int j = 0; j < genres.length; j++) {
			
			ps.printf ("%s: %d \n", genres[j], genreTable.get (genres [j]));
		}
		
		for (int i = 0; i < genres.length; i++) {
			
			int trace = genreTable.get (genres[i]);
			totalGenres = totalGenres + trace;
		}
		
		ps.println ("Total Genres: " + totalGenres);
	}
	
	public int getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Movies[] getMovies() {
		return movies;
	}

	public void setMovies(Movies[] movies) {
		this.movies = movies;
	}
	
	public int getYear () {
		return year;
	}
	
	public void setYear (int year) {
		this.year = year;
	}
	
	

}
