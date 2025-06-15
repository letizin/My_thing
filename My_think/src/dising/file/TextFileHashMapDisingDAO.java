package dising.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dising.DisingVO;
import dising.HashMapDisingDAO;

public class TextFileHashMapDisingDAO extends HashMapDisingDAO implements FileDisingDB{

	private String dataFilename = DATE_FILE + ".txt";
	private final String DATE_FORMAT = "YYYY-MM-dd HH:mm:ss";
	
	@Override
	public void saveDisings() {
		
		try(
			FileWriter fw = new FileWriter(dataFilename);
			PrintWriter pw = new PrintWriter(fw);	
		) {
			for(DisingVO ds : disingDB.values()) {
				pw.println(ds.getDisingNo());
				pw.println(ds.getDisingName());
				pw.println(ds.getCreateUser());
				pw.println(ds.isShare());
				pw.println(ds.getRegdate());
				
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
				pw.println(sdf.format(ds.getRegdate()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void loadDisings(){
		
		try(
			FileReader fr = new FileReader(dataFilename);
			BufferedReader br = new BufferedReader(fr);
		){
			while(br.ready()){
				int DisingNo = Integer.parseInt(br.readLine());
				String DisingName = br.readLine().strip();
				String CreateUser = br.readLine().strip();
				
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
				Date regdate = sdf.parse(br.readLine());
				
				disingDB.put(DisingNo, new DisingVO(DisingNo, DisingName, CreateUser, true, regdate));
				
				if(disingSeq <= DisingNo)
					disingSeq = DisingNo + 1;
			}
			
		}catch(FileNotFoundException e) {
			System.out.println("[로딩] " + dataFilename + "이 없습니다.");
		}catch(IOException | ParseException e) {
			e.printStackTrace();
		}
	}

}
