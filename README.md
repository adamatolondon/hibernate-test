# hibernate-test
Hibernate Jpa Unit tests

Built using Java SE 17 and Maven  

## Building
Built using Java SE 17
1. Install Java SE 17
2. Install Maven 3.6.3 or higher
3. git clone https://github.com/anthodamato/hibernate-test.git
4. cd hibernate-test
5. mvn clean install -Dmaven.test.skip
6. mvn test


## Setting up test database on Docker
- **MySQL**:
    - docker run --name mysql9 -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -d mysql:9.7.1
    - docker exec -it mysql9 bash
    - mysql -u root -ppassword
    - create database minijpatest;
- **PostgreSQL**
    - docker run --name postgres18 -p5432:5432 -e POSTGRES_PASSWORD=password -d postgres:18.4
    - docker exec -it postgres18 bash
    - psql -Upostgres
    - create database minijpatest;
- **MariaDB**
    - docker run -p 3306:3306  --name mariadb12 -e MARIADB_ROOT_PASSWORD=password -d mariadb:12.3.2
    - docker exec -it mariadb12 bash
    - mariadb -uroot -ppassword
    - create database minijpatest;


## Running the unit tests
- **Apache Derby**
    - mvn test  

- **H2**:
    - mvn test -Dhibernate.test=h2  

- **MySQL**:
    - mvn test -Dhibernate.test=mysql

- **PostgreSQL**
    - mvn test -Dhibernate.test=postgres

- **MariaDB**
    - mvn test -Dhibernate.test=mariadb

- **Oracle**
    - mvn test -Dhibernate.test=oracle

