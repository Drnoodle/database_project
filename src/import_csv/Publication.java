package import_csv;

import java.sql.Connection;
import java.sql.SQLException;

import utils.CsvFile;
import utils.PublicationType;

public class Publication extends AbstractImport{

	public Publication(Connection conn) throws SQLException {
		super(CsvFile.PUBLICATIONS, conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addBatch(String[] row) throws SQLException {

		this.setStringInInsert(1, row[0]);
		this.setStringInInsert(2, row[1]);
		this.setStringInInsert(3, row[2]);
		
		String pages = row[4].replaceAll("[^\\p{N}]", "");

		if(Integer.parseInt(pages)==0){
			pages = "";
		}

		this.setStringInInsert(4, pages);
		
		this.setStringInInsert(5, row[7]);
		this.setStringInInsert(6, row[8]);
		String price = row[9].replaceAll("[^\\p{N}^.]", "");
		
		if(price.length() <= 0){
			price = "\\N";
		}
		
		if(price.length() >4){
			price = price.substring(0,4);
		}
		
		String curr = row[9].replaceAll("[\\p{N}.]", "");
		this.setStringInInsert(7, price);
		this.setStringInInsert(8, curr);
		
		PublicationType type = PublicationType.get(row[6]);
		this.setStringInInsert(9, ""+type.ordinal());
		
		this.setStringInInsert(10, row[5]);
		this.setStringInInsert(11, row[12]);
		this.setStringInInsert(12, row[11]);
		this.setStringInInsert(13, row[10]);
		this.setStringInInsert(14, row[3]);
		
		
		this.insert.addBatch();
	}

	@Override
	protected String getSqlRequest() {

		return new StringBuilder()
				.append("INSERT INTO publication ")
				.append("(ID_PUBLICATION, TITLE,DATE,PAGES,ISBN,IMG_LINK,")
				.append("PRICE,CURRENCY,TYPE,PACKAGING,")
				.append("SERIE_NUMBER,ID_PUBLICATION_SERIE,ID_NOTE,ID_PUBLISHER)")
				.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);")
				.toString();
	}

	
	
}
