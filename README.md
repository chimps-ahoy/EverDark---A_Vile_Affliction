```
   ▄████████  ▄█    █▄     ▄████████    ▄████████ ████████▄     ▄████████    ▄████████    ▄█   ▄█▄ 
  ███    ███ ███    ███   ███    ███   ███    ███ ███   ▀███   ███    ███   ███    ███   ███ ▄███▀ 
  ███    █▀  ███    ███   ███    █▀    ███    ███ ███    ███   ███    ███   ███    ███   ███▐██▀   
 ▄███▄▄▄     ███    ███  ▄███▄▄▄      ▄███▄▄▄▄██▀ ███    ███   ███    ███  ▄███▄▄▄▄██▀  ▄█████▀    
▀▀███▀▀▀     ███    ███ ▀▀███▀▀▀     ▀▀███▀▀▀▀▀   ███    ███ ▀███████████ ▀▀███▀▀▀▀▀   ▀▀█████▄    
  ███    █▄  ███    ███   ███    █▄  ▀███████████ ███    ███   ███    ███ ▀███████████   ███▐██▄   
  ███    ███ ███    ███   ███    ███   ███    ███ ███   ▄███   ███    ███   ███    ███   ███ ▀███▄ 
  ██████████  ▀██████▀    ██████████   ███    ███ ████████▀    ███    █▀    ███    ███   ███   ▀█▀ 
                                       ███    ███                           ███    ███   ▀        
                                       ---A Vile Affliction---
                                              v0.0.5.1
```                                             
A game I'm making for fun meant to be overly complicated under the hood, but look archaic and simple on the surface. (something like Dwarf Fortress).
Secondary objective is to make an RPG where there is limitless roleplay. This will be achieved in many ways; however, in this version it is not implemented and is simply planned.

This version contains the groundwork functionality in the 'engine' that will be vital for future versions to build off of. The InputHandler contains logic to recognize and act on defined commands. Current implemented commands are:

- Move \<direction\>, moves the player character one tile in the direction specified. Command will be performed as many times are there are \<direction\> arguments following the command. i.e - move north west, move north north, etc.

- Look, prints a textual description of the current map whose level of detail depends on the player character's Perception stat.

- Survey, prints a topographical map of the area

- Exit, exits the program (progress is not saved as of yet)

The main additions in this version are audio playback, along with a proper main menu to choose if you'd like to create a new character or load from file.
