/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import static controller.JDBCUtil.getConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Brand;

/**
 *
 * @author vhsf0
 */
public class BrandControllerDAO extends JDBCUtil {
    private static final String sqlFindAllBrands = "SELECT * FROM Brands order by brand_id";
    private static final String sqlFindOneBrand = "SELECT * FROM Brands WHERE brand_id = ?";
    private static final String sqlGetNumberRows = "SELECT COUNT (*) FROM Brands";
    private static final String sqlInsert = "INSERT INTO Brands (brand_id, brand_name, brand_year_created, brand_website) VALUES (?, ?, ?, ?)";
    private static final String sqlUpdate = "UPDATE Brands SET brand_name = ?, brand_year_created = ?, brand_website = ? WHERE brand_id = ?";
    private static final String sqlDelete = "DELETE FROM Brands WHERE brand_id = ?";

    protected StringBuilder sbBrands = new StringBuilder();
    
    public BrandControllerDAO() {

    }
    
    public boolean insert(String name, int yearCreated, String website) {
        try {
            int type = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concurrency = ResultSet.CONCUR_UPDATABLE;
            int newId;
            
            pstdata = connection.prepareStatement(sqlFindAllBrands, type, concurrency);
            rsdata = pstdata.executeQuery();
            if(rsdata.next() != false) {
                rsdata.last();
                newId = getBrand().getIdBrand() + 1;
            } else {
                newId = 1;
            }
            
            pstdata = getConnection().prepareStatement(sqlInsert, type, concurrency);
            pstdata.setInt(1, newId);
            pstdata.setString(2, name);
            pstdata.setInt(3, yearCreated);
            pstdata.setString(4, website);
            int answer = pstdata.executeUpdate();
            pstdata.close();

            if (answer == 1) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }
        } catch (SQLException err) {
            System.out.println("Insertion error = " + err);
        }
        return false;
    }

    public boolean update(int id, String name, int yearCreated, String website) {
       try {
            int type = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concurrency = ResultSet.CONCUR_UPDATABLE;
            pstdata = connection.prepareStatement(sqlUpdate, type, concurrency);
            pstdata.setString(1, name);
            pstdata.setInt(2, yearCreated);
            pstdata.setString(3, website);
            pstdata.setInt(4, id);
            int answer = pstdata.executeUpdate();
            pstdata.close();
            
            if (answer == 1) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }
        } catch (SQLException err) {
            System.out.println("Update error = " + err);
        }
        return false;
    }

    public boolean delete(int id) {
        try {
            int type = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concurrency = ResultSet.CONCUR_UPDATABLE;
            pstdata = connection.prepareStatement(sqlDelete, type, concurrency);
            pstdata.setInt(1, id);
            int answer = pstdata.executeUpdate();
            pstdata.close();
            if (answer == 1) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }
        } catch (SQLException err) {
            System.out.println("Delete error = " + err);
        }
        return false;
    }

    public boolean findAll() {
        try {
            int type = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concurrency = ResultSet.CONCUR_READ_ONLY;
            sbBrands.setLength(0);
            
            pstdata = connection.prepareStatement(sqlGetNumberRows, type, concurrency);
            rsdata = pstdata.executeQuery();
            rsdata.next();
            long numberRows = rsdata.getLong(1);
            
            pstdata = connection.prepareStatement(sqlFindAllBrands, type, concurrency);
            rsdata = pstdata.executeQuery();
            if(numberRows <= 0) {
                sbBrands.append("List currently empty!");
            }
            for(int i = 0; i < numberRows; i++) {
                rsdata.next();
                sbBrands
                    .append("ID: ")
                    .append(getBrand().getIdBrand())
                    .append("\n")
                    .append("Name: ")
                    .append(getBrand().getName())
                    .append("\n")
                    .append("Year of Creation: ")
                    .append(getBrand().getYearCreated())
                    .append("\n")
                    .append("Website: ")
                    .append(getBrand().getWebsite())
                    .append("\n\n");
            }
            return true;
        } catch (SQLException err) {
            System.out.println("Reading error = " + err);
        }
        return false;
    }
    
    public boolean findOne(int id) {
        try {
            int type = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concurrency = ResultSet.CONCUR_READ_ONLY;
            sbBrands.setLength(0);

            pstdata = connection.prepareStatement(sqlFindOneBrand, type, concurrency);
            pstdata.setInt(1, id);
            rsdata = pstdata.executeQuery();
            
            rsdata.next();
            sbBrands
                    .append("ID: ")
                    .append(getBrand().getIdBrand())
                    .append("\n")
                    .append("Name: ")
                    .append(getBrand().getName())
                    .append("\n")
                    .append("Year of Creation: ")
                    .append(getBrand().getYearCreated())
                    .append("\n")
                    .append("Website: ")
                    .append(getBrand().getWebsite())
                    .append("\n\n");
            return true;
        } catch (SQLException err) {
            System.out.println("Reading error = " + err);
        }
        return false;
    }

    public Brand getBrand() {
        Brand brand = null;
        if (rsdata != null) {
            try {
                int id = rsdata.getInt("brand_id");
                String name = rsdata.getString("brand_name");
                int year = rsdata.getInt("brand_year_created");
                String website = rsdata.getString("brand_website");
                brand = new Brand(id, name, year, website);
            } catch (SQLException err) {
                System.out.println(err);
            }
        }
        return brand;
    }

    //getters
    public ResultSet getRsdados() {
        return rsdata;
    }

    public StringBuilder getSbBrands() {
        return sbBrands;
    }
    
    //setters
    public void setSbBrands (StringBuilder sbBrands) {
        this.sbBrands = sbBrands;
    }
}
