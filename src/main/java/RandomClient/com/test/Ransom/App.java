package RandomClient.com.test.Ransom;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        try {

    		Client client = Client.create();

    		WebResource webResource = client
    		   .resource("https://www.random.org/integers/?num=10&min=1&max=6&col=1&base=10&format=plain&rnd=new");

    		ClientResponse response = webResource.accept("application/json")
                       .get(ClientResponse.class);

    		if (response.getStatus() != 200) {
    		   throw new RuntimeException("Failed : HTTP error code : "
    			+ response.getStatus());
    		}

    		String output = response.getEntity(String.class);

    		System.out.println("Output from Server .... \n");
    		System.out.println(output);

    	  } catch (Exception e) {

    		e.printStackTrace();

    	  }

        
        
    }
}
