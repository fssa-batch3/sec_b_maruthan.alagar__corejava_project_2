
# M A M Billing  Checklist

 #### [Milestones](https://github.com/fssa-batch3/sec_b_maruthan.alagar__corejava_project_2/milestones)

## Database Design

- [ ] Create an ER diagram of the database

[![HD02Net.md.png](https://iili.io/HD02Net.md.png)](https://freeimage.host/i/HD02Net)

- [ ] Write Create table scripts [script](/src/main/resources/db/migration/V1__create_products.sql)



## Project Setup

- [ ] Create a new Java project
- [ ] Set up a MySQL database
- [ ] Add necessary libraries
	- [ ] JDBC, 
	- [ ] MySQL Connector, 
	- [ ] JUnit, 
	- [ ] Dotenv

## Module 1 : Product (Milestone 1)


### Feature 1: Product Creation 
#### User Story:
 User can create new product.
#### Prerequisites:
- [ ] Create Product table
- [ ] Implement Product model
- [ ] Implement Product DAO (create)
- [ ] Complete Price Module Feature 1.
#### Validations:

##### Form Validation:
- [ ] Product Name (Null , Pattern & Length)
- [ ] Quantity (Quantity <= 0)
- [ ] Quantity Type (ENUM - mg , ml , Nos)
- [ ] Special Name (Null , Pattern & Length) **(Optional)**
#####   Business Validation:
- [ ] Check The product is already exists or not using Name and Quantity.
#### Messages: 
 -  Name Cannot be Null or Empty.
 -  Quantity cannot be less than or equal to 0.
 -  Choose any Quantity Type.
 - Special Name Cannot be Null or Empty. 


#### Flow: 

```mermaid
graph  TD;

A[Product Service: Create Product]  -->  B[Form Validation]

B  -- Yes -->  C{Business Validation}
B -- No --> G[Throws Exception]

C  -- Yes -->  D[Product DAO: Create Product]

D  -->  E[Product Service: Product Created ]

C  -- No -->  F[Product Service: Product Already Exists ]
```
### Feature 2 : List All Product
#### User Story:
 User can view the product list.
#### Prerequisites:
- [ ] Complete Product Module Feature 1.
- [ ] Implement Product DAO (Find All)

#### Validations:
#####   Business Validation:
- [ ] Check the Product table is already exists or not.
#### Messages: 
 -  Product Table Not Found.

#### Flow: 

```mermaid
graph  TD;

A[Product Service: Find All Product] -->  B{Business Validation}

B  -- Yes -->  C[Product DAO: Read All Products ]

C  -->  D[Product Service: Display Product Details ]

B  -- No -->  F[Product Service: Table Doesn't Exists ]
```
### Feature 3 : Update Product Details
#### User Story:
 User can update product detail.
#### Prerequisites:
- [ ] Complete Product Module Feature 1.
- [ ] Implement Product DAO (Update)


#### Validations:

##### Form Validation:

- [ ]  Product Name (Null , Pattern & Length)
- [ ] Product ID ( ID <= 0)
- [ ] Quantity ( Quantity <= 0)
- [ ] Quantity Type (ENUM - mg , ml , Nos)
- [ ] Special Name (Null , Pattern & Length)
#####   Business Validation:
- [ ] Check The product is exists or not.
#### Messages: 
 -  Product is Not Found
 - Product ID cannot be less than or equal to 0
 -  Name cannot be Null or Empty.
 -  Quantity cannot be less than or equal to 0.
 -  Choose any Quantity Type.
 -  Special Name cannot be Null or Empty.


#### Flow: 

```mermaid
graph  TD;

A[Product Service: Update Product]  -->  B[Form Validation]

B  -- Yes -->  C{Business Validation}
B -- No --> G[Throws Exception]

C  -- Yes -->  D[Product DAO: Update Product]

D  -->  E[Product Service: Product Updated]

C  -- No -->  F[Product Service: Product Not Found ]
```
### Feature 4 : Delete Product Details
#### User Story:
User can delete a product.
#### Prerequisites:
- [ ] Complete Product Module Feature 1.
- [ ] Implement Product DAO (Delete)


#### Validations:

##### Form Validation:

- [ ] Product ID ( ID <= 0)
#####   Business Validation:
- [ ] Check The product is exists or not.
#### Messages: 
 -  Product Not Found
 -  Product ID cannot be less than or equal to 0



#### Flow: 

```mermaid
graph  TD;

A[Product Service: Delete  Product]  -->  B[Form Validation]
B  -- Yes -->  C{Business Validation}
B -- No --> G[Throws Exception]

C  -- Yes -->  D[Product DAO: Delete Product]

D  -->  E[Product Service: Product Deleted]

C  -- No -->  F[Product Service: Product Not Found ]
```
### Feature 5 : Product Details
#### User Story:
 User can view the single product details.
#### Prerequisites:
- [ ] Complete Product Module Feature 1.
- [ ] Implement Product DAO (Find By ID)


#### Validations:

##### Form Validation:

- [ ] Product ID ( ID <= 0)
#####   Business Validation:
- [ ] Check The product is exists or not.
#### Messages: 
 -  Product Not Found
 -  Product ID cannot be less than or equal to 0

#### Flow: 

```mermaid
graph  TD;

A[Product Service: Product Details]  -->  B[Form Validation]

B  -- Yes -->  C{Business Validation}
B -- No --> G[Throws Exception]

C  -- Yes -->  D[Product DAO: Read A Product]

D  -->  E[Product Service: Display Product Details]

C  -- No -->  F[Product Service: Product Not Found ]
```

## Module 2 : Price (Milestone 1)
### Feature 1: Price Creation 
#### User Story:
 User can create new Price.
#### Prerequisites:
- [ ] Create Price table
- [ ] Implement Price model
- [ ] Implement Price DAO (create)


#### Validations:

##### Form Validation:

- [ ] MRP (MRP <= 0)
- [ ] Tax (Tax <= 0 and Tax > 100)
- [ ]  Discount (Discount <= 0 and Discount > 100)

#####   Business Validation:
- [ ] Check the Price table is exists or not.
#### Messages: 
 -  MRP cannot be less than or equal to 0.
 -  Tax cannot be less than or equal to 0.
 -  Discount cannot be less than or equal to 0.
 -  Tax cannot be greater than 100.
 -  Discount cannot be greater than 100.

#### Flow: 


```mermaid
graph  TD;

A[Price Service: Create Price]  -->  B[Form Validation]

B  -- Yes -->  C{Business Validation}
B -- No --> G[Throws Exception]

C  -- Yes -->  D[Price DAO: Create Price]

D  -->  E[Price Service: Price Created ]

C  -- No -->  F[Price Service: Product Doesn't Exists ]
```
### Feature 2 : List All Price
#### User Story:
 User can view the Price list.
#### Prerequisites:
- [ ] Complete Price Module Feature 1.
- [ ] Implement Price DAO (Find All)
#### Validations:
#####   Business Validation:
- [ ] Check the Price table is already exists or not.
#### Messages: 
 -  Price Table Not Found.


#### Flow: 

```mermaid
graph  TD;

A[Price Service: Find All Price] -->  B{Business Validation}

B  -- Yes -->  C[Price DAO: Read All Price]

C  -->  D[Price Service: Display Price Details ]

B  -- No -->  F[Price Service: Table Doesn't Exists ]
```
### Feature 3 : Update Price Details
#### User Story:
 User can update Price.
#### Prerequisites:
- [ ] Complete Price Module Feature 1.
- [ ] Implement Price DAO (Update)

#### Validations:

##### Form Validation:

- [ ] Price ID (ID<= 0)
- [ ] MRP (MRP <= 0)
- [ ] Tax (Tax <= 0 and Tax > 100)
- [ ] Discount (Discount <= 0 and Discount > 100)

#####   Business Validation:
- [ ] Check The Price Detail is exists.
#### Messages: 
 -  Price ID is Invalid.
 -  MRP cannot be less than or equal to 0.
 -  Tax cannot be less than or equal to 0.
 -  Discount cannot be less than or equal to 0.
 -  Tax cannot be greater than 100.
 -  Discount cannot be greater than 100.


#### Flow: 

```mermaid
graph  TD;

A[Price Service: Update Price]  -->  B[Form Validation]

B  -- Yes -->  C{Business Validation}
B -- No --> G[Throws Exception]
C  -- Yes -->  D[Price DAO: Update Price]

D  -->  E[Price Service: Price Updated]

C  -- No -->  F[Price Service: Product Not Found ]
```
## Module: User (Milestone 2)


### Feature 1: User Creation 
#### User Story:
 Shop can create new User or Customer .
#### Prerequisites:
- [ ] Create User table
- [ ] Implement User model
- [ ] Implement User DAO (create)
#### Validations:

##### Form Validation:
- [ ] Name (Null , Pattern & Length)
- [ ] Phone Number ( Pattern )
- [ ] Email ( Null , Pattern)  **(Optional)**
- [ ] Address (Null , Empty , Length) **(Optional)**
#####   Business Validation:
- [ ] Check The user is already exists using phone number
#### Messages: 
 -  Name Cannot be Null or Empty.
 -  Phone Number doesn't match the Pattern.
 -  Email doesn't match the Pattern.
 -  Address Cannot be Empty. 


#### Flow: 

```mermaid
graph  TD;

A[User Service: Create User]  -->  B[Form Validation]

B  -- Yes -->  C{Business Validation}
B -- No --> G[Throws Exception]

C  -- Yes -->  D[User DAO: Create User]

D  -->  E[User Service: User Created ]

C  -- No -->  F[User Service: User Already Exists ]
```
### Feature 2 : List All Users
#### User Story:
 User can view the Customer List.
#### Prerequisites:
- [ ] Complete User Module Feature 1.
- [ ] Implement User DAO (Find All)

#### Validations:
#####   Business Validation:
- [ ] Check the User table is already exists or not.
#### Messages: 
 -  User Table Not Found.

#### Flow: 

```mermaid
graph  TD;

A[User Service: Find All User] -->  B{Business Validation}

B  -- Yes -->  C[User DAO: Read All Users]

C  -->  D[User Service: Display User Details ]

B  -- No -->  F[User Service: Table Doesn't Exists ]
```
### Feature 3 : Update User Details
#### User Story:
 Shop can update User or Customer details.
#### Prerequisites:
- [ ] Complete User Module Feature 1.
- [ ] Implement User DAO (Update)
#### Validations:

##### Form Validation:
- [ ] Name (Null , Pattern & Length)
- [ ] Phone Number ( Pattern )
- [ ] Email ( Null , Pattern)  **(Optional)**
- [ ] Address (Null , Empty , Length) **(Optional)**
#####   Business Validation:
- [ ] Check the user is exists or not.

#### Messages: 
 -  Name Cannot be Null or Empty.
 -  Phone Number doesn't match the Pattern.
 -  Email doesn't match the Pattern.
 -  Address Cannot be Empty. 


#### Flow: 

```mermaid
graph  TD;

A[User Service: Update User]  -->  B[Form Validation]

B  -- Yes -->  C{Business Validation}
B -- No --> G[Throws Exception]

C  -- Yes -->  D[User DAO: Update User]

D  -->  E[User Service: User Successfully Updated]

C  -- No -->  F[User Service: User Not Found ]
```
### Feature 4 : Find User By Phone Number
#### User Story:
User can view the single User details.
#### Prerequisites:
- [ ] Complete User Module Feature 1.
- [ ] Implement User DAO (find by phone number)


#### Validations:

##### Form Validation:

- [ ] Phone Number ( Pattern )
#####   Business Validation:
- [ ] Check the User is exists or not.
#### Messages: 
 -  User Not Found
 -  Phone number doesn't  match the pattern.



#### Flow: 

```mermaid
graph  TD;

A[User Service: Get User by phone number]  -->  B[Form Validation]
B  -- Yes -->  C{Business Validation}
B -- No --> G[Throws Exception]

C  -- Yes -->  D[User DAO: find user by phone number]

D  -->  E[User Service: Display user details]

C  -- No -->  F[User Service: User Not Found ]
```


## Module 2 : Bill (Milestone 2)
### Feature 1: Bill Creation 
#### User Story:
 User can create new Bill.
#### Prerequisites:
- [ ] Create Bill table
- [ ] Implement Bill model
- [ ] Implement Bill DAO (create)
- [ ] Complete Product Module Feature 1.
- [ ] Complete User Module Feature 1.
- [ ] Complete Bill Details Module Feature 1.



#### Validations:

##### Form Validation:

- [ ] User Id ( id<1 )
- [ ] Time Stamp
- [ ] Bill DTO ( null )

#### Messages: 
 -  Invalid User ID.
 -  User doesn't exists.
 -  Invalid Bill Details.

#### Flow: 


```mermaid
graph  TD;

A[Bill Service: Create Bill]  -->  B[Form Validation]
B  -- Yes -->  D[Bill DAO: Create Bill]
B -- No --> F[Throws Exception]
D  -->  E[Bill Service: Bill Created ]

```

### Feature 2 : Find bills by user phone number
#### User Story:
User can find bill details by using user phone number.
#### Prerequisites:
- [ ] Complete User Module Feature 1.
- [ ] Complete Bill Module Feature 1.
- [ ] Implement Bill DAO (find by phone number)


#### Validations:

##### Form Validation:
- [ ] Phone Number ( Pattern )
- [ ] Id ( id<1 )
#####   Business Validation:
- [ ] Check the User is exists or not.
- [ ] Check Bill Table exists or not.
#### Messages: 
 - Phone number doesn't  match the pattern.
 -  User Not Found
 -  Invalid User ID




#### Flow: 

```mermaid
graph  TD;

A[User Service: Get User by phone number]  -->  B[Form Validation]
B  -- Yes -->  C{Business Validation}
B -- No --> G[Throws Exception]

C  -- Yes -->  D[User DAO: find user by phone number]

D  -->  E[User Service: Get User ID ]
E-->H
C  -- No -->  F[User Service: User Not Found ]
H[Bill Service: Get Bill by User ID]  -->  I[Form Validation]
I  -- Yes -->  K[Bill DAO: find Bill by User ID]

K  -->  L[Bill Service: Display Bill Details]
I -- No --> M[Throws Exception]


```

### Feature 3 : Find All Recent Bills
#### User Story:
 User can View the recent Bills.
#### Prerequisites:
- [ ] Complete Bill Module Feature 1.
- [ ] Implement Bill DAO (Find All Recent Bills)

#### Validations:

#####   Business Validation:
- [ ] Check the Bill and Bill Details Table Exists or Not.
- [ ] Check the Time zone and Select Last 10 Minutes Bills
#### Messages: 
 - Bill table not Found.
 - Bill Details table not Found.
 - There is no Recents Bills.


#### Flow: 

```mermaid
graph  TD;

A[Bill Service: Get Recent bills]  

A  -->  C{Business Validation}
C  -- Yes -->  D[Bill DAO: Find all Recent Bills ]

D  -->  E[Bill Service: Display Recent Bills]

C  -- No -->  F[Bill Service: There is no recent bills ]
```
## Module 3 : Bill Details (Milestone 2)
### Feature 1: Bill Details Creation 
#### User Story:
 User can create new Bill Details.
#### Prerequisites:
- [ ] Create Bill Details table
- [ ] Implement Bill DTO
- [ ] Implement  Bill Details DAO (create)
- [ ] Complete Bill Module Feature 1.


#### Validations:

##### Form Validation:

 - [ ] Bill ID (id < 1)
 
#####   Business Validation:
- [ ] Check the Bill table is exists or not.
- [ ] Check the Bill  Details table is exists or not.
#### Messages: 
 -  Invalid Bill ID.
 -  Bill table not found.
 - Bill details table not found.

#### Flow: 


```mermaid
graph  TD;

A[Bill Details Service: Get bill details by bill ID]  -->  B[Form Validation]

B  -- Yes -->  C{Business Validation}
B -- No --> G[Throws Exception]

C  -- Yes -->  D[Bill Details DAO: Find bill details by bill ID]

D  -->  E[Bill Details Service: Display the Details ]

C  -- No -->  F[Bill Details Service: Bills not found ]
```


