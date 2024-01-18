package mypack;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/*
CREATE TABLE `student1` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`name` varchar(50) NOT NULL,
`dob` date NOT NULL,
`city` varchar(50) NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
*/

public class DemoJDBC {
	public static void main(String args[]) 
	{
//		try 
//		{
			//Connection con = DriverManager.getConnection("jdbc:mysql://192.168.29.150/ce4_59", "ce4_59", "ce4_59");
			try(Connection con = DriverManager.getConnection("jdbc:mysql://192.168.29.150/ce4_59", "ce4_59", "ce4_59"))
			{
				Statement s;
				s = con.createStatement();
				
				String createTable = "CREATE TABLE `demo` (\n"
						+ "`id` int(11) NOT NULL AUTO_INCREMENT,\n"
						+ "`name` varchar(50) NOT NULL,\n"
						+ "`dob` date NOT NULL,\n"
						+ "`city` varchar(50) NOT NULL,\n"
						+ "PRIMARY KEY (`id`)\n"
						+ ")";
				boolean bool = s.execute(createTable);
				System.out.println("Table Created Successfully: "+bool); 
				     
				String date = "2021-01-01";
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date dateStr = formatter.parse(date);
				java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());
				String insertQuery = "INSERT INTO `demo`(`name`, `dob`, `city`) VALUES( 'sonal mehta', '" + dateDB
				+ "', 'Nadiad' )";
				int i = s.executeUpdate(insertQuery);
				System.out.println(i + " - rows inserted");
				
				String selectQuery = "SELECT * FROM `demo`";
				ResultSet rs;
				rs = s.executeQuery(selectQuery);
				while(rs.next()) {
					System.out.println("........................");
					System.out.println("Auto id: " + rs.getInt(1));
					System.out.println("Name: " + rs.getString(2));
					System.out.println("DoB: " + rs.getDate(3));
					System.out.println("City: " + rs.getString(4));
					System.out.println("........................");
					System.out.println();
				}
				
				String updateQuery = "UPDATE `demo` SET `city`='Anand' WHERE `id`=1";
				int u = s.executeUpdate(updateQuery);
				System.out.println(u + " - rows updated");
				rs = s.executeQuery(selectQuery);
				while (rs.next()) {
					System.out.println("........................");
					System.out.println("Auto id: " + rs.getInt(1));
					System.out.println("Name: " + rs.getString(2));
					System.out.println("DoB: " + rs.getDate(3));
					System.out.println("City: " + rs.getString(4));
					System.out.println("........................");
					System.out.println();
				}
				
				String deleteQuery = "DELETE FROM `demo` WHERE `id`=3";
				int d = s.executeUpdate(deleteQuery);
				System.out.println(d + " - rows deleted");
				rs = s.executeQuery(selectQuery);
				while (rs.next()) {
					System.out.println("........................");
					System.out.println("Auto id: " + rs.getInt(1));
					System.out.println("Name: " + rs.getString(2));
					System.out.println("DoB: " + rs.getDate(3));
					System.out.println("City: " + rs.getString(4));
					System.out.println("........................");
					System.out.println();
				}
			} 
			catch (SQLException e) {
				System.out.println(e);
			} 
			catch (ParseException e) {
				System.out.println(e);
			}
//		}
	}
}