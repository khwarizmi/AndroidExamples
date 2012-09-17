package Model;

public class ImageAndText {

	private String imageUrl;
	private String text;
	
	public ImageAndText(String imageUrl, String text)
	{
		this.imageUrl = imageUrl;
		this.text = text;
	}
	
	public String getImageUrl()
	{
		return this.imageUrl;
	}
	public String getText()
	{
		return this.text;
	}
	
}
