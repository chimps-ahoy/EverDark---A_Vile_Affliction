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
                                              v0.0.7
```                                             
A game I'm making for fun meant to be overly complicated under the hood, but look archaic and simple on the surface. (something like Dwarf Fortress).
Secondary objective is to make an RPG where there is limitless roleplay. This will be achieved in many ways; however, in this version it is not implemented and is simply planned.

This version contains the groundwork functionality in the 'engine' that will be vital for future versions to build off of. The InputHandler contains logic to recognize and act on defined commands. The important 'engine' features are:

- Creation and spawning of Entities, NPCs within the game, along with some interaction with them (see: Dialogue and Speak command).

- Creation of Maps and handling Player movement through them.

- Dialogue routines to handle Player input and branching dialogue trees.

- MapLinks/Portals, Map elements that will take the player from one Map to another.

- Routines to handle playing a unique music track for each Map location.

- Loading characters from files

- Character creation (rudementary and will be reworked in the future).

Current implemented commands are:

- Move \<direction\>, moves the player character one tile in the direction specified. Command will be performed as many times are there are \<direction\> arguments following the command. i.e - move north west, move north north, etc.
- Speak \<direction\>, speaks to the entity in the adjacent tile in the direciton specified. If the entity has a larger dialogue tree then the player will enter Dialogue-Mode where they will be prompted for dialogue options (chosen with a number). User input that is not a number (or is a number not listed for an option) will prompt an error. 

- Look, prints a textual description of the current map whose level of detail depends on the player character's Perception stat.

- Survey, prints a topographical map of the area

- Exit, exits the program (progress is not saved as of yet)

The main additions in this version are dialogue and MapLinks.
