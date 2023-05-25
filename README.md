# File Client/Server

Implementazione di un file server: il client chiede il trasferimento dei dati di un file 
di testo e binario, che gli viene inviato dal server: in client invia una stringa con il nome della risorsa che vuole ricevere e il file letto il nome della risorsa, se presente, la invia come risposta sulla socket al client.

Il codice del [Server](./src/Server.java) e del [Client](./src/Client.java).

Azionato il server, questi è in ascolto sulla porta `8900` di connessioni da parte del
client che chiedono il tasferimento dei file sotto la directory [files](./files). 

![](./files.PNG)

E' possibile chiedere anche file in sottodirectory della directory base *files* specificando il percorso: ad esempio */product/prod1.jpg* per ricevere il file *prod1.jpg*
nella sottodirectory *product* di *files*.

In caso il file non sia presente, viene inviato un messaggio d'errore. 

Se viene specificata una directory deve fare il directory listing. 

Ad esempio, inserendo dal client la richiesta:

```
/
```

l'output di risposta dal server sarebbe:

```
index.html (272 bytes)
javalogo.gif (2512 bytes)
moreinfo.png (3315 bytes)
product/ (0 bytes)
```

invece inserendo nella richiesta:

```
product
```

l'output di risposta del server sarebbe:

```
prod1.jpg (582 bytes)
prod2.jpg (767 bytes)
prod3.jpg (616 bytes)
prod4.jpg (703 bytes)
```
## Esempi

Esempio di HTTP Webserver
[Java: Performance benefits of virtual threads — 2](https://medium.com/deno-the-complete-reference/java-performance-benefits-of-virtual-threads-2-f3b5d6552dd)

Un Http server utilizzando classi del JDK: [NativeHelloWorldServer](./src/NativeHelloWorldServer.java)  
Un Http server con i *virtual thread*: [NativeHelloWorldServerVT](./src/NativeHelloWorldServerVT.java)