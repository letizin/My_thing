package dising;

import java.util.LinkedList;
import java.util.List;

public class ListDisingDAO implements DisingDAO{

	private List<DisingVO> disingList = new LinkedList<DisingVO>();
	private int disingSeq = 111;
	
	@Override
	public boolean CreateDising(DisingVO date) {
		date.setDisingNo(disingSeq++);
		System.out.println(date.toString());
		disingList.add(date);
		return true;
	}

	@Override
	public DisingVO SelectDising(String date) {
		for(DisingVO ds : disingList) {
			if (ds.getDisingName().equals(date)) {
				if(ds.isShare()) {
					return ds;
				}
			}
		}
		return null;
	}

	@Override
	public List<DisingVO> SelectAllDising() {
		return disingList;
	}

	@Override
	public boolean UpdateDising(DisingVO date) {
		for(int i = 0; i < disingList.size(); i++) {
			if(disingList.get(i).getDisingNo() == date.getDisingNo()) {
				disingList.set(i, date);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deleteDising(String date) {
		for(DisingVO ds : disingList) {
			if(ds.getDisingName().equals(date)) {
				int dsNo = ds.getDisingNo();
				disingList.remove(dsNo);
				return true;
			}
		}
		return false;
	}

	@Override
	public DisingVO SelectDising(String date, String userNm) {
		for(DisingVO ds : disingList) {
			if (ds.getDisingName().equals(date)) {
				if(ds.isShare()) {
					if(ds.getCreateUser().equals(userNm)) {
						return ds;
					}
				}
			}
		}
		return null;
	}

	@Override
	public boolean deleteDising(String date, String userNm) {
		for(DisingVO ds : disingList) {
			if(ds.getDisingName().equals(date)) {
				if(ds.getCreateUser().equals(userNm)) {
					int dsNo = ds.getDisingNo();
					disingList.remove(dsNo);
					return true;
				}
			}
		}
		return false;
	}

}
