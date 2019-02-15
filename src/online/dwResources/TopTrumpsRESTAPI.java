package online.dwResources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import online.configuration.TopTrumpsJSONConfiguration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import commandline.Card;

@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands
 * to the Web page.
 * 
 * Below are provided some sample methods that illustrate how to create
 * REST API methods in Dropwizard. You will need to replace these with
 * methods that allow a TopTrumps game to be controled from a Web page.
 */
public class TopTrumpsRESTAPI {

	/** A Jackson Object writer. It allows us to turn Java objects
	 * into JSON strings easily. */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	List<String> cardList  = new ArrayList<String>();
	List<String> shuffledCardList  = new ArrayList<String>();
	/**
	 * Contructor method for the REST API. This is called first. It provides
	 * a TopTrumpsJSONConfiguration from which you can get the location of
	 * the deck file and the number of AI players.
	 * @param conf
	 * @throws JsonProcessingException 
	 */
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) throws JsonProcessingException {
		// ----------------------------------------------------
		// Add relevant initalization here
		// ----------------------------------------------------
//		Card card = new Card();
//		oWriter.writeValueAsString(card.getDeck());
	}
	
	// ----------------------------------------------------
	// Add relevant API methods here
	// ----------------------------------------------------
	
	@GET
	@Path("/helloJSONList")
	/**
	 * Here is an example of a simple REST get request that returns a String.
	 * We also illustrate here how we can convert Java objects to JSON strings.
	 * @return - List of words as JSON
	 * @throws IOException
	 */
	public String helloJSONList() throws IOException {
		Card card = new Card();
		card.setAttNames();
		List<String> listOfWords = new ArrayList<String>();
		listOfWords.add(card.getCat1Name());
		listOfWords.add(card.getCat2Name());
		listOfWords.add(card.getCat3Name());
		listOfWords.add(card.getCat4Name());
		listOfWords.add(card.getCat2Name());
		
		// We can turn arbatory Java objects directly into JSON strings using
		// Jackson seralization, assuming that the Java objects are not too complex.
		String listAsJSONString = oWriter.writeValueAsString(listOfWords); 
		
		return listAsJSONString;
	}
	
	@GET
	@Path("/cardsasstrings")
	public List<String> cardsAsStrings() throws IOException {
		Card card = new Card();
		String card1 = oWriter.writeValueAsString(""+card.getDeck().get(0));
		String card2 = oWriter.writeValueAsString("" +card.getDeck().get(1));
		String card3 = oWriter.writeValueAsString("" +card.getDeck().get(2));
		String card4 = oWriter.writeValueAsString("" +card.getDeck().get(3));
		String card5 = oWriter.writeValueAsString("" +card.getDeck().get(4));
		String card6 = oWriter.writeValueAsString("" +card.getDeck().get(5));
		String card7 = oWriter.writeValueAsString("" +card.getDeck().get(6));
		String card8 = oWriter.writeValueAsString("" +card.getDeck().get(7));
		String card9 = oWriter.writeValueAsString("" +card.getDeck().get(8));
		String card10 = oWriter.writeValueAsString("" +card.getDeck().get(9));
		String card11 = oWriter.writeValueAsString("" +card.getDeck().get(10));
		String card12 = oWriter.writeValueAsString("" +card.getDeck().get(11));
		String card13 = oWriter.writeValueAsString("" +card.getDeck().get(12));
		for (int  i = 0; i<3;  i ++) {
			cardList.add(card1);
			cardList.add(card2);
			cardList.add(card3);
			cardList.add(card4);
			cardList.add(card5);
			cardList.add(card6);
			cardList.add(card7);
			cardList.add(card8);
			cardList.add(card9);
			cardList.add(card10);
			cardList.add(card11);
			cardList.add(card12);
			cardList.add(card13);
		}
		cardList.add(card2);
		return cardList;
	}
	@GET
	@Path("/shuffled")
	public List<String> shuffledDeck() throws IOException {
		Collections.shuffle(cardsAsStrings());
		shuffledCardList = cardList;
		return shuffledCardList;
	}
	
	@GET
	@Path("/readd")
	public String readd() throws IOException{
		Card card = new Card();
		String stringCard = oWriter.writeValueAsString(card.getDeck().toString());
		return stringCard;
	}
	
	@GET
	@Path("/test")
	public String readFirstCard() throws IOException {
			Card card = new Card();
			card.getDeck();
			String complete = ("I have got past the getDeck() method! The deck is as follows: " + card.getDeck());
//								"\n The second card is: " + card.getDeck().get(1));
			return complete;
		
	}
	
	@GET
	@Path("/helloWord")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public String helloWord(@QueryParam("Word") String Word) throws IOException {
		return "Hello "+Word;
	}
	
	@GET
	@Path("/game")
	/**
	 * 
	 */
	public String getCard() throws JsonProcessingException {
		Card c = new Card();
//		ArrayList<Card> deck = new ArrayList<Card>();
//		c.setAttNames();
//		deck = c.getDeck();
		c.setCat1Name("Speed");
		System.out.println(c.getCat1Name());
		String name = oWriter.writeValueAsString(c.getCat1Name());
//		String test = "please work JSON!!";
//		c.setC1(5);
//		int testInt = c.getC1();
//		Card card = deck.get(1);
//		String cardString = oWriter.writeValueAsString(card);
		return name;
	}
	
	public String test() throws JsonProcessingException {
		Card card = new Card("test", 2, 2, 2, 2, 2);
		String stringCard = oWriter.writeValueAsString(card.toString());
		return stringCard;
	}
	
	
}