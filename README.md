# wafepa
Full stack web application, developed during 3rd module of Java Web Development course.
"wafepa" describes my try to better understand concepts of java web development.

it covers folowing topics:
    1. Spring boot back-end
        1.1. Maven project configuration, modeling classes and service layer
        1.2. Use the Spring boot to implement REST api (basic CRUD operations) for entities
        1.3 api level validation
        1.4. dependency injection
        1.5. DTO (Data Transfer Object) class
        1.6. ORM - Object-Relational Mapping (with hibernate Java Persistance API (JPA))
        1.6. Spring Data JPA
        1.7. ORM - Connections between objects (one to many etc.)
        1.8. Pagination
    2. AngularJs front-end
        2.1. SPA (Single Page Application) concept
        2.2. AngularJS app
        2.3. Bootstrap
        2.4. Use of REST Countries API http://restcountries.eu/
        2.5. Use of themoviedb.org API https://www.themoviedb.org/documentation/api




#########################################################
-------- Sql script -------


CREATE USER IF NOT EXISTS jwduser IDENTIFIED BY 'pass';

DROP DATABASE IF EXISTS jwddb;
CREATE DATABASE jwddb DEFAULT CHARACTER SET utf8;

USE jwddb;

GRANT ALL ON jwddb.* TO 'jwduser'@'%';

FLUSH PRIVILEGES;

#########################################################