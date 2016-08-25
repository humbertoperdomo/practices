#!/usr/bin/python3
# table_printer.py
"""Displays a table in a well-organixed table.
"""

TABLE_DATA = [['apples', 'oranges', 'cherries', 'banana'],
              ['Alice', 'Bob', 'Carol', 'David'],
              ['dogs', 'cats', 'moose', 'goose']]

def get_longest_item(items):
    """Gets the size of the longest item in the list
    """
    longest = 0
    for item in items:
        if len(item) > longest:
            longest = len(item)
    return longest

def print_table(table):
    """Prints a table
    """
    col_width = [0] * len(table)
    for i in range(len(col_width)):
        col_width[i] = get_longest_item(table[i])

    for row in range(len(table[0])):
        row_text = ''
        for column in range(len(table)):
            row_text += table[column][row].rjust(col_width[column])
            if (column + 1) < len(table):
                row_text += ' '
        print(row_text)

print_table(TABLE_DATA)
