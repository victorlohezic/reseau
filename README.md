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
| make test  |   Compile les tests du client et les exécute |
| make clean  |   Supprime le dossier build |

Pour lancer le serveur. Il faut exécuter : 
```Bash
build/server 
```
et pour lancer le client 
```Bash 
java -cp build Client
```

## Ajout de tests en Java

Pour ajouter un test pour le client, il faut créer un fichier `TestNomClasseATester.java`. 
Ensuite, il faut écrire les méthodes de test, celles-ci doivent commencer par `testNomDeCeQueLOnTeste`. 
Puis dans le makefile, il faut rajoute à la variable `TEST`, le nom de la classe de test. 


