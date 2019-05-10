@rem uncomment the following to set the location of your desired hotspot or jrockit VM
set JAVA_HOME=e:\Oracle\Middleware\jrockit_160_29_D1.2.0-10
%JAVA_HOME%/bin/javac ch2/VisualiseMe.java
@rem %JAVA_HOME%/bin/java -Xmx128M ch2.VisualiseMe 

@rem to monitor the process remotely with rockit use the following command
%JAVA_HOME%/bin/java -Xmanagement:ssl=false,authenticate=false,port=7099 -Xmx128M ch2.VisualiseMe 