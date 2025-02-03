# kataBank

Ce projet implémente une API de gestion de compte bancaire en architecture hexagonale avec **Spring Boot 3, Java 17, Maven, H2 et Swagger**.

## 📌 Fonctionnalités
- 💰 Dépôt d'argent
- 🏧 Retrait d'argent
- 📊 Consultation du solde
- 🔄 Historique des transactions

## Architecture
Le projet suit une **architecture hexagonale**:
- **domain**: contient les logiques métier principale (`Account, Transaction`).
- **service**: contient les cas d'utilisation et l'orchestration des services (`AccountService`).
- **controller**: gère l'exposition des API (`AccountController`).

## Gestion des logs
- Les logs sont enregistré dans le dossier 'logs' à la racine

## 🚀 Installation et Exécution
- Installer Java 17
- Installer mvn et verifier qu'il tourne avec Java 17 (mvn -version)
```bash
mvn spring-boot:run
```

L'API est accessible sur :  
📌 **Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)  

## 📂 Endpoints
| Méthode | Endpoint | Description |
|---------|---------|-------------|
| `POST` | `/api/account/{id}/deposit/{amount}` | Déposer de l'argent |
| `POST` | `/api/account/{id}/withdraw/{amount}` | Retirer de l'argent |
| `GET`  | `/api/account/{id}/balance` | Consulter le solde |
| `GET`  | `/api/account/{id}/transactions` | Voir l'historique des transactions |

## 🛠️ Technologies utilisées
- **Spring Boot 3**
- **Java 17**
- **Lombok**
- **H2 Database (en mémoire)**
- **Swagger (Springdoc OpenAPI 2.0.2)**

## 🧪 Tests
Lancer les tests unitaires et d'intégration avec :
```bash
mvn test
```
