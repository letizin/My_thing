package dising;

import java.util.*;

public interface DisingDAO {
	boolean CreateDising(DisingVO date);
	DisingVO SelectDising(String date);
	DisingVO SelectDising(String date, String userNm);
	List<DisingVO> SelectAllDising();
	boolean UpdateDising(DisingVO date);
	boolean deleteDising(String date);
	boolean deleteDising(String date, String userNm);
}
