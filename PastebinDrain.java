package com.sinfonier.drains;

/*
* PasteBinDrain
* Drain diseñado para dumpear el contenido deseado en pastebin.com.
*
* Este drain utiliza la API oficial de pastebin para publicar el contenido. 
* Los campos requeridos en la petición son:
* api_option : Este valor debe ser la cadena de texto "paste" para publicar contenido.
* api_user_key :
* api_paste_private : Visibilidad del paste. Puede ser público y privado
* api_paste_name : Titulo del paste
* api_paste_expire_data : Fecha de expiracion del pastebin
* api_paste_format : Lenguaje
* api_dev_key :
* api_paste_code : Contenido del paste
* 
*/
public class PasteBinDrain  extends BaseSinfonierDrain {
	private String apiKey;
	private String visibility;
	private String expirationTime;
	private String pasteFormat;
	private final String API_URI = "http://pastebin.com/api/api_post.php";
//api_option=paste&api_user_key='.$api_user_key.'&api_paste_private='.$api_paste_private.'&api_paste_name='.$api_paste_name.'&api_paste_expire_date='.$api_paste_expire_date.'&api_paste_format='.$api_paste_format.'&api_dev_key='.$api_dev_key.'&api_paste_code='.$api_paste_code.'');
	
	public (String xmlFile) {
        	super(xmlFile);
	}

    	@Override
	public void userprepare() {
        	this.apiKey = (String)this.getParam("api_key");
		this.visibility = (String)this.getParam("visibility");
		this.expirationTime = (String)this.getParam("expiration");
		this.pasteFormat = (String)this.getParam("format");
    	}

    	@Override
	public void userexecute() {
		this.pasteContent = this.getRawJson();
		submitPaste();
    	}

    	public void usercleanup() {
    	}

	/*
	*  Esta es la función que manda el contenido a la API de pastebin.com. 
	*  Por default se utilizan los siguientes valores 
	*/
	public Boolean submitPastebin() {
		ClientConfig config = new DefaultClientConfig();
        	Client client = Client.create(config);
        	
		WebResource service = client.resource(UriBuilder.fromUri(API_URI).build());
        
		Form form = new Form();
        	form.add("api_option", "paste");
        	form.add("api_user_key", apiKey);
        	form.add("api_paste_private", apiKey);
        	form.add("api_paste_name", p);
		form.add("api_paste_expire_date", expirationTime);
        	form.add("api_paste_format", pasteFormat);
		form.add("api_dev_key", apikey);
        	form.add("api_paste_code", pasteContent);

		ClientResponse response = service.path("restPath").path("resourcePath").
        	type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, form);
        
		System.out.println("Response " + response.getEntity(String.class));
	}
}
