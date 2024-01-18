package mypack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdatableResultSet {

	public static void main(String args[]) 
	{
		try(Connection con = DriverManager.getConnection("jdbc:mysql://192.168.29.150/ce4_59", "ce4_59", "ce4_59"))
		{
			
			int resultsetType = ResultSet.TYPE_FORWARD_ONLY;
			/*
			 This means that the ResultSet created using this type will be a forward-only result set, 
			 which allows traversing the result set only in the forward direction 
			 (from the first row to the last row).
			*/
			
			resultsetType = ResultSet.TYPE_SCROLL_INSENSITIVE;
			/*
			 This type of result set allows scrolling both forward and backward, 
			 and it is insensitive to changes made by other users while the result set is open. 
			 It means that changes made by other users in the database won't be reflected in the result set.
			*/
			
			resultsetType = ResultSet.TYPE_SCROLL_SENSITIVE;
			/*
			 This type of result set is also scrollable in both directions, 
			 but it is sensitive to changes made by other users. 
			 This means that if another user modifies the data in the database, 
			 those changes will be reflected in the result set.
			*/
			
			int resultsetConcurrency = ResultSet.CONCUR_UPDATABLE;
			/*
			 This specifies the concurrency mode of the result set. In this case, 
			 it indicates that the result set is updatable, 
			 allowing you to make changes to the data in the result set and propagate those changes back to the database.
			*/
			
			Statement s;
			s = con.createStatement();
			s = con.createStatement(resultsetType, resultsetConcurrency);

			String selectQuery = "SELECT * FROM `demo`";
			ResultSet rs;
			rs = s.executeQuery(selectQuery);

			rs.next();
			System.out.println("...........1.............");
			System.out.println("Auto id: " + rs.getInt(1));
			System.out.println("Name: " + rs.getString(2));
			System.out.println("DoB: " + rs.getDate(3));
			System.out.println("City: " + rs.getString(4));
			
			java.sql.Date d =  rs.getDate(3);
			rs.updateDate(3, d);
			rs.updateString(4,"ABCD");
			rs.updateRow();
			
			System.out.println("Auto id: " + rs.getInt(1));
			System.out.println("Name: " + rs.getString(2));
			System.out.println("DoB: " + rs.getDate(3));
			System.out.println("City: " + rs.getString(4));
				
			// insert row using programming
			rs.moveToInsertRow();
			rs.updateString(2,"tina");
			rs.updateDate(3, d);
			rs.updateString(4, "nadiad");
			rs.insertRow();

		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
