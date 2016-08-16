#!/usr/bin/python3

def commaCode(elements):
    cadena = ''
    for i in range(len(elements)):
        if (i + 1) == len(elements) and i == 0:
            cadena = elements[i]
        elif (i + 1) == len(elements):
            cadena += ' and ' + elements[i]
        else:
            cadena += elements[i] + ', '
    return cadena

#spam = ['apples', 'bananas', 'tofu', 'cats']
spam = ['bananas', 'tofu', 'cats']
#spam = ['tofu', 'cats']
#spam = ['cats']
print(commaCode(spam))
