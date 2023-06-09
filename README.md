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
                                              v0.0.9
```                                             
A game I'm making for fun meant to be overly complicated under the hood, but look archaic and simple on the surface. (something like Dwarf Fortress).
Secondary objective is to make an RPG where there is limitless roleplay. This will be achieved in many ways, such as Tone Arguments for dialogue.

This version contains the MAJORITY of engine features that will be needed in the future, such as:

- InputHandler for handling user inputs to interact with the game

- Creation and spawning of Entities, NPCs within the game, along with some interaction with them (see: Dialogue and Speak command).

- Creation of Maps and handling Player movement through them, accounting for Entities, map features, or changes in elevation preventing the player movement.

- Dialogue routines to handle Player input and branching dialogue trees.

- Tone Arguments for dialogue options - appending certain letters to your dialogue selection will have certain effects depending on the dialogue and the argument. Ex: '1 l' would be option 1, and your character is lying when they say this option.

- MapLinks/Portals, Map elements that will take the player from one Map to another.

- Routines to handle playing a unique music track for each Map location.

- Character creation (rudementary and will be reworked in the future).

- A config file to specify screen size and file locations.

- Proper save files for players.

Current implemented commands are:

- Move \<direction\>, moves the player character one tile in the direction specified. Command will be performed as many times are there are \<direction\> arguments following the command. i.e - move north west, move north north, etc.
- Speak \<direction\>, speaks to the entity in the adjacent tile in the direciton specified. If the entity has a larger dialogue tree then the player will enter Dialogue-Mode where they will be prompted for dialogue options (chosen with a number). User input that is not a number (or is a number not listed for an option) will prompt an error. 

- Look, prints a textual description of the current map whose level of detail depends on the player character's Perception stat.

- Survey, prints a topographical map of the area

- Stats, prints a little character sheet with all the player's stats, name, and icon.

- Save, saves the current GameState to a .ed file in the 'saves' directory specified in config.txt.

- Exit, exits the program (progress is not saved as of yet)

The main additions in this is Tone Arguments and the config file.
