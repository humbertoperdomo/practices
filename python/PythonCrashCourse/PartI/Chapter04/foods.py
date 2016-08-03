#!/usr/bin/python3

my_foods = ['pizza', 'falafel', 'carrot cake']
friend_foods = my_foods[:]

# This doesn't work: 
# This tells Python to conect the new variable friend_foods to the list
# that is already contained in my_foods, so now both variables point to 
# the same list.
#friend_foods = my_foods

my_foods.append('cannoli')
friend_foods.append('ice cream')

print("My favorite foods are: ")
print(my_foods)

print("\nMy friend's favorite foods are: ")
print(friend_foods)
