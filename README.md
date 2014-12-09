#Carburez!
![Build](https://magnum.travis-ci.com/galexandre/mmm-Carburez.svg?token=dbf4gWvbxbZcGxXbLqj3)

I - Ajouter le contenu de lib/ dans le build path du projet. Clic droit sur le jar en question > Build Path > Add to Build Path

II - Suivez ces étapes pour accéder à l'API de Google, donc Google Map.

1) Importer le projet "google-play-services_lib" se trouvant dans le sdk android. 
<chemin jusqu'à votre SDK>/extras/google/google_play_services/libproject/google-play-services_lib/
File > Import > Existing Android Project

2) Sur le projet "carburez", clic droit > properties > android > sélectionnez "Google Apis 4.1.2" avec l'API level 16

3) Sur la même fenêtre tout en bas, importez le projet du 1)

source: http://developer.android.com/google/play-services/setup.html

4) Vu que Google Services est très gourmand, il se peut que la configuration d'Eclipse par défaut ne tienne pas le coup.
Ouvrez le fichier eclipse.ini qui se trouve à la racine du dossier d'installation.

**************************************************************************************************************
Edit eclipse.ini file

update the default values

-Xms40m

-Xmx512m

with new values

-Xms512m

-Xmx1024m

Mac users : try this http://blog.mynumnum.com/2011/08/how-to-modify-eclipseini-settings-in.html

try this https://docs.oseems.com/general/application/eclipse/fix-gc-overhead-limit-exceeded
**************************************************************************************************************

source : http://stackoverflow.com/questions/25742546/eclipse-crashes-on-importing-googleplay-service-library-to-the-project
