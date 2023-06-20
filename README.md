# Client_Server_Application

Um die Anwendung direkt in der Konsole testen zu können, muss Oracle **OpenJDK version 18.0.1.1** (https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html) auf deinem System installiert und in den Umgebungsvariablen gesetzt sein. 
  - Systemvariable `JAVA_HOME` = Pfad zum heruntergeladenen JDK 18.0.1.1
  - der Systemvariable `Path` den Wert `%JAVA_HOME%\bin` hinzufügen 

Anschließend die Konsole öffnen und zu dem Order *.../Client_Server_Application/out/artifacts/Client_Server_jar/* navigieren. 
Nun mittels `java -jar Server.jar` den Server starten. Jetzt kannst du noch bis zu drei weitere Konsolenfenster öffnen und jeweils einen Client starten mittels `java -jar Client.jar`. 
Um einen Client auszuloggen einfach "quit" eingeben. 

### Troubleshoot
Wenn der folgende Fehler auftaucht `Exception in thread "main" java.lang.NullPointerException: Cannot invoke "java.net.ServerSocket.close()" because "this.server" is null
	at Server.Server.<init>(Server.java:29)
	at Server.ServerMain.main(ServerMain.java:13)`, dann ist der Port 4001 bei Dir höchstwahrscheinlich belegt. Sobald Du einen anderen Port gewählt und nochmal gebaut hast (oder den Port 4001 freigegeben hast), sollte alles einwandfrei funktionieren. 

Viel Spaß!
