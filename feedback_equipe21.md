# Feedback DM1

> **Équipe 21**  
> Yasmine Ben Youssef (20237210)  
> Olivier Mahmoud (20190798)  
> Arpad Botond Rigo (20241538)  

## Cadre du projet

- Le cadre du projet est bien défini

### Introduction

Un peu bref, mais correct
- En quoi les travaux sont-ils souvent mal planifiés? 
- Exemple de perturbations importantes

### Échéancier

Bon échéancier, mais il calque trop l'exemple fourni

- Il doit refléter votre planification

### Hypothèses

1. Utilisateurs : L'application sera principalement utilisée par des résidents de Montréal.
   - Cette hypothèse est valide vu que l'application est conçue pour informer les résidents de Montréal sur les travaux publics et privés qui les affectent directement.
2. Notifications : Les utilisateurs veulent des notifications en temps réel sur les travaux.
   - Des notifications trop fréquentes pourraient nuire aux utilisateurs (résidents). Il serait préférable de les offrir un moyen de contrôler la fréquence et le type de notifications à recevoir, en fournissant une bonne configuration initiale (par défaut)
3. Mises à jour : Les informations sur les travaux seront mises à jour quotidiennement
   - Une mise à jour quotidienne n'est pas toujours pertinente pour un projet. Généralement la mise à jour (externe) signale un incrément significatif dans l'avancement des travaux. Le suivi quotidien des travaux n'est pas prévu par l'application MaVille

## Exigences

### Glossaire

Un bon glossaire dans l'ensemble, mais il manque certains éléments essentiels à répertorier dans le glossaire.

### Termes importants

- Application MaVille
- Code de la ville
- Résident
- Service Info Entraves et Travaux
- Type de travaux
- Type de problème
- Signaler un problème

### Persona (optionnel)

Bon persona illustrant bien de possibles types d'utilisateurs.

- Vous n'êtes pas limité à deux
- Le pourcentage indique la proportion d'utilisateurs représentée par ce profil
  - Actuellement vous couvrez 55% (30+25) des utilisateurs

### Cas d'utilisation

Très bon diagramme.

- Résident et Intervenant pourraient être généralisés
- Manque la ville comme acteur secondaire
- Ajouter le CU `Se connecter`
- Renommer les points d'extension. Actuellement ils sont tous désignés par ExtensionPoint
  - ex: Filtrer les travaux
- Un résident reçoit des notifications, mais le CU serait plutôt `Consulter ses notifications`
- `Planification aprticipative` est trop vague. Il serait préférable de faire le lien directement avec le CUs connecté (ex: Fournir des plages horaires)
- `Consulter la liste des requêtes de travail et soumettre sa candidature` regroupe 2 CUs: `Consulter la liste des requêtes de travail` et `Soumettre sa candidature` 
- `Soumettre une requête de travail et faire son suivi` regroupe 2 CUs: `Soumettre une requête de travail` et `Faire le suivi d'une requête` 
- Pertinence du CU inclus `Decrire le projet`. La requête de travail n'est pas un projet.

### Scénarios

- Bon usage des préconditions
- Le système n'est pas un acteur secondaire
- Les étapes doivent alterner entre le système et les acteurs. Éviter d'avoir plus de 2 étapes successives pour le même acteur
- Les scénarios sont un peu trop simples dans l'ensemble. Ça ne traduit pas avec sufffisememnt de détails l'interaction entre le système et l'acteur.
- Toute étape de vérification doit avoir un scénario alternatif
- Pour les cas avec la précondition `Le résident doit être connecté à l'application`, il ne faudrait pas avoir d'étape de connexion dans le scénario principal 
- CU (inclus) `Fournir information personnel` manquant 
- CU (inclus) `Décrire le projet` manquant 
- CU `Consulter la liste des requêtes de travail et soumettre sa candidature` incomplet
- CU `Consulter les travaux`
  - Manque de scénarios alternatif pour les points d'extension
  - Le système *affiche une carte des travaux*?? L'intégration d'une carte n'est pas prévu
- CU `Rechercher des travaux`
  - Manque de scénarios alternatifs pour les points d'extension
- CU `Consulter la liste des requêtes de travail et soumettre sa candidature`
  - Manque de scénarios alternatifs pour les points d'extension
- CU `Recevoir des notifications personnalisées`
  - La postcondition n'est pas pertinente. Le résident reçoit des notifications suite à un changement dans un projet ou une requête.
- CU `Planification participative`
  - Manque de scénarios alternatifs pour les points d'extension

## Diagramme d'activités

- Diagramme général: Il montre bien le flux général de l'application
- Consulter les travaux
  - Assurez-vous d'utiliser un neoud de fusion pour joindre les actions faisant suite à un nœud de décision
  - Ajoutez un descriptif à vos nœuds de décision
  - Le traitement des filtres nécessitent un peu plus de détails
  - L'action `Choisir des filtres` n'est pas nécessaire
  - `Cliquer sur le travail voulu` devrait être optionnel
  - Utilisez le nœud final plutôt que le nœud de fin de flot
- Recherche de travaux manque de détail
  - `Sélectionner un travail spécifique` n'a pas de suite. Il faudrait des actions similaires à la Consultation des travaux
  - Utilisez le nœud final plutôt que le nœud de fin de flot
- Signaler un problème manque de détail
  - On devrait remplir un formulaire
  - Il devrait y avoir une validation
  - Il pourrait y avoir un problème dans l'envoi
  - Utilisez le nœud final plutôt que le nœud de fin de flot
- Soumettre une requête de travail est incomplet
  - `Mettre les détails de la requête` ne devrait pas être la première action ici
  - `Suivi de la requête de travail` devrait être dans une branche de décision où l'on sélectionne un travail en particulier
  - Utilisez le nœud final plutôt que le nœud de fin de flot

## Analyse

Bonne analyse

### Risques

1. Risque n'est pas clairement énoncé. Si la technologie n'est pas adoptée par tous les résidents, ce n'est pas grave. L'adoption peut être progressive.
2. Ce n'est pas un risque, car les préférences des résidents ne sont pas un blocage à la planification des travaux.
3. OK.
4. Risque non justifié
5. Risque n'est pas clairement énoncé.

### Exigences non-fonctionnelles

Manque de justification

### Besoins matériels

Absent

### Stockage 

Développez un peu plus votre solution de stockage. 

- Quelles sont les considérations à prendre pour la solution proposée?
- Comparaison avec l'utilisation de fichiers pour faire ressortir les avantages de cette approche.

### Intégration

Très bien, mais vous devez également explorer l'intégration avec des systèmes existants comme le service Info entraves et travaux.

## Prototype

Très bien

- Il faut une adresse courriel pour se connecter.
- Pour certaines entrées, l'application plante.

## Rapport

- Lien vers répertoire Git manquant
- Les diagrammes ne s'affichent pas dans le rapport
- Instructions pour utiliser le prototype ne sont pas clairs
- L'échéancier est le même que l'exemple fourni
- Template n'a pas été nettoyé et pleinement personnalisé

## Git

README et Release présents.

3/3 membres ont fait au moins 1 commit.