package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

    public class Connect {
        
    	private String USERNAME = "root";
        private String PASSWORD = "";
        private String DATABASE = "dope_gamestore";
        private String HOST = "localhost:3306";
        
        private final String CONNECTION = 
        		String.format("jdbc:mysql://%s/%s", HOST, DATABASE);
        
        private Connection con;
        public Statement st;
        public ResultSet rs;
        public PreparedStatement ps;
        
        private static Connect instance = null;
        
        public static Connect getInstance(){
        	if (instance == null) instance = new Connect();
        	return instance;
        }

        public Connect() {
            
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                con = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
                st = con.createStatement();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        
        public ResultSet getData(String query) {
        	try {
				st = con.createStatement();
				rs = st.executeQuery(query);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
        	return rs;
        	
        }
        
        
        public int updateData(String query) {
        	int hasil = 0;
        	
        	try {
				st = con.createStatement();
				hasil = st.executeUpdate(query);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}     	
        	
        	return hasil;
        }
        
        public int getTotalRow(String query) {
        	int result = 0;
        	
        	try {
				st = con.createStatement();
				rs = st.executeQuery(query);
				rs.last();
				result = rs.getRow();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
        	return result;
        }
        
        
        public ResultSet executeQuery(String query) {
            rs = null;
            try {
                rs = st.executeQuery(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            return rs;
        }
        
        public int executeUpdate(String query) {
            
            try {
               return st.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
        }
        
        public PreparedStatement preparedStatement (String query) {
        	ps = null;
        	try {
        		ps = con.prepareStatement(query);
        	}catch (SQLException e) {
                e.printStackTrace();
            }
            return ps;
        }
        

    }