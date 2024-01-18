package mypack;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PreparedStatementCRUD {
	public static void main(String args[]) 
	{
	
		try(Connection con = DriverManager.getConnection("jdbc:mysql://192.168.29.150/ce4_59", "ce4_59", "ce4_59"))
		{
			String insertQuery = "INSERT INTO `demo`(`name`, `dob`, `city`) VALUES( ?,?,?)";
			// PreparedStatement inherits Statement interface
			PreparedStatement ps = con.prepareStatement(insertQuery);

			ps.setString(1, "SDP");

			String date = "2021-01-01";
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
			java.util.Date dateStr = formatter.parse(date);
			java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());
			ps.setDate(2, dateDB);

			ps.setString(3, "Nadiad");

			int i = ps.executeUpdate();
			System.out.println(i + " rows inserted");

			String selectQuery = "SELECT * FROM `demo` WHERE `id` > ?";
			ps = con.prepareStatement(selectQuery);

			ps.setInt(1, 3);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println("........................");
				System.out.println("Auto id: " + rs.getInt(1));
				System.out.println("Name: " + rs.getString(2));
				System.out.println("DoB: " + rs.getDate(3));
				System.out.println("City: " + rs.getString(4));
			}

		} 
		
		catch (SQLException e) {
			System.out.println(e);
		} 
		catch (ParseException e) {
			System.out.println(e);
		}

	}
}
