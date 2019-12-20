# Chillfix

## Sådan kører du programmet

Programmet er udviklet til at virke med [Oracle Java 8 JDK](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html), så sørg derfor at det er denne version der er installeret og valgt som standard på din computer.
Dette kan tjekkes ved at åbne et terminal vindue og skrive kommandoen `java -version`
På denne komputer giver det anledning til følgende output:
```
java version "1.8.0_231"
Java(TM) SE Runtime Environment (build 1.8.0_231-b11)
Java HotSpot(TM) 64-Bit Server VM (build 25.231-b11, mixed mode)
```

Såfremt overstående krav er opfyldt, køres applikationen ved at dobbeltklikke på `.jar` filen.
Hvis der ikke sker noget, skyldes det muligvis at der er intalleret flere forskellige java versioner.
Som alternativ, kan applikationen derfor køres ved at man i en terminal 
der peger på denne mappe, kører følgende kommando:

```
java -jar target/chillfix-1.0-SNAPSHOT.jar
``` 

## Vejledning til videreudvikling og oprettelse af produktionsudgave

### Udvikling 
For at køre Chillfix i udviklertilstand, anvendes eksempelvis [IntelliJ IDEA]

1. Opret en ny
1. Kør projektet i `IntelliJ` ved at trykke på den grønne trekant `▶` knap
1. Applikationen skulle gerne åbne i et nyt vindue

## Produktion

For at oprette en produktionsudgave af Chillfix i en enkeltstående `.jar` fil, kan man med fordel anvende [Maven](https://maven.apache.org/). 

Tidligere nævnte krav for projektet gælder stadig.

Med maven bygges projektet med følgende kommando:
```
mvn package
```
Dette producerer en `chillfix<VERSION>.jar` fil i mappen `target` (se understående), såfremt den rigtige java version anvendes. 

<img src="https://github.itu.dk/storage/user/2778/files/1baf6480-22f6-11ea-9d7c-f358419499ff" alt="target\chillfix-1.0-SNAPSHOT.jar">

Herefter kan denne fil køres, som beskrevet i  `Sådan kører du programmet` afsnittet.