package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.SampleDB;

//Data Access Object（DAO）
public class DAO {
	//*************************************
	// メンバ変数の定義
	//*************************************
	private Connection db;
	private PreparedStatement ps;
	private ResultSet rs;

	//*************************************
	// メソッドの定義
	//*************************************
	// MySQLとの接続処理
	private void getConnection() throws NamingException, SQLException{	
			Context context=new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/jsp");
			this.db=ds.getConnection();	
	}
	
	// MySQLとの切断処理
	private void disconnect(){
		try{
			if(rs != null){rs.close();}
			if(ps != null){ps.close();}
			if(db != null){db.close();}
		}catch(SQLException e){
			e.printStackTrace();
		}	
	}
	
	public List<SampleDB> findAll(){
		
		List<SampleDB> productList=new ArrayList<>();
		try {
			this.getConnection();
			ps=db.prepareStatement("SELECT * FROM products ORDER BY id DESC");
			rs=ps.executeQuery();
			while(rs.next()){
				int id=rs.getInt("id");
				String name=rs.getString("name");
				int price=rs.getInt("price");
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String updated=sdf.format(rs.getTimestamp("updated"));
				Product product=new Product(id,name,sex,born);
				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}finally{
			this.disconnect();
		}
		return productList;
	}
}