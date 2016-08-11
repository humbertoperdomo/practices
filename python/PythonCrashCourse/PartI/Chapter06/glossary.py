#!/usr/bin/python3

glossary = {
        'comment': "It is a line in the code that begins with a pound sign.",
        'upper': "It is a method that output a string in upper case.",
        'list': "It is a data structure that can hold information using indexes.",
        'tuple': "It is a data structure similar to list but tuple is unmodified.",
        'if': "It is a control flow statement that allows to execute an action if a condition is met.",
        'set': "It is a data structure similar to list but it does not allow duplicated values",
        'for': "It is a control structure that helps to iterate over elements in a list",
        'string': "It is a sequence of characters that can be handle as an object.",
        'variable': "It is a element used to save a reference to an object",
        'print': "It is a function that helps to output information through a output device", 
        }

for term, meaning in glossary.items():
    print(term.title() + ":\n\t" + meaning)
