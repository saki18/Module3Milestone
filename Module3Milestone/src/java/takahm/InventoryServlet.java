/*
 * Masaki Takahashi
 * CITP 290
 * Milestone 2 
 * Java Web Application that allows you to view the list of products by running
 * your application and going to http://localhost:8080/store/inventory in a 
 * web browser on your computer.  You should be able to view and use the form for
 * adding products by going to http://localhost:8080/store/inventoryForm.html
 */
package takahm;

import edu.lcc.citp.inventory.InventoryManager;
import edu.lcc.citp.inventory.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "InventoryServlet", urlPatterns = {"/inventory"})
/** 
 InventoryServlet class that extends the HttpServelet class. The servlet container
 runs and control the execution of the servlet through the methods defined in the 
 HttpServlet class. This will allow you to create, edit and delete your inventory 
 utilizing the inventory.jsp. 
 */
public class InventoryServlet extends HttpServlet {

    InventoryManager i = new InventoryManager();

    // do post will input the information. 
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String button = req.getParameter("button");

        // creates variable 
        Product p = new Product();
        p.setShortDetails(req.getParameter("shortDetails"));
        p.setLongDetails(req.getParameter("longDetails"));
        p.setUpc(req.getParameter("upc"));

        // the try catchm exception handler. 
        try {
            p.setPrice(BigDecimal.valueOf(Double.parseDouble(req.getParameter("price"))));
        } catch (NumberFormatException numberFormatException) {
            p.setPrice(null);
        }
        try {
            p.setStock(Integer.parseInt(req.getParameter("stock")));
        } catch (NumberFormatException numberFormatException) {
            p.setStock(null);
        }
        // the create button, you need it. 
        if (button.equals("Create")) {
            try {
                i.addProduct(p);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            // this will create the edit buttong and update. 
        } else if (button.equals("Edit")) {
            try {
                i.updateProduct(p);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            // this will delete and remove the product. 
        } else if (button.equals("Delete")) {
            try {
                i.removeProduct(p.getUpc());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        // printer out will tell you Product Saved! 
        PrintWriter out = resp.getWriter();
        out.print("Product saved!");

        resp.sendRedirect("inventory.jsp");
    }

    // do get will go ahead and get the information the user wants. 
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Product> products = null;
        try {
            products = i.getProductList();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        // the following will be the options. 
        PrintWriter out = resp.getWriter();
        for (Product p : products) {
            out.print("<p>");
            out.println("UPC: " + p.getUpc());
            out.println("Short Details: " + p.getShortDetails());
            out.println("Long Details: " + p.getLongDetails());
            out.println("Price: " + p.getPrice());
            out.println("Stock: " + p.getStock());
            out.print("</p>");
        }
    }
}
