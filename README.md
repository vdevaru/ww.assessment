# Assignment 2 - 
Weight Watchers site automation

1) Please Run as "maven test" on the project
2) Day of the week is given as input from testng.xml as parameter 
Like "<parameter name="day" value="SUN" />"
3) Using Google Chrome as Browser with Version 84.0.4147.30 of chrome driver , which is included in src/test/resources
4) have included all the dependencies using maven
5) If the test suite doesnt run on the first, do consider and rerun it. There can be issues with Chrome driver. 

Assignment Given:
1. Navigate to https://www.weightwatchers.com/us/
2. Verify loaded page title matches “WW (Weight Watchers): Weight Loss & Wellness Help” ----( | WW USA missing )
3. On the right corner of the page, click on “Find a Studio”
4. Verify loaded page title contains “Find WW Studios & Meetings Near You | WW USA”
5. In the search field, search for meetings for zip code: 10011
6. Print the title of the first result and the distance (located on the right of location title/name)
7. Click on the first search result and then, verify displayed location name/title matches with the name of the first searched result that was clicked.
8. From this location page, print TODAY’s hours of operation (located towards the bottom of the page)  -------(This element is not seen on the page)
9. Create a method to print the number of meeting the each person(under the scheduled time) has a particular day of the week
e.g. printMeetings("Sun")
Output should be:
Person A  3
Person B  1
