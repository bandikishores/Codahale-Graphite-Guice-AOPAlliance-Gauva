# Codahale-Graphite-Guice-AOPAlliance-Gauva
1. Using graphite to check time taken by methods. <br/>
2. AOPAlliance to intercept the method to perform Automatic logging and monitoring. <br/>
3. Uses Gauva Cache to save and retrieve the value. <br/><br/>

Graphite is a tool responsible for sending Metrics about APIs to another server for analysis. Can formulate the percentiles such as 99th percentile, occurances over a period of time.<br/>
1. It uses Carbon internally to process requests.<br/>
   Carbon has 3 states.<br/>
      i. agent - Which accepts the request.<br/>
      ii. cache - Which caches the request.<br/>
      iii. persistor - Which collects all the data and pushes them to Whisper time-to-time. <br/>
      (MoreInfo : http://graphite.wikidot.com/carbon)<br/>
2. Whisper - is a database library, which reads the data from carbon and writes it to DB.<br/><br/>

1. Graphite makes use of Timer, Histogram, Meter to mark the time taken, history and number of times the method was hit.<br/>
GraphiteReporter sends this report to Graphite server.<br/>
Currently the code pushes the data to local graphite server listening to 2003 port.<br/>

2. Uses AOPAlliance (Method Interceptor) to block calls to API's and create a Timer/Histogram/Meter for each API.<br/><br/>


For Configuring Graphite Server in local Follow steps at<br/>
1. https://www.digitalocean.com/community/tutorials/installing-and-configuring-graphite-and-statsd-on-an-ubuntu-12-04-vps<br/>
2. https://www.digitalocean.com/community/tutorials/how-to-install-and-use-graphite-on-an-ubuntu-14-04-server<br/><br/>

Once installed, <br/>
1. You can bring up (carbon) the listener which publishes data to graphite server by using<br/>
 <code>
  sudo service carbon-cache start </code><br/>
  <code>  or  </code> <br/>
 <code> sudo service carbon-cache stop (To Stop)  </code><br/>
  <code>  or  </code><br/>
  <code> sudo service carbon-cache status (For Status) </code><br/>
 
2. To bring up graphite web server<br/>
  <code>sudo service apache2 start</code><br/>
  <code>  or</code><br/>
 <code> sudo service apache2 reload</code><br/>
3. the graphite web can be accessed at http://localhost/<br/><br/><br/>



Configuration of Graphite:<br/>
1. for DB connections - /etc/graphite/local_settings.py<br/>
