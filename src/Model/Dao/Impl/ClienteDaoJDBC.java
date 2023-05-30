package Model.Dao.Impl;

import Model.Dao.ClientDao;
import Model.Entites.Client;
import db.DB;
import db.DbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDaoJDBC implements ClientDao {
    private Connection conn;
    public ClienteDaoJDBC(Connection conn) {this.conn=conn;}

    @Override
    public void insert(Client obj) {
            PreparedStatement st = null;
            try {
                st = conn.prepareStatement(
                        "INSERT INTO cliente "
                                + "(nome, email,telefone,idade) "
                                + "VALUES "
                                + "(?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS);

                st.setString(1, obj.getNome());
                st.setString(2,obj.getEmail());
                st.setString(3, obj.getTelefone());
                st.setString(4,obj.getIdade());

                int rowsAffected = st.executeUpdate();

                if (rowsAffected > 0) {
                    ResultSet rs = st.getGeneratedKeys();
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        obj.setId(id);
                    }
                    DB.closeResultSet(rs);
                }
                else {
                    throw new DbException("Unexpected error! No rows affected!");
                }
            }
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
            finally {
                DB.closeStatement(st);
            }
    }


    @Override
    public void update(Client obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE cliente "
                            + "SET id = ?, nome = ?, email = ?, telefone = ?, idade = ? "
                            + "WHERE Id = ?");

            st.setInt(1, obj.getId());
            st.setString(2, obj.getNome());
            st.setString(3,obj.getTelefone());
            st.setString(4, obj.getEmail());
            st.setString(5,obj.getIdade());

            st.executeUpdate();
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }
    @Override
    public void deleteById(Integer id) {
            PreparedStatement st = null;
            try {
                st = conn.prepareStatement("DELETE FROM cliente WHERE Id = ?");

                st.setInt(1, id);

                st.executeUpdate();
            }
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
            finally {
                DB.closeStatement(st);
            }
        }

    @Override
    public Void finByName(Client obj) {
        return null;
    }




    @Override
    public Client findById(Integer id) {
        return null;
    }

    public ClienteDaoJDBC() {
    }

    //retorna a tabela
    @Override
    public List<Client> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM cliente ORDER BY id");
            rs = st.executeQuery();

            List<Client> list = new ArrayList<>();

            while (rs.next()) {
                Client obj = new Client();
                obj.setId(rs.getInt("Id"));
                obj.setNome(rs.getString("nome"));
                obj.setEmail(rs.getString("Email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setIdade(rs.getString("idade"));
                list.add(obj);
            }
            return list;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }


}

