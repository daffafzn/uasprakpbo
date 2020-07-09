package uasprakpbo;

// import bumbu-bumbu yang diperlukan
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

// bisa juga diimpor sekaligus seperti ini:
// import java.sql.*


public class uasprakpbo {
    
    // Menyiapkan paramter JDBC untuk koneksi ke datbase
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/datamahasiswa";
    static final String USER = "root";
    static final String PASS = "";

    // Menyiapkan objek yang diperlukan untuk mengelola database
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;

    public static void main(String[] args) {
        
        // Melakukan koneksi ke database
        // harus dibungkus dalam blok try/catch
        try {
            // register driver yang akan dipakai
            Class.forName(JDBC_DRIVER);
            
            // buat koneksi ke database
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            // buat objek statement
            stmt = conn.createStatement();
            
            // buat query ke database
            String sql = "SELECT * FROM datamahasiswa";
            
            // eksekusi query dan simpan hasilnya di obj ResultSet
            rs = stmt.executeQuery(sql);
            
            // tampilkan hasil query
            while(rs.next()){
                System.out.println("id input : " + rs.getInt("id_input_data"));
                System.out.println("nim : " + rs.getString("nim"));
                System.out.println("nama : " + rs.getString("nama"));
                System.out.println("alamat : " + rs.getString("alamat"));
                System.out.println("ttl : " + rs.getString("ttl"));
            }
            
            stmt.close();
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}