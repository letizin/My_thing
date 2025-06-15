package dising.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import dising.DisingVO;
import dising.HashMapDisingDAO;

public class ObjFileHashMapDisingDAO extends HashMapDisingDAO implements FileDisingDB{

	private String dataFilename = DATE_FILE + ".obj";
	
	public ObjFileHashMapDisingDAO() {
		loadDisings();
	}
	
	@Override
	public void saveDisings() {
		try(
			FileOutputStream fos = new FileOutputStream(dataFilename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
		){
			oos.writeObject(disingDB);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void loadDisings() {
		try(
				FileInputStream fis = new FileInputStream(dataFilename);
				ObjectInputStream ois = new ObjectInputStream(fis);
		){
			disingDB = (Map<Integer, DisingVO>)ois.readObject();
			disingSeq = Collections.max(disingDB.keySet()) + 1;
		}catch(FileNotFoundException e) {
			System.out.println("[DB로딩] " + dataFilename + "가 없습니다.");
		}catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean CreateDising(DisingVO date) {
		boolean result = super.CreateDising(date);
		if(result) saveDisings();
		return result;
	}

	@Override
	public boolean UpdateDising(DisingVO date) {
		boolean result = super.UpdateDising(date);
		if(result) saveDisings();
		return result;
	}

	@Override
	public boolean deleteDising(String date) {
		boolean result = super.deleteDising(date);
		if(result) saveDisings();
		return result;
	}

}
