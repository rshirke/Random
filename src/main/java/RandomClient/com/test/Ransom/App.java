package RandomClient.com.test.Ransom;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
//        System.out.println( "Hello World!" );
//        try {
//
//    		Client client = Client.create();
//
//    		WebResource webResource = client
//    		   .resource("https://www.random.org/integers/?num=10&min=1&max=6&col=1&base=10&format=plain&rnd=new");
//
//    		ClientResponse response = webResource.accept("application/json")
//                       .get(ClientResponse.class);
//
//    		if (response.getStatus() != 200) {
//    		   throw new RuntimeException("Failed : HTTP error code : "
//    			+ response.getStatus());
//    		}
//
//    		String output = response.getEntity(String.class);
//
//    		System.out.println("Output from Server .... \n");
//    		System.out.println(output);
//
//    	  } catch (Exception e) {
//
//    		e.printStackTrace();
//
//    	  }
    	
  	createImage();

    }
    
    private static void createImage()
    {
    	

    	int arr []= new int[100];
    	try {

    		Client client = Client.create();

    		WebResource webResource = client
    		   .resource("https://www.random.org/integers/?num=9&min=1&max=10&col=1&base=10&format=plain&rnd=new");

    		ClientResponse response = webResource.accept("application/json")
                       .get(ClientResponse.class);

    		if (response.getStatus() != 200) {
    		   throw new RuntimeException("Failed : HTTP error code : "
    			+ response.getStatus());
    		}

    		String output = response.getEntity(String.class);

    		String [] arrString= output.split("\n");
    		
    		for(int i=0; i< arrString.length;i++)
    		{
    		 arr[i] = Integer.parseInt(arrString[i]);
    		}
    		System.out.println("Output from Server .... \n");
    		System.out.println(output);

    	  } catch (Exception e) {

    		e.printStackTrace();

    	  }

    	BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
    	img.getRaster().setPixels(0, 0, 3, 3, arr);

    	JLabel jLabel = new JLabel(new ImageIcon(img));

    	JPanel jPanel = new JPanel();
    	jPanel.add(jLabel);
    	JFrame r = new JFrame();
    	r.add(jPanel);
    	r.show();
    }
}
