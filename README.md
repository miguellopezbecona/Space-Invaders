## Overview
This repo consists in a simplified version of the classic Space Invaders game, written in Java. It was originally made for one subject from my BSc in Computer Science and was more "primitive". You can move the hero ship using the left and right keyboard arrows and shoot one missile at once pressing the space key.

It uses Swing library for the graphical part, so it does not rely on dependencies. Running if from an IDE is quite easy. If you want to do it from a shell, type the following:
```bash
wget https://github.com/miguellopezbecona/Space-Invaders/archive/master.zip
unzip master.zip
cd Space-Invaders-master
javac si/*.java
jar -cfe Space-Invaders.jar si.SpaceInvaders img/* si/*.class
java -jar Space-Invaders.jar
```

## License
You are completely free to adapt part of the source code to your project.


