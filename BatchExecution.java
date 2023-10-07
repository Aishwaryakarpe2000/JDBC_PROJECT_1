package jdbc_product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;

public class BatchExecution {

	public static void main(String[] args) throws Exception {
		
	
	Product p1=new Product();
	p1.setId(1);
	p1.setName("TV");
	p1.setPrice(15000);
	p1.setBrand("Samsung");
	
	Product p2=new Product();
	p2.setId(2);
	p2.setName("Laptop");
	p2.setPrice(30000);
	p2.setBrand("Acer");
	
	Product p3=new Product();
	p3.setId(3);
	p3.setName("Mb");
	p3.setPrice(16000);
	p3.setBrand("Vivot1");
	
	List<Product>l =new ArrayList<Product>();
	l.add(p1);
	l.add(p2);
	l.add(p3);
	
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/productdb","root","root");
	PreparedStatement prst=con.prepareStatement("Insert into product values(?,?,?,?)");
	
	
	for(Product p:l)
	{
		prst.setInt(1, p.getId());
		prst.setString(2, p.getName());
		prst.setDouble(3, p.getPrice());
		prst.setString(4, p.getBrand());
		prst.addBatch();
	}
	prst.executeBatch();
	con.close();
	System.out.println("Batch Executed");
	}
}
