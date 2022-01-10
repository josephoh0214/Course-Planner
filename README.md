# Course Planner

CourseTracker is best suited for using Java program. The user can either run the web app or 
run the program from the command line in Ed. CourseTracker helps the user to see what prerequisite
courses that he or she has to complete before applying for the major. If the user currently has a
major, our program will let him or her know what courses they have to take in order to graduate 
with a Bachelor's degree of the degree they are interested in. Currently, our database only
contains information for Informatics major but it can be updated in the future with different data
of various majors so that the users interested in other majors can use it as well.

## User guide for CourseTracker:

### Link to Video: https://youtu.be/gKTAEPMaKRo

In order to launch and use console version:

The user has to create a new terminal and enter 
###             -->   javac courseTracker.java && java courseTracker   <--
Then, the program asks the user to input his/her current or intended major. The user can input 
"Informatics" (case-insensitive) to see all the pre-requisite courses for the major. Then, the user
can say Yes/No to whether or not they are currently in this major. If the user says no, the program
exits and the user can view the pre-requisite courses for informatics. If the user enters yes, the 
user can view the core courses of graduation requirements and the degree options. If the user 
chooses one of the options from six degree options, then depending on the option, user can see the 
rest of the required courses to graduate from the chosen degree.


In order to launch webapp version:

Open Terminal, paste the following command, and press Enter.
###             -->   javac ServerApp.java && java ServerApp   <--
Then, open the Network dropdown and select host 0.0.0.0:8000. This will open a web page where user can 
choose a major which will show them the prerequisite courses to complete to apply for the major
selection. Then, the user will have to input by choosing yes or no to let the program know that 
he or she is currently in the selected major. If the user had been admitted to the major, the 
program will provide a list of core courses of graduation requirements. Lastly the user will have
to select a degree among six options and see different requirements for the degree options. Users
are able to refresh the page with updated input by pressing the submit button. When finished, press
Ctrl-c to exit the web Server.

## Design
**1. informatics_prereq.csv**
- This file will be used as a database for the prerequisite courses that students need to take 
in order to apply to the major of their interests. We currently only have data for informatics
major in this file but we can add more data of different majors in the future so that students
interested in other majors can also utilize Course Tracker. The first value in this comma
seperated file is the name of the major. The user can either enter their major of interest or
select a major from a drop down menu in the web app. If the user input matches the first value
in this file, this program will print out the courses that follow after the major name. 

**2. informatics_gradreq.csv**
- This file is the database for the core courses that students need to take in order to graduate
with a Bachelor's degree in their major of interests. This file also currently contains data
for students who are in informatics major but more files can be created in same format for 
other majors. This file currently contains 10 requirements of core courses.

**3. informatics_degree.csv**
- This file is the database for the courses that students need to take to earn a Bachelor's degree
in a degree of options they are interested in. Required courses are additional courses on top of
the core courses students have to take and they vary depending on the degrees. Currently, there 
are 6 degree options to choose from and the courses students need to take is in this file. The 
numbers above the courses are as same as the options users can choose from the console or the web
app. 

**4. courseTracker.java**
- This file is created so that users can run the program in the console. We implemented 
Scanner(System.in) to read the user input and store it into a variable. Based on the variables
that stores the user input, we compare them with our database to inform the users with a list of
courses accordingly. An intro method will be called first to greet the users with brief explanation
of our program. Next, it will ask the users what their major of interest is. The scanner will read
the input and store it to a string variable named major. Then it will read the 
informatics_prereq.csv to print out all the prerequisites to inform the users. After, it will ask
the users if they are currently in the major that they have input. If they type n for no, the
program will end with a short message wishing them best of luck on their application. If they type
y for yes, it will print out the core courses of graduation requirements to fulfill to acquire a
Bachelor's degree. We used a set of strings to store all the core courses before printing out.
Then, for each loop is used to print out the core courses. After, it will ask the user to choose
from the degree options UW iSchool currently provides. Depeding on the selection of a degree, it
will inform the users of what additional courses they have to take. The console will provide
numbers to choose from each representing different degree option.  

**5. ServerApp.java**
- This file is created to publish a web app version of our program. By providing the users with a
connection to the localhost server to visualize our program, we were able to make the users to
better interact with our program. Since our program requires multiple input from users, interaction
will be a key component to highlight. By publishing a web app we were able to provide users with
a visualization of our program to increase the readability.

**6. index.html**
- This file is created to build a website for our web app version of our program by using html
language. In our webpage, instead of making the users to type in their query, we chose to implement
a drop down menu to select from. By choosing to do this, we were able to minimalize the error 
happening from the front-end. To increse the user-friendliness, we added the submit button which 
works as a refresh button that updates the users' input when they want to change what they want to
search. Moreover, with the button, we were able to take care of the hassle of refershing our 
webpage everytime when the user wants a different result.  
