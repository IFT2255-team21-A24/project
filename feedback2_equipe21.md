# Feedback

## Révision

### Échéancier

Échéancier mis à jour

### diagrammes CU 

Il manque un nouvel acteur "API de la ville".

Il manque un nouveau CU "Consulter ou Chercher les entraves routières".

"Consulter ses notifications" est présent 2 fois.

### Diagrammes d'activités 

Diagrammes d'activités mis à jour, mais contient encore des erreurs.

### Analyse

La solution de stockage et d'intégration manquent de détail.

## Architecture

L'interaction "Ligne de commande" vers "Aplication MaVille" devrait être plus explicite (HTTP?)

Il manque une frontière qui délimite les composantes du système des autres composantes.

## Diagramme de classe

L'interface MaVille a un peu trop de responsabilité.

Il manques des classes Ex: les vues de l'application.

Il manque des paramètres et des signatures pour certaines méthodes. Ex : "modifierRequete()" dans la classe RequeteDeTravail

## Diagramme de séquence 

Certaines méthodes appelées n'existe pas dans le diagramme de classe.

Les flèches pleines doivent être des appels de méthodes.

Les objets n'existent pas dans le diagramme de classe.

### Consulter les entraves

2.1 comment le résident a entré les critères?

### Soumettre une requête de travail

Le flux est assez cohérent.

### Consulter la liste des requêtes de travail

Entre 4 et 4.1 il manques les étapes de l'affichage de la requête et de la soummision de la candidature.

## Discussion design

Discussion design absente

## Implémentation

Les 3 username/password ne marchent pas.

Le code est assez différent de la conception.

## Tests unitiaire

Tests exécutables et passent tous.

"testData()" est mal structuré.
