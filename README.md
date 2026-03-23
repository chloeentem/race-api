# TP — Création d'une API REST : Gestion d'inscriptions à une course

# Lancer le projet

Pour démarrer le projet, nous n'avons pas modifier la manière de faire du sujet

## 1 — Démarrer la base de données

Pour lancer votre base de données SQL et Adminer :

```bash
docker compose up -d
```

(vous devez avoir lancé Docker Desktop au préalable si vous êtes sur Windows)

---

## 2 — Accéder à Adminer

Adminer permet de visualiser la base de données.

URL :

```
http://localhost:8081
```

Paramètres de connexion :

| Champ    | Valeur        |
| -------- |---------------|
| System   | PostgreSQL    |
| Server   | race_postgres |
| Username | race          |
| Password | race          |
| Database | race_db       |

---

## 3 — Lancer l'application

Lancer votre configuration directement sur IntelliJ.

Sinon, depuis votre IDE ou en ligne de commande :

```bash
mvn spring-boot:run
```

L'API sera disponible sur :

```
http://localhost:8080
```

---

# endpoint implémentés

Tous nos endpoints ont été testé avec Postman, nous avons réaliser les endpoints demandé mais pas les bonus

## Gestion des coureurs

### Lister les coureurs

```
GET /runners
```

---

### Récupérer un coureur

```
GET /runners/{id}
```
---

### Supprimer un coureur

```
DELETE /runners/{id}
```

---

### Créer un coureur

```
POST /runners
```
---

### Modifier un coureur

```
PUT /runners/{id}
```
---

# Gestion des courses

### Lister les courses

```
GET /races
```

---

### Récupérer une course

```
GET /races/{id}
```

---

### Créer une course

```
POST /races
```
---

### Compter le nombre de participants d'une course

GET /races/{raceId}/participants/count

---

# Gestion des inscriptions

### Inscrire un coureur à une course

```
POST /races/{raceId}/registrations
```
---

### Lister les participants d'une course

```
GET /races/{raceId}/registrations
```

---

### Lister les courses d'un coureur

```
GET /runners/{runnerId}/races
```
