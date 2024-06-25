package _SteGraMageCore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ChannelConverter implements Converter {

	private String _channel;
	
		
	@Override
	public char[] channelToIntegers() {
		return _channel.toCharArray();
	}

	@Override
	public void integersToChannel(char[] channel) {
		_channel = String.copyValueOf(channel);
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
