# Graphite-AOPAlliance
Using graphite to check time taken by methods and AOPAlliance to intercept the method to perform logging and monitoring.

Graphite is a tool responsible for sending Metrics about APIs to another server for analysis. Can formulate the percentiles such as 99th percentile, occurances over a period of time.
1) It uses Carbon internally to process requests.
  a) Carbon has 3 states.
      i) agent - Which accepts the request.
      ii) cache - Which caches the request.
      iii) persistor - Which collects all the data and pushes them to Whisper time-to-time. 
      (MoreInfo : http://graphite.wikidot.com/carbon)
2) Whisper - is a database library, which reads the data from carbon and writes it to DB.

1) Graphite makes use of Timer, Histogram, Meter to mark the time taken, history and number of times the method was hit.
GraphiteReporter sends this report to Graphite server.
Currently the code pushes the data to local graphite server listening to 2003 port.

2) Uses AOPAlliance (Method Interceptor) to block calls to API's and create a Timer/Histogram/Meter for each API.


For Configuring Graphite Server in local Follow steps at
1) https://www.digitalocean.com/community/tutorials/installing-and-configuring-graphite-and-statsd-on-an-ubuntu-12-04-vps
2) https://www.digitalocean.com/community/tutorials/how-to-install-and-use-graphite-on-an-ubuntu-14-04-server

Once installed, 
1) You can bring up the server by using
  sudo service carbon-cache start
    or 
  sudo service carbon-cache stop (To Stop)
    or
  sudo service carbon-cache status (For Status)
2) the graphite web can be accessed at http://localhost/



Configuration of Graphite:
1) for DB connections - /etc/graphite/local_settings.py
