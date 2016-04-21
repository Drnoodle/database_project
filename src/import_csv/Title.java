package import_csv;

import java.sql.SQLException;

import data_access.DBConnection;
import utils.CsvFile;
import utils.TitleType;

public class Title extends AbstractImport{

	public Title(DBConnection conn) throws SQLException {
		super(CsvFile.TITLES, conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addBatch(String[] row) throws SQLException {

		this.setStringInInsert(1, row[0]);
		this.setStringInInsert(2, row[1]);
		this.setStringInInsert(3, row[7]);
		this.setStringInInsert(4, row[10]);
		this.setStringInInsert(5, row[4]);
		this.setStringInInsert(6, row[3]);
	
		TitleType type = TitleType.get(row[8]);
		this.setStringInInsert(7, ""+type.ordinal());
		this.setStringInInsert(8, row[6]);
		this.setStringInInsert(9, row[5]);
		
		String isGraphic;
		
		if(row[11].equals("NO")){
			isGraphic = "N";
		}
		else {
			isGraphic = "Y";
		}
		
		this.setStringInInsert(10, isGraphic);
		
		String idOrigin = "";
		if(row[9] != "0"){
			idOrigin = row[9];
		}
		this.setStringInInsert(11, idOrigin);
		
		String transl = "";
		
		String[] languageAndTrans = row[2].split(",");
		if(languageAndTrans.length == 2){
			transl = languageAndTrans[1];
		}
		this.setStringInInsert(12, transl);
		
		
		this.insert.addBatch();
	}

	@Override
	protected String getSqlRequest() {

		  
		  
		return new StringBuilder()
				.append("INSERT INTO title ")
				.append("(ID_TITLE,TITLE,STORY_LENGTH,")
				.append("ID_LANGUAGE,ID_NOTE,ID_SYNOPSIS,TYPE,SERIE_NUMBER,")
				.append("ID_SERIE,IS_GRAPHICS,ID_ORIGINAL,TRANSLATOR_NAME)")
				.append(" VALUES ")
				.append("(?,?,?,?,?,?,?,?,?,?,?,?);")
				.toString();
	}	

}
