# File Client/Server

Implementazione di un file server: il client chiede il trasgerimento dei dati di un file 
di testo e binario, che gli viene inviato dal server.

Il server [Server](./src/Server.java) e il Client [Client](./src/Client.java).

Azionato il server, questi è in ascolto sulla porta 8900 di connessioni da parte del
client che chiedono il tasferimento dei file sotto la directory [files](./files). E' possibile chiedere anche file in sottodirectory della directory base *files* specificando il percorso: ad esempio */product/prod1.jpg* per ricevere il file *prod1.jpg*
nella sottodirectory *product* di *files*.

In caso il file non sia presente, viene inviato un messaggio d'errore. 

**TODO:** se viene specificata una directory deve fare il directory listing. 

## Esempi

Esempio di HTTP Webserver
Java: Performance benefits of virtual threads — 2

https://medium.com/deno-the-complete-reference/java-performance-benefits-of-virtual-threads-2-f3b5d6552dd

Un Http server utilizzando classi del JDK: [NativeHelloWorldServer](./src/NativeHelloWorldServer.java)  
Un Http server con i *virtual thread*: [NativeHelloWorldServerVT](./src/NativeHelloWorldServerVT.java)