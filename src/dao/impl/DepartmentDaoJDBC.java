package dao.impl;

import dao.DepartmentDao;
import db.DB;
import db.DbException;
import db.DbIntegrityException;
import entities.Department;
import entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement ps = null;
        try{
            String sql = "INSERT INTO department (Name) VALUES(?)";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, obj.getName());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            }else{
                throw new DbException("Error inserting object");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closeStatement(ps);
        }
    }

    @Override
    public void update(Department obj) {
        PreparedStatement ps = null;
        try{
            String sql = "UPDATE department SET Name = ? WHERE Id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, obj.getName());
            ps.setInt(2, obj.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closeStatement(ps);
        }

    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement ps = null;
        try{
            String sql = "DELETE FROM department WHERE Id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows == 0) {
                throw new DbException("The id of the object that you want to delete does not exist in the table");
            }


        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closeStatement(ps);
        }

    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT * FROM department WHERE Id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if(rs.next()) {
                Department dep = instantiateDepartment(rs);
                return dep;
            }
            return null;

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("Id"));
        dep.setName(rs.getString("Name"));
        return dep;
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT * FROM department";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            List<Department> list = new ArrayList<>();

            while(rs.next()) {
                Department dep = instantiateDepartment(rs);
                list.add(dep);
            }
            return list;

        }catch (SQLException e){
            throw new DbIntegrityException(e.getMessage());
        }
        finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }
}
