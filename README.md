# Gruppe 3 DAT109 Prosjekt
###### Enah Lanto | Espen Mæland | Heine Fjeldberg | Herborg Irgens Sjo | Ingrid Klepsvik | Raida Talukdar | Sondre Lindaas Gjesdal | Truls Martinsen
–––
###
1. Installer maven på ditt operativsystem [her](https://maven.apache.org/install.html)
2. Clone prosjektet i en ønsket mappe
3. ```cd DAT109_Prosjekt/```
4. Endre klassen ```/src/main/java/no/hvl/dat109/prosjekt/utilities/ProsjektPaths.java``` og endre ```HOST``` til hva link du vil at QRkodene skal lage linkene til.
5. Kjør kommandoen ```mvn spring-boot:run``` i ```DAT109_Prosjekt/``` for å starte prosjektet. (Må være i samme mappe som pom.xml)
6. Serveren kjører nå på localhost:8080
___
### Setup
1. Clone prosjektet inn i Intellij --> ````File->New->Project From Version Control->Git````
2. Åpne ````Project Structure````
3. Velg `````Project````` i venstre kolonne
4. Velg din `````Project SDK`````, sett ````Project Langauge Level --> 8````
5. Lag en mappe i prosjektet med navn ````out```` og sett ```Project Compiler Output``` til den mappen du nett lagde.
6. Gå inn i `````src/main````` og høyreklikk på ````java: Mark Directory as -> Sources Root````
7. Høyreklikk på prosjektmappen din og trykk ````Add Framework Support -> Maven```` da skal alle errorene forsvinne og du kan begynne å programmere

___

### Sette opp PlantUML + Graphviz
1. Last ned Plugin ```PlantUML integration``` i IntelliJ
2. Gå til [Graphviz](https://www.graphviz.org/download/) og last ned versjonen til ditt operativsystem. Du skal nå kunne se diagrammene i mappen ```Diagrammer```

___

### Enable HotSwap(automatisk oppdatering av endringer)
1. Enable automatic build ```Settings/Preferences (CTRL+ALT+S/CMD+,) -> Build, Execution, Deployment -> Compiler``` 
enable ```Build Project Automatically```

2. ```ctrl+shift+A``` og søk etter registry. I registry skal du huke av på: ```compiler.automake.allow.when.running``` 

___
### Does Travis approve of the code?

[![Build Status](https://travis-ci.org/571530/DAT109_Prosjekt.svg?branch=master)](https://travis-ci.org/571530/DAT109_Prosjekt) 
