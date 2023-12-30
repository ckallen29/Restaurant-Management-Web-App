# WESTERN GOVERNOR UNIVERSITY 
## D287 – JAVA FRAMEWORKS

Cheyenne Allen, ID#011016512

PA Requirements are as follows…

**A.  Create your subgroup and project by logging into GitLab using the web link provided and using the “GitLab How-To” web link, and do the following:**
    •  Clone the project to the IDE.
    •  Commit with a message and push when you complete each of the tasks listed below (e.g., parts C to J).
        Note: You may commit and push whenever you want to back up your changes, even if a task is not complete.
    •  Submit a copy of the Git repository URL and a copy of the repository branch history retrieved from your repository, which must include the commit messages and dates.
        Note: Wait until you have completed all the following prompts before you create your copy of the repository branch history.

**B.  Create a README file that includes notes describing where in the code to find the changes you made for each of parts C to J. Each note should include the prompt, file name, line number, and change.**

README.md
    All: Added README.md and begin tracking changes made per assignment requirement under each section

**C.  Customize the HTML user interface for your customer’s application. The user interface should include the shop name, the product names, and the names of the parts.**
    Note: Do not remove any elements that were included in the screen. You may add any additional elements you would like or any images, colors, and styles, although it is not required.

mainscreen.html
    14: Changed title to shop name
    19: Changed shop name
    21: Changed "Parts" header to "Additions"
    40-51: Added part names
    88-99: Added product names

**D.  Add an “About” page to the application to describe your chosen customer’s company to web viewers and include navigation to and from the “About” page and the main screen.**

about.html
    All: Added about.html. Added navigation between mainscreen.html and about.html

mainscreen.html
    20-25: Added navigation between mainscreen.html and about.html

**E.  Add a sample inventory appropriate for your chosen store to the application. You should have five parts and five products in your sample inventory and should not overwrite existing data in the database.**
    Note: Make sure the sample inventory is added only when both the part and product lists are empty. When adding the sample inventory appropriate for the store, the inventory is stored in a set so duplicate items cannot be added to your products. When duplicate items are added, make a “multi-pack” part.

BootStrapData.java
    42-143: Added parts instances with if statement that checks if the partRepository is empty before adding parts
    150-162: Added product instances with if statement that checks if the productRepository is empty before adding products

**F.  Add a “Buy Now” button to your product list. Your “Buy Now” button must meet each of the following parameters:**
    •  The “Buy Now” button must be next to the buttons that update and delete products.
    •  The button should decrement the inventory of that product by one. It should not affect the inventory of the associated parts.
    •  Display a message that indicates the success or failure of a purchase.

mainscreen.html
    91-92: Added "Buy Now" button to product list next to "Update" and "Delete" buttons

confirmationbuyproduct.html
    All: Added purchase confirmation page after "Buy Now" confirmation with navigation back to mainscreen

errorbuyproduct.html
    All: Added purchase error after "Buy Now" confirmation, triggered when inventory of product is 0 and cannot be purchased, with navigation back to mainscreen

ProductServiceImpl.java
    69-84: Created "buyProduct" method to decrement product inventory in repository, taking the productId as a parameter, referencing the object, getting the inventory count, decrementing by 1, setting the inventory count, and saving the object back to the repository

ProductService.java
    20: Added "buyProduct" method to interface

AddProductController.java
    32-33: Injected ProductRepository into controller
    129-149: Created the controller for the "buyProduct" method, passing in the ProductId parameter and creating a productService referencing the ProductServiceImpl as a class, checks the inv attribute is greater than 0, then redirecting to the product purchase confirmation page if purchase is confirmed and decrementing inventory of the product; if not, redirects to the product purchase error page

**G.  Modify the parts to track maximum and minimum inventory by doing the following:**
    •  Add additional fields to the part entity for maximum and minimum inventory.
    •  Modify the sample inventory to include the maximum and minimum fields.
    •  Add to the InhousePartForm and OutsourcedPartForm forms additional text inputs for the inventory so the user can set the maximum and minimum values.
    •  Rename the file the persistent storage is saved to.
    •  Modify the code to enforce that the inventory is between or at the minimum and maximum value.

Part.java
    32-34: Added maxInv and minInv attributes to Part class with error message on min value
    49-50, 58-59: Added maxInv and minInv to both class constructors
    93-100: Created getter and setter methods for maxInv and minInv

BootStrapData.java
    76-77, 86-87, 96-97, 106-107, 116-117: Modified sample inventory to include max and min inventory fields for each part

OutsourcedPartForm.html
    8-23: Created javascript function to check inv against maxInv and throw an alert when failed
    28-29: Added onsubmit functionality to form to run validateInv()
    42-47: Added id's to "inv" and "maxInv" for easy selection, added "maxInv" and "minInv" input fields

InhousePartForm.html
    8-23: Created javascript function to check inv against maxInv and throw an alert when failed
    27-29: Added onsubmit functionality to form to run validateInv()
    42-47: Added id's to "inv" and "maxInv" for easy selection, added "maxInv" and "minInv" input fields

application.properties
    6: Renamed the file of persistent storage to "d287-db"

**H.  Add validation for between or at the maximum and minimum fields. The validation must include the following:**
    •  Display error messages for low inventory when adding and updating parts if the inventory is less than the minimum number of parts.
    •  Display error messages for low inventory when adding and updating products lowers the part inventory below the minimum.
    •  Display error messages when adding and updating parts if the inventory is greater than the maximum.

OutsourcedPartForm.html, InhousePartForm.html
    8-23: Remove temporary max logic

EnufPartsValidator.java
    37-38: Validate that inventory is not below the minimum or above maximum

AddOutsourcedPartController.java, AddInhousePartController.java
    49-60: Add logic so inventory cannot be set over max or below min, and return error message if attempted
    (Correction) 49-52: Added logic to prevent user-entered inv from exceeding max
    (Correction) 54, 59: Corrected comparison to user-entered form data, not repository, so user-entered data cannot contradict

**I.  Add at least two unit tests for the maximum and minimum fields to the PartTest class in the test package.**

PartTest.java
    103-138: Create tests for getMaxInv, setMaxInv, getMinInv, and setMinInv

**J.  Remove the class files for any unused validators in order to clean your code.**

The following documents each instance of a removed class file and what line it was removed from…

AddProductController.java
    35: private List<Part> theParts;

MainScreenControllerr.java
    25: Remove private final PartRepository partRepository;
    26: private final ProductRepository productRepository;'
    31: private List<Part> theParts;
    32: private List<Product> theProducts;
    34-37: public MainScreenControllerr(PartRepository partRepository, ProductRepository productRepository)

InhousePartService.java
    19: public void deleteById(int theId);

InhousePartServiceImpl.java
    58-62: public void deleteById(int theId)

DeletePartValidator.java
    All: File removed