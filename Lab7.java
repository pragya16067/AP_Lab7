//NAME : PRAGYA PRAKASH
//ROLL NO. : 2016067

package Lab7Code;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Song implements Serializable{
	private String SongName;
	private String SingerName;
	private double Duration;
	
	public Song(String n, String sn, double d)
	{
		SongName = n;
		SingerName = sn;
		Duration = d;
	}
	
	@Override
	public String toString() {
		return ("Name of song is "+SongName + ", Name of Singer is " + SingerName + ", Duration of song is " + Duration);
	}
	
	public String getSongName()
	{
		return this.SongName;
	}
	
	public	boolean	equals(Object	o)	{
		
		if(o!=null	&&	getClass()==o.getClass())
		{	
				Song s = (Song)	o;	
				return	((SongName.equals(s.SongName)) && ( SingerName.equals(s.SingerName)) && (Duration == s.Duration));	
		}	
		else
		{
			return false;
		}
			
}	

	
}


public class Lab7 {
	
	public static void Serialize(String filename, ArrayList<Song> songs) throws IOException {
		filename= "./src/PlayLists/"+filename+".txt";
		ObjectOutputStream out=null;
		try
		{
			out= new ObjectOutputStream(new FileOutputStream(filename));
			for(Song s : songs)
			{
				out.writeObject(s);
			}
		}
		finally
		{
			out.close();
		}
		
	}
	
	public static ArrayList<Song> Deserialize(String filename) throws IOException, ClassNotFoundException {
		filename= "./src/PlayLists/"+filename+".txt";
		ArrayList<Song> songs=new ArrayList<Song> ();
		ObjectInputStream in=null;
		try
		{
			in = new ObjectInputStream(new FileInputStream(filename));
			Song s=(Song) in.readObject();
			while(s != null)
			{
				songs.add(s);
				//System.out.println(s);
				s=(Song) in.readObject();
			}
		}
		catch(EOFException e)
		{
			//The End Of File has been reached
		}
		finally
		{
			in.close();
		}
		return songs;
		
	}
	
	public static void CreateSampleInput() throws IOException{
		ArrayList<Song> songs = new ArrayList<Song> ();
		
		//Creating file PlayList1
		Song s1=new Song("Tum se hi", "Mohit Chauhan", 3.53);
		songs.add(s1);
		Song s2=new Song("Closer","Chainsmokers", 2.88);
		songs.add(s2);
		Serialize("PlayList1" , songs);
		songs=new ArrayList<Song> ();
		
		//Creating file PlayList2
		s1=new Song("Let It Go", "Idina Menzel", 4.22);
		songs.add(s1);
		s2=new Song("Do Re Mi","James Walker Chorus", 6.52);
		songs.add(s2);
		Song s3=new Song("Fixer Upper","Rober Lopez", 3.28);
		songs.add(s3);
		Serialize("PlayList2" , songs);
		songs=new ArrayList<Song> ();
		
		
		//Creating file PlayList3
		s1=new Song("Lean On", "Major Lazer", 2.55);
		songs.add(s1);
		s2=new Song("Rozaana","Shreya Ghoshal", 3.789);
		songs.add(s2);
		s3=new Song("Jab Tak","Armaan Mallik", 2.267);
		songs.add(s3);
		Song s4=new Song("Perfect strangers","Jonas Blue", 3.16);
		songs.add(s4);
		Serialize("PlayList3" , songs);
		songs=new ArrayList<Song> ();
		
		return;
	}
	

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Reader.init(System.in);
		
		//Create a sample input space
		CreateSampleInput();
		
		System.out.println("The various PlayLists are :");
		//code to display names of all files in a directory
		File folder=new File("./src/PlayLists");
		File[] Playlists = folder.listFiles();
		for(File f : Playlists)
		{
			System.out.println(f.getName());
		}
		
		System.out.println("Choose one of the above");
		String filename=Reader.reader.readLine();
		ArrayList<Song> s=new ArrayList<Song> ();
		
		s = Deserialize(filename);
		
		int ch=0;
		while(ch!=6)
		{
			
			s = Deserialize(filename);
			
			System.out.println("There are " + s.size() + " songs in this PlayList");
			
			System.out.println("THE MENU STARTS");
			System.out.println("1. Add Song");
			System.out.println("2. Delete Song");
			System.out.println("3. Search Song");
			System.out.println("4. Show All Songs");
			System.out.println("5. Back to Start");
			System.out.println("6. Exit");
			
			ch=Reader.nextInt();
			
			if(ch==1)
			{
				//Song details
				String Songnm = Reader.reader.readLine();
				String Singnm = Reader.reader.readLine();
				double len = Reader.nextDouble();
				
				Song NewSong = new Song(Songnm, Singnm, len);
				
				//Code to add a song in the chosen PlayList
				s=Deserialize(filename);
				s.add(NewSong);
				Serialize(filename, s);
				
				System.out.println("The new number of songs are " + s.size());
				
			}
			
			else if(ch==2)
			{
				//Song Name
				String Songnm = Reader.reader.readLine();
				
				//Code to Delete a song in the chosen PlayList
				s=Deserialize(filename);
				
				int found=0;
				for(Song song : s)
				{
					if(song.getSongName().equals(Songnm))
					{
						s.remove(song);
						found=1;
						break;
					}
				}
				
				Serialize(filename, s);
				
				if(found==0)
				{
					System.out.println("Error : Cannot find any such song in this Playlist");
				}
				else
				{
					System.out.println("The new number of songs are " + s.size());
				}
					
			}
			
			else if(ch==3)
			{
				//Song Name
				String Songnm = Reader.reader.readLine();
				
				//Code to search for a song in the chosen PlayList
				s=Deserialize(filename);
				
				int found=0;
				for(Song song : s)
				{
					if(song.getSongName().equals(Songnm))
					{
						System.out.println(song);
						found=1;
						break;
					}
				}
				
				Serialize(filename, s);
				
				if(found==0)
				{
					System.out.println("Error : Cannot find any such song in this Playlist");
				}
			
			}
			
			else if(ch==4)
			{
				//Display all songs in a playlist
				s=Deserialize(filename);
				
				if(s.size()==0)
				{
					System.out.println("No Songs Exist");
				}
				
				for(Song song : s)
				{
					System.out.println(song);
				}
				
				Serialize(filename, s);
			}
			
			else if(ch==5)
			{
				System.out.println("The various PlayLists are :");
				//code to display names of all files in a directory
				folder=new File("./src/PlayLists");
				Playlists = folder.listFiles();
				for(File f : Playlists)
				{
					System.out.println(f.getName());
				}
				
				System.out.println("Choose one of the above");
				filename = Reader.reader.readLine();
				continue;
			}
			else
			{
				break;
			}
		}
		
	}

}

/** Class for buffered reading from Input Stream */
class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /** call this method to initialize reader for InputStream */
    static void init(InputStream input) {
        reader = new BufferedReader(
                     new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    /** get next word */
    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                   reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }
    
    static float nextFloat() throws IOException {
        return Float.parseFloat( next() );
    }
    
    static long nextLong() throws IOException {
        return Long.parseLong( next() );
    }
	
    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
}

/** Class for buffered reading from Files */
class ReaderFromFile {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /** call this method to initialize reader for InputStream 
     * @throws FileNotFoundException */
    static void init(String input) throws FileNotFoundException {
        reader = new BufferedReader(
                     new FileReader(input) );
        tokenizer = new StringTokenizer("");
    }

    /** get next word */
    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                   reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }
    
    static float nextFloat() throws IOException {
        return Float.parseFloat( next() );
    }
    
    static long nextLong() throws IOException {
        return Long.parseLong( next() );
    }
	
    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
}

