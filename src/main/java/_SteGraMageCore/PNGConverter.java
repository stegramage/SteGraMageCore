package _SteGraMageCore;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PNGConverter implements Converter {

	static final int RED = 16;
	static final int GREEN = 8;
	static final int BLUE = 0;
	static final int MASK = 0x000000ff;
	
	private BufferedImage _image;
	
	@Override
	public int[] channelToIntegers () {
		int[] ret = new int[_image.getHeight() * _image.getWidth() * 3];
		int cont_b = 0;
		
		for (int i = 0; i < _image.getWidth(); i++) {
			for (int j = 0; j < _image.getHeight(); j++) {
				int pixel = _image.getRGB(i, j);
				ret[cont_b + 0] = extractByte(pixel, RED);
				ret[cont_b + 1] = extractByte(pixel, GREEN);
				ret[cont_b + 2] = extractByte(pixel, BLUE);
				cont_b += 3;
			}
		}
		
		return ret;
	}
	
	int extractByte(int pixel, int shift)
	{
		pixel = (pixel >>> shift) & MASK;
		return pixel;
	}
	
	@Override
	public void integersToChannel (int[] channel) {
		BufferedImage ret = new BufferedImage(_image.getWidth(), _image.getHeight(), _image.getType());
		int cont_pos = 0;
		for (int i = 0; i < ret.getWidth(); i++) {
			for (int j = 0; j < ret.getHeight(); j++) {
				int red = channel[cont_pos + 0];
				int green = channel[cont_pos + 1];
				int blue = channel[cont_pos + 2];
				int pixel = joinRGB(red, green, blue);
				
				ret.setRGB(i, j, pixel);
				cont_pos += 3;
			}
		}
		
		_image = ret;
	}
	
	private int joinRGB(int red, int green, int blue) 
	{
		return ((red << RED) + (green << GREEN) + blue) ^ 0xff000000;
	}
	
	@Override
	public void openChannel(String path) {
		try {
			_image = ImageIO.read(new File(path));
			//System.out.println("Carga completa.");
		}
		catch (IOException e) {
			System.out.println("No se encontro la imagen.");
		}		
	}
	
	@Override
	public void saveChannel(String path) {
		path = path.replaceFirst("\\.png", "_out.png");
		try {
			File f = new File(path);  //output file path
			System.out.println(ImageIO.write(_image, "png", f) ? "Imagen guardada." : "Hubo un error al guardar la imagen.");
		}
		catch (IOException e) {
			System.out.println("Error: " + e);
		}
	}
}
