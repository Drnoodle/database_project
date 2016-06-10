package import_csv;

import utils.CsvFile;
import utils.TitleType;

import java.sql.Connection;
import java.sql.SQLException;

public class Title extends AbstractImport{

	public Title(Connection conn) throws SQLException {
		super(CsvFile.TITLES, conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addBatch(String[] row) throws SQLException {

		this.setStringInInsert(1, row[0]);

        if(row[1].length() > 150){
            row[1] = row[1].substring(0,150);
        }
		this.setStringInInsert(2, row[1]);

        if(row[7].length() > 10){
            row[7] =  "";
        }

		this.setStringInInsert(3, row[7]);
		this.setStringInInsert(4, row[10]);
		this.setStringInInsert(5, row[4]);
		this.setStringInInsert(6, row[3]);
	
		TitleType type = TitleType.get(row[8]);
		this.setStringInInsert(7, ""+type.ordinal());
		
		String serieNumber = row[6].replaceAll("[^\\p{N}]", "");
		
		this.setStringInInsert(8, serieNumber);
		
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
				.append("(?,?,?,?,?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE ID_TITLE=ID_TITLE;")
				.toString();
	}	

}
