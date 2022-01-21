import java.util.*;

public class ATM {
	
	static Scanner sc = new Scanner(System.in);
	static int money[] = new int[4];
	static String Admin_User = "Admin";
	static String Admin_Password = "qwerty";
	static int Admin_attempt = 1;
	static int State = 1;
	static ATM[] atm;
	static int Current_User = 0;
	
	//object creation for User
	public String User_Name, User_Pin;
	public int User_Balance = 0;
	public int User_attempt;
	public ArrayList<String> User_Statement;
	
	//
	ATM(String Name, String Pin, int Balance) {
		this.User_Name = Name;
		this.User_Pin = Pin;
		this.User_Balance = Balance;
		this.User_attempt = 1;
	}
	
	//
	public static void Admin_Login() {
		while(Admin_attempt <= 3) {
			System.out.print("\t Welcome Admin \nEnter the Admin User Id : ");
			String User_Id = sc.next();
			sc.nextLine();
			System.out.print("\nEnter the Admin Password : ");
			String User_Password = sc.next();
			sc.nextLine();
			
			//
			if(Admin_User.equals(User_Id) && Admin_Password.equals(User_Password)) {
				Admin();
			} else {
				Admin_attempt +=1;
			}
		}
		
		//
		if(Admin_attempt == 4) {
			System.out.println("Your Account Has been Locked :-)");
			main(null);
		}
	}
	
	//
	public  static void Admin() {
		int i = 1;
		while(i != 0) {
			System.out.println("\t Welcome Admin Panel \n 1 => Deposit Money in ATM\n 2 => Check Balance in ATM\n 3 => Back");
			int option = sc.nextInt();
			switch (option) {
			
			//
			case 1:
				System.out.print("Enter 2000 count : ");
				money[0] += sc.nextInt() * 2000;
				System.out.println();
				System.out.print("Enter 500 count : ");
				money[1] += sc.nextInt() * 500;
				System.out.println();
				System.out.print("Enter 200 count : ");
				money[2] += sc.nextInt() * 200;
				System.out.println();
				System.out.print("Enter 100 count : ");
				money[3] += sc.nextInt() * 100;
				System.out.println();
				break;
			
			//
			case 2:
				System.out.println("2000 count : " + (money[0] / 2000) + " Amount Present : " + money[0]);
				System.out.println("500 count : " + (money[1] / 500) + " Amount Present : " + money[1]);
				System.out.println("200 count : " + (money[2] / 200) + " Amount Present : " + money[2]);
				System.out.println("100 count : " + (money[3] / 100) + " Amount Present : " + money[3]);
				System.out.println("Total Amount Present : " + (money[0] + money[1] + money[2] + money[3]));
				break;
				
			//
			case 3:
				main(null);
				i = 0;
				break;
				
			//
			default:
				System.out.println("Enter the valid case !");
			}
		}
	}
	
	//
	public static void User_Login() {
		int k = 1;
		while (atm[Current_User].User_attempt <=3 && k != 0) {
			System.out.print("\t Welcome User Panel \nEnter the User Id : ");
			String User_Id = sc.next();
			sc.nextLine();
			System.out.print("\nEnter the User Password : ");
			String User_Password = sc.next();
			sc.nextLine();
			
			//
			for (int i = 0; i < 2; i++) {
				// System.out.println("----------");
				// System.out.println(atm[i].User_Name);
				if (atm[i].User_Name.equals(User_Id) && atm[i].User_Pin.equals(User_Password)) {
					Current_User = i;
					User();
					k += 1;
					break;
				}
			}
			atm[Current_User].User_attempt += 1;
		}
		
		//
		if(atm[Current_User].User_attempt == 4) {
			System.out.println("Your Account Has been Locked :-)");
			main(null);
		}
	}
	
	//
	public static void User() {
		int i = 1;
		while (i != 0) {
			System.out.println("\t Welcome User Panel \n 1 => Deposit Money \n 2 => Check Balance\n 3 => Widthdraw Money\n 4 => Mini Statemnt\n 5 => Change Pin\n 6 => Back to Main Menu");
			int option = sc.nextInt();
			switch (option) {
			
			//
			case 1:
				System.out.print("Enter 2000 count : ");
				int x = sc.nextInt() * 2000;
				atm[Current_User].User_Balance += x;
				money[0] += x;
				System.out.println();
				System.out.print("Enter 500 count : ");
				x = sc.nextInt() * 500;
				atm[Current_User].User_Balance += x;
				money[1] += x;
				System.out.println();
				System.out.print("Enter 200 count : ");
				x = sc.nextInt() * 200;
				atm[Current_User].User_Balance += x;
				money[2] += x;
				System.out.println();
				System.out.print("Enter 100 count : ");
				x = sc.nextInt() * 100;
				atm[Current_User].User_Balance += x;
				money[3] += x;
				System.out.println();
				String date = java.time.LocalDateTime.now() +"---Deposit---" + atm[Current_User].User_Balance;
				atm[Current_User].User_Statement.add(date);
				break;
				
			//
			case 2:
				System.out.println("User Name : " + atm[Current_User].User_Name);
				System.out.println("User Balance : " + atm[Current_User].User_Balance);
				break;
				
			//
			case 3:
				System.out.print("Enter the Amount to be WidthDraw : ");
				int Withdraw_Amount =  sc.nextInt();
				if (Withdraw_Amount <= (money[0] + money[1] + money[2] + money[3])) {
					if(Withdraw_Amount <= atm[Current_User].User_Balance) {
						Width_draw(Withdraw_Amount);
					} else {
						System.out.println("Insufficient Amount In your Account !");
					}
				} else {
					System.out.println("InSufficient Amount in ATM !");
				}
				break;
				
			//
			case 4:
				System.out.println("Mini Statemnet !");
				for (int k = atm[Current_User].User_Statement.size()-1; k >=0; k--) {
					System.out.println(atm[Current_User].User_Statement.get(k));
				}
				break;
				
			//
			case 5:
				System.out.print("Enter the New Password : ");
				String New_Pin = sc.next();
				sc.nextLine();
				atm[Current_User].User_Pin = New_Pin;
				System.out.println("Pin has been Changed !");
				break;
				
			//
			case 6:
				main(null);
				break;
				
			//
			default:
				System.out.println("Enter the valid case !");
			}
		}
	}

	public static void Width_draw(int Widthdraw_Amount) {
		int enteramount = Widthdraw_Amount;
		int temp = enteramount;
		int withdrawnotes[] = { 0, 0, 0, 0 };
		int notesname[] = { 2000, 500, 200, 100 };
		int count = 0;
		if (enteramount <= atm[Current_User].User_Balance && enteramount <= (money[0] + money[1] + money[2] + money[3])) {
			for (int i = 0; i < 4; i++) {
				withdrawnotes[i] = temp / notesname[i];
				temp = temp % notesname[i];
			}
			for (int i = 0; i < 4; i++) {
				if (withdrawnotes[i] <= money[i]) {
					count++;
				}
			}
			if(count == 4) {
				atm[Current_User].User_Balance = atm[Current_User].User_Balance - enteramount;
				String date = java.time.LocalDateTime.now() + "---Widthdraw---" + atm[Current_User].User_Balance;
				atm[Current_User].User_Statement.add(date);
				for (int i = 0; i < 4; i++) {
					money[i] = money[i] - withdrawnotes[i] * notesname[i];
				}
				System.out.print("withdraw successfull");
				for (int i : withdrawnotes) {
					System.out.print(i + " ");
				}
				System.out.println();
						
			} else {
				for (int i = 0; i < 4; i++) {
					System.out.println(notesname[i] + "-->" + money[i]);
				}
				System.out.println();
			}
		}
	}
	public static void main(String[] args) {
		
		int i = 1;
		
		//
		
		if (State == 1) {
			atm = new ATM[2];
			atm[0] = new ATM("James", "9876", 50000);
			atm[0].User_Statement = new ArrayList<>();
			atm[1] = new ATM("Elsa", "1234", 60000);
			atm[1].User_Statement = new ArrayList<>();
			State = 0;
		}
		
		//
		while (i !=0) {
			System.out.println("Welcome to ATM \n 1 => Admin \n 2 => User \n 3 => Exit");
			int userType = sc.nextInt();
			
			//
			if (userType == 1) {
				Admin_Login();
				break;
			}
			//
			else if (userType == 2) {
				User_Login();
				break;
			}
			
			//
			else if (userType == 3) {
				i = 0;
				System.out.println("Thank You for Visiting US !");
				System.exit(0);
				break;
			}
			
			//
			else {
				System.out.println("Please Enter the valid Case !");
				break;
			}
		}
	}
}
