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
                                              v0.0.5
```                                             
A game I'm making for fun meant to be overly complicated under the hood, but look archaic and simple on the surface. (something like Dwarf Fortress).
Secondary objective is to make an RPG where there is limitless roleplay. This will be achieved in many ways; however, in this version it is not implemented and is simply planned.

This version contains the groundwork functionality in the 'engine' that will be vital for future versions to build off of. The InputHandler contains logic to recognize and act on defined commands, however in this version the only commands are 'move' (followed by a direction, North, South, East, and West), and 'exit' to terminate the program. (exit simply closes the InputHandler, but in this version that will end the entire program).

The main additions in this version are audio playback, along with a proper main menu to choose if you'd like to create a new character or load from file.
