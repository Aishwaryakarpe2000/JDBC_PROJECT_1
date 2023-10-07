package jdbc_product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainControllerp {
	
	public static void main(String []args) throws Exception
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("How many products you wants to add from batch");
		int size=sc.nextInt();
		
		List<Product>list =new ArrayList<Product>();
		
		for(int i=0;i<size;i++)
		{
			Product p=new Product();
			System.err.println("Enter the id");
			p.setId(sc.nextInt());
			System.err.println("Enter the name");
			p.setName(sc.next());
			System.err.println("Enter the price");
			p.setPrice(sc.nextDouble());
			System.err.println("Enter the brand");
			p.setBrand(sc.next());
			list.add(p);
		}
		//1
		Class.forName("com.mysql.cj.jdbc.Driver");
		//2
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/productdb","root","root");
		//3
		PreparedStatement prst=con.prepareStatement("Insert into product values(?,?,?,?)");
		
		for(Product p:list)
		{
			prst.setInt(1, p.getId());
			prst.setString(2, p.getName());
			prst.setDouble(3, p.getPrice());
			prst.setString(4, p.getBrand());
			prst.addBatch();
		}
		prst.executeBatch();
		//5
		con.close();
		System.out.println("Batch Executed");
		sc.close();
	}
}
