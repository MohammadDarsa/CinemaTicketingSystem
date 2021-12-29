<h1>This file is a reference to use while using DBManager class</h1>

<p>There will be 3 sections:</p>
<ol>
    <li>Entity class section</li>
    <li>Attributes</li>
    <li>Excuting queries using DBManager</li>
</ol>

<div id="section1">
    <h2>Entity Class</h2>
    <p>An entity class is a class that represents a table in the database.  Each object created of this class can represent 1 record in the table it maps to. 3 main annotations are created
        to keep track of the table name, the id and the table columns.
    </p>
<ul>
    <li>Table</li>    
    <li>ID</li>    
    <li>Col</li>    
</ul>
<p>An example of creating an entity class:</p>
<p>In our database we got a table called <b>movie</b>, with the columns:</p>
<ul>
    <li><b>id_movie</b></li>
    <li><b>name_movie</b></li>
    <li><b>desc_movie</b></li>
    <li><b>tor_movie</b></li>
    <li><b>price_movie</b></li>
</ul>
<h3>Here's an example of our Movie Entity Class:</h3>

```java
@Table("movie")
public class Movie {
    @ID
    @Col(name = "id_movie")
    private String id;

    @Col(name = "name_movie", updateIgnore = true)
    private String name;

    @Col(name = "desc_movie", insertIgnore = true)
    private String desc;

    @Col(name = "tor_movie")
    private String tor;

    @Col(name = "price_movie")
    private String price;

    public Movie() {
    }
}
```
<h3><b>THE EMPTY CONSTRUCTOR IS IMPORTANT, YOU CAN ALSO ADD OTHER CONSTRUCTORS AS YOU SEE FIT.</b></h3>
<p>updateIgnore and insertIgnore makes the annotated field ignore being updated or inserted by queries in the database.</p>
</div>

<div id="section2">
    <h1>Attributes</h1>
    <p>The Attributes class can store query conditionals and fields, best explained using an example</p>

```java
Attributes attributes = new Attributes("table_name");
attributes.addAttribute("col_name", "value");
```
<p>These attributes will be processed by functions in the DBManager class, these can be used to filter out records for example:</p>

```java
Attributes attributes = new Attributes("movie");
attributes.addAttribute("name_movie", "A Silent Voice");
List<Movie> movies = dbManager.selectAll(Movie.class, attributes);
```
<p>The list will then be filled with movies that have name_movie = "A Silent Voice". Multiple attributes can be set and queried. example:</p>

```java
Attributes attributes = new Attributes("movie");
attributes.addAttribute("name_movie", "A Silent Voice");
attributes.addAttribute("desc_movie", "Cute Anime Movie");
List<Movie> movies = dbManager.selectAll(Movie.class, attributes);
```
<p>Here the list will be filled with movies that have both name_movie = "A Silent Voice" <b>and</b> desc_movie = "Cute Anime Movie".</p>
</div>
<div id="section3">
    <h1>DBManager Class</h1>
<p>DBManager class is a singleton responsible for connecting and querying the database. A list of it's functionalities can found below:</p>
<ul>
    <li>Insert an entity</li>
    <li>Update an entity</li>
    <li>Delete an entity</li>
    <li>Retrieve all elements from a table</li>
</ul>
<p>All the above functionalities can be modified with using attributes as mentioned before, more about this in the examples below:</p>
<div>
    <h2>Insert An Entity</h2>
    <p>Simply create an entity and use the insertEntity method. We can also set ignoreInsert to true in the Customer Entity class to any field and it will stop it from being inserted.</p>

```java
DBManager dbManager = DBManager.getInstance();
Customer lina = new Customer("laluna", "somePassword", "laluna11@gmail.com", "111122000");
dbManager.insertEntity(lina, Customer.class);
```
</div>

<div>
    <h2>Update An Entity</h2>
    <p>Simply create an entity and use the updateEntity method, this entity should have the same id as an entity represented in the table. In this example the password got updated as the rest of the fields are still the same. We can also set ignoreUpdate to true in the Customer Entity class to any field and it will stop updating.</p>

```java
DBManager dbManager = DBManager.getInstance();
Customer lina = new Customer("1", "laluna", "newPassword", "laluna11@gmail.com", "111122000");
dbManager.updateEntity(lina, Customer.class);
```
</div>

<div>
    <h2>Delete An Entity</h2>
    <p>There are 2 ways to delete an entity using deleteEntity method. Either provide an entity with a valid id and it will be deleted from the database, or use the attributes to search for the entities you want to delete. Here's some examples:</p>

```java
DBManager dbManager = DBManager.getInstance();
Customer someCustomer = new Customer("1", "maybeLina", "somePassword", "hmmmm", "idk");
dbManager.deleteEntity(someCustomer, Customer.class);
```
<p>After this, customer with id = "1" will be deleted, as you noticed the other fields don't matter, the username can be anything as this won't affect the query.</p>
<p>The other way is to provide attributes, example:</p>


```java
DBManager dbManager = DBManager.getInstance();
Attributes attributes = new Attributes("customer");
attributes.addAttribute("username_customer", "laluna");
attributes.addAttribute("email_customer", "laluna11@gmail.com");
dbManager.deleteEntity(attributes);
```
<p>This is equivalent to:</p>

```sql
delete from customer where username_customer = "laluna" and email_customer = "laluna11@gmail.com";
```
<p>Attributes are usually added after the <b>where</b> keyword to apply filtering, the default joint is "and" and the default operator is "=", these can also be changed.</p>
</div>

<div>
        <h2>Retrieve All Elements From a Table</h2>
        <p>There are few ways to do this using selectAll method. We can use attributes to filter our table, or we can simply provide the entity class and get all the records. It will always retrieve a list of the specified entity class. Here's some examples:</p>

```java
DBManager dbManager = DBManager.getInstance();
List<Customer> customers = dbManager.selectAll(Customer.class);
```
<p>This retrieves all the customers table as a list of customers. Let's use attributes next:</p>


```java
DBManager dbManager = DBManager.getInstance();
Attributes attributes = new Attributes("customer");
attributes.addAttribute("username_customer", "laluna");
attributes.addAttribute("email_customer", "laluna11@gmail.com");
List<Customer> customers = dbManager.selectAll(Customer.class, attributes);
```
<p>This will retrieve all the customers having username_customer = "laluna" and email_customer = "laluna11@gmail.com". SQL version:</p>

```sql
select * from customer where username_customer = "laluna" and email_customer = "laluna11@gmail.com";
```
<p>We can also modify the joint and the operator as follow:</p>

```java
DBManager dbManager = DBManager.getInstance();
Attributes attributes = new Attributes("customer");
attributes.addAttribute("username_customer", "laluna");
attributes.addAttribute("email_customer", "laluna11@gmail.com");
List<Customer> customers = dbManager.selectAll(Customer.class, attributes, "or", "like");
```
<p>This is now equivalent to:</p>

```sql
select * from customer where username_customer like "laluna" or email_customer like "laluna11@gmail.com";
```

</div>

<div>
    <h2>More on executing custom queries soon.</h2>
</div>

</div>