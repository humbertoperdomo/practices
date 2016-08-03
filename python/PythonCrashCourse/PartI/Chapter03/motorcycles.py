#!/usr/bin/python3

#motorcycles = ['honda', 'yamaha', 'suzuki']
#print(motorcycles)
#motorcycles[0] = 'dukati'

# Appending Elements to the End of a List
#motorcycles.append('dukati')

motorcycles =[]

motorcycles.append('honda') 
motorcycles.append('yamaha') 
motorcycles.append('suzuki') 

# Insertng Elements into a List
motorcycles.insert(0, 'ducati')

#print(motorcycles)

# removing an Item Using the del Statement
#del motorcycles[1]

#print(motorcycles)

# Removing an Item Using the pop() Method
#popped_motorcycle = motorcycles.pop()
print(motorcycles)
#print(popped_motorcycle)

#last_owned = motorcycles.pop()
#print("The last motorcycle I owned was a " + last_owned.title() + ".")

# Popping Items from any Position in a List
#first_owned = motorcycles.pop(0)
#print("The first motorcycle I owned was a " + first_owned.title() + ".")

# Removing an Item by Value
#motorcycles.remove('yamaha')
#print(motorcycles)

too_expensive = 'ducati'
motorcycles.remove(too_expensive)
print(motorcycles)
print("\nA " + too_expensive.title() + " is too expensive for me.")
