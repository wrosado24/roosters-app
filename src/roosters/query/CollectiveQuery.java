package roosters.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

import roosters.dao.CollectiveDAO;

public class CollectiveQuery implements CollectiveDAO{

	@Override
	public JSONObject save() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject update() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject delete() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject list() {
        ResultSet rs = null;
        Connection cn = null;
        PreparedStatement ps = null;
        
        JSONArray data = new JSONArray();
        JSONObject response = new JSONObject();
        JSONObject obj = null;
        
        try {
            cn = MySQLDAOFactory.getConnection();

            String sql = "select *from collective";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
            	obj = new JSONObject();
            	obj.put("id",rs.getInt("id"));
            	obj.put("names",rs.getString("names"));
            	obj.put("location",rs.getString("localition"));
                data.put(obj);
            }
            
        } catch (SQLException ex) {
        	response.put("message", ex.getLocalizedMessage());
            response.put("status", false);
            return response;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                System.out.println("Ha ocurrido el siguiente error: " + ex.getMessage());
            }
        }
        response.put("data", data);
        response.put("status", true);
        return response;
	}

}
