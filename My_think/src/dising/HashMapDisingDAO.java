package dising;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapDisingDAO implements DisingDAO{

	protected Map<Integer, DisingVO> disingDB = new HashMap<>();
	protected int disingSeq = 111;
	
	@Override
	public boolean CreateDising(DisingVO date) {
		for (Integer key : disingDB.keySet()) {
		    if(disingDB.get(key).getDisingName().equals(date.getDisingName())) {
		    	return false;
		    }
		}
		
		date.setDisingNo(disingSeq++);
		date.setRegdate(new Date());
		disingDB.put(date.getDisingNo(), date);
		return true;
	}

	@Override
	public DisingVO SelectDising(String disingName) {
		DisingVO setDi = null;
		for (Integer key : disingDB.keySet()) {
		    if(disingDB.get(key).getDisingName().equals(disingName)) {
		    	setDi = disingDB.get(key);
		    }
		}
		
	    if (setDi == null) {
	        System.out.println("디자인 정보를 찾을 수 없습니다: " + disingName);
	        return null;
	    }

	    return setDi;
	}

	@Override
	public List<DisingVO> SelectAllDising() {
		return new ArrayList<>(disingDB.values());
	}

	@Override
	public boolean UpdateDising(DisingVO date) {
		disingDB.put(date.getDisingNo(), date);
		return false;
	}

	@Override
	public boolean deleteDising(String date) {
		return disingDB.remove(date) != null;
	}

	@Override
	public DisingVO SelectDising(String date, String userNm) {
		DisingVO setDi = null;
		for (Integer key : disingDB.keySet()) {
		    if(disingDB.get(key).getDisingName().equals(date)) {
		    	if(disingDB.get(key).getCreateUser().equals(userNm)) {
			    	setDi = disingDB.get(key);
		    	}
		    }
		}
		
	    if (setDi == null) {
	        System.out.println("디자인 정보를 찾을 수 없습니다: " + date);
	        return null;
	    }

	    return setDi;
	}

	@Override
	public boolean deleteDising(String date, String userNm) {
		
		DisingVO setDi = null;
		for (Integer key : disingDB.keySet()) {
		    if(disingDB.get(key).getDisingName().equals(date)) {
		    	if(disingDB.get(key).getCreateUser().equals(userNm)) {
			    	setDi = disingDB.get(key);
		    	}
		    }
		}
		
	    if (setDi == null) {
	        return false;
	    }
	    
		return disingDB.remove(setDi.getDisingNo()) != null;
	}

}
