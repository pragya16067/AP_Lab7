//NAME : PRAGYA PRAKASH
//ROLL NO. : 2016067

package Lab7Code;

import static org.junit.Assert.*;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.junit.Test;

public class ThirdTest {
	
	@Test
	public void TestDelete() throws IOException, ClassNotFoundException
	{
		ArrayList<Song> s=new ArrayList<Song> ();
		Song s1=new Song("Lean On", "Major Lazer", 2.55);
		s.add(s1);
		Song s2=new Song("Rozaana","Shreya Ghoshal", 3.789);
		s.add(s2);
		Song s3=new Song("Jab Tak","Armaan Mallik", 2.267);
		s.add(s3);
		Song s4=new Song("Perfect strangers","Jonas Blue", 3.16);
		s.add(s4);
		Serialize("PlayListForTest3" , s);
		
		String delname="Ikk Vaari Aa";
		
		//Checking if this name can be found
		ArrayList<Song> newList = Deserialize("PlayListForTest3");
		int found=0;
		for(Song song : newList)
		{
			if(song.getSongName().equals(delname))
			{
				newList.remove(song);
				found=1;
				break;
			}
		}
		
		assertEquals(found,0);
	}
	
	
	
	
	
	
	
	
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



}
