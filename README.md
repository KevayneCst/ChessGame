Jeu d'échec crée par le groupe EJiK & Mat

**Comment lancer/démarrer le jeu ?**

>  Prérequis: Avoir mis dans les variables d'environnement PATH l'accès vers le jdk (pour les commandes javac et java dans l'invite de commande)
/!\ Si votre version du jdk n'est pas la 8ème (class file version 52.0), il se produira une erreur de version lors de l'exécution de la commande "java echec/EchecMain". 
Faites attention à choisir la bonne version du jdk pour l'ajouter dans PATH /!\

Une fois avoir téléchargé le code source (en .zip) et l'avoir dézippé dans votre Bureau, se rendre dans l'invite de commandes, placez vous dans le dossier téléchargé (normalement nommé "ejiketmat-master" par défaut) via la commande "cd".

Une fois dans "ejiketmat-master\WS_Echec\src>" saisissez les commandes suivantes: (en italique)

*mkdir ..\bin*

Puis

*javac -d ../bin echec/Case.java echec/Cavalier.java echec/Couleur.java echec/EchecMain.java echec/Fou.java echec/Partie.java echec/Piece.java echec/Pion.java echec/Plateau.java echec/Portee.java echec/Reine.java echec/Roi.java echec/Tour.java*

Ensuite, saissiez la commande suivante: (en italique toujours)
*cd ../bin*

Et enfin
*java echec/EchecMain*

Et voilà bon amusement ! :D


**Règles à respecter lorsque vous rejouerez**

Le jeu respecte toutes les règles du jeu d'échec de base (déplacements, échec, prise en passant, roque, échat et mat/pat ainsi que la promotion).

Lorsque vous lancerez le jeu, vous serez directement invité(e) à saisir les coordonnées de la pièces de départ, et par la suite (si votre saisie est valide) la destination.
Les messages d'erreurs en cas de non respect de la saisie sont assez explicites, mais voici quelques règles à respecter:
* Votre saisie devra contenir 2 caractères (ni plus ni moins)
* Le premier caractère sera une lettre (minuscule ou majuscule le système s'adapte) comprise entre 'a' et 'h' qui correspondra à la colonne de la pièce choisie.
* Le second caractère (collé au premier) sera un chiffre compris entre 1 et 8 qui correspondra à la ligne de la pièce choisie.

Si vous ne respectez pas ces indications, le système vous invitera à saisir de nouveau. Voici quelque cas où vous serez invité(e) à saisir de nouveau:
* Votre saisie est invalide (départ ou destination)
* Vous avez saisi une case vide dans la case de départ
* Vous avez saisi une case de destination que la pièce de départ ne peut atteindre
* Vous essayez de déplacer une pièce de votre adversaire
* Votre déplacement met en échec votre propre roi
* Votre déplacement ne permet pas à votre roi de sortir de la situation d'échec (si il est en échec)
* Lorsque vous essayez de faire un roque (case de départ: votre roi et case de destination: une de vos tours) OU (case de départ: une de vos tours et case de destination: votre roi) et que le roi est en échec lorsqu'il se "déplace" pour le faire
* Si vous essayez de faire un roque alors que les cases entre la tour sélectionnée et le roi ne sont pas vide


Si par erreur, vous avez saisi en case de départ une de vos pièces que vous ne souhaitiez pas bouger, il suffit de saisir un déplacement impossible (mais la saisie doit être conforme aux 3 règles de saisie). 

 
**Précisions pour le jeu**

Sur l'échiquier, les pièces sont symbolisées par 2 choses: 
*  Le type de pièce (Pion P, Tour T, Cavalier C, Fou F, Reine(on a choisit l'initiale anglaise) Q, Roi(même chose que pour la reine) K)
*  La couleur de la pièce (Blanche b, Noir n)

Par exemple, un Pion noir sera affiché "Pn" sur l'échiquier, une Reine blanche sera affichée "Qb" etc. 
 
Les cases vides sont représentées par deux "." donc => ".." 
Les numéros de colonnes sont situés sous la dernière ligne de l'échiquier, et les numéros de lignes tout à gauche de l'échiquier.

Enfin dernier point peut-être un peu ambigu, pour effectuer le roque (grand ou petit[](https://fr.wikipedia.org/wiki/Roque_(%C3%A9checs))) il y a 2 manières de faire:
*  Vous saisissez en case de départ votre Roi, et en case d'arrivée une de vos tours (selon la direction que vous souhaitez entre le petit et le grand roque)
*  Cas inverse, vous saisissez une de vos tours, et en case d'arrivée votre Roi


**Créateurs**

*  Marine Demonchaux
*  Kévin Constantin
*  Eloïse Delhomelle
*  Julie Lemenan
