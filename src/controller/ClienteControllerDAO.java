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
    
    public boolean Insert(Cliente cli) {
        try {
            int type = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concurrency = ResultSet.CONCUR_UPDATABLE;
            pstdados = getConnection().prepareStatement(sqlInsert, type, concurrency);
            pstdados.setInt(1, cli.getIdCliente());
            pstdados.setString(2, cli.getName());
            pstdados.setString(3, cli.getEmail());
            pstdados.setString(4, cli.getPhone());
            int answer = pstdados.executeUpdate();
            pstdados.close();
            //DEBUG
            System.out.println("Insertion answer = " + answer);
            //FIM-DEBUG
            if (answer == 1) {
                getConnection().commit();
                return true;
            } else {
                getConnection().rollback();
                return false;
            }
        } catch (SQLException err) {
            System.out.println("Insertion error = " + err);
        }
        return false;
    }

    public boolean Update(Cliente cli) {
       try {
            int type = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concurrency = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlUpdate, type, concurrency);
            pstdados.setString(1, cli.getName());
            pstdados.setString(2, cli.getEmail());
            pstdados.setString(3, cli.getPhone());
            pstdados.setInt(4, cli.getIdCliente());
            int answer = pstdados.executeUpdate();
            pstdados.close();
            //DEBUG
            System.out.println("Update answer = " + answer);
            //FIM-DEBUG
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

    public boolean Delete(Cliente cli) {
        try {
            int type = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concurrency = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlDelete, type, concurrency);
            pstdados.setInt(1, cli.getIdCliente());
            int resposta = pstdados.executeUpdate();
            pstdados.close();
            //DEBUG
            System.out.println("Delete answer = " + resposta);
            //FIM-DEBUG
            if (resposta == 1) {
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

    public boolean FindAll() {
        try {
            int type = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concurrency = ResultSet.CONCUR_READ_ONLY;
            sbClientes.setLength(0);
            
            pstdados = connection.prepareStatement(sqlGetNumberRows, type, concurrency);
            rsdados = pstdados.executeQuery();
            rsdados.next();
            long numberRows = rsdados.getLong(1);
            
            pstdados = connection.prepareStatement(sqlFindAllClientes, type, concurrency);
            rsdados = pstdados.executeQuery();
            for(int i = 0; i < numberRows; i++) {
                rsdados.next();
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
        } catch (SQLException erro) {
            System.out.println("Reading error = " + erro);
        }
        return false;
    }

    public Cliente getCliente() {
        Cliente cli = null;
        if (rsdados != null) {
            try {
                int id = rsdados.getInt("cliente_id");
                String name = rsdados.getString("cliente_name");
                String email = rsdados.getString("cliente_email");
                String phone = rsdados.getString("cliente_phone");
                cli = new Cliente(id, name, email, phone);
            } catch (SQLException err) {
                System.out.println(err);
            }
        }
        return cli;
    }
    
//    public ArrayList<Cliente> getClientes() {
//        ArrayList<Cliente> Clientes = new ArrayList<>();
//        if (rsdados != null) {
//            try {
//                int id = rsdados.getInt("cliente_id");
//                String name = rsdados.getString("cliente_name");
//                String email = rsdados.getString("cliente_email");
//                String phone = rsdados.getString("cliente_phone");
//                cli = new Cliente(id, name, email, phone);
//            } catch (SQLException err) {
//                System.out.println(err);
//            }
//        }
//        return cli;
//    }

    //getters
    public ResultSet getRsdados() {
        return rsdados;
    }

    public StringBuilder getSbClientes() {
        return sbClientes;
    }
    
    //setters

    public void setSbClientes(StringBuilder sbClientes) {
        this.sbClientes = sbClientes;
    }
    

}
