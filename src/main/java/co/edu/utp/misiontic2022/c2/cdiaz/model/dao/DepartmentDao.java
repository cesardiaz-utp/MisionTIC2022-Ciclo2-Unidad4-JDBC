package co.edu.utp.misiontic2022.c2.cdiaz.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.utp.misiontic2022.c2.cdiaz.model.vo.Department;
import co.edu.utp.misiontic2022.c2.cdiaz.util.JDBCUtilities;

public class DepartmentDao {

    public List<Department> findAll() throws SQLException {
        var response = new ArrayList<Department>();
        try (var connection = JDBCUtilities.getConnection()) {
            var statement = connection.prepareStatement("select * from departments");
            var rset = statement.executeQuery();
            while (rset.next()) {
                int i = 0;
                var department = new Department();
                department.setId(rset.getInt(i++));
                department.setName(rset.getString(i++));
                response.add(department);
            }
        }
        return response;
    }

    public Department findById(Integer id) throws SQLException {
        Department response = null;
        try (var connection = JDBCUtilities.getConnection()) {
            var statement = connection.prepareStatement("select * from departments where id = ?");
            statement.setInt(1, id);
            
            var rset = statement.executeQuery();
            if (rset.next()) {
                response = new Department();
                int i = 1;
                response.setId(rset.getInt(i++));
                response.setName(rset.getString(i++));
            }
        }
        return response;
    }
}
