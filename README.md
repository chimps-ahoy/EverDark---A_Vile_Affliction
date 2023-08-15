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
                                              v0.1.3
```                                             
A game I'm making for fun meant to be overly complicated under the hood, but look archaic and simple on the surface. (something like Dwarf Fortress).
Secondary objective is to make an RPG where there is limitless roleplay. This will be achieved in many ways, such as Tone Arguments for dialogue.

This version has the groundwork of the game (since v0.1.0) to display what sort of game is planned, and engine features continue to be developed. This version is the beginning of work on the Item system, and includes a minor tweak to the stat system to facilitate this.

- InputHandler for handling user inputs to interact with the game

- Creation and spawning of Entities, NPCs within the game, along with some interaction with them (see: Dialogue and Speak command).

- Creation of Maps and handling Player movement through them, accounting for Entities, map features, or changes in elevation preventing the player movement. There is also a framework in place for 'events' that trigger upon
contact. Right now it is only used for MapLinks, which teleport the player to a new map.

- Dialogue routines to handle Player input and branching dialogue trees. (being reworked)

- Tone Arguments for dialogue options - appending certain letters to your dialogue selection will have certain effects depending on the dialogue and the argument. Ex: '1 l' would be option 1, and your character is lying when they say this option.

- Routines to handle playing a unique music track for each Map location.

- Character creation.

- A config file to specify screen size and file locations, as well as colour-mode vs. b&w.

- Proper save files to store the gamestate of the world.

Current implemented commands are:

- Move \<direction\>, moves the player character one tile in the direction specified. Command will be performed as many times are there are \<direction\> arguments following the command. i.e - move north west, move north north, etc.
- Speak \<direction\>, speaks to the entity in the adjacent tile in the direciton specified. If the entity has a larger dialogue tree then the player will enter Dialogue-Mode where they will be prompted for dialogue options (chosen with a number). User input that is not a number (or is a number not listed for an option) will prompt an error. 

- Look, prints a textual description of the current map whose level of detail depends on the player character's Perception stat.

- Survey, prints a topographical map of the area

- Stats, prints a little character sheet with all the player's stats, name, and icon.

- Save, saves the current GameState to a .ed file in the 'saves' directory specified in config.txt.

- Exit, exits the program (progress is not saved as of yet)

The main additions in this version are several NPC character with dialogue trees, as well as several maps to walk throughout. This is mainly to show off what the game is planned to be like, and is subject to change 
before the final verison.
