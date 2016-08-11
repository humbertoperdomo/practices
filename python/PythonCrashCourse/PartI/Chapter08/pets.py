#!/usr/bin/python3

# Positional Arguments
#def describe_pet(animal_type, pet_name):
   #"""Display information about a pet.""" 
   #print("\nI have a " + animal_type + ".")
   #print("My " + animal_type + "'s name is " + pet_name.title() + ".")

# Default values
def describe_pet(pet_name, animal_type='dog'):
   """Display information about a pet.""" 
   print("\nI have a " + animal_type + ".")
   print("My " + animal_type + "'s name is " + pet_name.title() + ".")

# Multiple function calls
#describe_pet('hamster', 'harry')
#describe_pet('dog', 'willie')

# Order matters in positional arguments
#describe_pet('qwerty', 'betta fish')

# Keyword arguments
describe_pet(animal_type='cat', pet_name='garfield')
describe_pet(pet_name='odie', animal_type='dog')

# Default values
describe_pet(pet_name='gaia')
describe_pet('cleo')
describe_pet('samantha', 'dog')

# Avoiding argument errors
#describe_pet()
