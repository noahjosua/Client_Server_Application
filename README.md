# Client_Server_Application

Um die Anwendung direkt in der Konsole testen zu können, muss Oracle **OpenJDK version 18.0.1.1** (https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html) auf deinem System installiert und in den Umgebungsvariablen gesetzt sein. 
  - Systemvariable `JAVA_HOME` = Pfad zum heruntergeladenen JDK 18.0.1.1
  - der Systemvariable `Path` den Wert `%JAVA_HOME%\bin` hinzufügen 

Anschließend die Konsole öffnen und zu dem Order *.../Client_Server_Application/out/artifacts/Client_Server_jar/* navigieren. 
Nun mittels `java -jar Server.jar` den Server starten. Jetzt kannst du noch bis zu drei weitere Konsolenfenster öffnen und jeweils einen Client starten mittels `java -jar Client.jar`. 
Um einen Client auszuloggen einfach "quit" eingeben. 

### Troubleshoot
Wenn der folgende Fehler auftaucht 
`Exception in thread "main" java.lang.NullPointerException: Cannot invoke "java.net.ServerSocket.close()" because "this.server" is null 
at Server.Server.<init>(Server.java:29)
at Server.ServerMain.main(ServerMain.java:13)`
, dann ist der Port 4001 bei Dir höchstwahrscheinlich belegt. 

*Für Windows*

Öffne deine Konsole und suche nach dem Prozess, der den Port belegt: `netstat -ano | findstr :4001`
In der fünften Spalte findest du eine vierstellige Zahl - das ist die PID (z.B. 8672)
Führe das folgende Kommando aus, um den Prozess zu beenden und den Port 4001 somit freizugeben: `taskkill /PID 8672 /F`
Wenn du eine Meldung wie "SUCCESS: The process with PID 8672 has been terminated." erhältst, dann hat alles geklappt und du kannst nun den Server starten. 


Viel Spaß!
