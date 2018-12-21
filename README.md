# Wikipedia Matrix

This project is held at the University of Rennes 1, ISTIC, in Master 1 (MIAGE).
The goal of our project is to carry out a software project with open technologies and data.
Many challenges lie ahead, requiring skills in project management, modeling, and programming.



### Objective

The software aim to return you as much table as possible from a wikipedia page that you provide to it in CSV format. 
It will try to exclude the table that cannot be parsed into CSV and inform you about how much table it can parse.


## Results

We use one and one only URL of a Wikipedia page to produces a CSV file that contains its tables  (eg https://fr.wikipedia.org/wiki/Coupe_du_monde_de_football)

The project analyzes Wikipedia page in two different ways:
• By searching for the corresponding Wikitext code
• By exploiting the HTML rendering of the Wikipedia page

If you give a wikipedia page link that contains tables, the software will generate you the CSV for every valid tables in the page and exclude the ones that cannot be converted into CSV.
If you give a wikipedia page link that doesn't contains any valid tables or no tables at all, the software will inform you of this and won't generate any file.
If you give any other link than wikipedia, the software will tell you that the page you provide is not compatible and won't generate any file.
At every steps you can check the list of link that you provided to the software and check if and how many table it can extract.

## Prerequisites

You will need a java compiler to compile and run the program properly. Alternatively you can choose one the bellow IDE to make it easier.
IntelliJ IDEA
Eclipse
NetBeans


## Installing

Depending on the IDE that you chose, you will have to import the pom.xml file to your project. 
This will allow Maven to automatically import the missing libraries to properly run the project.
Everything is now set and you are ready to run the program.

## Running the tests

The Tests are located under:

Pdl/src/src/main/java/model/test

You can simply run them with your favorite IDE.


## Deployment

No deployment of the project is planned on client servers/computers as the client didn't specifically asked to do it. 
The project can only be used via console commands.


## Built With

*IntelliJ IDEA - The IDE mainly used by our crew.
*Maven - Dependency Management.
*Jsoup - The java based HTML Parser.
*StarUML - The UML editor.
*Word - The document editor used to create the specifications.

## Authors

**Adrien Royer** - **Romain Muckenhirn** - **Mani Rus** - Julien Lavazay - **Aquil Ali**


## License

This project is licensed under the MIT License



