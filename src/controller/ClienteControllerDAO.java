/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Cliente;

/**
 *
 * @author vhsf0
 */
public class ClienteControllerDAO extends JDBCUtil {
    private static final String sqlFindAllClientes = "SELECT * FROM Clientes order by cliente_id";
    private static final String sqlFindOneCliente = "SELECT * FROM Clientes WHERE cliente_id = ?";
    private static final String sqlGetNumberRows = "SELECT COUNT (*) FROM Clientes";
    private static final String sqlInsert = "INSERT INTO Clientes (cliente_id, cliente_name, cliente_email, cliente_phone) VALUES (?, ?, ?, ?)";
    private static final String sqlUpdate = "UPDATE Clientes SET cliente_name = ?, cliente_email = ?, cliente_phone = ? WHERE cliente_id = ?";
    private static final String sqlDelete = "DELETE FROM Clientes WHERE cliente_id = ?";

    protected StringBuilder sbClientes = new StringBuilder();
    
    public ClienteControllerDAO() {

    }
    
    public boolean insert(String name, String email, String phone) {
        try {
            int type = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concurrency = ResultSet.CONCUR_UPDATABLE;
            int newId;
            
            pstdata = connection.prepareStatement(sqlFindAllClientes, type, concurrency);
            rsdata = pstdata.executeQuery();
            if(rsdata.next() != false) {
                rsdata.last();
                newId = getCliente().getIdCliente() + 1;
            } else {
                
                newId = 1;
            }
            
            pstdata = getConnection().prepareStatement(sqlInsert, type, concurrency);
            pstdata.setInt(1, newId);
            pstdata.setString(2, name);
            pstdata.setString(3, email);
            pstdata.setString(4, phone);
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

    public boolean update(int id, String name, String email, String phone) {
       try {
            int type = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concurrency = ResultSet.CONCUR_UPDATABLE;
            pstdata = connection.prepareStatement(sqlUpdate, type, concurrency);
            pstdata.setString(1, name);
            pstdata.setString(2, email);
            pstdata.setString(3, phone);
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
        } catch (SQLException erro) {
            System.out.println("Delete error = " + erro);
        }
        return false;
    }

    public boolean findAll() {
        try {
            int type = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concurrency = ResultSet.CONCUR_READ_ONLY;
            sbClientes.setLength(0);
            
            pstdata = connection.prepareStatement(sqlGetNumberRows, type, concurrency);
            rsdata = pstdata.executeQuery();
            rsdata.next();
            long numberRows = rsdata.getLong(1);
            
            pstdata = connection.prepareStatement(sqlFindAllClientes, type, concurrency);
            rsdata = pstdata.executeQuery();
//            if(numberRows <= 0) {
//                sbClientes.append("List currently empty!");
//            }
            for(int i = 0; i < numberRows; i++) {
                rsdata.next();
                sbClientes
                    .append("ID: ")
                    .append(getCliente().getIdCliente())
                    .append("\n")
                    .append("Name: ")
                    .append(getCliente().getName())
                    .append("\n")
                    .append("Email: ")
                    .append(getCliente().getEmail())
                    .append("\n")
                    .append("Phone: ")
                    .append(getCliente().getPhone())
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
            sbClientes.setLength(0);

            pstdata = connection.prepareStatement(sqlFindOneCliente, type, concurrency);
            pstdata.setInt(1, id);
            rsdata = pstdata.executeQuery();
            
            rsdata.next();
            sbClientes
                    .append("ID: ")
                    .append(getCliente().getIdCliente())
                    .append("\n")
                    .append("Name: ")
                    .append(getCliente().getName())
                    .append("\n")
                    .append("Email: ")
                    .append(getCliente().getEmail())
                    .append("\n")
                    .append("Phone: ")
                    .append(getCliente().getPhone())
                    .append("\n\n");
            return true;
        } catch (SQLException err) {
            System.out.println("Reading error = " + err);
        }
        return false;
    }

    public Cliente getCliente() {
        Cliente cli = null;
        if (rsdata != null) {
            try {
                int id = rsdata.getInt("cliente_id");
                String name = rsdata.getString("cliente_name");
                String email = rsdata.getString("cliente_email");
                String phone = rsdata.getString("cliente_phone");
                cli = new Cliente(id, name, email, phone);
            } catch (SQLException err) {
                System.out.println(err);
            }
        }
        return cli;
    }
    
//    public ArrayList<Cliente> getClientes() {
//        ArrayList<Cliente> Clientes = new ArrayList<>();
//        if (rsdata != null) {
//            try {
//                int id = rsdata.getInt("cliente_id");
//                String name = rsdata.getString("cliente_name");
//                String email = rsdata.getString("cliente_email");
//                String phone = rsdata.getString("cliente_phone");
//                cli = new Cliente(id, name, email, phone);
//            } catch (SQLException err) {
//                System.out.println(err);
//            }
//        }
//        return cli;
//    }

    //getters
    public ResultSet getRsdados() {
        return rsdata;
    }

    public StringBuilder getSbClientes() {
        return sbClientes;
    }
    
    //setters
    public void setSbClientes(StringBuilder sbClientes) {
        this.sbClientes = sbClientes;
    }
    

}
