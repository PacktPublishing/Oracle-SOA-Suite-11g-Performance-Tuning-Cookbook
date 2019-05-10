#uncomment the following to set the location of your desired hotspot or jrockit VM
#export JAVA_HOME=
$JAVA_HOME/bin/javac ch2/VisualiseMe.java
$JAVA_HOME/bin/java -Xmx128M ch2.VisualiseMe

# to monitor the process remotely with rockit use the following command
# $JAVA_HOME/bin/java -Xmanagement:ssl=false,authenticate=false,port=7099 -Xmx128M ch2.VisualiseMe 