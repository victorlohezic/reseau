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
| make clean  |   Supprime le dossier build |

Actuellement, pour lancer le serveur. Il faut exécuter : 
```Bash
build/server n°_port
```
et pour lancer le client 
```Bash 
java build/Client @IP_serveur n°_port
```