@echo off
echo Cleaning bin folder...
if exist bin rmdir /s /q bin
mkdir bin

echo Compiling model classes...
javac -cp "lib/mysql-connector-java-8.0.33.jar" -d bin src/main/java/com/hotel/model/*.java

echo Compiling dao classes...
javac -cp "lib/mysql-connector-java-8.0.33.jar" -d bin src/main/java/com/hotel/dao/*.java

echo Compiling util classes...
javac -cp "lib/mysql-connector-java-8.0.33.jar" -d bin src/main/java/com/hotel/util/*.java

echo Compiling controller classes...
javac -cp "lib/mysql-connector-java-8.0.33.jar" -d bin src/main/java/com/hotel/controller/*.java

echo Compiling view classes...
javac -cp "lib/mysql-connector-java-8.0.33.jar" -d bin src/main/java/com/hotel/view/*.java

echo Compiling Main class...
javac -cp "lib/mysql-connector-java-8.0.33.jar" -d bin src/main/java/com/hotel/Main.java

echo Running application...
java -cp "bin;lib/mysql-connector-java-8.0.33.jar" main.java.com.hotel.Main

pause