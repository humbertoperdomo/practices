#!/usr/bin/python3
"""Class Dice."""
from random import randint

class Dice():
    """Model a Dice."""

    def __init__(self, sides=6):
        """Initialize Dice attributes."""
        self.sides = sides
        self.last_roll_die = None

    def roll_die(self):
        """Roll die."""
        self.last_roll_die = randint(1, self.sides)
        print(self.last_roll_die)


print("\nDice 6-sides:")
dice = Dice()
for i in range(10):
    dice.roll_die()

print("\nDice 10-sides:")
dice_10 = Dice(10)
for i in range(10):
    dice_10.roll_die()

print("\nDice 20-sides:")
dice_20 = Dice(20)
for i in range(10):
    dice_20.roll_die()
