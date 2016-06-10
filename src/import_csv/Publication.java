package import_csv;

import utils.CsvFile;
import utils.PublicationType;

import java.sql.Connection;
import java.sql.SQLException;

public class Publication extends AbstractImport{

	public Publication(Connection conn) throws SQLException {
		super(CsvFile.PUBLICATIONS, conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addBatch(String[] row) throws SQLException {

		if(row.length != 13){
			return;
		}
		this.setStringInInsert(1, row[0]);

        if(row[1].length() > 200){
            row[1] = row[1].substring(0,200);
        }
		this.setStringInInsert(2, row[1]);
		this.setStringInInsert(3, row[2]);
		
		String pages = row[4].replaceAll("[^\\p{N}]", "");

        if(pages.length() > 4){
            pages = "";
        }

		this.setStringInInsert(4, pages);

        if(row[7].length() > 12){
            row[7] = row[7].substring(0,12);
        }

		this.setStringInInsert(5, row[7]);


		this.setStringInInsert(6, row[8]);
		String price = row[9].replaceAll("[^\\p{N}^.]", "");
		

		if(price.length() > 4){
			price = price.substring(0,4);
		}
		
		String curr = row[9].replaceAll("[\\p{N}.]", "");

        if(curr.length() > 7){
            curr = curr.substring(0,7);
        }
		this.setStringInInsert(7, price);
		this.setStringInInsert(8, curr);
		
		PublicationType type = PublicationType.get(row[6]);
		this.setStringInInsert(9, ""+type.ordinal());
		
		this.setStringInInsert(10, row[5]);
		
		this.setStringInInsert(11, row[12].replaceAll("[^\\p{N}]", ""));
		
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
