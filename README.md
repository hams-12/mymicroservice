<h5>Test du Microservice inventory-service via le port 9092</h5>
<img src="captures/products.png">
<h5>Test du Microservice customer-service via le port 9091</h5>
<img src="captures/customers0.png">
<h3>Configuration statique de la gateway</h3>
<p>On a deux manière pour configurer la gateway de manière statique. Soit on utilise un fichier yml ou on utilise la classe de configuration</p>
<h4>Configuration statique 1 de la gateway dans un fichier yml</h4>
<img src="captures/config-gateway-1.png">
<h4>Configuration statique 2 de la gateway dans la classe de config</h4>
<img src="captures/config-gateway-2.png">
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
<h4>Accès aux microservices via leurs noms</h4>
<p>La partie cliente peut accéder à un microservice en connaissant uniquement son nom</p>
<img src="captures/2-acces-gateway-via-nom-ms.png">
<img src="captures/3-acces-gateway-via-nom-ms.png">