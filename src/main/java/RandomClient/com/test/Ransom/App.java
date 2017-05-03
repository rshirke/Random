package RandomClient.com.test.Ransom;

import java.awt.image.BufferedImage;
import java.security.GeneralSecurityException;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

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
	public static void main( String[] args ) throws Exception
	{

		createImage();  //this will create an random image and show it on java applet JFrame.  

		keyPairGenerator(); //this will generate the private key pair of the array which was returned from the server

	}

	private static void keyPairGenerator() throws GeneralSecurityException
	{
		String output = null;
		try {
			//used Jersey client to call the REST API call 
			Client client = Client.create();

			WebResource webResource = client
					.resource("https://www.random.org/integers/?num=9&min=1&max=10&col=1&base=10&format=plain&rnd=new");

			ClientResponse response = webResource.accept("application/json")
					.get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			output = response.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}

		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(512);

		byte[] publicKey = output.getBytes();
		// keyGen.genKeyPair().getPublic().getEncoded();
		StringBuffer retString = new StringBuffer();
		for (int i = 0; i < publicKey.length; ++i) {
			retString.append(Integer.toHexString(0x0100 + (publicKey[i] & 0x00FF)).substring(1));
		}
		System.out.println("Encrypted value is :" + retString);
	}

	private static void createImage()
	{

		int arr []= new int[100];
		try {
			//used Jersey client to call the REST API call 
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
			
			//creating array string from output of plain text from server
			for(int i=0; i< arrString.length;i++)
			{
				arr[i] = Integer.parseInt(arrString[i]);
			}
			System.out.println("Output from Server .... \n");
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}

		
		//this will create an image and show it in JFrame
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
