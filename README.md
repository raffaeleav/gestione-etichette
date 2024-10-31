<p align="center">
  <img src="https://github.com/user-attachments/assets/09f79f71-52d6-43a3-9135-bb6cdbfec979" width="512" heigth="120">
</p>

<p align="center">
  A Java GUI developed as a project for the Basi di Dati (Database) course, part of the Computer Science Bachelor's Degree program at the University of Salerno
</p>


## Table of Contents
- [Overview](#Overview)
- [Preview](#Preview)
- [Features](#Features)
- [E-R diagram](#E-R-diagram)
- [Requirements](#Requirements)
- [How to replicate](#How-to-replicate)
- [Built with](#Built-with)


## Overview
<p>
  Gestione Etichette was developed with the goal of consolidating knowledge related to databases in a practical context. The GUI allows users to execute predefined queries on a MySQL database. 
The implemented queries range from simple selections to queries operating on sets.
</p>


## Preview
<p>
  <img src="https://github.com/raffaeleav/gestione-etichette/assets/114619463/d90e07ae-20fc-404c-a0d6-fd0834bdeeee" width="400" heigth="400">
</p>


## Features 
1) Ordered selection on a table attribute with AND and OR conditions
2) Selection on two or more tables with conditions
3) Aggregated selection on all values
4) Aggregated selection on groupings
5) Aggregated selection on groupings with conditions
6) Aggregated selection on groupings with conditions that include another grouping function
7) Selection with set operations
8) Selection with relational division


## E-R diagram
<p>
  <img src="https://github.com/raffaeleav/gestione-etichette/assets/114619463/c51d9e99-f3dc-4944-a6c4-0ca84ffdd467" width="400" heigth="400">
</p>


## Requirements 
- [JDK 19](https://jdk.java.net/java-se-ri/19)
- [MySQL](https://dev.mysql.com/downloads/installer/)
  - [Connector/J](https://dev.mysql.com/downloads/connector/j/)


## How to replicate
1) Clone the repository
```bash
git clone https://github.com/raffaeleav/gestione-etichette.git
```
2) Switch to the project directory
```bash
cd gestione-etichette
```
3) Execute the database script 
```bash
sudo mysql -u root -p < ./src/main/resources/database.sql
```
4) Download the Connector/J JDBC driver
```bash
curl -OL https://downloads.mysql.com/archives/get/p/3/file/mysql-connector-j-8.4.0.tar.gz 
tar -xzvf mysql-connector-j-8.4.0.tar.gz 
```
5) Compile the project
```bash
javac $(find . -name "*.java")
```
6) Run the GUI with the Connector/J JDBC driver
```bash
java -cp ./mysql-connector-j-8.4.0/mysql-connector-j-8.4.0.jar:./src/main/java gestioneetichette.ProgettoViewer
```


## Built with
- [Java](https://www.oracle.com/it/java/technologies/downloads/) - used for the GUI development
- [SQL](https://www.w3schools.com/sql/) - used for implementing the physical schema of the database
