## Mantel Group Programming Task
The task is to parse a log file containing HTTP requests and to report on its contents.
For a given log file we want to know,
- The number of unique IP addresses
- The top 3 most visited URLs
- The top 3 most active IP addresses

## Example Data
177.71.128.21 - - [10/Jul/2018:22:21:28 +0200] "GET /intranet-analytics/ HTTP/1.1" 200 3574 "-" "Mozilla/5.0 (X11; U; Linux x86_64; fr-FR) AppleWebKit/534.7 (KHTML, like Gecko) Epiphany/2.30.6 Safari/534.7"

## VERSIONS
Java 11
Gradle 7.4.2

# Assumptions
- Application will take log file(absolute path) as command line argument 
- FileNotFoundException is thrown if log file not found
- Record will be skipped if not in correct format
- 2 urls will be considered as same if base url matches 
   e.g. http://google.com/xyz and http://google.com/abc
- 2 urls will be considered different if one using http and another https
   e.g. https://google.com/abc and http://google.com/abc

# Run Tests
`gradle test`

# Run Integration Tests
`gradle integrationTest`

# Run Application
`java -jar ${ProjectRoot}/build/libs/log-analyser.jar  <log-file>`