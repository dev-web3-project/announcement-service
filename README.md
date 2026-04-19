# Announcement Service

Un service Spring Boot pour la gestion des annonces dans un système de gestion de l'apprentissage (LMS).

## Description

Ce service permet de gérer différents types d'annonces :
- **Devoirs** : Annonces avec dates limites
- **Événements** : Annonces d'événements
- **Examens** : Annonces d'examens
- **Maintenance** : Annonces de maintenance système

Le service fait partie d'une architecture microservices avec Eureka pour la découverte des services et utilise Spring Cloud Config pour la configuration centralisée.

## Technologies

- **Java 17**
- **Spring Boot 3.3.2**
- **Spring Cloud 2023.0.3**
- **Spring Data JPA** avec PostgreSQL et H2
- **Spring Security** pour l'authentification
- **Spring Cloud Netflix Eureka** pour la découverte des services
- **Spring Cloud Config** pour la configuration centralisée
- **SpringDoc OpenAPI** pour la documentation API
- **Lombok** pour la réduction du code boilerplate
- **Maven** pour la gestion des dépendances
- **Docker** pour la conteneurisation

## Architecture

### Microservices
- **Service Registry** : Eureka Server pour la découverte des services
- **Config Server** : Spring Cloud Config pour la configuration centralisée
- **Announcement Service** : Service actuel pour la gestion des annonces

### Base de données
- **PostgreSQL** : Base de données principale
- **H2** : Base de données en mémoire pour les tests

## API Endpoints

### Annonces
- `GET /api/announcement/all` - Lister toutes les annonces
- `POST /api/announcement/assignment` - Créer une annonce de devoir
- `POST /api/announcement/event` - Créer une annonce d'événement
- `POST /api/announcement/exam` - Créer une annonce d'examen
- `POST /api/announcement/maintenance` - Créer une annonce de maintenance
- `DELETE /api/announcement/{id}` - Supprimer une annonce

### Messages
- `GET /api/message/all` - Lister tous les messages
- `POST /api/message/send` - Envoyer un message

### Notifications
- `GET /api/notification/all` - Lister toutes les notifications
- `POST /api/notification/send` - Envoyer une notification

## Documentation API

La documentation Swagger/OpenAPI est disponible une fois l'application démarrée :
- URL : `http://localhost:8080/swagger-ui.html`
- Spécification OpenAPI : `http://localhost:8080/v3/api-docs`

## Prérequis

- Java 17 ou supérieur
- Maven 3.6 ou supérieur
- Docker (optionnel, pour la conteneurisation)
- PostgreSQL (pour la production)

## Installation et Démarrage

### 1. Cloner le projet
```bash
git clone <repository-url>
cd announcement-service
```

### 2. Compiler le projet
```bash
./mvnw clean install
```

### 3. Démarrer l'application
```bash
./mvnw spring-boot:run
```

L'application démarrera sur le port 8080 par défaut.

### 4. Avec Docker
```bash
# Construire l'image Docker
docker build -t announcement-service .

# Démarrer le conteneur
docker run -p 8080:8080 announcement-service
```

## Configuration

### Variables d'environnement
- `SPRING_PROFILES_ACTIVE` : Profil actif (dev, prod)
- `SPRING_DATASOURCE_URL` : URL de la base de données
- `SPRING_DATASOURCE_USERNAME` : Nom d'utilisateur de la base de données
- `SPRING_DATASOURCE_PASSWORD` : Mot de passe de la base de données
- `EUREKA_CLIENT_SERVICE_URL_DEFAULT_ZONE` : URL du serveur Eureka

### Fichiers de configuration
- `application.properties` : Configuration par défaut
- `application-dev.properties` : Configuration pour le développement
- `application-prod.properties` : Configuration pour la production

## Tests

### Exécuter les tests unitaires
```bash
./mvnw test
```

### Exécuter les tests d'intégration
```bash
./mvnw verify
```

## Développement

### Structure du projet
```
src/
├── main/
│   ├── java/com/lms/announcement/
│   │   ├── AnnouncementServiceApplication.java
│   │   ├── config/          # Configuration (Security, OpenAPI, etc.)
│   │   ├── controller/      # Contrôleurs REST
│   │   ├── dto/            # Objets de transfert de données
│   │   ├── entity/         # Entités JPA
│   │   ├── exception/      # Exceptions personnalisées
│   │   ├── repository/     # Repositories Spring Data
│   │   └── services/       # Services métier
│   └── resources/
│       └── application.properties
└── test/                   # Tests unitaires et d'intégration
```

### Bonnes pratiques
- Utiliser Lombok pour réduire le code boilerplate
- Suivre les conventions de nommage Spring Boot
- Écrire des tests unitaires pour chaque service
- Utiliser les DTOs pour les échanges API
- Gérer les exceptions de manière appropriée

## Déploiement

### Docker
Le projet inclut un Dockerfile multi-stage pour optimiser la taille de l'image produite.

### Kubernetes
Pour le déploiement sur Kubernetes, créez les ressources suivantes :
- Deployment
- Service
- ConfigMap
- Secret (pour les variables d'environnement sensibles)

## Monitoring

### Actuator
L'application expose des endpoints de monitoring via Spring Boot Actuator :
- `/actuator/health` - État de santé de l'application
- `/actuator/metrics` - Métriques de l'application
- `/actuator/info` - Informations sur l'application

### Logs
Les logs sont configurés pour sortir en console avec le format par défaut de Spring Boot.

## Contributeurs

- [Votre nom] - Développeur principal

## Licence

Ce projet est sous licence [Type de licence].

## Contact

Pour toute question ou suggestion, veuillez contacter :
- Email : [votre-email@example.com]
- Projet : [lien-vers-le-projet]
