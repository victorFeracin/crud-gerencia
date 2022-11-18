/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import static controller.JDBCUtil.getConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Car;

/**
 *
 * @author PICHAU
 */
public class CarControllerDAO extends JDBCUtil {
    private static final String sqlFindAllCars = "SELECT * FROM Cars order by car_id";
    private static final String sqlFindOneCar = "SELECT * FROM Cars WHERE car_id = ?";
    private static final String sqlGetNumberRows = "SELECT COUNT (*) FROM Cars";
    private static final String sqlInsert = "INSERT INTO Cars (car_id, car_name, car_year, car_brand, car_price) VALUES (?, ?, ?, ?, ?)";
    private static final String sqlUpdate = "UPDATE Cars SET car_name = ?, car_year = ?, car_brand = ?, car_price = ? WHERE car_id = ?";
    private static final String sqlDelete = "DELETE FROM Cars WHERE car_id = ?";

    protected StringBuilder sbCars = new StringBuilder();
    
    public CarControllerDAO() {

    }
    
    public boolean insert(String name, int year, String brand, Double price) {
        try {
            int type = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concurrency = ResultSet.CONCUR_UPDATABLE;
            int newId;
            
            pstdata = connection.prepareStatement(sqlFindAllCars, type, concurrency);
            rsdata = pstdata.executeQuery();
            if(rsdata.next() != false) {
                rsdata.last();
                newId = getCar().getIdCar() + 1;
            } else {
                newId = 1;
            }
            
            pstdata = getConnection().prepareStatement(sqlInsert, type, concurrency);
            pstdata.setInt(1, newId);
            pstdata.setString(2, name);
            pstdata.setInt(3, year);
            pstdata.setString(4, brand);
            pstdata.setDouble(5, price);
            int answer = pstdata.executeUpdate();
            pstdata.close();

            //DEBUG
            System.out.println("Resposta da inserção = " + answer);
            //FIM-DEBUG
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

    public boolean update(int id, String name, int year, String brand, Double price) {
       try {
            int type = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concurrency = ResultSet.CONCUR_UPDATABLE;
            pstdata = connection.prepareStatement(sqlUpdate, type, concurrency);
            pstdata.setString(1, name);
            pstdata.setInt(2, year);
            pstdata.setString(3, brand);
            pstdata.setDouble(4, price);
            pstdata.setInt(5, id);
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
            //DEBUG
            System.out.println("Delete answer = " + answer);
            //FIM-DEBUG
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
            sbCars.setLength(0);
            
            pstdata = connection.prepareStatement(sqlGetNumberRows, type, concurrency);
            rsdata = pstdata.executeQuery();
            rsdata.next();
            long numberRows = rsdata.getLong(1);
            
            pstdata = connection.prepareStatement(sqlFindAllCars, type, concurrency);
            rsdata = pstdata.executeQuery();
            if(numberRows <= 0) {
                sbCars.append("List currently empty!");
            }
            for(int i = 0; i < numberRows; i++) {
                rsdata.next();
                sbCars
                    .append("ID: ")
                    .append(getCar().getIdCar())
                    .append("\n")
                    .append("Name: ")
                    .append(getCar().getName())
                    .append("\n")
                    .append("Year: ")
                    .append(getCar().getYear())
                    .append("\n")
                    .append("Brand: ")
                    .append(getCar().getBrand())
                    .append("\n")
                    .append("Price: ")
                    .append(getCar().getPrice())
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
            sbCars.setLength(0);

            pstdata = connection.prepareStatement(sqlFindOneCar, type, concurrency);
            pstdata.setInt(1, id);
            rsdata = pstdata.executeQuery();
            
            rsdata.next();
            sbCars
                    .append("ID: ")
                    .append(getCar().getIdCar())
                    .append("\n")
                    .append("Name: ")
                    .append(getCar().getName())
                    .append("\n")
                    .append("Year: ")
                    .append(getCar().getYear())
                    .append("\n")
                    .append("Brand: ")
                    .append(getCar().getBrand())
                    .append("\n")
                    .append("Price: ")
                    .append(getCar().getPrice())
                    .append("\n\n");
            return true;
        } catch (SQLException err) {
            System.out.println("Reading error = " + err);
        }
        return false;
    }

    public Car getCar() {
        Car car = null;
        if (rsdata != null) {
            try {
                int id = rsdata.getInt("car_id");
                String name = rsdata.getString("car_name");
                int year = rsdata.getInt("car_year");
                String brand = rsdata.getString("car_brand");
                Double price = rsdata.getDouble("car_price");
                car = new Car(id, name, year, brand, price);
            } catch (SQLException err) {
                System.out.println(err);
            }
        }
        return car;
    }

    //getters
    public ResultSet getRsdados() {
        return rsdata;
    }

    public StringBuilder getSbCars() {
        return sbCars;
    }
    
    //setters
    public void setSbCars(StringBuilder sbCars) {
        this.sbCars = sbCars;
    }
}
