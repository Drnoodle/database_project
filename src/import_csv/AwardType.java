package import_csv;

import java.sql.Connection;
import java.sql.SQLException;

import utils.CsvFile;

public class AwardType extends AbstractImport{

	public AwardType(Connection conn) throws SQLException {
		super(CsvFile.AWARD_TYPES, conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addBatch(String[] row) throws SQLException {

		this.setStringInInsert(1, row[0]);
		this.setStringInInsert(2, row[1]);
		this.setStringInInsert(3, row[2]);
		this.setStringInInsert(4, row[4]);
		this.setStringInInsert(5, row[5]);
		this.setStringInInsert(6, row[6]);
		
		String isPoll = "Y";
		if(row[7].equals("No")){
			isPoll="N";
		}
			
		this.setStringInInsert(7, isPoll);
		
		String isNonGenre = "Y";
		if(row[8].equals("No")){
			isNonGenre="N";
		}
		
		this.setStringInInsert(8, isNonGenre);
		this.setStringInInsert(9, row[3]);
		this.insert.addBatch();
		
	}

	@Override
	protected String getSqlRequest() {

		return new StringBuilder()
				.append("INSERT INTO award_type ")
				.append("(ID_AWARD_TYPE,CODE_NUM,NAME,BY_NAME,FOR_NAME,SHORT_NAME,POLL,NON_GENRE,ID_NOTE)")
				.append(" VALUES (?,?,?,?,?,?,?,?,?);")
				.toString();
		
	}

}
