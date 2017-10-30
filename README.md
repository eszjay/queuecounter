<h1>Queue Counter</h1>

A multi-threaded queue-processing service that reads large integers (supporting over 100 digits) from an input queue of String and finds min, max and count above a threshold.

<p>To execute the application, start your ActiveMQ broker(e.g. <code>java -jar activemq-all-5.15.1.jar start</code>), then start this application (com.github.eszjay.queuecounter.Application.main())
The application reads from a queue named <code>NumbersToProcess</code>. The REST API is documented at [localhost:8080/v1/swagger-ui.html](http://localhost:8080/v1/swagger-ui.html)</p>

<p>The requirements for this application are documented in com/github/eszjay/queuecounter/queuecounter.feature.</p>

<p>The automated tests for this application be executed via com.github.eszjay.queuecounter.RunCukes or <code>gradlew build</code>.</p>