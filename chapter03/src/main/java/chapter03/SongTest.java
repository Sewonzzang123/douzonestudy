package chapter03;

public class SongTest {

	public static void main(String[] args) {
		Song song = new Song();
		
		song.setTitle("좋은날");
		song.setArtist("아이유");
		song.setAlbum("Real");
		song.setYear(2010);
		song.setTrack(3);
		song.setComposer("이민수");
		
		song.show();
		
		Song song2= new Song("롤린","브레이브걸스");
		song2.show();

	}

}
