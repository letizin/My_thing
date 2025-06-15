package dising;

import java.util.List;

public class NJDisingService implements DisingService{

	private DisingDAO disingDAO;
	
	public NJDisingService(DisingDAO disingDAO) {
		this.disingDAO = disingDAO;
	}
	
	@Override
	public boolean registDising(DisingVO date) {
		return disingDAO.CreateDising(date);
	}

	@Override
	public List<DisingVO> listDising() {
		return disingDAO.SelectAllDising();
	}

	@Override
	public DisingVO detailDisingInfo(String dsNm) {
		return disingDAO.SelectDising(dsNm);
	}

	@Override
	public boolean updateDisingShare(String dsNm, boolean change, String userNm) {
		DisingVO ds = disingDAO.SelectDising(dsNm);
		
		if(ds != null) {
			if(ds.getCreateUser().equals(userNm)) {
				ds.setShare(change);
				disingDAO.UpdateDising(ds);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean updateDisingName(String dsNm, String chNm, String userNm) {
		DisingVO ds = disingDAO.SelectDising(dsNm);
		
		if(ds != null) {
			if(ds.getCreateUser().equals(userNm)) {
				ds.setDisingName(chNm);
				disingDAO.UpdateDising(ds);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean removeDising(String dsNm) {
		return disingDAO.deleteDising(dsNm);
	}

	@Override
	public DisingVO selectDising(String dsNm, String userNm) {
		return disingDAO.SelectDising(dsNm, userNm);
	}

	@Override
	public boolean removeDising(String dsNm, String userNm) {
		return disingDAO.deleteDising(dsNm, userNm);
	}
}
