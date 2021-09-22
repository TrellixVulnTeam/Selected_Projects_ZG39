default: runProgram

runProgram: FrontEnd.class
	java FrontEnd

FrontEnd.class: FrontEnd.java
	javac LinkedNode.java
	javac HashTableMap.java
	javac MapADT.java
	javac StudentInfoReader.java
	javac Student.java
	javac BackendInterface.java
	javac FrontendInterface.java
	javac FrontEnd.java

test: Tester.class
	java -jar junit5.jar -cp  . --scan-classpath -n Tester

Tester.class: Tester.java
	javac -cp .:junit5.jar  Tester.java -Xlint

clean:
	rm *.class

        

