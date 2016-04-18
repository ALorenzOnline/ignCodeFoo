----------------------------------------------
+++++++++++++BACKEND CHALLENGE++++++++++++++++
----------------------------------------------

----------------------------------------------
++++++++++++++++Scrabble++++++++++++++++++++++
----------------------------------------------
Created the program to read in a file. Then it 
reads each word one at a time, and caluculates how many
points it is worth.

----------------------------------------------
++++++++++++++++ISO 8601++++++++++++++++++++++
----------------------------------------------
Created several different functions that are called, when parseAndRec()
reads particular Identifiers with in the string such as a /,+,:,-,or worded Month.
Based on the identifier the string will be sent to the proper functions. There 
are functions that will handle different parts of the date format such as date,time,and tzd.
once all of the parts are handled they will be rturned and combined to create the final date.

The default constructor will look for a file named dates.txt
But it can be overloaded with a string that contains a date.

use the iso8601.jar to import parseLib.date.*;
parseDate()-is the constructor
readText()-will load the dates given into an array
parseAndRec()-will start the conversions

Also the .java file will be located in the src folder

------------------------------------------------
++++++++++++++++++AZERTY++++++++++++++++++++++++
------------------------------------------------
For this I used javas APPLET, and created a key listener.
If the key was a Q or an A it would swap them, same with the Z and the W
the H key was also made to be a stickey key, and shift will now type H.

Also the .java file will be located in the src folder


-------------------------------------------------
+++++++++++++Front End Challenge+++++++++++++++++
-------------------------------------------------

Started Off strong by using PHP to gather the info from the api
then I stored the information in arrays based on their key,
then I created links based on the Slug, and date that yould be 
utilized later to navigate to igns webpages.

As for the design, I used bootstrap to create rows and columns
so it could adapt to whatever size the screen was.

I then used basic javascript(I know jquery is available through boot strap, but dont have experience with it) 
to pass information from php to the HTML page.I also used JS to detect when 
a mouse was over a particular row

As for the design, I used css to make color adjustments for text
