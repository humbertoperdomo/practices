#!/usr/bin/python3

child = 'elliot'
print(child)
print("Is child == 'elliot'? I predict True.")
print(child == 'elliot')

print("\nIs child == 'evangeline'? I predict False.")
print(child == 'evangeline')

print("\nIs child.title() == 'Elliot'? I predict True.")
print(child.title() == 'Elliot')


childs = ['Evangeline', 'Nicole', 'Elliot']
print("\n")
print(childs)
print("Is 'Nicole' in childs? I predict True.")
print('Nicole' in childs)

print("Is not 'Elliot' in child? I predict False.")
print('Elliot' not in childs)

print("Is 'Nicole' and 'Evangeline' in childs? I predict True.")
print('Nicole' in childs and 'Evangeline' in childs)

print("Is 'Elliot' or 'Isaac' in childs? I predict True.")
print('Elliot' in childs or 'Isaac' in childs)

print("Is 9 == 9? I predict True.")
print(9 == 9)
