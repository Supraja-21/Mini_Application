import java.util.*;

public class Amazon {
    static Scanner sc = new Scanner(System.in);
    static String Ad_Id = "ADD";
    static String Ad_Password = "2545";
    static int Ad_attempt = 3;
    static int meraant_log_attempt = 3;
    static int User_log_Attempt = 3;
    static int M_Id = 1;
    static int U_Id = 1;
    static int Current_meraant_log = -1;
    static int Current_User_log = -1;
    static Dictionary<String, ArrayList<String>> All_Products = new Hashtable<>();
    static ArrayList<Create_meraant_log_obj> meraant_logs = new ArrayList<>();
    static ArrayList<Creat_User_log_Obj> User_log_List = new ArrayList<>();
    
    static void ad_log() {
        while (Ad_attempt >= 0) {
            System.out.print("Welcome to Ad Login \nEnter the Ad Id : ");
            String ID = sc.next();
            sc.nextLine();
            System.out.print("\nEnter the Ad Password : ");
            String Password = sc.next();
            sc.nextLine();
            if (ID.equals(Ad_Id) && Password.equals(Ad_Password)) {
                Ad();
            } else {
                Ad_attempt -= 1;
                System.out.println(
                        "You have entered the wrong Password!\nYou have only " + Ad_attempt + " Attempts :-)");
            }
        }
    }
    static void Ad() {
        while (true) {
            System.out.println(
                    "Welcome to Ad Panel\n1 => View all Products\n2 => Add new mera_logent\n3 => meraant_log's Approval\n4 => List of mera_logents\n5 => Delete meraant_log\n6 => Add new Product\n7 => Back");
            int i = sc.nextInt();
            if (i == 1) {
                System.out.println(All_Products);
            } else if (i == 2) {
                Add_mera_logent();
            } else if (i == 3) {
                System.out.println("Enter meraant_log Id : ");
                String MerId = sc.next();
                sc.nextLine();
                for (Create_meraant_log_obj mera_log : meraant_logs) {
                    if (MerId.equals(Integer.toString(mera_log.M_Id))) {
                        mera_log.M_Verified = "Yes";
                        System.out.println("==> meraant_log Name : " + mera_log.M_Name + " meraant_log Id : " + mera_log.M_Id
                                + " is Verified : " + mera_log.M_Verified);
                    } else {
                        System.out.println("User_log Id is not founded");
                    }
                }
            } else if (i == 4) {
                List_Of_mera_logents();
            } else if (i == 5) {
                System.out.print("Enter the Id of the meraant_log : ");
                String id = sc.next();
                sc.nextLine();
                int temp = -1;
                int i1 = 0;
                for (Create_meraant_log_obj mera_log : meraant_logs) {
                    if ((Integer.toString(mera_log.M_Id)).equals(id)) {
                        temp = i1;
                    }
                    i1 += 1;
                }
                if (temp != -1) {
                    meraant_logs.remove(temp);
                } else {
                    System.out.println("Id is not mata with any other Id's :-)");
                }
            } else if (i == 6) {
                System.out.print("Enter the Product Name : ");
                String P_Name = sc.next();
                sc.nextLine();
                List<String> arr = new ArrayList<>();
                arr.add("0");
                arr.add("0");
                Enumeration enu = All_Products.keys();
                int k = -1;
                while (enu.hasMoreElements()) {
                    if (P_Name.equals(enu.nextElement())) {
                        k = 1;
                    }
                }
                if (k == -1) {
                    All_Products.put(P_Name, (ArrayList<String>) arr);

                } else {
                    System.out.println("Product is already found in the List !");
                }
            } else if (i == 7) {
                main(null);
            } else {
                System.out.println("Enter the valid i !");
            }
        }
    }

    public static void List_Of_mera_logents() {
        System.out.println("meraant_logs Names :");
        for (Create_meraant_log_obj mera_log : meraant_logs) {
            System.out.println("==> meraant_log Name : " + mera_log.M_Name + " meraant_log Id : " + mera_log.M_Id
                    + " is Verified : " + mera_log.M_Verified);
        }
    }

    public static void Add_mera_logent() {
        System.out.print("Enter mera_logent Name : ");
        String name = sc.next();
        sc.nextLine();
        System.out.print("Enter mera_logent Password : ");
        String password = sc.next();
        sc.nextLine();
        meraant_logs.add(new Create_meraant_log_obj(name, password, "Yes", M_Id));
        System.out.println("Your User_log Id => " + M_Id);
        M_Id += 1;
    }
    static void mera_log() {
        System.out.println("Welcome to meraant_log \n1 => For Login\n2 => For Regester\n3 => Back");
        int a = sc.nextInt();
        if (a == 1) {
            Mer_Login();
        } else if (a == 2) {
            Mer_Reg(a);
        } else if (a == 3) {
            main(null);
        } else {
            System.out.println("Enter the valid i !");
        }
    }

    static void Mer_Login() {
        while (meraant_log_attempt >= 0 || true) {
            System.out.print("Welcome to meraant_log Login \nEnter your Id : ");
            String ID = sc.next();
            sc.nextLine();
            System.out.print("\nEnter your Password : ");
            String Password = sc.next();
            sc.nextLine();
            int i = 0;
            for (Create_meraant_log_obj mera_log : meraant_logs) {
                if (ID.equals(Integer.toString(mera_log.M_Id)) && Password.equals(mera_log.M_Password)) {
                    if (mera_log.M_Verified.equals("Yes")) {
                        meraant_log_attempt = 3;
                        Current_meraant_log = i;
                        meraant_log();
                    } else {
                        System.out.println("User_log is not verified !");
                        mera_log();
                    }
                }
                i += 1;
            }
            meraant_log_attempt -= 1;
            mera_log();
        }
    }
    static void Mer_Reg(int a) {
        System.out.println("\nmera_logent Register \nEnter Your Name : ");
        String name = sc.next();
        sc.nextLine();
        System.out.println("Enter your Password : ");
        String password = sc.next();
        sc.nextLine();
        meraant_logs.add(new Create_meraant_log_obj(name, password, "No", M_Id));
        System.out.println("Your User_log Id => " + M_Id);
        M_Id += 1;
        mera_log();
    }
    static void meraant_log() {
        while (true) {
            System.out.println(
                    "Welcome to meraant_log Panal\n1 => Add Product\n2 => Update Product\n3 => Compare Product\n4 => Remove Product\n5 => List the Products\n6 => Back");
            int a = sc.nextInt();
            if (a == 1) {
                Add_Product(Current_meraant_log);
            } else if (a == 2) {
                Meraant_Update_Product();
            } else if (a == 3) {
                Meraant_Comp_Product();
            } else if (a == 4) {
                Meraant_Remove_Product();
            } else if (a == 5) {
                Meraant_Show_List();
            } else if (a == 6) {
                main(null);
            } else {
                System.out.println("Enter the valid Input !");
            }
        }
    }

    static void Meraant_Comp_Product() {
        System.out.println("Enter the Product Name : ");
        String product = sc.next();
        sc.nextLine();
        System.out.println("Product Name : " + product);
        int k = 0;
        for (int i = 0; i < meraant_logs.size(); i++) {
            Enumeration enu = meraant_logs.get(i).M_Products.keys();
            while (enu.hasMoreElements()) {
                if (enu.nextElement().equals(product)) {
                    System.out.println("meraant_log Name : " + meraant_logs.get(i).M_Name + "  Product Price : "
                            + meraant_logs.get(i).M_Products.get(product).get(1));
                    k += 1;
                }
            }
        }
        if (k <= 0) {
            System.out.println("Product is Not Found !");
        }
    }
    static void Meraant_Remove_Product() {
        System.out.print("Enter the product name : ");
        String product = sc.next();
        sc.nextLine();
        try {
            List<String> s = meraant_logs.get(Current_meraant_log).M_Products.remove(product);
            System.out.println(s + "   " + meraant_logs.get(Current_meraant_log).M_Products);
            List<String> arr = new ArrayList<>();
            int count1 = Integer.parseInt(All_Products.get(product).get(0)) - Integer.parseInt(s.get(0));
            String price = All_Products.get(product).get(1);
            arr.add(Integer.toString(count1));
            arr.add(price);
            All_Products.put(product, (ArrayList<String>) arr);
            System.out.println("Product removed Successfully !");
        } catch (Exception e) {
            System.out.println("Product is Not Found in your Product List !");
        }
    }

    static void Meraant_Update_Product() {
        try {
            System.out.print("Enter the product name : ");
            String product = sc.next();
            sc.nextLine();
            if (!meraant_logs.get(Current_meraant_log).M_Products.get(product).get(0).equals("null")) {

                System.out.println("Enter the product Count : ");
                int count = sc.nextInt();
                System.out.println("Enter the Updated Price : ");
                int price = sc.nextInt();
                List<String> arr1 = new ArrayList<>();
                String h = meraant_logs.get(Current_meraant_log).M_Products.get(product).get(0);
                arr1.add(Integer.toString(count));
                arr1.add(Integer.toString(price));
                meraant_logs.get(Current_meraant_log).M_Products.put(product, (ArrayList<String>) arr1);
                List<String> arr = new ArrayList<>();
                int count1 = (Integer.parseInt(All_Products.get(product).get(0))) - (Integer.parseInt(h) - count);
                arr.add(Integer.toString(count1));
                arr.add(Integer.toString(price));
                All_Products.put(product, (ArrayList<String>) arr);

            }
        } catch (Exception e) {
            System.out.println("The product is not found in the List !");
        }
    }

    static void Add_Product(int id) {
        System.out.println("Enter the Product Name : ");
        String product = sc.next();
        sc.nextLine();
        Enumeration enu = All_Products.keys();
        int k = -1;
        while (enu.hasMoreElements()) {
            if (product.equals(enu.nextElement())) {
                k = 1;
            }
        }
        if (k == -1) {
            System.out.println("Product is not found in the Ad's List !");
        } else {
            System.out.println("Enter the count of the product : ");
            int count = sc.nextInt();
            System.out.println("Enter the price for a Item : ");
            int price = sc.nextInt();
            int count1 = count + Integer.parseInt(All_Products.get(product).get(0));
            List<String> arr = new ArrayList<>();
            arr.add(Integer.toString(count1));
            arr.add(Integer.toString(price));
            All_Products.put(product, (ArrayList<String>) arr);
            List<String> arr1 = new ArrayList<>();
            String g = "0";
            try {
                g = meraant_logs.get(Current_meraant_log).M_Products.get(product).get(0);
            } catch (Exception e) {
                System.out.println("");
            }
            count += Integer.parseInt(g);
            arr1.add(Integer.toString(count));
            arr1.add(Integer.toString(price));
            meraant_logs.get(Current_meraant_log).M_Products.put(product, (ArrayList<String>) arr1);
        }
    }

    static void Meraant_Show_List() {
        System.out.println(meraant_logs.get(Current_meraant_log).M_Products);
    }
    static void User_log() {
        System.out.println("Welcome to User_log Panel :\n1 => Login\n2 => Register\n3 => Go Back");
        int a = sc.nextInt();
        User_log_Attempt = 3;
        if (a == 1) {
            User_Login();
        } else if (a == 2) {
            User_Register();
        } else if (a == 3) {
            main(null);
        } else {
            System.out.println("Enter the valid i !");
        }
    }

    static void User_Login() {
        while (User_log_Attempt >= 0) {
            System.out.print("Login Page\nEnter the User_log User_log Id : ");
            String id = sc.next();
            sc.nextLine();
            System.out.print("Enter the Password : ");
            String password = sc.next();
            sc.nextLine();
            for (int i = 0; i < User_log_List.size(); i++) {
                if (Integer.toString(User_log_List.get(i).U_Id).equals(id)
                        && User_log_List.get(i).U_Password.equals(password)) {
                    Current_User_log = i;
                    User_login_panel();
                    break;
                }
            }
            User_log_Attempt -= 1;
            User_log();
        }
    }

    static void User_login_panel() {
        System.out.println("Hello " + User_log_List.get(Current_User_log).U_Name + " Welcome to User_log Panel : ");
        while (true) {
            System.out.println(
                    "1 => List of Products \n2 => Show Cart \n3 => Puraase History \n4 => Wallet \n5 => Exit ");
            int a = sc.nextInt();
            if (a == 1) {
                User_login_Products_List();
            } else if (a == 2) {
                User_login_Cart_List();
            } else if (a == 3) {
                System.out.println("Puraase History !");
                for (int k = User_log_List.get(Current_User_log).U_Buy.size() - 1; k >= 0; k--) {
                    System.out.println(User_log_List.get(Current_User_log).U_Buy.get(k));
                }
            } else if (a == 4) {
                Wallet();
            } else if (a == 5) {
                main(null);
            } else {
                System.out.println("Please Enter the valid i !");
            }
        }
    }

    static void User_login_Products_List() {
        while (true) {
            Enumeration enu = All_Products.keys();
            while (enu.hasMoreElements()) {
                String pro = (String) enu.nextElement();
                System.out.println("==> Product Name : " + pro + " Product Count : "
                        + All_Products.get(pro).get(0));
            }
            System.out.println("1 => Add to Cart \n2 => Back");
            int a = sc.nextInt();
            if (a == 1) {
                User_Buy_login();
            } else if (a == 2) {
                User_login_panel();
            } else {
                System.out.println("Please Enter the valid i !");
            }
        }
    }

    static void User_Buy_login() {
        System.out.print("Enter the Product Name : ");
        String Product = sc.next();
        sc.nextLine();
        int k = -1;
        for (int i = 0; i < All_Products.size(); i++) {
            if (Integer.parseInt(All_Products.get(Product).get(0)) > 0) {
                k = i;
            }
        }
        if (k == -1) {
            System.out.println("Product Out Of Stock !");
        } else {
            Show_Products_List(Product);
        }
    }

    static void Show_Products_List(String product) {
        int k = 0;
        for (int i = 0; i < meraant_logs.size(); i++) {
            Enumeration enu = meraant_logs.get(i).M_Products.keys();
            while (enu.hasMoreElements()) {
                if (enu.nextElement().equals(product)
                        && Integer.parseInt(meraant_logs.get(i).M_Products.get(product).get(0)) > 0) {
                    System.out.println("==> meraant_log Name : " + meraant_logs.get(i).M_Name + " -- meraant_log Id : "
                            + meraant_logs.get(i).M_Id + "  --Product Count : "
                            + meraant_logs.get(i).M_Products.get(product).get(0) + "  --Product Price : "
                            + meraant_logs.get(i).M_Products.get(product).get(1));
                    k += 1;
                }
            }
        }
        if (k <= 0) {
            System.out.println("Product is Not Found !");
        } else {

            System.out.print("Enter the meraant_log Id :");
            String M_Id = sc.next();
            sc.nextLine();
            int not = 0;

            for (int i = 0; i < meraant_logs.size(); i++) {

                if (Integer.toString(meraant_logs.get(i).M_Id).equals(M_Id)) {

                    System.out.println(M_Id + " -- " + product);
                    System.out.print("Enter the Product Count : ");
                    int count = sc.nextInt();

                    if (Integer.parseInt(meraant_logs.get(i).M_Products.get(product).get(0)) >= count) {

                        List<String> arr = new ArrayList<>();

                        arr.add(Integer
                                .toString(Integer.parseInt(meraant_logs.get(i).M_Products.get(product).get(0)) - count));
                        arr.add(meraant_logs.get(i).M_Products.get(product).get(1));

                        meraant_logs.get(i).M_Products.put(product, (ArrayList<String>) arr);

                        List<String> arr1 = new ArrayList<>();

                        arr1.add(Integer.toString(Integer.parseInt(All_Products.get(product).get(0)) - count));
                        arr1.add(All_Products.get(product).get(1));

                        All_Products.put(product, (ArrayList<String>) arr1);

                        int cou = 0;
              
                        try {
                            cou = Integer.parseInt(User_log_List.get(Current_User_log).U_Cart.get(product).get(0));
                        }  catch (Exception e) {
                            cou = 0;
                        }

                        List<String> arr2 = new ArrayList<>();

                        arr2.add(Integer.toString(count + cou));
                        arr2.add(meraant_logs.get(i).M_Products.get(product).get(1));
                        product = meraant_logs.get(i).M_Id + "-" + product;

                        User_log_List.get(Current_User_log).U_Cart.put(product, (ArrayList<String>) arr2);

                        System.out.println("--------Added to Cart SuccessFully--------");
                        not = 1;
                        break;

                    } else {
                        System.out.println("Your count is more then the available Product !");
                    }
                } else {
                    not = 1;
                }
            }
            if (not == 0) {
                System.out.println("User_log Not Found !");
            }
        }
    }
    static void User_login_Cart_List() {
        while (true) {
            System.out.println(User_log_List.get(Current_User_log).U_Cart);
            System.out.println("1 => Buy \n2 => Back");
            int a = sc.nextInt();

            if (a == 1) {
                System.out.println("Enter the Product Name :");
                String product = sc.next();
                sc.nextLine();

                try {

                    System.out.println("Enter the Product Count : ");
                    int count = sc.nextInt();

                    if (count <= Integer.parseInt(User_log_List.get(Current_User_log).U_Cart.get(product).get(0))) {
                        if (Integer.parseInt(User_log_List.get(Current_User_log).U_Cart.get(product).get(0)) > 0) {

                            if (count
                                    * Integer.parseInt(
                                            User_log_List.get(Current_User_log).U_Cart.get(product).get(1)) <= User_log_List
                                                    .get(Current_User_log).U_Money) {

                                List<String> arr = new ArrayList<>();

                                arr.add(Integer.toString(
                                        Integer.parseInt(User_log_List.get(Current_User_log).U_Cart.get(product).get(0))
                                                - count));
                                arr.add(User_log_List.get(Current_User_log).U_Cart.get(product).get(1));

                                User_log_List.get(Current_User_log).U_Cart.put(product, (ArrayList<String>) arr);

                                User_log_List.get(Current_User_log).U_Buy
                                        .add(java.time.LocalDateTime.now() + " ---Buy--- " + product + " ---Price--- "
                                                + count
                                                        * Integer.parseInt(User_log_List.get(Current_User_log).U_Cart
                                                                .get(product).get(1)));

                                User_log_List.get(Current_User_log).U_Money -= (count
                                        * Integer.parseInt(User_log_List.get(Current_User_log).U_Cart.get(product).get(1)));

                                System.out.println("------Success------");

                            } else {
                                System.out.println("Insuficient Amount In your Wallet !");
                            }
                        } else {
                            System.out.println("Product is Empty! :-)");
                        }
                    } else {
                        System.out.println("Count is more than the Available Count in the Cart !");
                    }
                } catch (Exception e) {
                    System.out.println("Product Not Found !");
                }
            } else if (a == 2) {
                User_login_panel();
            } else {
                System.out.println("Please Enter the valid i !");
            }
        }
    }

    static void Wallet() {
        while (true) {
            System.out.println("1 => check Balance \n2 => Deposite \n3 => Statement \n4 => Back");
            int a = sc.nextInt();

            if (a == 1) {
                System.out.println("User_log Name : " + User_log_List.get(Current_User_log).U_Name + "\nAvailable Balance : "
                        + User_log_List.get(Current_User_log).U_Money);
            }

            else if (a == 2) {
                System.out.println("Enter the Amount : ");
                int amount = sc.nextInt();
                User_log_List.get(Current_User_log).Statement.add(
                        java.time.LocalDateTime.now() + "---Deposit---"
                                + (amount + User_log_List.get(Current_User_log).U_Money));
                User_log_List.get(Current_User_log).U_Money += amount;
                System.out.println("Amount Added Successfully !");
            }

           
            else if (a == 3) {
                System.out.println("Mini Statement !");
                for (int k = User_log_List.get(Current_User_log).Statement.size() - 1; k >= 0; k--) {
                    System.out.println(User_log_List.get(Current_User_log).Statement.get(k));
                }
            }

          
            else if (a == 4) {
                User_login_panel();
            }

            else {
                System.out.println("Enter the valid i !");
            }
        }
    }

    static void User_Register() {
        System.out.print("Register Page\nEnter the User_log User_log Name : ");
        String name = sc.next();
        sc.nextLine();
        System.out.print("Enter the Password : ");
        String password = sc.next();
        sc.nextLine();
        User_log_List.add(new Creat_User_log_Obj(name, password, U_Id));
        System.out.println("User_log Registered successfuly !\n\nYour U_Id : " + U_Id + "\n");
        U_Id += 1;
        User_Login();
    }

    public static void main(String args[]) {
        System.out.println("\tWelcome to Amazon.com \n choose one of the following :");
        while (true) {
            System.out.println("1 => Ad\n2 => meraant_log\n3 => User_log\n4 => Exit");
            int n = sc.nextInt();
            if (n == 1) {
                ad_log();
            } else if (n   == 2) {
                mera_log();
            } else if (n == 3) {
                User_log();
            } else if (n == 4) {
                System.out.println("Thank you for your visit !");
                System.exit(0);
            } else {
                System.out.println("Please Enter the valid input !");
            }
        }
    }
}

class Create_meraant_log_obj {
    public String M_Name, M_Password, M_Verified;
    public int M_Id;
    public Dictionary<String, ArrayList<String>> M_Products = new Hashtable<>();

    Create_meraant_log_obj(String name, String password, String verified, int id) {
        this.M_Name = name;
        this.M_Password = password;
        this.M_Verified = verified;
        this.M_Id = id;
       
    }
}

class Creat_User_log_Obj {
    public String U_Name, U_Password;
    public int U_Id, U_Money;
    public Dictionary<String, ArrayList<String>> U_Cart = new Hashtable<>();
    public List<String> Statement = new ArrayList<>();
    public List<String> U_Buy = new ArrayList<>();

    Creat_User_log_Obj(String name, String password, int id) {
        this.U_Name = name;
        this.U_Password = password;
        this.U_Id = id;
        this.U_Money = 0;
    }
}