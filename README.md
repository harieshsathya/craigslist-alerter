# craigslist-alerter

A java application that sends out email alerts for the provided filters such as,

1. Number of beds/baths
2. Minimum/Maximum price
3. Rent Control

Note: Based on the parse interval that you have configured, there will be an iniital dealy. This avoids an email storm
as soon as the application is started.

## Requirements
* Java 1.7
* Maven

## Usage
```
mvn clean compile assembly:single
java -jar target/craigslist-alert-1.0-SNAPSHOT-jar-with-dependencies.jar /path/to/app_properties.yml
```

## Yaml File Description
feedUrl: The craigslist rss feed url.
Example: https://sfbay.craigslist.org/search/sfc/apa?format=rss

parseInterval: The interval in minutes at which the rss feed is parsed. 
Example: 10 minutes

toEmail: The email to send alerts to.

smtpUsername: SMTP username
smtpPassword: SMTP password

smtpServer: SMTP server.
Example: smtp.gmail.com

smtpServerPort: SMTP server port.

### Filter Options:

bedrooms: Minimum number of bedrooms. Integer value.

bathrooms: Maximum number of bedrooms. Integer value.

minPrice: Minimum rent. Integer value.

maxPrice: Maximum rent. Integer value.

isRencontrolled: Boolean value.

## Notes
* A sample app_properties.yml is included under src/main/resources
* All the parameters are required.
* For Gmail SMTP, use smtp.gmail.com as server and 587 as port. Username is your email address and your password.
* Specify the parseInterval at an appropriate value to avoid an email storm.


