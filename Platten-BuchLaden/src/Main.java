
public class Main {

	public static void main(String[] args) {
		
		//Main functions	
//		CreateMediaTypes.createNewMediaTypeFile("Vinyls");
//		CreateMediaTypes.createNewMediaTypeFile("Animes");
//		CreateMediaTypes.createNewMediaTypeFile("Games");
//		CreateMediaTypes.createNewMediaTypeFile("CDs");
		
		CreateMediaTypes.createMediaItems("Anime1", "Animes", "Naruto", "Idunnowhomadeit", 60);
		
		System.out.println(CreateMediaTypes.returnListOfAllExistingMediaTypes());

	}

}
