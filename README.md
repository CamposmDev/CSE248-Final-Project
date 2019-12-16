# CamposCSE248FinalProject
College Navigator App

1.	Data Retrieval Module - I used the jackson API to gather all the data of the colleges from College Scorecard API.  Using the my class DBLoader I download the data yearly  
and using ObjectMapper and JsonNode to parse the JSON files I store the data into a SQLite database using SQLiteOpenHelper.  I use the metadata to loop through all 100 pages of the JSON file that has 20 colleges per page.  
2.	User Profile Module - If the user doesn't have an account, he/she can sign up to create an account.  
On the login screen, the user can click the text "Don't have an account? Click here!" which will open an activity
where the user can enter his/her information such username and password.  Username has to be unique or it will display an alert
saying it is not.  The user has the option to enter his/her first and last name, sat scores and email.  The user can update
his/her information later on in the program.  Also if the user wishes, he/she can delete his/her account.
3.	User Login Module - On start up of the program, the user can enter his/her username and password.  If the user enters his/her
username correctly and the password is wrong, the program will say you entered the wrong password.  If the username is not found in the
database it will display an alert saying the username does not exist.  
4.	College Search Module - Once the user has logged in, he/she can search for a college by its college name, city, zip, and state.   In order for the program to 
search for a college, the college name field must be filled in.  Once this is done, the user can press 
the button "Find My Colllege' which will open an activity containing a ListView displaying all the college names that were found by the fields 
given by the user.  Then the user can click on an item in the ListView and it will open a window containing all the information the database has that college the user selected.  
5.	College Match Module - In the window that displays information about the college, there's a button called "Compare SAT Scores" where it will take the users SAT score and compare it
to the college's 25th and 75th percentile SAT score.  Then an alert will appear and say the user has above a 75% chance or between a 75% and 25% chance or less than 25% of being 
accepted to the school.  The alert will also display the user's and school's SAT scores.  