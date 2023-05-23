# Projet de réseau
## Explications du projet
Celle-ci sont disponibles [ici](https://docs.google.com/document/d/1MzZG0qDfVr8U50v8X79yvaPfToj8Yz4DwiGg3swl1kk/edit).

## Exécution du projet 

Les fichiers view.cfg et controller.cfg permettent de configurer les vues et le controlleur du modèle. 

Les différentes commandes du projets sont résumées dans ce tableau : 
Les différents paramètres sont résumés dans ce tableau :
| Commande  |  Utilité |
| :--------------- | :-----|
| make  |     Compile le projet |
| make build  |     Compile le projet |
| make client  |     Compile le client |
| make server  |   Compile le serveur |
| make test  |   Compile les tests du client et les exécuter |
| make clean  |   Supprime le dossier build |

Pour lancer le serveur. Il faut exécuter : 
```Bash
build/server 
```
et pour lancer le client 
```Bash 
java -cp build Client
```

## Commandes disponibles pour le client

Pour obtenir des informations sur l'acquarium :
```
status
```


Cette commande permet à l'utilisateur d’ajouter un poisson à l’aquarium :
```
addFish chouchou at 61x52, 4x3, RandomWayPoint
```

Les poissons disponibles sont : 
- chouchou
- toutou
- chacha 
- sasa

Les mobilités disponibles sont : 
- HorizontalPathWay 
- RandomWayPoint

Pour supprimer un poisson : 
``` 
delFish Chouchou
```

Cette commande permet à l'utilisateur de démarrer un poisson : 
```
startFish PoissonNain
```

## Commandes disponibles pour le serveur
Chargement de l’aquarium
```
load aquarium1
```
Affichage de la topologie
```
show aquarium
```
Ajouter une vue
```
add view N5 400x400+400+200
```
Supprimer une vue
```
del view N
```
Enregistrer / exporter les données de l’aquarium actuelle dans un fichier
```
save aquarium2
```

## Ajout de tests en Java

Pour ajouter un test pour le client, il faut créer un fichier `TestNomClasseATester.java`. 
Ensuite, il faut écrire les méthodes de test, celles-ci doivent commencer par `testNomDeCeQueLOnTeste`. 
Puis dans le makefile, il faut rajoute à la variable `TEST`, le nom de la classe de test. 


