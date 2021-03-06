package import_csv;

import java.sql.Connection;
import java.sql.SQLException;

import utils.CsvFile;

public class AwardCategory extends AbstractImport{

	public AwardCategory(Connection conn) throws SQLException {
		super(CsvFile.AWARD_CATEGORIES, conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addBatch(String[] row) throws SQLException {
		
		try {
			Integer.parseInt(row[2]);
		} catch(NumberFormatException e){
			return;
		}
		
		
		this.setStringInInsert(1,row[1]);
		this.setStringInInsert(2,row[3]);
		this.setStringInInsert(3,row[2]);	
		this.insert.addBatch();
		
		
	}

	@Override
	protected String getSqlRequest() {

		return new StringBuilder()
				.append("INSERT INTO award_category ")
				.append("(NAME,ORDER_NUM,ID_AWARD_TYPE)")
				.append(" VALUES (?,?,?);")
				.toString();
		
	}

}
