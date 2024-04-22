package _SteGraMageCore;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PNGConverter implements Converter {

	static final int ALPHA = 24;
	static final int RED = 16;
	static final int GREEN = 8;
	static final int BLUE = 0;
	static final int MASK = 0x000000ff;
	
	private BufferedImage _image;
	
	@Override
	public byte[] channelToBytes () {
		byte[] ret = new byte[_image.getHeight() * _image.getWidth() * 4];
		int cont_b = 0;
		
		for (int i = 0; i < _image.getWidth(); i++) {
			for (int j = 0; j < _image.getHeight(); j++) {
				int pixel = _image.getRGB(i, j);
				ret[cont_b] =  extractByte(pixel, ALPHA);
				ret[cont_b + 1] = extractByte(pixel, RED);
				ret[cont_b + 2] = extractByte(pixel, GREEN);
				ret[cont_b + 3] = extractByte(pixel, BLUE);
				cont_b += 4;
			}
		}
		
		return ret;
	}
	
	byte extractByte(int pixel, int shift)
	{
		pixel = (pixel >>> shift) & MASK;
		return (byte) pixel;
	}
	
	@Override
	public void bytesToChannel (byte[] channel) {
		BufferedImage ret = new BufferedImage(_image.getWidth(), _image.getHeight(), _image.getType());
		int cont_pos = 0;
		for (int i = 0; i < ret.getWidth(); i++) {
			for (int j = 0; j < ret.getHeight(); j++) {
				int alpha = channel[cont_pos];
				int red = channel[cont_pos + 1];
				int green = channel[cont_pos + 2];
				int blue = channel[cont_pos + 3];
				int pixel = joinRGB(alpha, red, green, blue);
				
				ret.setRGB(i, j, pixel);
				cont_pos += 4;
			}
		}
		
		_image = ret;
	}
	
	private int joinRGB(int alpha, int red, int green, int blue) 
	{
		return ((alpha << ALPHA) + (red << RED) + (green << GREEN) + blue);
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
