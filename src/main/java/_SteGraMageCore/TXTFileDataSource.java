package _SteGraMageCore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TXTFileDataSource implements DataSource<String> {
	
	File _openedFile;

	@Override
	public String read(String path) {
		String ret ="";
		try {
			_openedFile = new File(path);
			FileReader fr = new FileReader(_openedFile);
			BufferedReader br = new BufferedReader(fr);
			StringBuilder sb = new StringBuilder();
			String line;
			while (( line = br.readLine()) != null) {
                sb.append(line);
            }
			
			ret = sb.toString();
			br.close();
		}
		catch (IOException e) {
			System.out.println("Error: " + e);
		}
		return ret;
	}

	@Override
	public void write(String data) {
		String path = _openedFile.getPath();
		path = path.replaceFirst("\\.txt", "_out.txt");
		try {
			File f = new File(path);  //output file path
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			bw.write(data);
			bw.close();
		}
		catch (IOException e) {
			System.out.println("Error: " + e);
		}
		
	}

}
