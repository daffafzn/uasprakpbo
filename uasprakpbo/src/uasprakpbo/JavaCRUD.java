package belajar.java.mysql;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class JavaCRUD {
    public static void cls(){
		try{
			new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
		}
		catch(Exception E){
			System.out.println(E);
		}
	}
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/datamahasiswa";
    static final String USER = "root";
    static final String PASS = "";

    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(inputStreamReader);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            // register driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            while (!conn.isClosed()) {
                showMenu();
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void showMenu() {
        System.out.println("           HHHHHHHHHHHHHHHHH       "+"                                                "+"\n       HHHHHHHHHHHHHHHHHHHHHHHHH   "+"                                                "+"\n     HHHHHHHHHHHHHHHHH     HHHHHHH "+"                                                "+"\n   HHHHHHHHHHHHHHHHHHHH  ,HHHHH"+" __  __   ___                       .           "+"\n  HHHHHHHHHHHHHHHHHHHHHHHHHHH  "+"|  |/  `.'   `.                   .'|           "+"\n HHHHHHHHHHHHHHHHHHHHHHHHH     "+"|   .-.  .-.   '              .| <  |           "+"\n HHHHHHHHHHHHHHHHHHHHHHH       "+"|  |  |  |  |  |    __      .' |_ | |           "+"\n.HHHHHHHHHHHHHHHHHHHHH         "+"|  |  |  |  |  | .:--.'.  .'     || | .'''-.    "+"\n HHHHHHHHHHHHHHHHHHHHHHH       "+"|  |  |  |  |  |/ |   | |'--.  .-'| |/.'''. |   "+"\n HHHHHHHHHHHHHHHHHHHHHHHHHH    "+"|  |  |  |  |  |'. __ | |   |  |  |  /    | |   "+"\n  HHHHHHHHHHHHHHHHHHHHHHHHHHH*"+" |__|  |__|  |__| .'.''| |   |  |  | |     | |   "+"\n   .HHHHHHHHHHHHHHHHHHHHHHHHHHHH"+"               / /   | |_  |  '.'| |     | |   "+"\n     #HHHHHHHHHHHHHHHHHHHHHHHHHHH/"+"             | .._,| '/  |   / | '.    | '.  "+"\n        HHHHHHHHHHHHHHHHHHHHHHH  "+"               `----- '   `'-'  '---'   '---' "+"\n             HHHHHHHHHHHHH  	 "+"                                              ");
            
        System.out.println("\n========= MENU UTAMA =========");
        System.out.println("1. Masukkan Data");
        System.out.println("2. Perlihatkan Data");
        System.out.println("3. Edit Data");
        System.out.println("4. Hapus Data Berdasarkan ID");
        System.out.println("0. EXIT");
        System.out.println("");
        System.out.print("PILIHAN> ");
       
        try {
            int pilihan = Integer.parseInt(input.readLine());

            switch (pilihan) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    insertData();
                    break;
                case 2:
                    showData();
                    
                    break;
                    
                case 3:
                    updateData();
                    break;
                case 4:
                    hapusData();
                    break;
                default:
                    System.out.println("Pilihan salah!");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static void showData() {
        String sql = "SELECT * FROM datamahasiswa";

        try {
            rs = stmt.executeQuery(sql);
            System.out.println("+----------------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|No|\tNIM\t|\t\tNAMA\t\t|\t\t\tALAMAT\t\t\t\t|\tTTL\t");
            System.out.println("+----------------------------------------------------------------------------------------------------------------------------------+");
            while (rs.next()) {
                int id_input_data = rs.getInt("id_input_data");
                String nim = rs.getString("nim");
                String nama = rs.getString("nama");
                String alamat = rs.getString("alamat");
                String ttl = rs.getString("ttl");

                
                System.out.println(String.format("|%d.  %s   %s   \t\t %s\t\t\t\t %s", id_input_data, nim, nama, alamat, ttl));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void insertData() {
        try {
            // ambil input dari user
            System.out.print("Masukkan NIM : ");
            String nim = input.readLine().trim();
            System.out.print("Masukkan Nama : ");
            String nama = input.readLine().trim(); 
            System.out.print("Masukkan Alamat : ");
            String alamat = input.readLine().trim(); 
            System.out.print("Masukkan TTL (yy:mm:dd) : ");
            String ttl = input.readLine().trim(); 
            
 
            // query simpan
            String sql = "INSERT INTO datamahasiswa (nim, nama, alamat, ttl) VALUE('%s', '%s', '%s', '%s')";
            sql = String.format(sql, nim, nama, alamat, ttl);

            // simpan datamahasiswa
            stmt.execute(sql);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void updateData() {
        try {
            
            // ambil input dari user
            System.out.print("ID yang mau diedit: ");
            int id_input_data = Integer.parseInt(input.readLine());
            System.out.print("Masukkan NIM : ");
            String nim = input.readLine().trim();
            System.out.print("Masukkan Nama : ");
            String nama = input.readLine().trim(); 
            System.out.print("Masukkan Alamat : ");
            String alamat = input.readLine().trim(); 
            System.out.print("Masukkan TTL (yy:mm:dd) : ");
            String ttl = input.readLine().trim(); 

            // query update
            String sql = "UPDATE datamahasiswa SET nim='%s', nama='%s', alamat='%s', ttl='%s' WHERE id_input_data=%d";
            sql = String.format(sql, nim, nama, alamat, ttl, id_input_data);

            // update data datamahasiswa
            stmt.execute(sql);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void hapusData() {
        try {
            
            // ambil input dari user
            System.out.print("ID yang mau dihapus: ");
            int id_input_data = Integer.parseInt(input.readLine());
            
            // buat query hapus
            String sql = String.format("DELETE FROM datamahasiswa WHERE id_input_data=%d", id_input_data);

            // hapus data
            stmt.execute(sql);
            
            System.out.println("Data telah terhapus...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}