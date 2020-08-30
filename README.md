# sample-retailstore
Sample retail store java application based on command line input

Instruction to run the application:

1. clone this repository.
2. After that to download the dependencies mentioned in the pom.xml. please run the following command -> mvn clean install   
3. Run the main class : RetailStoreApplication
4. please consider the following points before testing the application

  A. Application is using dummy data for processing. which is returning from the service: IProductDataService
  B. There are 4 different options provided for the user. 
  C. provide the correct option to execute the functionality.
  
  Application Views:
    1. Main menu view:
        MENU:
          1.Show all products
          2.Show total sale
          3.checkout products
          4.Exit
          Enter your option:
          
    2. If you choose Option 1 selected then productDetailView is: 
        All product details:
          Product id:1, Name:product-A1, product code:ITEM-A1, price: 5.0, Category:A
          Product id:2, Name:product-A2, product code:ITEM-A2, price: 10.0, Category:A
      
    3. If you choose Option 2 selected then Show totalsale view is:
          case 1: if there are some products that are already checked out then ->
                    Total Sales:
                    Subtotal Amount: 5.0
                    Total Sales tax: 0.5
                    Total Amount: 5.5
                      
          case 2: if no products are checked out yet ->
            show message like : "NO SALE"
     
    4. If you choose Option 3 selected then Checkout view is:
        Enter product code by comma separated:
          item-a1, item-b1
        DO YOU WANT TO CHECKOUT? Y/N 
          y
        Checkout Details:
        Your Order Id is: 1
        Checkout products are: 
        Product id:1, Name:product-A1, product code:ITEM-A1, price: 5.0, Category:A Applicable SalesTax: 0.5
        Product id:6, Name:product-B1, product code:ITEM-B1, price: 5.0, Category:B Applicable SalesTax: 1.0
        Sub Total: Rs. 10.0
        Sales Tax: Rs. 1.5
        Total: Rs. 11.5
        
     5. select option 4 to stop the application.   



