Side Panel
Expand side panel
Breadcrumb:

    Table of Contents Module 3 - Working With and Storing Data Module 3 Milestone Review

Module 3 Milestone Review
Hide Description

Submission Due By

11:59 PM on Wednesday, March 30th

Reviews Due By

11:59 PM on Saturday, April 2nd

Milestone Requirements

    Create a copy of your Module 2 Project and name it "Module 3 Milestone", or make a copy of the Module 2 Project example provided by the instructor.
    Create one new interface and four new classes as shown in the following UML Class Diagram...

    Implementing these classes...
        DataAccessObject must include a generic type parameter in its declaration (i.e. "public interface DataAccessObject<E>").  This allows you to use "E" within DataAccessObject.java as a placeholder for whatever the implementing class uses for "E".
        Note that even though FileProductDao and FileUserDao don't declare any methods themselves, they are required to include the five methods declared by DataAccessObject.  Wherever you see "E" in DataAccessObject it should be replaced by "Product" or "User" as appropriate.
        The five methods of FileProductDao are nearly identical to the five methods of InventoryManager, so you can re-use that code here.  FileUserDao will be very similar, except it will use the User class instead.
        The two DataAccessObjectFactory methods are static.  Also, don't overthink these methods!  All they need to do is return a new object of the requested type.
        User's isAdministrator() should return true if the roles Set contains the String "ADMIN".  isInventoryManager() should return true if it contains "INV_MAN" or if isAdministrator() is true.  In other words, all administrators are also inventory managers, but not all inventory managers are administrators.
    Update "InventoryServlet" and "inventory.jsp" as follows...
        Have InventoryServlet override the HttpServlet "init()" method.  Inside of "init()", use DataAccessObjectFactory to retrieve a Product DAO, and use "this.getServletContext().setAttribute("productDao", yourProductDaoVariable)" to store the DAO as an application-scoped bean.
        Inside of inventory.jsp replace your InventoryManager bean with the new bean named "productDao" with the type of "DataAccessObject".  We will not be using InventoryManager any more.
    Create a "UserServlet"  (path of "/users") and a "users.jsp" that behave similar to InventoryServlet and inventory.jsp.
        On users.jsp, make sure you don't display any user passwords; replacing them with "********" is fine.  Also, use checkboxes labeled "Administrator" and "Inventory Manager" to set the roles.
    Create a "LoginServlet" (path of "/login") and a "login.jsp"...
        LoginServlet's doGet method should redirect to login.jsp.
        LoginServlet's doPost should check for "username" and "password" parameters.
            If the parameters exist then it should check to see if they match a username and password of an existing User.  If they do match then the matching User object should be assigned to the session as the attribute "currentUser".
            If the parameters exist but do not match then it should redirect to "login.jsp?failed=true".
            If the parameters do not exist then it should check for the parameter "logout".  If the value of logout is "true" (the String, not the boolean) then the session attribute of "currentUser" should be set to null and it should redirect to "login.jsp".
        login.jsp should contain a form with two fields (Username and Password) and post to action "login".  If the parameter "failed" has the value "true" (the String, not the boolean) then the page should display an error message such as "The username or password provided are not valid."
    Modify inventory.jsp, users.jsp, InventoryServlet, and UserServlet redirect to login.jsp if the session attribute "currentUser" is null.  They should not display data.  Additionally, inventory.jsp and InventoryServlet should check to see if the currentUser has the inventory manager role, and users.jsp and UserServlet should check for the administrator role.

