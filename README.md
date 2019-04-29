# Battleship

## Written by Kanan JAFARLI and Nezir AHMEDLI

## Game rules

It is a simple 2-player game on console in Java. One user can play with another user on the same computer or with Bot. But it is easy bot. Bot each time give random positions.

First, user set its ships, then choose its opponent that with whom want to play (user or bot). Each of them have 5 ships.

* length of one ship is 2.
* length of two ships is 3.
* length of one ship is 4.
* length of one ship is 5.

They give positions one by one to shoot ships. Whose all ships are destroyed, it loses.

## How to lunch game

After downloading or cloning the game in your computer, then

* open in ecliplse (or other Java softwares) and run *Main.java*.
* open in the terminal from the folder containing the game files and 
    * To compile files *javac* _*.java_
    * To run the generated class file *java* _Main_ 

### Error Cases

1. User can not give wrong size or one size multiple times (**exception:** you can give 3 as size two times because there are 2 ships in three sizes).
1. User can not give beginnig position of ship less than 0 and greater than 9 *(because row and column of my field is from 0 to 10 (10 is not included), you can change row and column)*.
1. User can not give orientation of ships different from *V,v* (vertical) and *H,h* (horizontal).   
1. User can not give position to fire less than 0 and greater than 9 *(as in the second case)*. 
1. User can not shoot one place more than one.
1. Ships can not be intersect and can not be side by side.
1. Ships can not go out of the field when user give orientation.



