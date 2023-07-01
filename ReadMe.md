
# RSSParser

The goals of this project are:
- Read the jobs from bayt client RSS: (https://careers.moveoneinc.com/rss/all-rss.xml/)
- Parse the fetched jobs to get the job titles.
- Draw the jobs in a list.
- Draw the jobs based on locations on google maps (you can choose javascript or any backend language)




## Tech and Tools

- Java Spring
- JS/HTML/CSS
- Google maps API
- nominatim openstreetmap API
- thymeleaf
- lombok
- bootswatch




## High Level Design

#### To perform the project's goal I designed the project by three parts:
### 1) Fetching Data
opend a connection with the provided URL: https://careers.moveoneinc.com/rss/all-rss.xml  Then stored the input stream in a document variable.


### 2) Processing Data
- Created a GeoCoordinates class which holds latitude and longitude data.
- Created a GeocodingService Class to map the country name to the adjacent latitude and longitude values.
- Created an Item class which is represented by title, country and geoCoordinates variables.
- Looped through the document variable to store needed information in a List<Item> , this information is: item title, item country.
- Converted the country value to geoCoordinates using my GeocodingService.
- Finally passed the List<Item> to a view (HTML page) named job.html

### 3) Displaying Data
This HTML page is responsible to display each item on a table row, which when clicked it will draw a marker on the google map to that item location. Every time a new row is clicked the map will be cleard from all previous markers and will zoom on the new location.

To be able to call the google map API I created a project on the google map console and enabled the JavaScript Library, then generated my API key.
https://console.cloud.google.com/getting-started


To beautify the page content I used a combination of Bootswatch styleSheet as well as inline styles.

## Testing and Running
To test this project locally you need to get a google map API key (create your own: https://console.cloud.google.com/getting-started)
after running type this url in the browser: http://localhost:8080/job





## Related Links and Tutorials

- https://stackoverflow.com/questions/2310139/how-to-read-xml-response-from-a-url-in-java/54420207#54420207
- https://www.youtube.com/watch?v=5G_MfwsGUIo&list=LL&index=1&t=244s
- https://stackoverflow.com/questions/27841664/put-marker-into-country-from-country-name
