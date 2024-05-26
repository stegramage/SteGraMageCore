package _SteGraMageCore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MokingConverter implements Converter {

	private String _channel;
	
		
	@Override
	public int[] channelToIntegers() {
		int[] ret = new int[_channel.length()];
		for (int i = 0; i < _channel.length(); i++) {
			ret[i] = (int) _channel.charAt(i);
		}
		return ret;
	}

	@Override
	public void integersToChannel(int[] channel) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < channel.length; i++) {
			sb.append(Character.toChars(channel[i]));
		}
		_channel = sb.toString();
	}

	@Override
	public void openChannel(String path) {
		try {
			FileReader fr = new FileReader(new File(path));
			BufferedReader br = new BufferedReader(fr);
			StringBuilder sb = new StringBuilder();
			String line;
			while (( line = br.readLine()) != null) {
                sb.append(line);
            }
			
			_channel = sb.toString();
			br.close();
		}
		catch (IOException e) {
			System.out.println("Error: " + e);
		}

	}

	@Override
	public void saveChannel(String path) {
		path = path.replaceFirst("\\.txt", "_out.txt");
		try {
			File f = new File(path);  //output file path
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			bw.write(_channel);
			bw.close();
		}
		catch (IOException e) {
			System.out.println("Error: " + e);
		}
	}

}
