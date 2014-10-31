package lab4ela;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DAOClass {
	String url = "jdbc:mysql://localhost:3307/lab4";
	String username = "java";
	String password = "1";
	Connection connection = null;

	protected void finilize(){
	    System.out.println("Closing the connection.");
	    if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
	
	}
	Connection getConnection(){
		if(connection != null)
			return this.connection;
	try {
	    System.out.println("Connecting database...");
	    connection = (Connection) DriverManager.getConnection(url, username, password);
	    System.out.println("Database connected!");
	} catch (SQLException e) {
	    throw new RuntimeException("Cannot connect the database!", e);
	}
	return this.connection;
	}
	Student getByID(int id){
		return null;
	}
	ArrayList<Student> getAll() throws SQLException{
		ArrayList<Student> ls = new ArrayList<Student>();
		Statement stmt = (Statement) this.getConnection().createStatement();
	
		String sql = "select * from students";
		 ResultSet rs;
		
			rs = stmt.executeQuery(sql);
		
	      //STEP 5: Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	    	  Student s = new Student();
	    	  s.setId(rs.getInt("id"));
	         s.setAge(rs.getString("age"));
	         s.setEmpName(rs.getString("name"));
	         s.setAddress(rs.getString("address"));
	         ls.add(s);
	         //Display values
	      }
	      rs.close();
		stmt.close();
		return ls;
	}
	
	boolean	insertStudent(Student s) throws SQLException{
		Statement stmt = (Statement) this.getConnection().createStatement();
		String sql = "insert into students(name,address,age) values("+s.getEmpName()+","+s.getAddress()+","+s.getAge()+")";
		try{
		stmt.executeUpdate(sql);
		stmt.close();
		return true;
		}
		catch(Exception e){
			
		}
		
		return false;
	}
	boolean updateStudent(Student s) throws SQLException{
Statement stmt = (Statement) this.getConnection().createStatement();
 System.out.print(s+"DASDASDASDSADS");

		String sql = "update students set name="+s.getEmpName()+", address = "+s.getAddress()+", age="+s.getAge()+" where id="+s.getId();
		System.out.print(sql);
		try{
		stmt.execute(sql);
		stmt.close();
		return true;
		}
		catch(Exception e){
		e.printStackTrace();
		}
		return false;
	}
	boolean deleteStudentByID(int id) throws SQLException{
		Statement stmt = (Statement) this.getConnection().createStatement();
		
		System.out.print("TESTING IN PROGRESS");
		String sql = "delete from students where id="+id;
		try{
		stmt.executeUpdate(sql);
		stmt.close();
		return true;
		}
		catch(Exception e){
			
		}
		return false;
	}
}
