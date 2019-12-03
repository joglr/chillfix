# Chill Fix

## Kom godt i gang

### 1/3 Installer JavaFX
1. [Download JavaFX \<Platform\> SDK	](https://gluonhq.com/products/javafx/) (vælg versionen der passer til dit styresystem under Latest Releases)
1. Pak indholdet af zip filen ud i din JAVA_HOME mappe. Denne sti er enten
   * `C:\Program Files\Java` eller
   * `C:\Program Files (x86)\Java`
1. Åben den nyudpakkede `javafx-sdk` mappe og åben undermappen `lib`
1. Kopier denne sti til senere brug
1. Clone dette repository med git
1. Åben projektet i IntelliJ
1. Åben menuen `File > Project Structure > Libraries`
1. Klik på `+` knappen og vælg `Java`
1. Indsæt stien du kopierede tidligere til `java-fx\lib` og tryk `enter` og `OK`

### 2/3 Opsæt IntelliJ konfiguration

Mac:
1. Kopier følgende kommandoer og indsæt den i IntelliJ terminalen, alt efter dit styresystem og tryk enter:

**Windows:**

    git checkout windows -- .idea/runConfigurations/Main.xml.ignore .idea/libraries/lib.xml.ignore
    mv .idea/runConfigurations/Main.xml.ignore .idea/runConfigurations/Main.xml
    mv .idea/libraries/lib.xml.ignore .idea/libraries/lib.xml

**Mac:**

    git checkout mac -- .idea/runConfigurations/Main.xml.ignore .idea/libraries/lib.xml.ignore
    mv .idea/runConfigurations/Main.xml.ignore .idea/runConfigurations/Main.xml
    mv .idea/libraries/lib.xml.ignore .idea/libraries/lib.xml

2. Derefter klikker du på `Add configuration` og vælger `Application > Main` og trykker `OK`


### 3/3 Kør projektet
1. Kør projektet i `IntelliJ` ved at trykke på den grønne trekant `▶` knap
1. Applikationen skulle gerne åbne i et nyt vindue

