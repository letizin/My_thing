package dising;

import java.util.List;

public interface DisingService {
	boolean registDising(DisingVO date);
	List<DisingVO> listDising();
	DisingVO detailDisingInfo(String dsNm);
	DisingVO selectDising(String dsNm, String userNm);
	boolean updateDisingShare(String dsNm, boolean change, String userNm);
	boolean updateDisingName(String dsNm, String chNm, String userNm);
	boolean removeDising(String dsNm);
	boolean removeDising(String dsNm, String userNm);
}
