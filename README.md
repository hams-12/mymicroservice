<h1>Architecture du projet</h1>
<p>Mise en place de 3 microservices(inventory, customer et billing), d'un discovery-service et d'une gateway avec Spring côté backend. Et côté frontend, une application Angular. Pour la sécurité, on utilisera Keycloak avec JWT</p>
<p>Les dépendances Spring des 3 microservices : </p>
<ul>
    <li>Spring Web</li>
    <li>Spring Data JPA</li>
    <li>H2 Database</li>
    <li>Lombok</li>
    <li>Rest Repositories</li>
    <li>Eureka Discovery Client</li>
    <li>Spring Boot Actuator</li>
</ul>
<p>La particularité de billing-service est qu'il va depéndre des deux autres microservices. Pour billing-service, on a va ajouter deux dependances supplémentaires<p>
<ul>
<li>OpenFeign qui va nous permettre de faire communiquer les microservices</li>
<li>Spring HATEOAS</li>
</ul>
<img src="captures/architecture.png">
<h1>Partie Backend</h1>
<h5>Test du Microservice inventory-service via le port 9092</h5>
<img src="captures/products.png">
<h5>Test du Microservice customer-service via le port 9091</h5>
<img src="captures/customers0.png">
<h3>Configuration statique de la gateway</h3>
<p>On a deux manière pour configurer la gateway de manière statique. Soit on utilise un fichier yml ou on utilise la classe de configuration</p>
<h4>Configuration statique 1 des routes dans le service gateway dans un fichier yml</h4>
<img src="captures/config-gateway-1.png">
<h4>Configuration statique 2 des routes dans le service gateway dans la classe de config</h4>
<img src="captures/config-gateway-2.png">
<h4>Configuration dynamique des routes dans le service gateway dans la classe de config</h4>
<img src="captures/config-dynamique.png">
<h5>Accès aux microservices via la gateway avec le port 9999</h5>
<img src="captures/acces-ms-customer-via-gateway.png">
<img src="captures/acces-ms-product-via-gateway.png">
<h5>Discovery Service avant activation</h5>
<img src="captures/discovery-service-avant-activation.png">
<h5>Discovery Service après activation</h5>
<p>On change la propriété spring.cloud.discovery.enabled=true des microservices et de la gateway pour leur permettre d'être visible par discovery sercice.</p>
<img src="captures/capt.png">
<img src="captures/discovery-apres-activation.png">
<h5>Configuration automatique de la gateway</h5>
<img src="captures/0-config-auto-gateway.png">
<h4>Accès aux microservices via leurs noms de puis la gateway</h4>
<p>Une fois la configuration dynamique effectuée, la gateway ne connait plus le nom des microservices. Le client(ça peut être une application front par exemple) doit preciser dans sa requete le nom du microservice pour y accéder depuis la gateway.La gateway va à son tour contacter discovery avec le nom du microservice fournit par le client et discovery lui donne l'adresse du microservice qu'elle va par la suite founir au client.</p>
<img src="captures/2-acces-gateway-via-nom-ms.png">
<img src="captures/3-acces-gateway-via-nom-ms.png">
<h2>Ajout du microservie billing-service</h2>
<h4>Affichage de bills dans la base de données</h4>
<img src="captures/t-billing-service-bd-1.png">
<img src="captures/t-billing-service-bd-2.png">
<img src="captures/t-code-methode-fullbill.png">
<h4>Test de la méthode fullbill depuis l'adresse du microservice</h4>
<img src="captures/t-test-billing-service-avec-son-port.png">
<h4>Test de la méthode fullbill depuis la gateway</h4>
<img src="captures/t-test-billing-service-avec-gateway.png">
<h1>Partie FrontEnd avec angular</h1>
<h4>Affichage de la liste des customers</h4>
<img src="captures/angular-customer.png">
<h4>Affichage de la liste des products</h4>
<img src="captures/angular-product.png">
<img src="captures/angular-road-1.png">
<img src="captures/angular-road-2.png">
<h4>Affichage de la liste des détails d'une facture</h4>
<img src="captures/angular-road-4.png">
<h1>Partie Sécurité avec Keycloak</h1>
<p>On va commencer par créer un Realm. Un Realm peut contenir plusieurs clients (applications front-end). Dans notre cas ici, notre client est l'application angular</p>
<img src="captures/secure-client.png">
<h4>Création d'utilisateurs et de roles</h4>
<img src="captures/secure-users.png">
<img src="captures/secure-roles.png">
<p>Côté backend, On va sécuriser chaque microservice (inventory, custormers et billing). Pour cela on ajoute un package security avec les classes KeycloakAdapterConfig et SecurityConfig</p>
<p>Generation de l'access et du refresh token</p>
<img src="captures/g1-generation-du-tocken.png">
<p>On tente d'acceder au microservice sans token, ça ne marche pas</p>
<img src="captures/g2-acces-sans-token.png">
<p>Une fois qu'on teste avec le token, ça marche bien</p>
<img src="captures/g3-acces-avec-token.png">
<h4>Ajout de l'intercepteur à Billing service</h4>
<p>Après avoir sécurisé chaque microservice, on se retrouve face à un problème car le service BILLING-SERVICE depend des microservices INVENTORY-SERVICE et CUSTMOMER-SERVICE. Lorsque ce microservice essaye de contacter directement les autres microservices, aucun jeton n'est envoyé. Pour palier à ce problème on va mettre en place un intercepteur dans le sercie billing, ce qui aura pour effet d'intercepter les requetes et d'ajouter le header avec le jeton.</p>
<img src="captures/interceptor.png">
