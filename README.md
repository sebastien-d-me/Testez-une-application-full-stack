# Yoga App
Testez une application full-stack

---

### 🔄 Téléchargement 🔄
1. Téléchargez ou clonez le projet.
2. Nécessite les éléments suivants :
    - Angular 
    - Apache Maven
    - Java
    - NodeJS
    - PHP
    - PHPMyAdmin

---

### ⚙️ Configuration ⚙️
1. Créez vos variables d'environnements :
* `P5_OC_API__JWT` ayant pour valeur une clé de cryptage de 256bits
* `SPRING_DATASOURCE_USERNAME` ayant pour valeur l'identifiant d'accès à votre base de données
* `SPRING_DATASOURCE_PASSWORD` ayant pour valeur le mot de passe d'accès à votre base de données
2. Configurez le fichier `application.properties`:
* Mettez vos informations de base de données (pensez à la créer sur PhpMyAdmin)
3. Importez sur votre base de données nommé `yoga_app` le fichier SQL présent dans `ressources/sql/script.sql`

---

### 🔑 Configuration 🔑
1. Identifiants admin par défaut du front-end  :
* Nom d'utilisateur : `yoga@studio.com` 
* Mot de passe : `test!1234` 

---

### 💻 Installation 💻
1. Effectuez la commande : `npm install` dans le répertoire "front"
2. Effectuez la commande : `maven clean install` dans le répertoire "back"

---

### 🚀 Démarrage 🚀
1. Effectuez la commande : `npm run start` dans le répertoire "front"
2. Effectuez la commande `mvn spring-boot:run` dans le répertoire "back"
3. En cas de besoin, l'URL du front est la suivante : http://localhost:4200/
4. Pensez à changer l'adresse si votre serveur n'est pas liée à ce port

---

### ✅ Tests✅
1. Effectuez la commande : `npm run test:watch` dans le répertoire "front" (pour lancer les tests unitaires et d'intégrations et générer un rapport de couverture)
2. Effectuez la commande : `npm run e2e` dans le répertoire "front" (pour lancer les tests end to end et lancer le test allE2E)
3. Effectuez la commande `mvn clean test` dans le répertoire "back" (pour lancer les tests du back-end et générer un rapport de couverture)
4. En cas de besoin, la page des tests du front-end, se trouve dans le répertoire "front/coverage/jest/Icov-report"
5. En cas de besoin, la page des tests du back-end, se trouve dans le répertoire "back/target/site/jacoco"
