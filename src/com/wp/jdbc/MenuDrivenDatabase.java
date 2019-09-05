package com.wp.jdbc;

import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;


public class MenuDrivenDatabase {
	static Scanner sc;
	private String name;
	private int eno;
	private int salary;
	private String desg;
	private String dept;
	private static final String SQL_INSERT = "INSERT INTO EMPLOYEE (ENO,NAME,SALARY,DESG,DEPT) VALUES(?,?,?,?,?)";
	private static final String SQL_READ = "SELECT * FROM EMPLOYEE";
	private static final String SQL_DELETE = "DELETE FROM EMPLOYEE WHERE ENAME=?";
	private static final String SQL_UPDATE = "UPDATE EMPLOYEE SET SALARY=? WHERE ENO=?";
	static PreparedStatement ps;

	void insert_into_employee() {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("How many employee's you want to add??");
		int count = sc.nextInt();
		for (int i = 0; i < count; i++) {
			System.out.println("Enter the eno:");
			eno = sc.nextInt();

			System.out.println("Enter the name of employee:");
			sc.nextLine();
			name = sc.nextLine();

			System.out.println("Enter the salary of employee:");
			salary = sc.nextInt();

			System.out.println("Enter the desigination of employee :");
			sc.nextLine();
			desg = sc.nextLine();

			System.out.println("Enter the departement of the employee [IT/ACCOUNTS/MARKETING/HR]");
			dept = sc.nextLine();
			try {
				java.sql.PreparedStatement PS = ConnectionDemo.getConn().prepareStatement(SQL_INSERT);
				PS.setInt(1, eno);
				PS.setString(2, name);
				PS.setInt(3, salary);
				PS.setString(4, desg);
				PS.setString(5, dept);

				int number = PS.executeUpdate();
				System.out.println("Inserted Successfully..Total rows added " + number);
				PS.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	static void ViewAllEmp() {

		try {
			java.sql.PreparedStatement PS1 = ConnectionDemo.getConn().prepareStatement(SQL_READ);
			java.sql.ResultSet rs = PS1.executeQuery();
			while (rs.next()) {
				int eno = rs.getShort(1);
				String name = rs.getString(2);
				int salary = rs.getInt(3);
				String dept = rs.getString(4);
				String desg = rs.getString(5);
				System.out.println("Employee no:" + eno + "Employee name :" + name + "Salary :" + salary + " Dept :"
						+ dept + " Desg :" + desg);
				PS1.close();
			}
			
				

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	static void Remove() {
		System.out.println("Enter the ename whose data you want to remove");
		String remove = sc.nextLine();
		try {
			java.sql.PreparedStatement PS2 = ConnectionDemo.getConn().prepareStatement(SQL_DELETE);
			PS2.setString(1, remove);
			int n = PS2.executeUpdate();
			System.out.println("Total Row Deleted :" + n);
			PS2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void clearData() {
		try {
			String sql = "DELETE FROM EMPLOYEE";
			java.sql.Statement st = ConnectionDemo.getConn().createStatement();
			st.executeUpdate(sql);
			System.out.println("DATA CLEARED....!");
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void changeSalary() {
		System.out.println("enter eno of employee whom you want to change salary");
		System.out.println("enter the incresed  salary of employee");
		int newsal= sc.nextInt();
		
		try {
			java.sql.PreparedStatement PS3 = ConnectionDemo.getConn().prepareStatement(SQL_UPDATE);
			PS3.setInt(1, newsal);
			PS3.setInt(2, eno);
			int n = PS3.executeUpdate();
			System.out.println("Number of row where salary is changed are :" + n);
			PS3.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void searchEmployee() {
		System.out.println("ENTER THE ENO YOU WANT TO SEARCH THE INFO OF..!");
		try {
			java.sql.PreparedStatement PS4 = ConnectionDemo.getConn().prepareStatement(SQL_READ + "WHERE ENO=SEARCH");

			java.sql.ResultSet rs = PS4.executeQuery();
			while (rs.next()) {
				int eno = rs.getShort(1);
				String name = rs.getString(2);
				int salary = rs.getInt(3);
				String dept = rs.getString(4);
				String desg = rs.getString(5);
				System.out.println("Employee no:" + eno + "Employee name :" + name + "Salary :" + salary + " Dept :"
						+ dept + " Desg :" + desg);
				PS4.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deptWise() {
		try {
			java.sql.PreparedStatement PS5 = ConnectionDemo.getConn().prepareStatement("SELECT * FROM EMPLOYEE ORDER BY DEPT");

			java.sql.ResultSet rs = PS5.executeQuery();
			while (rs.next()) {
				int eno = rs.getInt(1);
				String name = rs.getString(2);
				int salary = rs.getInt(3);
				String dept = rs.getString(4);
				String desg = rs.getString(5);
				System.out.println("Employee no:" + eno + "Employee name :" + name + "Salary :" + salary + " Dept :"
						+ dept + " Desg :" + desg);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void SortEmployee() {
		java.sql.ResultSet rs = null;
		System.out.println("On What Basis You Want To Sort Employee List");
		System.out.println("1. Employee ID");
		System.out.println("2. Employee Name");
		System.out.println("3. Employee Department");
		System.out.println("4. Employee Salary");
		int x = sc.nextInt();
		System.out.println(x);
		try {
			if (x == 1) {
				java.sql.PreparedStatement ps =  ConnectionDemo.getConn().prepareStatement( "SELECT * FROM EMPLOYEE ORDER BY ENO");
				rs = ps.executeQuery();
			} else if (x == 2) {
				java.sql.PreparedStatement ps =  ConnectionDemo.getConn().prepareStatement( "SELECT * FROM EMPLOYEE ORDER BY NAME");
				rs = ps.executeQuery();
			} else if (x == 3) {
				java.sql.PreparedStatement ps=  ConnectionDemo.getConn().prepareStatement("SELECT * FROM EMPLOYEE ORDER BY DEPT");
				rs = ps.executeQuery();
			} else if (x == 4) {
				java.sql.PreparedStatement ps =  ConnectionDemo.getConn().prepareStatement( "SELECT * FROM EMPLOYEE ORDER BY SALARY");
				rs = ps.executeQuery();
			} else if (x > 4) {
				System.out.print("INVALID NUMBER");
			}

			while (rs.next()) {

				System.out.print("   ENO :" + rs.getInt(1));
				System.out.print("   Ename :" + rs.getString(2));
				System.out.print("   SALARY :" + rs.getInt(3));
				System.out.println(" DEPT :" + rs.getString(4));
				System.out.println(" DESG :" + rs.getString(5));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		MenuDrivenDatabase menuDriven = new MenuDrivenDatabase();
		int choice;
		while (true) {
			System.out.println("\n");
			System.out.println("Menu:");
			System.out.println("1.Add Employee");
			System.out.println("2.View all Employee");
			System.out.println("3.Remove Employee");
			System.out.println("4.Clear Data");
			System.out.println("5.Change Salary");
			System.out.println("6.Search Employee");
			System.out.println("7.view departement wise list:");
			System.out.println("8.viewSortedEmployee");
			System.out.println("9.Exit");
			System.out.println("Enter your Choice:");
			choice = sc.nextInt();
			 ConnectionDemo.ConnectDB();
			switch (choice) {
			case 1:

				 ConnectionDemo.create(
						"create Table  Employee (eno int,name varchar(20),salary int,desg  varchar(20),dept  varchar(20) )");
				System.out.println("TABLE CREATED ");
				menuDriven.insert_into_employee();

				break;

			case 2:
				ViewAllEmp();
				break;
			case 3:

				Remove();
				break;
			case 4:
				clearData();
				break;
			case 5:

				menuDriven.changeSalary();
				break;
			case 6:
				searchEmployee();
				break;

			case 7:
				menuDriven.deptWise();
				break;

			case 8:
				SortEmployee();
				break;
			case 9:
				 ConnectionDemo.close();
		
				System.exit(1);

			default:
				break;
			}

		}
	}
}
