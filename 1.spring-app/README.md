# **Web Application:**

### 1.Add dependency:

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>

### 2.Create html files:
create a directory **templates** under resources, add html files<br>
 `<html lang="en" xmlns:th=http://www.thymeleaf.org> `

### 3.Registering Mappings and Views:

Create a **WebConfig.java** File,<br>
        Mappings(URL) and Views(Page to be rendered) are simplified when using **thymeleaf** as **View Technology**.

        @Configuration
        public class WebConfig implements WebMvcConfigurer {
                @Override
                public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController(Mappings.WELCOME).setViewName(ViewNames.WELCOME_VIEW);
                registry.addViewController(Mappings.REDIRECT).setViewName(ViewNames.REDIRECT_VIEW);
                }
        }

When you hit the URL, the web page will be rendered directly.

### 4.Getting values through URl and use the value in Views:

Edit the controller methods and html file,
<br>**DemoController.java**:

        @Controller
        public class DemoController {
                @GetMapping("/")
                public String welcome(@RequestParam String UserName, Model model){      //#1
                        model.addAttribute(AttributeNames.USERNAME,UserName);   //AttributeNames.USERNAME = "USERNAME"
                        return ViewNames.WELCOME_VIEW;  //ViewNames.WELCOME_VIEW ="welcome"
                }
        }

#1: through @RequestParam, we can receive value from URL 
    <br>using model, we can manipulate values between web page and controller - Adding value to the model and the controller method will render the page along with those values.

**welcome.html**:

        <!DOCTYPE html>
        <html lang="en" xmlns:th=http://www.thymeleaf.org>
        <head>
            <meta charset="UTF-8">
            <title>Home</title>
        </head>
        <body>
        <h1 th:text="${USERNAME}">replacing text</h1>   <!--#1-->
        </body>
        </html>

#1: the text between h1 tags will be replaced by the value from the model.

To test,
        http://localhost:8080/?UserName=Value   (No " " or ' ')

#### Another method:
Exposing the value **globally**(_all web pages_) Via **@ModelAttribute**

1.String Value:
<br>**DemoController.java**:

        @Controller
        public class DemoController {

            @GetMapping("/")
            public String welcome(@RequestParam String UserName, Model model){
                model.addAttribute(AttributeNames.USERNAME,UserName);
                return ViewNames.WELCOME_VIEW;
            }

            @ModelAttribute("Message")
            public String Message(){
                return " Welcome to web app";
            }
        }

**welcome.html**:

        <!DOCTYPE html>
        <html lang="en" xmlns:th=http://www.thymeleaf.org>
        <head>
            <meta charset="UTF-8">
            <title>Home</title>
        </head>
        <body>
        <h1 th:text="${USERNAME}">replacing text</h1>   
        <h1 th:text="${Message}">replacing text</h1>
        </body>
        </html>

2.Collection Values:
<br>**DemoController.java**:

        @Controller
        public class DemoController {
            List<String> strings = List.of("Paul","D","Timothy");

            public List<String> getStrings() {      //getter
                return strings;
            }

            @GetMapping("/")
            public String welcome(@RequestParam String UserName, Model model){
                model.addAttribute(AttributeNames.USERNAME,UserName);
                return ViewNames.WELCOME_VIEW;
            }

            @ModelAttribute("Message")
            public String Message(){
                return " Welcome to web app";
            }

            @ModelAttribute("demoController")
            public DemoController demoController(){
                return new DemoController();    //#1
            }
        }

#1: exposing an object of the class

**welcome.html**:

        <!DOCTYPE html>
        <html lang="en" xmlns:th=http://www.thymeleaf.org>
        <head>
            <meta charset="UTF-8">
            <title>Home</title>
        </head>
        <body>
        <div align="center">
            <h1 th:text="${UserName}">replacing text</h1>
            <h1 th:text="${Message}">replacing text</h1>
        
            <table border="1" cellpadding="5">                  <!--#1-->
                <caption><h2>String Values</h2></caption>
                <tr>
                    <th>content</th>
                </tr>
        
                <tr th:each="Data: ${demoController.getStrings()}">     <!--#2-->
                    <td th:text="${Data}"/>
                </tr>
            </table>
        </div>
        </body>
        </html>

#1: create a table to display the value(s)
#2: the "for each" version of thymeleaf

### 5.Exposing an external link:

**welcome.html**:

        <!DOCTYPE html>
        <html lang="en" xmlns:th=http://www.thymeleaf.org>
        <head>
            <meta charset="UTF-8">
            <title>Home</title>
        </head>
        <body>
        <div align="center">
            <h1 th:text="${UserName}">replacing text</h1>
            <h1 th:text="${Message}">replacing text</h1>
        
            <table border="1" cellpadding="5">                  <!--#1-->
                <caption><h2>String Values</h2></caption>
                <tr>
                    <th>content</th>
                </tr>
        
                <tr th:each="Data: ${demoController.getStrings()}">     <!--#2-->
                    <td th:text="${Data}" />
                </tr>
            </table>
        
            <a th:text="Link" href="https://github.com/paultimothy12">Click here</a>    <!--#3-->
        </div>
        </body>
        </html>

#1: create a table to display the value(s)
#2: the "for each" version of thymeleaf
#3: Exposing an external link

### 6.Redirecting to other page Via Link:

**welcome.html**:

        <!DOCTYPE html>
        <html lang="en" xmlns:th=http://www.thymeleaf.org>
        <head>
            <meta charset="UTF-8">
            <title>Home</title>
        </head>
        <body>
        <div align="center">
            <h1 th:text="${UserName}">replacing text</h1>
            <h1 th:text="${Message}">replacing text</h1>
        
            <table border="1" cellpadding="5">                  <!--#1-->
                <caption><h2>String Values</h2></caption>
                <tr>
                    <th>content</th>
                </tr>
        
                <tr th:each="Data: ${demoController.getStrings()}">     <!--#2-->
                    <td th:text="${Data}" />
                </tr>
            </table>
        
            <a th:text="Link" href="https://github.com/paultimothy12">Click here</a>    <!--#3-->
            <a th:text="RedirectPage" th:href="redirect">replacing text</a>     <!--#4-->
        </div>
        </body>
        </html>

#1: create a table to display the value(s)
#2: the "for each" version of thymeleaf
#3: Exposing an external link
#4: Exposing a redirect page as a link

### 7.Form and Manipulating Input:

**redirect.html**:

        <!DOCTYPE html>
        <html lang="en" xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>ADD PAGE</title>
        </head>
        <body>
        <div align="center">
        
            <h3>ADD DATA</h3>
            <br>
            <form action="redirect" method="post">                  <!--#1-->
                <label for="numberIN">Enter Serial Number</label>   <!--#2-->
                <input id="numberIN" type="number" name="number">   <!--#3-->
        
                <label for="nameIN">Enter Name</label>
                <input id="nameIN" type="text" name="name">
        
                <input type="submit" value="Submit">        <!--#4-->
            </form>
            <br>
        
            <a th:text="viewData" th:href="viewdata">replacing text</a>
        </div>
        </body>
        </html>

#1: creating a form with action = current html page name || method = post
#2: label for an input field
#3: input tage with id = for value in #2 || name = VariableName, <br>
    which will be associated in POST Mapping as @RequestParam Variables in Controller methods. 

**viewdata.html**:

        <!DOCTYPE html>
        <html lang="en" xmlns:th=http://www.thymeleaf.org>
        <head>
          <meta charset="UTF-8">
          <title>View Data</title>
        </head>
        <body>
        <div align="center">
        
          <table border="1" cellpadding="5">
            <caption><h2>Database Values</h2></caption>
            <tr>
              <th>number</th>
              <th>name</th>
            </tr>
        
            <tr th:each="Data : ${demoController.getDatabaseList()}">   
              <td th:text="${Data.getNumber()}" />
              <td th:text="${Data.getName()}" />
            </tr>
          </table>
        
        <a th:text="addData" th:href="redirect">replacing text</a>
        </div>
        </body>
        </html>

**DemoController.java**:

        @Controller
        public class DemoController {
    
            @ModelAttribute("demoController")
            public DemoController demoController() {
                return new DemoController();
            }
        
            @GetMapping("/")
            public String welcome(@RequestParam String UserName, Model model) {
                model.addAttribute(AttributeNames.USERNAME, UserName);
                return ViewNames.WELCOME_VIEW;
            }
        
            @ModelAttribute("Message")
            public String Message() {
                return " Welcome to web app";
            }
        
            List<String> strings = List.of("Paul", "D", "Timothy");
        
            public List<String> getStrings() {
                return strings;
            }
        
        
            @GetMapping(Mappings.REDIRECT)
            public String redirect() {
                return ViewNames.REDIRECT_VIEW;
            }
        
            @GetMapping(Mappings.VIEW_DATA)
            public String viewData() {
                return ViewNames.VIEW_DATA_VIEW;
            }
        
            static List<Database> databaseList = new ArrayList<>();
        
            @PostMapping(Mappings.REDIRECT)                         //#1
            public String addData(@RequestParam Integer number, 
                                    @RequestParam String name) {
                databaseList.add(new Database(number, name));
                System.out.println(databaseList.get(0));
                return ViewNames.REDIRECT_VIEW;
            }
        
            public List<Database> getDatabaseList() {
                return databaseList;
            }
        }

#1: POST mapping, as we receive the data from form. Must also create GET mapping for same POST mapping.

To test,
http://localhost:8080/?UserName=Value   (No " " or ' ')